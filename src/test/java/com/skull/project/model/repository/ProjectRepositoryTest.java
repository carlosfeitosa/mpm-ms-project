package com.skull.project.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.project.model.Project;

@SpringBootTest
class ProjectRepositoryTest {

	private static final String TEST_PROJECT_NAME = "Test project";
	private static final String TEST_PROJECT_DESCRIPTION = "Test description";

	@Autowired
	private ProjectRepository repo;

	/**
	 * Test if a project can be saved with minimum information.
	 */
	@Test
	@DisplayName("Test if context can load")
	void testIfCanSaveMinimumProjectInformation() {
		Project project = new Project();

		project.setName(TEST_PROJECT_NAME);
		project.setDescription(TEST_PROJECT_DESCRIPTION);
		project.setCreatedBy(UUID.randomUUID());

		Project createdProject = repo.save(project);

		assertThat(createdProject.getId()).isNotNull();
	}

}
