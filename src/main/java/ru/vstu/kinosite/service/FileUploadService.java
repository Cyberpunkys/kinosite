package ru.vstu.kinosite.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.kinosite.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileUploadService {


    public void uploadFile(Movie movie, MultipartFile file, String uploadPath) throws IOException {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.isBlank()) {
                Path uploadDir = Path.of(uploadPath);

                if (!Files.exists(uploadDir)) {
                    Files.createDirectory(uploadDir);
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(Path.of(uploadPath + "/" + resultFilename));
                movie.setPoster(resultFilename);
            }
        }
    }
}
