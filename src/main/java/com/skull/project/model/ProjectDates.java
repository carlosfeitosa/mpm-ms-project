package com.skull.project.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Project's dates model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-08
 *
 */
@NoArgsConstructor
@ToString
@Entity(name = "project_dates")
public class ProjectDates {

	/**
	 * Project dates identifier.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@Getter
	private UUID id; // NOPMD by skull on 8/8/20, 10:11 AM

	/**
	 * Project.
	 */
	@OneToOne(mappedBy = "dates")
	private Project project;

	/**
	 * Project start date.
	 */
	@Column(name = "start_date", nullable = true)
	@Getter
	@Setter
	private Date startDate;

	/**
	 * Project end date.
	 */
	@Column(name = "end_date", nullable = true)
	@Getter
	@Setter
	private Date endDate;

	/**
	 * Project real start.
	 */
	@Column(name = "real_start_date", nullable = true)
	@Getter
	@Setter
	private Date realStartDate;

	/**
	 * Project new end date.
	 */
	@Column(name = "new_end_date", nullable = true)
	@Getter
	@Setter
	private Date newEndDate;
}
