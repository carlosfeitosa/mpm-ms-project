package com.skull.project.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

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
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "id")
@Entity(name = "project")
public class Project {

	/**
	 * Project identifier.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	/**
	 * Project name.
	 */
	@NonNull
	@Column(name = "name", length = 200)
	private String name;

	/**
	 * Project start date.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "start_date", nullable = true)
	private Date startDate;

	/**
	 * Project end date.
	 */
	@Temporal(value = TemporalType.DATE)
	@Column(name = "end_date", nullable = true)
	private Date endDate;

	/**
	 * Project client's id.
	 */
	@Column(name = "client_id", nullable = true)
	private UUID clientId;

	/**
	 * Project client's name.
	 */
	@Column(name = "client_name", nullable = true, length = 200)
	private String clientName;

	/**
	 * Project's description.
	 */
	@Column(name = "description", nullable = true, length = 2000)
	private String description;

	@Column(name = "created_by", nullable = false)
	private UUID createdBy;

	@CreationTimestamp
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "modified_by")
	private UUID modifiedBy;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;
}
