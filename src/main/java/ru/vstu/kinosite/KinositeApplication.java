package ru.vstu.kinosite;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class KinositeApplication implements CommandLineRunner {

	@Value("${upload.path}")
	private String uploadPath;

	public static void main(String[] args) {
		SpringApplication.run(KinositeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!Files.exists(Path.of(uploadPath))) {
			String sourceDirectoryLocation = "src/main/resources/kinositeUploads";
			String destinationDirectoryLocation = uploadPath;

			Files.walk(Path.of(sourceDirectoryLocation))
					.forEach(source -> {
						Path destination = Paths.get(destinationDirectoryLocation, source.toString()
								.substring(sourceDirectoryLocation.length()));
						try {
							Files.copy(source, destination);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
		}
	}
}
