package com.orange.bscs.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.builders.FactoryBuilder;

/**
 * BSCS configuration for test : mock backend
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.bscs.api.connection.builders.mock, com.orange.bscs.api.connection.mock" })
@ConditionalOnProperty(prefix = "bscs", value = "provider", havingValue = "mock", matchIfMissing = false)
public class BscsMockAutoConfiguration {

    @Autowired
    @Qualifier("mockFactoryBuilder")
    private FactoryBuilder mockFactoryBuilder;


    /**
     * Builds a mockBackendFactory
     * 
     * @return
     */
    @Bean
    public IConnectionBackendFactory backendFactory() {

        // mockbackendFactory, does not decorate anything
        return mockFactoryBuilder.build(null);
    }

}
