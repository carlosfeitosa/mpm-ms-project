package com.skull.project.dto;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import com.skull.project.enums.ProjectHealth;
import com.skull.project.enums.ProjectStatus;
import com.skull.project.enums.ProjectType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for project model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-05
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectDto extends RepresentationModel<ProjectDto> {

	/**
	 * Project identifier.
	 */
	private UUID id; // NOPMD by skull on 8/8/20, 7:18 PM

	/**
	 * Project code.
	 */
	private String code;

	/**
	 * Project name.
	 */
	private String name;

	/**
	 * Project's description.
	 */
	private String description;

	/**
	 * Project's type.
	 */
	private ProjectType type;

	/**
	 * Project's status.
	 */
	private ProjectStatus status;

	/**
	 * Project's health.
	 */
	private ProjectHealth health;

	/**
	 * Project's dates.
	 */
	private ProjectDatesDto dates;

	/**
	 * Project's client information.
	 */
	private ProjectClientInformationDto clientInformation;

	/**
	 * Project's total contracted hours.
	 */
	private Long totalHours;

	/**
	 * Project's attention points.
	 */
	private String attentionPoints;

	/**
	 * Project's action plan.
	 */
	private String actionPlan;

}
