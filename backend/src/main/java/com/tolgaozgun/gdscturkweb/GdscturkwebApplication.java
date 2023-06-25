package com.tolgaozgun.gdscturkweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class GdscturkwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdscturkwebApplication.class, args);
	}

}
