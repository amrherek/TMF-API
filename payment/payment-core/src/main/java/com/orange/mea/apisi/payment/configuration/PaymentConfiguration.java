package com.orange.mea.apisi.payment.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for payment
 *
 */
@Configuration
@ComponentScan(basePackages = {
        // "com.orange.mea.apisi.payment.rest",
        "com.orange.mea.apisi.payment.service, com.orange.mea.apisi.payment.backend" })
@PropertySources({ @PropertySource(value = "classpath:payment.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:payment-model.properties") })
public class PaymentConfiguration {

}

