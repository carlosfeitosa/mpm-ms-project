package com.skull.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.skull.project.dto.ProjectDto;

/**
 * Intarface for project controller.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-28
 *
 */
public interface ProjectController {

	/**
	 * Default route (list all projects).
	 * 
	 * @return list of all projects
	 */
	List<ProjectDto> getAll();

	/**
	 * Default route (save a new project)
	 * 
	 * @param projectDto project to save
	 * 
	 * @return saved project
	 */
	public ProjectDto newItem(@RequestBody ProjectDto projectDto);

	/**
	 * Return project by id.
	 * 
	 * @param projectId project UUID
	 * 
	 * @return project by id
	 */
	ProjectDto getById(@PathVariable(value = "id") UUID projectId);

	/**
	 * Update a project.
	 * 
	 * @param projectDto project dto
	 * @param projectId  project id
	 * 
	 * @return updated project
	 */
	ProjectDto updateItem(@RequestBody ProjectDto projectDto, @PathVariable(value = "id") UUID projectId);

	/**
	 * Delete a project.
	 * 
	 * @param projectId project id
	 */
	void deleteItem(@PathVariable(value = "id") UUID projectId);
}
