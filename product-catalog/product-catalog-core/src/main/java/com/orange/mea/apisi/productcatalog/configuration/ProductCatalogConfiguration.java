package com.orange.mea.apisi.productcatalog.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for Bucket Balance
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.productcatalog.rest", "com.orange.mea.apisi.productcatalog.service",
        "com.orange.mea.apisi.productcatalog.backend" })
@PropertySources({ @PropertySource(value = "classpath:product-catalog.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:product-catalog-model.properties") })
public class ProductCatalogConfiguration {

}

