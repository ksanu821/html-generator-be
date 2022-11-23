package com.acko.htlmgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acko.htlmgenerator"})
@EntityScan(basePackages = {"com.acko.htlmgenerator"})
@EnableJpaRepositories(
		basePackages = {
				"com.acko.htlmgenerator.repositories"
		})
public class HtlmgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtlmgeneratorApplication.class, args);
	}

}
