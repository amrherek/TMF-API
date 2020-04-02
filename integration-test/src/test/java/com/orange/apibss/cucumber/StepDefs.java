package com.orange.apibss.cucumber;

import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpStatusCodeException;

import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.cucumber.tools.Mappers;

import java.io.IOException;

/**
 * @author Thibault Duperron
 */
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
public class StepDefs {

    protected static final Logger log = LoggerFactory.getLogger(StepDefs.class);

    @Autowired
    protected SharedData sharedData;

	@Autowired
	protected Mappers mappers;

    /**
	 * Generic error checker
	 * 
	 * @param status
	 *            http status of the error
	 * @param code
	 *            fonctionnal error code
	 * @param message
	 *            error message
	 * @param descriptionPattern
	 *            pattern for error description
	 * @throws Throwable
	 *             Throwable
	 */
	protected void checkError(HttpStatus status, Integer code, String message, String descriptionPattern)
			throws Throwable {
        HttpStatusCodeException exception = sharedData.getException();
        assertThat(exception).isNotNull();
		ApiError error = mappers.parseError(exception);
		log.info("Error : " + error);
		assertThat(exception.getStatusCode()).isEqualTo(status);
        assertThat(error.getCode()).isEqualTo(code);
		assertThat(error.getMessage()).isEqualTo(message);
		assertThat(error.getDescription()).containsPattern(descriptionPattern);
    }

	/**
	 * Check there's no exception
	 *
	 * Log it if an exception is present
	 */
	protected void checkNoException() throws IOException {
		HttpStatusCodeException exception = sharedData.getException();
		if(null != exception){
			// Log error
			ApiError error = mappers.parseError(exception);
			log.info("Error : " + error);
		}
		assertThat(exception).isNull();
	}
}
