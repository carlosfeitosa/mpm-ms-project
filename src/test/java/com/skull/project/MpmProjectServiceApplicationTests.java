package com.skull.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skull.project.controller.ProjectController;

@SpringBootTest
class MpmProjectServiceApplicationTests {

	@Autowired
	private ProjectController controller;

	/**
	 * Test if context loads.
	 */
	@Test
	@DisplayName("Test if context can load")
	void testIfContextLoads() {
		MpmProjectServiceApplication.main(new String[] {});
		
		assertThat(controller).isNotNull();
	}

}
