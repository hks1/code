package com.hks.bcryptservicedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BcryptServiceDemoApplication {

	//UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BcryptServiceDemoApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner printAll(UserRepository userRepository){
		return args -> userRepository.findAll().forEach(System.out::println);
	}*/

}
