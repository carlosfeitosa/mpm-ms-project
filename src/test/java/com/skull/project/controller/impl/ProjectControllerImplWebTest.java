package com.skull.project.controller.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProjectControllerImplWebTest {

	@Value("${service.request.mapping}")
	private String requestMapping;

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String endpoint;

	@BeforeEach
	void setupTest() {
		endpoint = String.format("http://localhost:%d/%s/%s", port, contextPath, requestMapping);
	}

	@Test
	@DisplayName("Test if can get 200 response for default endpoint")
	void greetingShouldReturnDefaultMessage() throws Exception {

		ResponseEntity<String> response = restTemplate.getForEntity(endpoint, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
