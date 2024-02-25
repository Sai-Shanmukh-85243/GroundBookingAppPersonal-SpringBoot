package com.groundbooking.groundbookingmonolythicapp;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableEncryptableProperties
public class GroundbookingmonolythicappApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroundbookingmonolythicappApplication.class, args);
	}

}

