package com.skull.project.controller.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

		Project project = convertToEntity(projectDto);
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

	/**
	 * Convert entity to dto.
	 * 
	 * @param project entity
	 * 
	 * @return dto
	 */
	private ProjectDto convertToDto(Project project) {

		return modelMapper.map(project, ProjectDto.class);
	}

	/**
	 * Convert dto to entity
	 * 
	 * @param projectDto dto
	 * 
	 * @return entity
	 */
	private Project convertToEntity(ProjectDto projectDto) {

		return modelMapper.map(projectDto, Project.class);
	}

}
