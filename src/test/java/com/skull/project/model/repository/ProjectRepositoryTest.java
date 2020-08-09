package com.skull.project.model.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.skull.project.enums.ProjectHealth;
import com.skull.project.enums.ProjectStatus;
import com.skull.project.enums.ProjectType;
import com.skull.project.model.Project;
import com.skull.project.model.ProjectClientInformation;
import com.skull.project.model.ProjectDates;
import com.skull.project.repository.ProjectRepository;

@SpringBootTest
class ProjectRepositoryTest {

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
	private static final String TEST_PROJECT_SAVE_EXCEPTION_MESSAGE = "could not execute statement; SQL [n/a]; constraint [null];";
	private static final String TEST_PROJECT_NULL_NAME_EXCEPTION = "name is marked non-null but is null";

	@Autowired
	private ProjectRepository repo;

	private Project project;

	@BeforeEach
	void setupTest() {

		project = new Project();
	}

	@Test
	@DisplayName("Test if project can be saved with minimum project information")
	void testIfCanSaveMinimumProjectInformation() {

		project.setName(TEST_PROJECT_NAME);
		project.setDescription(TEST_PROJECT_DESCRIPTION);
		project.setCreatedBy(UUID.randomUUID());

		Project createdProject = repo.save(project);

		assertThat(createdProject.getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if project can be saved with all project information")
	void testIfCanSaveMaximumProjectInformation() {

		project.setCode(TEST_PROJECT_CODE);
		project.setName(TEST_PROJECT_NAME);
		project.setDescription(TEST_PROJECT_DESCRIPTION);

		ProjectDates dates = new ProjectDates();
		dates.setStartDate(new Date());
		dates.setEndDate(new Date());
		dates.setRealStartDate(new Date());
		dates.setNewEndDate(new Date());
		dates.setCreatedBy(UUID.randomUUID());

		project.setDates(dates);

		ProjectClientInformation clientInformation = new ProjectClientInformation();
		clientInformation.setClientProjectCode(TEST_CLIENT_PROJECT_CODE);
		clientInformation.setClientId(UUID.randomUUID());
		clientInformation.setClientName(TEST_PROJECT_CLIENT_NAME);
		clientInformation.setCreatedBy(UUID.randomUUID());

		project.setClientInformation(clientInformation);

		project.setContractedHours(TEST_PROJECT_TOTAL_CONTRACTED_HOURS);
		project.setAttentionPoints(TEST_PROJECT_ATTENTION_POINTS);
		project.setActionPlan(TEST_PROJECT_ACTION_PLAN);
		project.setType(TEST_PROJECT_TYPE);
		project.setStatus(TEST_PROJECT_STATUS);
		project.setHealth(TEST_PROJECT_HEALTH);
		project.setCreatedBy(UUID.randomUUID());
		project.setModifiedBy(UUID.randomUUID());
		project.setModifiedDate(new Date());

		Project createdProject = repo.save(project);

		assertThat(createdProject.getId()).isNotNull();
	}

	@Test
	@DisplayName("Test if an exception is throwed if project information is empty")
	void testIfCanSaveEmptyProject() {

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {

			repo.save(project);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_SAVE_EXCEPTION_MESSAGE);
	}

	@Test
	@DisplayName("Test if an exception is throwed if project name is null")
	void testIfCanSaveLessThenMinimumProjectInformation() {

		Exception exception = assertThrows(NullPointerException.class, () -> {

			project.setName(null);
		});

		String actualMessage = exception.getMessage();

		assertThat(actualMessage).contains(TEST_PROJECT_NULL_NAME_EXCEPTION);
	}

	@Test
	@DisplayName("Test if can find an project by id")
	void testIfCanFindById() {

		project.setName(TEST_PROJECT_NAME);
		project.setDescription(TEST_PROJECT_DESCRIPTION);
		project.setCreatedBy(UUID.randomUUID());

		Project createdProject = repo.save(project);
		Optional<Project> foundProject = repo.findById(createdProject.getId());

		assertThat(foundProject).isPresent();

		Project project = foundProject.get();

		assertThat(createdProject.getId()).isEqualTo(project.getId());
	}

	@Test
	@DisplayName("Test if an exception is not throwed if find by is passed wrong id")
	void testIfCanFindByWrongId() {

		Optional<Project> foundProject = repo.findById(UUID.randomUUID());

		assertThat(foundProject).isEmpty();
	}
}
