package com.ayushi.traininfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainInfoApplication {
	public static String appRoot = "https://api.railwayapi.site/api/";

	public static void main(String[] args) {

		SpringApplication.run(TrainInfoApplication.class, args);
	}

}
