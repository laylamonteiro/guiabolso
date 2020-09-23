package com.gb.guiabolso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class GuiabolsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuiabolsoApplication.class, args);
	}

}
