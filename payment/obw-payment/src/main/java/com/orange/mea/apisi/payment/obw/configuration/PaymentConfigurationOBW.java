package com.orange.mea.apisi.payment.obw.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OBW Payment
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.payment.configuration",
        "com.orange.mea.apisi.payment.rest" }) // standard controller
public class PaymentConfigurationOBW {

}

