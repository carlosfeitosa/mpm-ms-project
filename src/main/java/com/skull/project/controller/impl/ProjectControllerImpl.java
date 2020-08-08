package com.skull.project.controller.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.skull.project.model.ProjectClientInformation;
import com.skull.project.model.ProjectDates;
import com.skull.project.repository.ProjectRepository;

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
	private static final String PROJECT_LINK_REF = "repo";

	@Value("${service.request.mapping}")
	String controllerRequestMapping;

	@Autowired
	private ProjectRepository repo;

	@Autowired
	private ProjectConverter converter;

	@Override
	@GetMapping
	public CollectionModel<ProjectDto> getAll() {

		log.info("Getting all itens");

		List<Project> result = repo.findAll();

		List<ProjectDto> projects = result.stream().map(converter::convertFromEntity).collect(Collectors.toList());

		for (ProjectDto project : projects) {

			project.add(getLink(project.getId(), null));
		}

		return CollectionModel.of(projects, getLink(null, null));
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<ProjectDto> newItem(@RequestBody ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		Project project = converter.convertFromDto(projectDto);

		applyMaintenanceData(project);

		Project projectCreated = repo.save(project);

		ProjectDto result = converter.convertFromEntity(projectCreated);
		result.add(getLink(result.getId(), null));
		result.add(getLink(null, PROJECT_LINK_REF));

		return EntityModel.of(result);
	}

	@Override
	@GetMapping("/{id}")
	public EntityModel<ProjectDto> getById(@PathVariable(value = "id") UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Project project = repo.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException(PROJECT_NOT_AVAILABLE_FOR_ID + projectId));

		ProjectDto result = converter.convertFromEntity(project);
		result.add(getLink(result.getId(), null));
		result.add(getLink(null, PROJECT_LINK_REF));

		return EntityModel.of(result);
	}

	@Override
	@PutMapping("/{id}")
	public EntityModel<ProjectDto> updateItem(ProjectDto projectDto, UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Optional<Project> optProject = repo.findById(projectId);

		if (optProject.isPresent()) {

			Project project = optProject.get();

			applyMaintenanceData(project);

			ProjectDto result = converter.convertFromEntity(repo.save(converter.convertFromDto(projectDto, project)));
			result.add(getLink(result.getId(), null));
			result.add(getLink(null, PROJECT_LINK_REF));

			return EntityModel.of(result);
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

		applyMaintenanceData(project.getId(), project.getDates(), loggedUser);
		applyMaintenanceData(project.getId(), project.getClientInformation(), loggedUser);
	}

	/**
	 * Apply maintenance data for project dates entity.
	 * 
	 * @param projectId  project id
	 * @param dates      ProjectDates object
	 * @param loggedUser UUID of logged user
	 */
	private void applyMaintenanceData(UUID projectId, ProjectDates dates, UUID loggedUser) {

		if (null != dates) {

			if (null == projectId) {

				dates.setCreatedBy(loggedUser);
			} else {

				dates.setModifiedBy(loggedUser);
				dates.setModifiedDate(new Date());
			}
		}
	}

	/**
	 * Apply maintenance data for project client information entity.
	 * 
	 * @param projectId  project id
	 * @param dates      ProjectDates object
	 * @param loggedUser UUID of logged user
	 */
	private void applyMaintenanceData(UUID projectId, ProjectClientInformation clientInfo, UUID loggedUser) {

		if (null != clientInfo) {

			if (null == projectId) {

				clientInfo.setCreatedBy(loggedUser);
			} else {

				clientInfo.setModifiedBy(loggedUser);
				clientInfo.setModifiedDate(new Date());
			}
		}
	}

	/**
	 * Return link for controller.
	 * 
	 * @param id  project identification. If null, link will be to controller
	 *            default method.
	 * @param ref link reference. If null, link ref will be self
	 * 
	 * @return link to controller method
	 */
	private Link getLink(UUID id, String ref) {
		WebMvcLinkBuilder linkBuilder = linkTo(ProjectController.class).slash(controllerRequestMapping);

		log.info("Generating link for project");

		if (null != id) {

			log.debug(String.format("Genereting link for project #%s", id.toString()));

			linkBuilder = linkBuilder.slash(id.toString());
		}

		if (ref != null) {

			return linkBuilder.withRel(ref);
		}

		return linkBuilder.withSelfRel();
	}

}
