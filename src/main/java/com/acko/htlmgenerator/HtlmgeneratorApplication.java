package com.acko.htlmgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HtlmgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtlmgeneratorApplication.class, args);
	}

}
