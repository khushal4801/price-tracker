package com.github.khushal4801.price_drop_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PriceDropTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceDropTrackerApplication.class, args);
	}

}
