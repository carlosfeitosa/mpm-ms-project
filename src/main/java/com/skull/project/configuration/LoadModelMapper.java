package com.skull.project.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Loads model mapper for entity <-> dto convertion.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-05
 *
 */
@Configuration
@Slf4j
public class LoadModelMapper { // NOPMD by skull on 8/8/20, 7:06 PM

	/**
	 * Configuration for model mapper for entity <--> dto convetion.
	 * 
	 * @return model matter object
	 */
	@Bean
	public ModelMapper modelMapper() {

		log.info("Loading model mapper...");

		return new ModelMapper();
	}
}
