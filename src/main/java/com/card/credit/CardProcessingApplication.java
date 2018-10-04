package com.card.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.card"})
@EnableJpaRepositories("com.card.credit.repositories")
public class CardProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardProcessingApplication.class, args);
	}
}
