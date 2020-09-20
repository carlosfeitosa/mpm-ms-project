package com.skull.project.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.project.enums.ProjectHealthEnum;
import com.skull.project.enums.ProjectStatusEnum;
import com.skull.project.enums.ProjectTypeEnum;

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
	private static final ProjectTypeEnum TEST_PROJECT_TYPE = ProjectTypeEnum.INTERNAL_PROJECT;
	private static final ProjectStatusEnum TEST_PROJECT_STATUS = ProjectStatusEnum.ON_HOLD;
	private static final ProjectHealthEnum TEST_PROJECT_HEALTH = ProjectHealthEnum.ALERT;

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

		ProjectClientInformation clientInformation = new ProjectClientInformation();
		clientInformation.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation.setClientId(clientId);
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);

		project.setClientInformation(clientInformation);

		project.setTotalHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		project.setType(TEST_PROJECT_TYPE);
		project.setStatus(TEST_PROJECT_STATUS);
		project.setHealth(TEST_PROJECT_HEALTH);

		project.setCreatedBy(createdBy);
		project.setModifiedBy(modifiedBy);
		project.setModifiedDate(modifiedDate);

		String projectToString = project.toString();

		assertThat(projectToString).contains(TEST_PROJECT_CODE);
		assertThat(projectToString).contains(TEST_PROJECT_NAME);
		assertThat(projectToString).contains(TEST_CLIENT_PROJECT_CODE);
		assertThat(projectToString).contains(clientId.toString());
		assertThat(projectToString).contains(TEST_PROJECT_CLIENT_NAME);
		assertThat(projectToString).contains(startDate.toString());
		assertThat(projectToString).contains(endDate.toString());
		assertThat(projectToString).contains(TEST_PROJECT_DESCRIPTION);
		assertThat(projectToString).contains(createdBy.toString());
		assertThat(projectToString).contains(modifiedBy.toString());
		assertThat(projectToString).contains(modifiedDate.toString());
		assertThat(projectToString).contains(realStartDate.toString());
		assertThat(projectToString).contains(newEndDate.toString());
		assertThat(projectToString).contains(TEST_PROJECT_TOTAL_CONTRACTED_HOURS.toString());
		assertThat(projectToString).contains(TEST_PROJECT_ATTENTION_POINTS);
		assertThat(projectToString).contains(TEST_PROJECT_ACTION_PLAN);
		assertThat(projectToString).contains(TEST_PROJECT_TYPE.toString());
		assertThat(projectToString).contains(TEST_PROJECT_STATUS.toString());
		assertThat(projectToString).contains(TEST_PROJECT_HEALTH.toString());
	}

}
