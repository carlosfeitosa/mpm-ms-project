package com.skull.project.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProjectDtoTest {

	private static final String TEST_PROJECT_CODE = "00001234";
	private static final String TEST_CLIENT_PROJECT_CODE = "AAA-00001234";
	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	@Test
	@DisplayName("Test if can perform equals and hashcode")
	void testIfCanPerformEqualsAndHashCode() {

		ProjectDto project1 = new ProjectDto();
		ProjectDto project2 = new ProjectDto();

		UUID projectId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = Date
				.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		project1.setCode(TEST_PROJECT_CODE);
		project1.setId(projectId);
		project1.setName(TEST_PROJECT_NAME);
		project1.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		project1.setClientId(clientId);
		project1.setDescription(TEST_PROJECT_DESCRIPTION);
		project1.setClientName(TEST_PROJECT_CLIENT_NAME);
		project1.setStartDate(startDate);
		project1.setEndDate(endDate);

		project2.setCode(TEST_PROJECT_CODE);
		project2.setId(projectId);
		project2.setName(TEST_PROJECT_NAME);
		project2.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		project2.setClientId(clientId);
		project2.setDescription(TEST_PROJECT_DESCRIPTION);
		project2.setClientName(TEST_PROJECT_CLIENT_NAME);
		project2.setStartDate(startDate);
		project2.setEndDate(endDate);

		assertThat(project1).isEqualTo(project2);
		assertThat(project1.hashCode()).isEqualTo(project2.hashCode());

		project2.setClientId(UUID.randomUUID());

		assertThat(project1.hashCode()).isNotEqualTo(project2.hashCode());
	}

	@Test
	@DisplayName("Test if can perform toString()")
	void testIfCanPerformToString() {

		ProjectDto projectDto = new ProjectDto();

		UUID projectId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = Date
				.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		projectDto.setCode(TEST_PROJECT_CODE);
		projectDto.setId(projectId);
		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		projectDto.setClientId(clientId);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(startDate);
		projectDto.setEndDate(endDate);

		String projectToString = projectDto.toString();

		assertThat(projectToString).contains(TEST_PROJECT_CODE);
		assertThat(projectToString).contains(projectId.toString());
		assertThat(projectToString).contains(TEST_PROJECT_NAME);
		assertThat(projectToString).contains(TEST_CLIENT_PROJECT_CODE);
		assertThat(projectToString).contains(clientId.toString());
		assertThat(projectToString).contains(TEST_PROJECT_DESCRIPTION);
		assertThat(projectToString).contains(TEST_PROJECT_CLIENT_NAME);
		assertThat(projectToString).contains(startDate.toString());
		assertThat(projectToString).contains(endDate.toString());
	}
}
