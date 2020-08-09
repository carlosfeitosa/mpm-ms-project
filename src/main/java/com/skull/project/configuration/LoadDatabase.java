package com.skull.project.configuration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skull.project.enums.ProjectType;
import com.skull.project.model.Project;
import com.skull.project.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Loads mocked database.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-05
 *
 */
@Configuration
@Slf4j
public class LoadDatabase { // NOPMD by skull on 8/8/20, 7:07 PM

	/**
	 * Init database with mock data.
	 * 
	 * @param repository   repository used to save information.
	 * @param initMockedDb true to mock data into database
	 * 
	 * @return args
	 */
	@Bean
	public CommandLineRunner initDatabase(final ProjectRepository repository,
			final @Value("${service.preload.database}") boolean initMockedDb) {
		return args -> {

			if (initMockedDb) {

				log.info("Preloading database...");

				final Project project = new Project();

				for (int i = 1; i <= 10; i++) {

					setProjectInformation(project, i);

					repository.save(project);
				}
			}
		};
	}

	/**
	 * Set project information.
	 * 
	 * @param project project entity
	 * @param row     row number
	 */
	private void setProjectInformation(final Project project, final int row) {

		project.setName(String.format("Mocked project #%d", row));
		project.setDescription(String.format("Mocked description #%d", row));

		if (0 == row % 2) {

			project.setType(ProjectType.INTERNAL_PROJECT);
		} else {

			project.setType(ProjectType.TIME_AND_MATERIAL_ALLOCATION);
		}

		project.setCreatedBy(UUID.randomUUID());
	}
}
