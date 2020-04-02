package com.orange.mea.apisi.customerbill.obw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
@EnableEurekaClient
@EnableSwagger2
public class CustomerBillApplicationOBW extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CustomerBillApplicationOBW.class, args);
    }
}
