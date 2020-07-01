package com.skull.project.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectTest {

	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";

	private Project project;

	@BeforeEach
	void setupTest() {

		project = new Project();
	}

	@Test
	@DisplayName("Test if to .toString() has all expected object attributes")
	void testIfToStringHasAllExpectedObjectAttributes() {

		UUID clientId = UUID.randomUUID();
		UUID createdBy = UUID.randomUUID();
		UUID modifiedBy = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = Date
				.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Date modifiedDate = new Date();

		project.setName(TEST_PROJECT_NAME);
		project.setClientId(clientId);
		project.setClientName(TEST_PROJECT_CLIENT_NAME);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setDescription(TEST_PROJECT_DESCRIPTION);
		project.setCreatedBy(createdBy);
		project.setModifiedBy(modifiedBy);
		project.setModifiedDate(modifiedDate);

		String projectToString = project.toString();

		assertThat(projectToString.contains(TEST_PROJECT_NAME)).isTrue();
		assertThat(projectToString.contains(clientId.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_CLIENT_NAME)).isTrue();
		assertThat(projectToString.contains(startDate.toString())).isTrue();
		assertThat(projectToString.contains(endDate.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_DESCRIPTION)).isTrue();
		assertThat(projectToString.contains(createdBy.toString())).isTrue();
		assertThat(projectToString.contains(modifiedBy.toString())).isTrue();
		assertThat(projectToString.contains(modifiedDate.toString())).isTrue();
	}

}
