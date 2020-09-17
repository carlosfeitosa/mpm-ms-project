package com.skull.project.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skull.project.dto.ProjectDto;

/**
 * Class for embedded project dto list (response).
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-05
 *
 */
public class EmbeddedProjectList { // NOPMD by skull on 8/8/20, 7:00 PM

	/**
	 * Project list.
	 */
	@JsonProperty("projects")
	private List<ProjectDto> projects;

	public List<ProjectDto> getProjects() {

		return projects;
	}

	public void setProjects(final List<ProjectDto> projects) {

		this.projects = projects;
	}

}
