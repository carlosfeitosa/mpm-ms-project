package com.skull.project.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skull.project.dto.ProjectDto;
import com.skull.project.model.Project;

import lombok.extern.slf4j.Slf4j;

/**
 * Bi-directional project converter (dto <--> entity).
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-25
 *
 */
@Component
@Slf4j
public class ProjectConverter { // NOPMD by skull on 8/8/20, 6:56 PM

	/**
	 * Model mapper for entity <--> dto convertion.
	 */
	@Autowired
	private ModelMapper modelMapper; // NOPMD by skull on 8/8/20, 6:57 PM

	/**
	 * Convert entity to dto.
	 * 
	 * @param entity entity
	 * 
	 * @return dto
	 */
	public ProjectDto convertFromEntity(final Project entity) {

		log.info("Converting to dto");

		return modelMapper.map(entity, ProjectDto.class);
	}

	/**
	 * Convert dto to entity.
	 * 
	 * @param projectDto dto
	 * 
	 * @return entity
	 */
	public Project convertFromDto(final ProjectDto projectDto) {

		log.info("Converting to entity");

		return modelMapper.map(projectDto, Project.class);
	}

	/**
	 * Convert dto to entity.
	 * 
	 * @param dto             project source
	 * @param entityForUpdate project destination
	 * 
	 * @return entity
	 */
	public Project convertFromDto(final ProjectDto dto, final Project entityForUpdate) {

		log.info("Converting to entity with project destination");

		modelMapper.map(dto, entityForUpdate);

		return entityForUpdate;
	}
}
