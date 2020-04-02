package com.orange.mea.apisi.productordering.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for ProductOrdering
 * 
 * @author xbbs3851
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.productordering.rest, com.orange.mea.apisi.productordering.service, com.orange.mea.apisi.productordering.transformer, com.orange.mea.apisi.productordering.validator, com.orange.mea.apisi.productordering.orchestrator, com.orange.mea.apisi.productordering.backend" })
@PropertySources({ @PropertySource(value = "classpath:product-ordering.properties", ignoreResourceNotFound = true),
		@PropertySource("classpath:product-ordering-model.properties") })
public class ProductOrderingConfiguration {

}

