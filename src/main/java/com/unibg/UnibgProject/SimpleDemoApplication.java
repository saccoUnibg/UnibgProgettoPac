package com.unibg.UnibgProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SimpleDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleDemoApplication.class, args);
	}

}
