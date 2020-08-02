package com.skull.project.dto;

import java.util.Date;
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
	private UUID id;

	/**
	 * Project code.
	 */
	private String code;

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
	 * Project real start.
	 */
	private Date realStartDate;

	/**
	 * Project new end date.
	 */
	private Date newEndDate;

	/**
	 * Project client's id.
	 */
	private UUID clientId;

	/**
	 * Project client's name.
	 */
	private String clientName;

	/**
	 * Project client's project code.
	 */
	private String clientProjectCode;

	/**
	 * Project's description.
	 */
	private String description;

	/**
	 * Project's total contracted hours.
	 */
	private Long totalContractedHours;

	/**
	 * Project's attention points.
	 */
	private String attentionPoints;

	/**
	 * Project's action plan.
	 */
	private String actionPlan;

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

}
