package com.ben.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApisApplication.class, args);
	}

}
