package com.skull.project.model;

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
 * Project's client information model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-08
 *
 */
@NoArgsConstructor
@ToString
@Entity(name = "project_client_information")
public class ProjectClientInformation extends AbstractEntityModel {

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
	@OneToOne(mappedBy = "clientInformation")
	private Project project;

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

}
