package com.skull.project.configuration;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skull.project.enums.ProjectTypeEnum;
import com.skull.project.model.Project;
import com.skull.project.model.ProjectClientInformation;
import com.skull.project.model.ProjectDates;
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
	 * Project's name.
	 */
	private static final String PROJECT_NAME = "Mocked project #%d";
	/**
	 * Project's description.
	 */
	private static final String PROJECT_DESC = "Mocked description #%d";
	/**
	 * Project's client name.
	 */
	private static final String CLIENT_NAME = "Mocked client name #%d";

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

				for (int i = 1; i <= 10; i++) {

					final Project project = new Project(); // NOPMD by skull on 8/9/20, 10:32 PM

					setProjectInformation(project, i);
					setProjectDatesAndClientInformation(project, i);

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

		project.setName(String.format(PROJECT_NAME, row));
		project.setDescription(String.format(PROJECT_DESC, row));

		if (0 == row % 2) {

			project.setType(ProjectTypeEnum.INTERNAL_PROJECT);
		} else {

			project.setType(ProjectTypeEnum.TIME_AND_MATERIAL_ALLOCATION);
		}

		project.setCreatedBy(UUID.randomUUID());
	}

	/**
	 * Set project dates and client information.
	 * 
	 * @param project project entity
	 * @param row     row number
	 */
	private void setProjectDatesAndClientInformation(final Project project, final int row) {

		final ProjectDates dates = new ProjectDates();
		final ProjectClientInformation clientInformation = new ProjectClientInformation();

		dates.setStartDate(new Date());
		dates.setCreatedBy(UUID.randomUUID());
		clientInformation.setClientName(String.format(CLIENT_NAME, row));
		clientInformation.setCreatedBy(UUID.randomUUID());

		project.setDates(dates);
		project.setClientInformation(clientInformation);
	}
}
