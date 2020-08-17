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
public class MpmMSProjectApplication { // NOPMD by skull on 8/8/20, 10:02 AM

	/**
	 * Default main constructor.
	 * 
	 * @param args arg from command line
	 */
	public static void main(final String[] args) {

		SpringApplication.run(MpmMSProjectApplication.class, args);
	}
}
