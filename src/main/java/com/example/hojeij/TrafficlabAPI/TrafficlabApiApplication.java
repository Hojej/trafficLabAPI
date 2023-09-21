package com.example.hojeij.TrafficlabAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TrafficlabApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficlabApiApplication.class, args);
	}

	@Bean
	public static RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
