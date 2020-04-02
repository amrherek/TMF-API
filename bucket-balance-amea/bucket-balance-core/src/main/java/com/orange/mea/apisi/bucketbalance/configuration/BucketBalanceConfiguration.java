package com.orange.mea.apisi.bucketbalance.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for Bucket Balance
 *
 * @author xbbs3851
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.bucketbalance.rest, com.orange.mea.apisi.bucketbalance.service, com.orange.mea.apisi.bucketbalance.transformer"})
@PropertySources({@PropertySource(value = "classpath:bucket-balance.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:bucket-balance-model.properties")})
public class BucketBalanceConfiguration {

}

