package com.redisexampleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisExampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisExampleAppApplication.class, args);
	}

}
