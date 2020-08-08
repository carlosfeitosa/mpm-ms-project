package com.skull.project.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.skull.project.controller.ProjectController;
import com.skull.project.dto.ProjectClientInformationDto;
import com.skull.project.dto.ProjectDatesDto;
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

		CollectionModel<ProjectDto> projectList = controller.getAll();

		assertThat(projectList).isNotEmpty();
	}

	@Test
	@DisplayName("Test if controller can get item by id")
	void testIfCanGetProjectById() {

		CollectionModel<ProjectDto> projectList = controller.getAll();

		for (ProjectDto project : projectList) {

			EntityModel<ProjectDto> foundProject = controller.getById(project.getId());

			assertThat(foundProject.getContent().getId()).isEqualTo(project.getId());
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
		ProjectDatesDto datesDto = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		datesDto.setStartDate(new Date());
		datesDto.setEndDate(new Date());

		projectDto.setDates(datesDto);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if controller can update a project")
	void testIfCanUpdateAProject() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto datesDto = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		datesDto.setStartDate(new Date());
		datesDto.setEndDate(new Date());

		projectDto.setDates(datesDto);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID newClientId = UUID.randomUUID();

		createdProjectDto.getContent().getClientInformation().setClientId(newClientId);

		EntityModel<ProjectDto> updatedProject = controller.updateItem(createdProjectDto.getContent(),
				createdProjectDto.getContent().getId());

		assertThat(updatedProject.getContent().getClientInformation().getClientId()).isEqualTo(newClientId);
	}

	@Test
	@DisplayName("Test if controller throws exception trying to update a project id")
	void testIfUpdateAProjectIdThrowsException() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto dates = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		dates.setStartDate(new Date());
		dates.setEndDate(new Date());

		projectDto.setDates(dates);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID newProjectId = UUID.randomUUID();

		createdProjectDto.getContent().setId(newProjectId);

		ProjectDto projectForUpdate = createdProjectDto.getContent();

		Exception exception = assertThrows(NoSuchElementException.class, () -> {

			controller.updateItem(projectForUpdate, newProjectId);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_INVALID_PROJECT_ID_EXCEPTION);
	}

	@Test
	@DisplayName("Test if controller can delete a project")
	void testIfCanDeleteAProject() {

		ProjectDto projectDto = new ProjectDto();
		ProjectDatesDto dates = new ProjectDatesDto();
		ProjectClientInformationDto clientInformation = new ProjectClientInformationDto();

		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);

		dates.setStartDate(new Date());
		dates.setEndDate(new Date());

		projectDto.setDates(dates);

		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		projectDto.setClientInformation(clientInformation);

		EntityModel<ProjectDto> createdProjectDto = controller.newItem(projectDto);

		assertThat(createdProjectDto.getContent().getId()).isNotNull();

		UUID projectId = createdProjectDto.getContent().getId();

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
