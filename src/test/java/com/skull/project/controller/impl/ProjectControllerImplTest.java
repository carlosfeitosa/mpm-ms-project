package com.skull.project.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.skull.project.controller.ProjectController;
import com.skull.project.model.Project;

@SpringBootTest(properties = { "service.preload.database=true" })
class ProjectControllerImplTest {

	@Autowired
	private ProjectController controller;

	@Test
	@DisplayName("Test if controller can get all elements")
	void testIfCanGetAll() {
		List<Project> projectList = controller.getAll();

		assertThat(projectList.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Test if controller can get by id")
	void testIfCanGetById() {
		List<Project> projectList = controller.getAll();

		for (Project project : projectList) {
			ResponseEntity<Project> foundProject = controller.getById(project.getId());

			assertThat(foundProject.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(foundProject.getBody().getId()).isEqualTo(project.getId());
		}
	}

}
