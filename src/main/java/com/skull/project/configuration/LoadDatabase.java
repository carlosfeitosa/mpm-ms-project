package com.skull.project.configuration;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.skull.project.model.Project;
import com.skull.project.model.repository.ProjectRepository;

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
public class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(ProjectRepository repository,
			@Value("${service.preload.database}") boolean initMockedDatabase) {
		return args -> {

			if (initMockedDatabase) {

				log.info("Preloading database...");

				for (int i = 1; i <= 10; i++) {
					Project project = new Project();

					project.setName(String.format("Mocked project #%d", i));
					project.setDescription(String.format("Mocked description #%d", i));
					project.setCreatedBy(UUID.randomUUID());

					repository.save(project);
				}
			}
		};
	}
}
