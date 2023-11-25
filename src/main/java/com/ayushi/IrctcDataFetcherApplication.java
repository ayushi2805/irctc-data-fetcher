package com.ayushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IrctcDataFetcherApplication {
	public static String appRoot = "https://api.railwayapi.site/api/";

	public static void main(String[] args) {
		SpringApplication.run(IrctcDataFetcherApplication.class, args);
	}

}
