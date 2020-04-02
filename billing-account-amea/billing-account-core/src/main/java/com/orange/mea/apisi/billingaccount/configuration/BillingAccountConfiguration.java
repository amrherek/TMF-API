package com.orange.mea.apisi.billingaccount.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for OTN BillingAccount
 * 
 * @author ecus6396
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.billingaccount.rest, com.orange.mea.apisi.billingaccount.service, com.orange.mea.apisi.billingaccount.backend" })
@PropertySources({ @PropertySource(value = "classpath:billingAccount.properties", ignoreResourceNotFound = true),
		@PropertySource("classpath:billingAccount-model.properties") })
public class BillingAccountConfiguration {

}

