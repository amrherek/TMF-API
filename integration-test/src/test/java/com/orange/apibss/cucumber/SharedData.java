package com.orange.apibss.cucumber;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class SharedData {

    /**
     * Exception when calling a service
     */
    private HttpStatusCodeException exception;
    /**
     * Arguments for calling a service
     */
    private Map<Arguments, String> arguments = new HashMap<>();
}
