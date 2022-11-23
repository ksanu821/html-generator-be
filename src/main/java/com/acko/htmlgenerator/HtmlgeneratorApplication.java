package com.acko.htmlgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acko.htmlgenerator"})
@EntityScan(basePackages = {"com.acko.htmlgenerator"})
@EnableJpaRepositories(
		basePackages = {
				"com.acko.htmlgenerator.repositories"
		})
public class HtmlgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmlgeneratorApplication.class, args);
	}

}
