package com.skull.project.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private UUID id;

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
	 * Project start date.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "start_date", nullable = true)
	@Getter
	@Setter
	private Date startDate;

	/**
	 * Project end date.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "end_date", nullable = true)
	@Getter
	@Setter
	private Date endDate;

	/**
	 * Project real start.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "real_start_date", nullable = true)
	@Getter
	@Setter
	private Date realStartDate;

	/**
	 * Project new end date.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "new_end_date", nullable = true)
	@Getter
	@Setter
	private Date newEndDate;

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
	private Long totalContractedHours;

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

	@Column(name = "created_by", nullable = false)
	@Getter
	@Setter
	private UUID createdBy;

	@CreationTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	@Getter
	private Date createdDate;

	@Column(name = "modified_by")
	@Getter
	@Setter
	private UUID modifiedBy;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	@Getter
	@Setter
	private Date modifiedDate;

}
