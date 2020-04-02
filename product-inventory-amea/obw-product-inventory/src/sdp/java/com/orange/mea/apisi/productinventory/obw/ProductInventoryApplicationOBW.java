package com.orange.mea.apisi.productinventory.obw;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Springboot laucher for OBW
 * 
 * @author Thibault Duperron
 *
 */
@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
public class ProductInventoryApplicationOBW {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ProductInventoryApplicationOBW.class, args);
    }
}