package com.orange.mea.apisi.eligibility.obw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main root class for OBW Eligibility API
 *
 * @author Thibault Duperron
 *
 */
@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
public class EligibilityApplicationOBW {

    public static void main(final String[] args) {
        SpringApplication.run(EligibilityApplicationOBW.class, args);
    }
}
