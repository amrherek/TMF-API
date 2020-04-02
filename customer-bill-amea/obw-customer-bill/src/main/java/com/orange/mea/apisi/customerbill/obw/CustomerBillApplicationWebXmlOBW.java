package com.orange.mea.apisi.customerbill.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Launcher for app deployed on tomcat. {@link SpringBootServletInitializer}
 * 
 */
@SpringBootApplication
public class CustomerBillApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(CustomerBillApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
        return application.sources(CustomerBillApplicationWebXmlOBW.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(CustomerBillApplicationWebXmlOBW.class, args);
    }

}
