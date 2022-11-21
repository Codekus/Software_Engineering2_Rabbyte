package de.hbrs.se.rabbyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class RabbyteApplication  {


	public static void main(String[] args) {
		SpringApplication.run(RabbyteApplication.class, args);
	}

}
