package com.skull.project;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skull.project.model.Project;
import com.skull.project.model.repository.ProjectRepository;

@SpringBootApplication
public class MpmProjectServiceApplication {

	@Autowired
	private ProjectRepository service;

	public static void main(String[] args) {
		SpringApplication.run(MpmProjectServiceApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		for (int i = 1; i <= 10; i++) {
			Project project = new Project();
			
			project.setName(String.format("Mocked project #%d", i));
			project.setDescription(String.format("Mocked description #%d", i));
			project.setCreatedBy(UUID.randomUUID());

			service.save(project);
		}
	}
}
