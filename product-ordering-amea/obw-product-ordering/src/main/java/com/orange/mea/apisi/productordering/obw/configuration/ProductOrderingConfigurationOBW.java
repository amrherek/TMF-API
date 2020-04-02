package com.orange.mea.apisi.productordering.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.mea.apisi.obw.webservice.EmergencyWebService;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

/**
 * Configuration class for OBW Product Ordering
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.productordering.configuration" })
public class ProductOrderingConfigurationOBW {

    @Bean
    public ZteWebService createZteWebService() {
        return new ZteWebService();
    }

    @Bean
    public EmergencyWebService createEmergencyWebService() {
        return new EmergencyWebService();
    }

}

