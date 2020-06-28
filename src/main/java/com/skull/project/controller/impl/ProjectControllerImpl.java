package com.skull.project.controller.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skull.project.controller.ProjectController;
import com.skull.project.model.Project;
import com.skull.project.model.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Project controller.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-28
 *
 */
@RestController
@RequestMapping("api/projects")
@Slf4j
public class ProjectControllerImpl implements ProjectController {

	@Autowired
	private ProjectRepository repo;

	@Override
	@GetMapping
	public List<Project> getAll() {
		return repo.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Project> getById(@PathVariable(value = "id") UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Project project = repo.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException("Project not availbele for id:" + projectId));

		return ResponseEntity.ok().body(project);
	}

	/**
	 * Init database with 10 mocked projects.
	 */
	public void initMockDb() {
		for (int i = 1; i <= 10; i++) {
			Project project = new Project();

			project.setName(String.format("Mocked project #%d", i));
			project.setDescription(String.format("Mocked description #%d", i));
			project.setCreatedBy(UUID.randomUUID());

			repo.save(project);
		}
	}
}
