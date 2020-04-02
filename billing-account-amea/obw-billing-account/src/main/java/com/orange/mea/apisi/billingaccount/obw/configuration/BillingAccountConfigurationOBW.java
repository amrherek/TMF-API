package com.orange.mea.apisi.billingaccount.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.apibss.common.utils.InternationalMsisdnTool;

/**
 * Configuration class for OBW BillingAccount
 *
 * @author Thibault Duperron
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.billingaccount.configuration" })
public class BillingAccountConfigurationOBW {

    @Bean
    public InternationalMsisdnTool createInternationalMsisdnTool() {
        return new InternationalMsisdnTool();
    }

}

