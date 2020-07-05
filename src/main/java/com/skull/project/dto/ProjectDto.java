package com.skull.project.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

/**
 * DTO for project model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-05
 *
 */
@Data
public class ProjectDto {
	/**
	 * Project identifier.
	 */
	private UUID id;

	/**
	 * Project name.
	 */
	private String name;

	/**
	 * Project start date.
	 */
	private Date startDate;

	/**
	 * Project end date.
	 */
	private Date endDate;

	/**
	 * Project client's id.
	 */
	private UUID clientId;

	/**
	 * Project client's name.
	 */
	private String clientName;

	/**
	 * Project's description.
	 */
	private String description;

}
