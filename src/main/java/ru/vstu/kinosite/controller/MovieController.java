package ru.vstu.kinosite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.kinosite.model.Movie;
import ru.vstu.kinosite.service.FileUploadService;
import ru.vstu.kinosite.service.MovieService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    private final FileUploadService fileUploadService;

    @Value("${upload.path}")
    private String uploadPath;

    public MovieController(MovieService movieService, FileUploadService fileUploadService) {
        this.movieService = movieService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public String retrieve(
            Model model,
            @RequestParam(required = false, defaultValue = "") String filterName,
            @RequestParam(required = false, defaultValue = "") Integer filterYear
    ) {
        List<Movie> moviesList = movieService.getMovieList(filterName, filterYear);

        model.addAttribute("movies", moviesList);
        model.addAttribute("filterName", filterName);
        model.addAttribute("filterYear", filterYear);

        return "movies";
    }

    @PostMapping
    public String create(
            @RequestParam String name,
            @RequestParam int year,
            @RequestParam String description,
            @RequestParam("poster") MultipartFile file,
            Model model
    ) throws IOException {
        Movie movieToCreate = new Movie();
        movieToCreate.setName(name);
        movieToCreate.setYear(year);
        movieToCreate.setDescription(description);
        fileUploadService.uploadFile(movieToCreate, file, uploadPath);
        movieService.create(movieToCreate);

        List<Movie> movieList = movieService.getMovieList();
        model.addAttribute("movies", movieList);

        return "movies";
    }

    @GetMapping("{id}")
    public String editPage(Model model, @PathVariable Long id) {
        Movie movie = movieService.getOne(id);
        model.addAttribute("movie", movie);

        return "edit";
    }

    @PostMapping("{id}")
    public String update(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam int year,
            @RequestParam String description,
            @RequestParam("poster") MultipartFile file,
            Model model
    ) throws IOException {
        Movie movieToUpdate = movieService.getOne(id);
        movieService.update(movieToUpdate, name, year, description, file);

        model.addAttribute("movie", movieToUpdate);

        return "edit";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        Movie movieToDelete = movieService.getOne(id);
        movieService.delete(movieToDelete);

        return "redirect:/movies";
    }
}
