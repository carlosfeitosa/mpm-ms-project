package com.skull.project.model.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.skull.project.model.Project;

@SpringBootTest
class ProjectRepositoryTest {

	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";
	private static final String TEST_PROJECT_CLIENT_NAME = "Test client name";
	private static final String TEST_PROJECT_SAVE_EXCEPTION_MESSAGE = "not-null property references a null or transient value";
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

		project.setName(TEST_PROJECT_NAME);
		project.setClientId(UUID.randomUUID());
		project.setClientName(TEST_PROJECT_CLIENT_NAME);
		project.setStartDate(new Date());
		project.setEndDate(
				Date.from((LocalDate.now().plusMonths(1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		project.setDescription(TEST_PROJECT_DESCRIPTION);
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

		assertThat(foundProject.isPresent()).isTrue();

		Project project = foundProject.get();

		assertThat(createdProject.getId()).isEqualTo(project.getId());
	}

	@Test
	@DisplayName("Test if an exception is not throwed if find by is passed wrong id")
	void testIfCanFindByWrongId() {

		Optional<Project> foundProject = repo.findById(UUID.randomUUID());

		assertThat(foundProject.isPresent()).isFalse();
	}
}
