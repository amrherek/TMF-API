package com.orange.mea.apisi.productcatalog.obw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.orange.mea.apisi.obw.webservice.ZteWebService;

/**
 * Configuration class for OTN Product Catalog
 *
 * @author Thibault Duperron
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.mea.apisi.productcatalog.configuration" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProductCatalogConfigurationOBW {

    @Bean
    public ZteWebService createZteWebService() {
        return new ZteWebService();
    }

}

