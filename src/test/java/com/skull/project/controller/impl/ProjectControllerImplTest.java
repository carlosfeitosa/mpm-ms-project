package com.skull.project.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.project.controller.ProjectController;
import com.skull.project.dto.ProjectDto;

@SpringBootTest(properties = { "service.preload.database=true" })
class ProjectControllerImplTest {

	private static final String TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION = "Project not available for id";
	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	@Autowired
	private ProjectController controller;

	@Test
	@DisplayName("Test if controller can get all elements")
	void testIfCanGetAllProjects() {

		List<ProjectDto> projectList = controller.getAll();

		assertThat(projectList.size()).isGreaterThan(0);
	}

	@Test
	@DisplayName("Test if controller can get item by id")
	void testIfCanGetProjectById() {

		List<ProjectDto> projectList = controller.getAll();

		for (ProjectDto project : projectList) {

			ProjectDto foundProject = controller.getById(project.getId());

			assertThat(foundProject.getId()).isEqualTo(project.getId());
		}
	}

	@Test
	@DisplayName("Test if controller throws exception trying to get item by invalid id")
	void testIfThrowsExceptionTryingGetProjectByInvalidId() {

		UUID invalidProjectId = UUID.randomUUID();

		Exception exception = assertThrows(NoSuchElementException.class, () -> {

			controller.getById(invalidProjectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if controller can save a new project")
	void testIfCanCreateAProject() {

		ProjectDto projectDto = new ProjectDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientId(UUID.randomUUID());
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(new Date());
		projectDto.setEndDate(
				Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		ProjectDto createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if controller can update a project")
	void testIfCanUpdateAProject() {

		ProjectDto projectDto = new ProjectDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientId(UUID.randomUUID());
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(new Date());
		projectDto.setEndDate(
				Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		ProjectDto createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getId()).isNotNull();

		UUID newClientId = UUID.randomUUID();

		createdProjectDto.setClientId(newClientId);

		ProjectDto updatedProject = controller.updateItem(createdProjectDto, createdProjectDto.getId());

		assertThat(updatedProject.getClientId()).isEqualTo(newClientId);
	}

	@Test
	@DisplayName("Test if controller throws exception trying to update a project id")
	void testIfUpdateAProjectIdThrowsException() {

		ProjectDto projectDto = new ProjectDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientId(UUID.randomUUID());
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(new Date());
		projectDto.setEndDate(
				Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		ProjectDto createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getId()).isNotNull();

		UUID newProjectId = UUID.randomUUID();

		createdProjectDto.setId(newProjectId);

		Exception exception = assertThrows(NoSuchElementException.class, () -> {

			controller.updateItem(createdProjectDto, newProjectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if controller can delete a project")
	void testIfCanDeleteAProject() {

		ProjectDto projectDto = new ProjectDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientId(UUID.randomUUID());
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(new Date());
		projectDto.setEndDate(
				Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

		ProjectDto createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getId()).isNotNull();

		UUID projectId = createdProjectDto.getId();

		controller.deleteItem(projectId);

		Exception exception = assertThrows(NoSuchElementException.class, () -> {

			controller.getById(projectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);

	}

	@Test
	@DisplayName("Test if controller can throw an exception trying to delete an invalid project")
	void testIfThrowExceptionTryingToDeleteAnInvalidProject() {

		UUID projectId = UUID.randomUUID();

		Exception exception = assertThrows(NoSuchElementException.class, () -> {

			controller.getById(projectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);

	}
}
