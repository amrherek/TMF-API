package com.orange.mea.apisi.customerbill.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.apibss.common.utils.InternationalMsisdnTool;

/**
 * Specific configuration for OBW Customer Bill API
 *
 * @author Denis Borscia (deyb6792)
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.customerbill.configuration" })
public class CustomerBillConfigurationOBW {

    @Bean
    public InternationalMsisdnTool createInternationalMsisdnTool() {
        return new InternationalMsisdnTool();
    }

}
