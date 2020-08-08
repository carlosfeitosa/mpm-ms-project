package com.skull.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

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
public class ProjectDates extends AbstractModel {

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
