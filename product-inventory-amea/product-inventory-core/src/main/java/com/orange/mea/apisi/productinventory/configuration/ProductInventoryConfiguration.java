package com.orange.mea.apisi.productinventory.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for Product inventory
 *
 * @author Thibault Duperron
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.productinventory.configuration, " //
        + "com.orange.mea.apisi.productinventory.rest, " //
        + "com.orange.mea.apisi.productinventory.service, " //
        + "com.orange.mea.apisi.productinventory.orchestrator, " //
        + "com.orange.mea.apisi.productinventory.backend.bscs" })
@PropertySources({@PropertySource(value = "classpath:productinventory.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:productinventory-model.properties")})
public class ProductInventoryConfiguration {

}
