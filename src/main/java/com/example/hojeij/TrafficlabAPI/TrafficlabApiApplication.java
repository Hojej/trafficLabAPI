package com.example.hojeij.TrafficlabAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TrafficlabApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficlabApiApplication.class, args);
	}

	@Bean
	private static WebClient.Builder getWebClientBuilder(){
		final int size = 16 * 1024 * 1024;
		final ExchangeStrategies strategies = ExchangeStrategies.builder()
				.codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
				.build();
		return WebClient.builder()
				.exchangeStrategies(strategies);
	}
}
