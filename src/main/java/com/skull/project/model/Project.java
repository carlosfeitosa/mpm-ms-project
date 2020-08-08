package com.skull.project.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.skull.project.enums.ProjectHealth;
import com.skull.project.enums.ProjectStatus;
import com.skull.project.enums.ProjectType;

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
@ToString
@Entity(name = "project")
public class Project {

	/**
	 * Project identifier.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@Getter
	private UUID id; // NOPMD by skull on 8/8/20, 10:11 AM

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
	 * Project's dates.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_dates_id", referencedColumnName = "id")
	@Getter
	@Setter
	private ProjectDates dates;

	/**
	 * Project client's id.
	 */
	@Column(name = "client_id", nullable = true)
	@Getter
	@Setter
	private UUID clientId;

	/**
	 * Project client's name.
	 */
	@Column(name = "client_name", nullable = true, length = 200)
	@Getter
	@Setter
	private String clientName;

	/**
	 * Project client's project code.
	 */
	@Column(name = "client_project_code", nullable = true, length = 50)
	@Getter
	@Setter
	private String clientProjectCode;

	/**
	 * Project's description.
	 */
	@Column(name = "description", nullable = true, length = 2000)
	@Getter
	@Setter
	private String description;

	/**
	 * Project's total contracted hours.
	 */
	@Column(name = "total_contracted_hours", nullable = true)
	@Getter
	@Setter
	private Long contractedHours;

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

	/**
	 * Project's type.
	 */
	@Column(name = "type", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectType type;

	/**
	 * Project's status.
	 */
	@Column(name = "status", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectStatus status;

	/**
	 * Project's health.
	 */
	@Column(name = "health", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter
	@Setter
	private ProjectHealth health;

	/**
	 * User that created this project.
	 */
	@Column(name = "created_by", nullable = false)
	@Getter
	@Setter
	private UUID createdBy;

	/**
	 * Timestamp of project creation.
	 */
	@CreationTimestamp
	@Column(name = "created_date", nullable = false)
	@Getter
	private Date createdDate;

	/**
	 * User that modified this project.
	 */
	@Column(name = "modified_by")
	@Getter
	@Setter
	private UUID modifiedBy;

	/**
	 * Timestamp of project modification.
	 */
	@Column(name = "modified_date")
	@Getter
	@Setter
	private Date modifiedDate;

}
