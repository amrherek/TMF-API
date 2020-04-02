package com.orange.mea.apisi.party.obw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PartyApplicationWebXmlOBW extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(PartyApplicationWebXmlOBW.class);

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        log.trace("Starting application from webxml configuration");
		return application.sources(PartyApplicationWebXmlOBW.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PartyApplicationWebXmlOBW.class, args);
    }
}
