package com.orange.mea.apisi.eligibility.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.mea.apisi.obw.webservice.EmergencyWebService;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

/**
 * Configuration class for OTN Eligibility
 *
 * @author Thibault Duperron
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.eligibility.configuration",
        "com.orange.mea.apisi.eligibility.backend.bscs" })
public class EligibilityConfigurationOBW {

    @Bean
    public InternationalMsisdnTool createInternationalMsisdnTool() {
        return new InternationalMsisdnTool();
    }

    @Bean
    public ZteWebService createZteWebService() {
        return new ZteWebService();
    }

    @Bean
    public EmergencyWebService createEmergencyWebService() {
        return new EmergencyWebService();
    }

}

