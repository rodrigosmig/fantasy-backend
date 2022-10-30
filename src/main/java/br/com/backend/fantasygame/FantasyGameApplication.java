package br.com.backend.fantasygame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class FantasyGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyGameApplication.class, args);
	}
}
