package com.orange.mea.apisi.billingaccount.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Springboot wrapper for external tomcat
 * 
 * @author Thibault Duperron
 *
 */
@SpringBootApplication
public class BillingAccountApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(BillingAccountApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
        return application.sources(BillingAccountApplicationWebXmlOBW.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BillingAccountApplicationWebXmlOBW.class, args);
    }

}
