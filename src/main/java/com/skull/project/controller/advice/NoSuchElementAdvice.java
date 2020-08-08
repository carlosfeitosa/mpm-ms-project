package com.skull.project.controller.advice;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Advice for controller NoSuchElementException.
 * 
 * @author Carlos Feitosa (carlos.feitosa.nt@gmail.com)
 * @since 2020-07-18
 *
 */
@ControllerAdvice
public class NoSuchElementAdvice { // NOPMD by skull on 8/8/20, 7:02 PM

	/**
	 * Set 404 if NoSuchElementException was raised.
	 * 
	 * @param exception generated exception
	 * 
	 * @return exception message
	 */
	@ResponseBody
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String noSuchElementHandler(final NoSuchElementException exception) {

		return exception.getMessage();
	}
}
