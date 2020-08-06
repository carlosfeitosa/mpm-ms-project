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
public class EmbeddedProjectList {

	@JsonProperty("projectDtoList")
	private List<ProjectDto> projectDtoList;

	public List<ProjectDto> getProjectDtoList() {

		return projectDtoList;
	}

	public void setProjectDtoList(List<ProjectDto> projectDtoList) {

		this.projectDtoList = projectDtoList;
	}

}
