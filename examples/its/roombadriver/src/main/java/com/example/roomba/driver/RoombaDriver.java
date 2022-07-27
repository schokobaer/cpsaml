package com.example.roomba.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoombaDriver {

	public static void main(String[] args) {
		SpringApplication.run(RoombaDriver.class, args);
	}



}
