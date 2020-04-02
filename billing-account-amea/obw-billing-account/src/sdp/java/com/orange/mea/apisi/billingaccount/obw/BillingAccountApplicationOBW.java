package com.orange.mea.apisi.billingaccount.obw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot application
 * 
 * @author Thibault Duperron
 *
 */
@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
public class BillingAccountApplicationOBW {

    public static void main(final String[] args) {
        SpringApplication.run(BillingAccountApplicationOBW.class, args);
    }
}
