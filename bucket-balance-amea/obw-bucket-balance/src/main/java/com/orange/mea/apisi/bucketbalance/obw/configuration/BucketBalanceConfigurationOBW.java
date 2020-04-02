package com.orange.mea.apisi.bucketbalance.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.mea.apisi.obw.webservice.ZteWebService;

/**
 * Configuration class for OBW Bucket Balance
 *
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.bucketbalance.configuration" })
public class BucketBalanceConfigurationOBW {

    @Bean
    public ZteWebService createZteWebService() {
        return new ZteWebService();
    }

}

