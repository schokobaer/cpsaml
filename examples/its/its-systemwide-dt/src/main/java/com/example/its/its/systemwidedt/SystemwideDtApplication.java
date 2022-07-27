package com.example.its.its.systemwidedt;
	
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SystemwideDtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemwideDtApplication.class, args);
	}

}