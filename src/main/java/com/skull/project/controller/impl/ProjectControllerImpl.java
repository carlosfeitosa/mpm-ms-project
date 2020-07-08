package com.skull.project.controller.impl;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skull.project.controller.ProjectController;
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

	@Autowired
	private ProjectRepository repo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@GetMapping
	public List<ProjectDto> getAll() {

		List<Project> result = repo.findAll();

		return result.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ProjectDto newItem(@RequestBody ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		Project project = convertToEntity(projectDto);

		applyMaintenanceData(project);

		Project projectCreated = repo.save(project);

		return convertToDto(projectCreated);
	}

	@Override
	@GetMapping("/{id}")
	@ResponseBody
	public ProjectDto getById(@PathVariable(value = "id") UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Project project = repo.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException("Project not available for id: " + projectId));

		return convertToDto(project);
	}

	@Override
	@PutMapping("/{id}")
	@ResponseBody
	public ProjectDto updateItem(ProjectDto projectDto, UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Optional<Project> optProject = repo.findById(projectId);

		if (optProject.isPresent()) {

			return convertToDto(repo.save(updateProjectFromDto(optProject.get(), projectDto)));
		} else {

			throw new NoSuchElementException("Project not available for id: " + projectId);
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteItem(UUID projectId) {

		log.info("Deleting project");

		repo.deleteById(projectId);
	}

	/**
	 * Convert entity to dto.
	 * 
	 * @param project entity
	 * 
	 * @return dto
	 */
	private ProjectDto convertToDto(Project project) {

		log.info("Converting to dto");

		return modelMapper.map(project, ProjectDto.class);
	}

	/**
	 * Convert dto to entity.
	 * 
	 * @param projectDto dto
	 * 
	 * @return entity
	 */
	private Project convertToEntity(ProjectDto projectDto) {

		log.info("Converting to entity");

		return modelMapper.map(projectDto, Project.class);
	}

	/**
	 * Updates a project's entity by it's dto
	 * 
	 * @param project    project entity
	 * @param projectDto project dto
	 * 
	 * @return updated prpject entity
	 */
	private Project updateProjectFromDto(Project project, ProjectDto projectDto) {
		project.setName(projectDto.getName());
		project.setDescription(projectDto.getDescription());
		project.setClientId(projectDto.getClientId());
		project.setClientName(projectDto.getClientName());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());

		return project;
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
