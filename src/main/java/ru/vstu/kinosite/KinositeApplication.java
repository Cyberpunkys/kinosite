package ru.vstu.kinosite;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
@Slf4j
public class KinositeApplication implements CommandLineRunner {

	@Value("${upload.path}")
	private String uploadPath;

	public static void main(String[] args) {
		SpringApplication.run(KinositeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Path toHome = Path.of(uploadPath);
		if (!Files.exists(toHome)) {
			Files.createDirectory(toHome);
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/test.jpg").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/test.jpg"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/dr.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/dr.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/velvet.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/velvet.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/silicon.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/silicon.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/stalin.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/stalin.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/taxi.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/taxi.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/orange.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/orange.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/thing.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/thing.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
			try (InputStream inputStream = new ClassPathResource("kinositeUploads/psycho.webp").getInputStream()) {
				Files.copy(inputStream, Path.of(uploadPath + "/psycho.webp"), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
}
