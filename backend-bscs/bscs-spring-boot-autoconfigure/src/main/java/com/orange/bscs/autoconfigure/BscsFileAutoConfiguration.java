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
 * BSCS file mock configuration. Use it to activate file and mock backends
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.bscs.api.connection.builders.mock, com.orange.bscs.api.connection.builders.file, com.orange.bscs.api.connection.mock" })
@ConditionalOnProperty(prefix = "bscs", value = "provider", havingValue = "file", matchIfMissing = false)
public class BscsFileAutoConfiguration {

    @Autowired
    @Qualifier("mockFactoryBuilder")
    private FactoryBuilder mockFactoryBuilder;

    @Autowired
    @Qualifier("fileFactoryBuilder")
    private FactoryBuilder fileFactoryBuilder;

    /**
     * Builds a ConnectionBackendFactoryFile decorating a mockBackendFactory
     * 
     * @return
     */
    @Bean
    public IConnectionBackendFactory backendFactory() {

        // mockbackendFactory, does not decorate anything
        IConnectionBackendFactory mockBackendFactory = mockFactoryBuilder.build(null);

        return fileFactoryBuilder.build(mockBackendFactory);
    }

}

