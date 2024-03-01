package com.groundbooking.groundbookingmonolythicapp;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableEncryptableProperties
@EnableScheduling
public class GroundbookingmonolythicappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroundbookingmonolythicappApplication.class, args);
	}

}

