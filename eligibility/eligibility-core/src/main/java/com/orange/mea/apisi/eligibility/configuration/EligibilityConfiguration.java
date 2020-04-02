package com.orange.mea.apisi.eligibility.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for eligibility
 *
 * @author Thibault Duperron
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.eligibility.rest, com.orange.mea.apisi.eligibility.service" })
@PropertySources({@PropertySource(value = "classpath:eligibility.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:eligibility-model.properties")})
public class EligibilityConfiguration {

}

