package com.orange.mea.apisi.productordering.obw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main root class for OCM product ordering API
 * 
 * @author xbbs3851
 *
 */
@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
public class ProductOrderingApplicationOBW {

	public static void main(String[] args) {
		SpringApplication.run(ProductOrderingApplicationOBW.class, args);
	}
}
