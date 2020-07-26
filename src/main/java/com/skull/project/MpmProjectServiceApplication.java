package com.skull.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Microservice main class.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-28
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MpmProjectServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MpmProjectServiceApplication.class, args);
	}
}
