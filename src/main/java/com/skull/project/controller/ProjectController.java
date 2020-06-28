package com.skull.project.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skull.project.model.Project;
import com.skull.project.model.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/projects")
@Slf4j
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@GetMapping
	public List<Project> getAllUsers() {
		return projectRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Project> getUserById(@PathVariable(value = "id") UUID projectId) {

		log.info("Getting project by id");
		log.debug(String.format("Project id: %s", String.valueOf(projectId)));

		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new NoSuchElementException("Project not availbele for id:" + projectId));

		return ResponseEntity.ok().body(project);
	}
}
