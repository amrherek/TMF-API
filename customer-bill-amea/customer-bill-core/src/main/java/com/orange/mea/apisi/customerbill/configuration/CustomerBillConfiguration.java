package com.orange.mea.apisi.customerbill.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Common configuration for this API
 *
 * @author Denis Borscia (deyb6792)
 * TODO : Supprimer scan Transformer
 */
@Configuration
@ComponentScan({"com.orange.mea.apisi.customerbill.rest",
        "com.orange.mea.apisi.customerbill.service",
        "com.orange.mea.apisi.customerbill.transformer" })
@PropertySources({ @PropertySource(value = "classpath:customer-bill.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:customer-bill-model.properties", ignoreResourceNotFound = true) })
public class CustomerBillConfiguration {

}