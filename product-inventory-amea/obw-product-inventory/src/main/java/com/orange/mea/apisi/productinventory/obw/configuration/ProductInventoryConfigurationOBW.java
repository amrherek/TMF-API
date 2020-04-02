package com.orange.mea.apisi.productinventory.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

/**
 * Configuration class for OBW Product inventory
 * 
 * @author Thibault Duperron
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.productinventory.configuration" })
public class ProductInventoryConfigurationOBW {

    @Bean
    public InternationalMsisdnTool createInternationalMsisdnTool() {
        return new InternationalMsisdnTool();
    }

    @Bean
    public ZteWebService createZteWebService() {
        return new ZteWebService();
    }

}
