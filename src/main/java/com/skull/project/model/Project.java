package com.skull.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.skull.project.enums.ProjectHealthEnum;
import com.skull.project.enums.ProjectStatusEnum;
import com.skull.project.enums.ProjectTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Project model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-27
 *
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Entity(name = "project")
public class Project extends AbstractModel {

	/**
	 * Project code.
	 */
	@Column(name = "code", nullable = true, length = 50)
	@Getter
	@Setter
	private String code;

	/**
	 * Project name.
	 */
	@NonNull
	@Column(name = "name", length = 200)
	@Getter
	@Setter
	private String name;

	/**
	 * Project's description.
	 */
	@Column(name = "description", nullable = true, length = 2000)
	@Getter
	@Setter
	private String description;

	/**
	 * Project's type.
	 */
	@Column(name = "type", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectTypeEnum type;

	/**
	 * Project's status.
	 */
	@Column(name = "status", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectStatusEnum status;

	/**
	 * Project's health.
	 */
	@Column(name = "health", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectHealthEnum health;

	/**
	 * Project's dates.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_project_dates", referencedColumnName = "id")
	@Getter
	@Setter
	private ProjectDates dates;

	/**
	 * Project's client information.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_project_client_information", referencedColumnName = "id")
	@Getter
	@Setter
	private ProjectClientInformation clientInformation;

	/**
	 * Project's total contracted hours.
	 */
	@Column(name = "total_hours", nullable = true)
	@Getter
	@Setter
	private Long totalHours;

	/**
	 * Project's attention points.
	 */
	@Column(name = "attention_points", nullable = true)
	@Getter
	@Setter
	private String attentionPoints;

	/**
	 * Project's action plan.
	 */
	@Column(name = "action_plan", nullable = true)
	@Getter
	@Setter
	private String actionPlan;

}
