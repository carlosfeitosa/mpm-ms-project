package com.skull.project.controller.impl; // NOPMD by skull on 8/8/20, 7:29 PM

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
import com.skull.project.model.AbstractModel;
import com.skull.project.model.Project;
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
public class ProjectControllerImpl implements ProjectController { // NOPMD by skull on 8/8/20, 7:19 PM

	/**
	 * Constant for project not available error.
	 */
	private static final String NOT_AVAILABLE = "Project not available for id: ";

	/**
	 * Repository link name.
	 */
	private static final String LINK_REF = "repo";

	/**
	 * Service mapping.
	 */
	@Value("${service.request.mapping}")
	private String controllerRequestMapping; // NOPMD by skull on 8/8/20, 7:21 PM

	/**
	 * Project repository.
	 */
	@Autowired
	private ProjectRepository repo; // NOPMD by skull on 8/8/20, 7:21 PM

	/**
	 * Project entity <--> dto converter.
	 */
	@Autowired
	private ProjectConverter converter; // NOPMD by skull on 8/8/20, 7:20 PM

	@Override
	@GetMapping
	public CollectionModel<ProjectDto> getAll() {

		log.info("Getting all itens");

		final List<Project> result = repo.findAll();

		final List<ProjectDto> projects = result.stream().map(converter::convertFromEntity)
				.collect(Collectors.toList());

		for (final ProjectDto project : projects) {

			project.add(getLink(project.getId(), null));
		}

		return CollectionModel.of(projects, getLink(null, null));
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<ProjectDto> newItem(final @RequestBody ProjectDto projectDto) {

		log.info("Creating new item");
		log.debug(String.format("Project name: %s", projectDto.getName()));

		final Project project = converter.convertFromDto(projectDto);

		applyMaintenanceData(project);

		final Project projectCreated = repo.save(project);

		final ProjectDto result = converter.convertFromEntity(projectCreated);
		result.add(getLink(result.getId(), null));
		result.add(getLink(null, LINK_REF));

		return EntityModel.of(result);
	}

	@Override
	@GetMapping("/{id}")
	public EntityModel<ProjectDto> getById(final @PathVariable(value = "id") UUID projectId) { // NOPMD by skull on
																								// 8/8/20, 7:33 PM

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		final Project project = repo.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException(NOT_AVAILABLE + projectId));

		final ProjectDto result = converter.convertFromEntity(project);
		result.add(getLink(result.getId(), null));
		result.add(getLink(null, LINK_REF));

		return EntityModel.of(result);
	}

	@Override
	@PutMapping("/{id}")
	public EntityModel<ProjectDto> updateItem(final ProjectDto projectDto, final UUID projectId) {

		log.info("Updating project");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		final Optional<Project> optProject = repo.findById(projectId);

		if (optProject.isPresent()) {

			final Project project = optProject.get();

			applyMaintenanceData(project);

			final ProjectDto result = converter
					.convertFromEntity(repo.save(converter.convertFromDto(projectDto, project)));
			result.add(getLink(result.getId(), null));
			result.add(getLink(null, LINK_REF));

			return EntityModel.of(result);
		} else {

			throw new NoSuchElementException(NOT_AVAILABLE + projectId);
		}
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteItem(final UUID projectId) {

		log.info("Deleting project");

		if (repo.existsById(projectId)) {

			repo.deleteById(projectId);
		} else {

			throw new NoSuchElementException(NOT_AVAILABLE.concat(projectId.toString()));
		}
	}

	/**
	 * Apply maintenance data to project entity.
	 * 
	 * TODO apply looged user
	 * 
	 * @param project to apply maintenance data
	 */
	private void applyMaintenanceData(final Project project) {

		log.info("Applying maintenance data");

		final UUID loggedUser = UUID.randomUUID();

		applyMaintenanceData(project.getId(), project, loggedUser);
		applyMaintenanceData(project.getId(), project.getDates(), loggedUser);
		applyMaintenanceData(project.getId(), project.getClientInformation(), loggedUser);
	}

	/**
	 * Apply maintenance data for project dates entity.
	 * 
	 * @param projectId  project id
	 * @param model      entity object
	 * @param loggedUser UUID of logged user
	 */
	private void applyMaintenanceData(final UUID projectId, final AbstractModel model, final UUID loggedUser) {

		if (null != model) {

			if (null == projectId) {

				model.setCreatedBy(loggedUser);
			} else {

				model.setModifiedBy(loggedUser);
				model.setModifiedDate(new Date());
			}
		}
	}

	/**
	 * Return link for controller.
	 * 
	 * @param id  project identification
	 * @param ref link reference. If null, link ref will be self
	 * 
	 * @return link to controller method
	 */
	private Link getLink(final UUID id, final String ref) { // NOPMD by skull on 8/8/20, 7:34 PM

		WebMvcLinkBuilder linkBuilder = linkTo(ProjectController.class).slash(controllerRequestMapping);

		log.info("Generating link for project");

		if (null != id) {

			log.debug(String.format("Genereting link for project #%s", id.toString()));

			linkBuilder = linkBuilder.slash(id.toString());
		}

		if (ref != null) {

			return linkBuilder.withRel(ref); // NOPMD by skull on 8/8/20, 7:46 PM
		}

		return linkBuilder.withSelfRel();
	}

}
