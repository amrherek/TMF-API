package com.orange.mea.apisi.productcatalog.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Launcher for app deployed on tomcat. {@link SpringBootServletInitializer}
 *
 * @author Thibault Duperron
 */
@SpringBootApplication
public class ProductCatalogApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(ProductCatalogApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
        return application.sources(ProductCatalogApplicationWebXmlOBW.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(ProductCatalogApplicationWebXmlOBW.class, args);
    }
}
