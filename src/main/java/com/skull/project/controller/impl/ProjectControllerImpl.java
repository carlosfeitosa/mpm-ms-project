package com.skull.project.controller.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skull.project.controller.ProjectController;
import com.skull.project.converter.ProjectConverter;
import com.skull.project.dto.ProjectDto;
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
@RequestMapping("${service.request.mapping}")
@Slf4j
public class ProjectControllerImpl implements ProjectController {

	private static final String PROJECT_NOT_AVAILABLE_FOR_ID = "Project not available for id: ";

	@Autowired
	private ProjectRepository repo;

	@Autowired
	private ProjectConverter converter;

	@Override
	@GetMapping
	public List<ProjectDto> getAll() {

		log.info("Getting all itens");

		List<Project> result = repo.findAll();

		return result.stream().map(converter::convertFromEntity).collect(Collectors.toList());
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProjectDto newItem(@RequestBody ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		Project project = converter.convertFromDto(projectDto);

		applyMaintenanceData(project);

		Project projectCreated = repo.save(project);

		return converter.convertFromEntity(projectCreated);
	}

	@Override
	@GetMapping("/{id}")
	public ProjectDto getById(@PathVariable(value = "id") UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Project project = repo.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException(PROJECT_NOT_AVAILABLE_FOR_ID + projectId));

		return converter.convertFromEntity(project);
	}

	@Override
	@PutMapping("/{id}")
	public ProjectDto updateItem(ProjectDto projectDto, UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Optional<Project> optProject = repo.findById(projectId);

		if (optProject.isPresent()) {

			Project project = optProject.get();

			applyMaintenanceData(project);

			return converter.convertFromEntity(repo.save(converter.convertFromDto(projectDto, project)));
		} else {

			throw new NoSuchElementException(PROJECT_NOT_AVAILABLE_FOR_ID + projectId);
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteItem(UUID projectId) {

		log.info("Deleting project");

		try {

			repo.deleteById(projectId);
		} catch (Exception e) {

			throw new NoSuchElementException(
					PROJECT_NOT_AVAILABLE_FOR_ID + projectId + System.lineSeparator() + e.getMessage());
		}
	}

	/**
	 * Apply maintenance data to project entity.
	 * 
	 * TODO apply looged user
	 * 
	 * @param project to apply maintenance data
	 */
	private void applyMaintenanceData(Project project) {

		log.info("Applying maintenance data");

		UUID loggedUser = UUID.randomUUID();

		if (null == project.getId()) {

			project.setCreatedBy(loggedUser);
		} else {

			project.setModifiedBy(loggedUser);
			project.setModifiedDate(new Date());
		}
	}

}
