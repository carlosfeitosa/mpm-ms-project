package com.skull.project.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Abstract entity model.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-08-08
 *
 */
@MappedSuperclass
@ToString
public abstract class AbstractModel { // NOPMD by skull on 8/8/20, 11:32 AM

	/**
	 * Entity Identifier.
	 */
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@Getter
	private UUID id; // NOPMD by skull on 8/8/20, 10:11 AM

	/**
	 * User that created this entity.
	 */
	@Column(name = "created_by", nullable = false)
	@Getter
	@Setter
	private UUID createdBy;

	/**
	 * Timestamp of entity creation.
	 */
	@CreationTimestamp
	@Column(name = "created_date", nullable = false)
	@Getter
	private Date createdDate;

	/**
	 * User that modified this entity.
	 */
	@Column(name = "modified_by")
	@Getter
	@Setter
	private UUID modifiedBy;

	/**
	 * Timestamp of entity modification.
	 */
	@Column(name = "modified_date")
	@Getter
	@Setter
	private Date modifiedDate;

}
