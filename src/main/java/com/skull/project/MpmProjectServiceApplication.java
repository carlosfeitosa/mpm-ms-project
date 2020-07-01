package com.skull.project;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skull.project.controller.ProjectController;
import com.skull.project.controller.impl.ProjectControllerImpl;

/**
 * Microservice main class.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-28
 *
 */
@SpringBootApplication
public class MpmProjectServiceApplication {

	@Autowired
	private ProjectController controller;

	public static void main(String[] args) {

		SpringApplication.run(MpmProjectServiceApplication.class, args);
	}

	@PostConstruct
	private void init() {

		((ProjectControllerImpl) controller).initMockDb();
	}
}
