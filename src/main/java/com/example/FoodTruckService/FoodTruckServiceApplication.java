package com.example.FoodTruckService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FoodTruckServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodTruckServiceApplication.class, args);
	}

}
