package com.orange.mea.apisi.payment.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Springboot wrapper for external tomcat
 *
 */
@SpringBootApplication
public class PaymentApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(PaymentApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
        return application.sources(PaymentApplicationWebXmlOBW.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationWebXmlOBW.class, args);
    }

}
