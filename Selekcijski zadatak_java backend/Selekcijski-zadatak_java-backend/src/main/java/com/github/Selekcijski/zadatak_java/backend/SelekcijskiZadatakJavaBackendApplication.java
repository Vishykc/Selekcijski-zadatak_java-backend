package com.github.Selekcijski.zadatak_java.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class SelekcijskiZadatakJavaBackendApplication {

	//napravi potrebne foldere za spremanje h2 baze i CSV fajlova na lokalni C disk
	@PostConstruct
	private void postConstruct() throws IOException {
		Files.createDirectories(Paths.get("C:/Selekcijski zadatak_java backend_pomocni_resursi/h2db"));
		Files.createDirectories(Paths.get("C:/Selekcijski zadatak_java backend_pomocni_resursi/csv_fajlovi"));

	}

	public static void main(String[] args) {
		SpringApplication.run(SelekcijskiZadatakJavaBackendApplication.class, args);
	}
}
