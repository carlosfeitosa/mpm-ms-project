package com.skull.project.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skull.project.model.Project;

/**
 * Project repository.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-06-27
 *
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

}
