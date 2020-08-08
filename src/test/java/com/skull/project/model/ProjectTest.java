package com.skull.project.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.project.enums.ProjectHealth;
import com.skull.project.enums.ProjectStatus;
import com.skull.project.enums.ProjectType;

@SpringBootTest
class ProjectTest {

	private static final String TEST_PROJECT_CODE = "00001234";
	private static final String TEST_CLIENT_PROJECT_CODE = "AAA-00001234";
	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";
	private static final Long TEST_PROJECT_TOTAL_CONTRACTED_HOURS = 100000L;
	private static final String TEST_PROJECT_ATTENTION_POINTS = "No attention points for now";
	private static final String TEST_PROJECT_ACTION_PLAN = "Let's do it!";
	private static final ProjectType TEST_PROJECT_TYPE = ProjectType.INTERNAL_PROJECT;
	private static final ProjectStatus TEST_PROJECT_STATUS = ProjectStatus.ON_HOLD;
	private static final ProjectHealth TEST_PROJECT_HEALTH = ProjectHealth.ALERT;

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
		Date endDate = new Date();
		Date modifiedDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		project.setCode(TEST_PROJECT_CODE);
		project.setName(TEST_PROJECT_NAME);
		project.setDescription(TEST_PROJECT_DESCRIPTION);

		ProjectDates dates = new ProjectDates();
		dates.setStartDate(startDate);
		dates.setEndDate(endDate);
		dates.setRealStartDate(realStartDate);
		dates.setNewEndDate(newEndDate);

		project.setDates(dates);

		project.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		project.setClientId(clientId);
		project.setClientName(TEST_PROJECT_CLIENT_NAME);
		project.setContractedHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		project.setType(TEST_PROJECT_TYPE);
		project.setStatus(TEST_PROJECT_STATUS);
		project.setHealth(TEST_PROJECT_HEALTH);

		project.setCreatedBy(createdBy);
		project.setModifiedBy(modifiedBy);
		project.setModifiedDate(modifiedDate);

		String projectToString = project.toString();

		assertThat(projectToString.contains(TEST_PROJECT_CODE)).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_NAME)).isTrue();
		assertThat(projectToString.contains(TEST_CLIENT_PROJECT_CODE)).isTrue();
		assertThat(projectToString.contains(clientId.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_CLIENT_NAME)).isTrue();
		assertThat(projectToString.contains(startDate.toString())).isTrue();
		assertThat(projectToString.contains(endDate.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_DESCRIPTION)).isTrue();
		assertThat(projectToString.contains(createdBy.toString())).isTrue();
		assertThat(projectToString.contains(modifiedBy.toString())).isTrue();
		assertThat(projectToString.contains(modifiedDate.toString())).isTrue();

		assertThat(projectToString.contains(realStartDate.toString())).isTrue();
		assertThat(projectToString.contains(newEndDate.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_TOTAL_CONTRACTED_HOURS.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_ATTENTION_POINTS)).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_ACTION_PLAN)).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_TYPE.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_STATUS.toString())).isTrue();
		assertThat(projectToString.contains(TEST_PROJECT_HEALTH.toString())).isTrue();
	}

}
