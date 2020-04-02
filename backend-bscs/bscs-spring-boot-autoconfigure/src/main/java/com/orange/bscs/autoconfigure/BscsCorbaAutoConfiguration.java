package com.orange.bscs.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * BSCS prod configuration : using corba backend instead of mock or file
 * backends
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.orange.bscs.api.connection.corba" })
@ConditionalOnProperty(prefix = "bscs", value = "provider", havingValue = "corba", matchIfMissing = true)
public class BscsCorbaAutoConfiguration {

    @Autowired
    @Qualifier("corbaBackendFactory")
    private IConnectionBackendFactory backendFactory;

    @Bean
    public IConnectionBackendFactory backendFactory() {
        return backendFactory;
    }

}
