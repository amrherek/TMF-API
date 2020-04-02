package com.orange.mea.apisi.bucketbalance.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Launcher for app deployed on tomcat. {@link SpringBootServletInitializer}
 */
@SpringBootApplication
public class BucketBalanceApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(BucketBalanceApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
        return application.sources(BucketBalanceApplicationWebXmlOBW.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BucketBalanceApplicationWebXmlOBW.class, args);
    }
}


