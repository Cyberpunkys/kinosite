package ru.vstu.kinosite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.kinosite.model.Movie;
import ru.vstu.kinosite.repository.MovieRepo;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MovieService {

    private final MovieRepo movieRepo;

    private final FileUploadService fileUploadService;

    @Value("${upload.path}")
    private String uploadPath;

    public MovieService(MovieRepo movieRepo, FileUploadService fileUploadService) {
        this.movieRepo = movieRepo;
        this.fileUploadService = fileUploadService;
    }

    public List<Movie> getMovieList(String filterName, Integer filterYear) {
        boolean isFilterName = filterName != null && !filterName.isBlank();
        boolean isFilterYear = filterYear != null;
        if (isFilterName && isFilterYear) {
            return movieRepo.findByNameAndYear(filterName, filterYear);
        } else if (isFilterName) {
            return movieRepo.findByName(filterName);
        } else if (isFilterYear) {
            return movieRepo.findByYear(filterYear);
        }
        return getMovieList();
    }

    public List<Movie> getMovieList() {
        return movieRepo.findAll();
    }

    public void create(Movie movie) {
        movieRepo.save(movie);
    }

    public Movie getOne(Long id) {
        return movieRepo.findById(id);
    }

    public void update(Movie movie, String name, int year, String description, MultipartFile file) throws IOException {
        movie.setName(name);
        movie.setYear(year);
        movie.setDescription(description);
        fileUploadService.uploadFile(movie, file, uploadPath);

        movieRepo.update(movie);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }
}
