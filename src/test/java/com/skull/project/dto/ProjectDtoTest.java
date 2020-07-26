package com.skull.project.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skull.project.enums.ProjectHealth;
import com.skull.project.enums.ProjectStatus;
import com.skull.project.enums.ProjectType;

class ProjectDtoTest {

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

	@Test
	@DisplayName("Test if can perform equals and hashcode")
	void testIfCanPerformEqualsAndHashCode() {

		ProjectDto project1 = new ProjectDto();
		ProjectDto project2 = new ProjectDto();

		UUID projectId = UUID.randomUUID();
		UUID clientId = UUID.randomUUID();
		Date startDate = new Date();
		Date endDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		project1.setCode(TEST_PROJECT_CODE);
		project1.setId(projectId);
		project1.setName(TEST_PROJECT_NAME);
		project1.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		project1.setClientId(clientId);
		project1.setDescription(TEST_PROJECT_DESCRIPTION);
		project1.setClientName(TEST_PROJECT_CLIENT_NAME);
		project1.setStartDate(startDate);
		project1.setEndDate(endDate);
		project1.setRealStartDate(realStartDate);
		project1.setNewEndDate(newEndDate);
		project1.setTotalContractedHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project1.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project1.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		project1.setType(TEST_PROJECT_TYPE);
		project1.setStatus(TEST_PROJECT_STATUS);
		project1.setHealth(TEST_PROJECT_HEALTH);

		project2.setCode(TEST_PROJECT_CODE);
		project2.setId(projectId);
		project2.setName(TEST_PROJECT_NAME);
		project2.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		project2.setClientId(clientId);
		project2.setDescription(TEST_PROJECT_DESCRIPTION);
		project2.setClientName(TEST_PROJECT_CLIENT_NAME);
		project2.setStartDate(startDate);
		project2.setEndDate(endDate);
		project2.setRealStartDate(realStartDate);
		project2.setNewEndDate(newEndDate);
		project2.setTotalContractedHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project2.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project2.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		project2.setType(TEST_PROJECT_TYPE);
		project2.setStatus(TEST_PROJECT_STATUS);
		project2.setHealth(TEST_PROJECT_HEALTH);

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
		Date endDate = new Date();
		Date realStartDate = new Date();
		Date newEndDate = new Date();

		projectDto.setCode(TEST_PROJECT_CODE);
		projectDto.setId(projectId);
		projectDto.setName(TEST_PROJECT_NAME);
		projectDto.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		projectDto.setClientId(clientId);
		projectDto.setDescription(TEST_PROJECT_DESCRIPTION);
		projectDto.setClientName(TEST_PROJECT_CLIENT_NAME);
		projectDto.setStartDate(startDate);
		projectDto.setEndDate(endDate);
		projectDto.setRealStartDate(realStartDate);
		projectDto.setNewEndDate(newEndDate);
		projectDto.setTotalContractedHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		projectDto.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		projectDto.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		projectDto.setType(TEST_PROJECT_TYPE);
		projectDto.setStatus(TEST_PROJECT_STATUS);
		projectDto.setHealth(TEST_PROJECT_HEALTH);

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

		assertThat(projectToString).contains(realStartDate.toString());
		assertThat(projectToString).contains(newEndDate.toString());
		assertThat(projectToString).contains(String.valueOf(TEST_PROJECT_TOTAL_CONTRACTED_HOURS));
		assertThat(projectToString).contains(TEST_PROJECT_ATTENTION_POINTS);
		assertThat(projectToString).contains(TEST_PROJECT_ACTION_PLAN);
		assertThat(projectToString).contains(TEST_PROJECT_TYPE.toString());
		assertThat(projectToString).contains(TEST_PROJECT_STATUS.toString());
		assertThat(projectToString).contains(TEST_PROJECT_HEALTH.toString());
	}
}
