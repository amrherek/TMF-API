package com.orange.bscs.autoconfigure;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.builders.FactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * BSCS file mock configuration. Use it to activate file and mock backends
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.orange.bscs.api.connection.builders.mock",
        "com.orange.bscs.api.connection.mock",
        "com.orange.bscs.api.connection.builders.remote" })
@ConditionalOnProperty(prefix = "bscs", value = "provider", havingValue = "remote", matchIfMissing = false)
public class BscsRemoteAutoConfiguration {

    @Autowired
    @Qualifier("remoteFactoryBuilder")
    private FactoryBuilder remoteFactoryBuilder;

    @Autowired
    @Qualifier("mockFactoryBuilder")
    private FactoryBuilder mockFactoryBuilder; // to init AbstractSVLFactory

    /**
     * Builds a ConnectionBackendFactoryFile decorating a mockBackendFactory
     * 
     * @return
     */
    @Bean
    public IConnectionBackendFactory backendFactory() {
        mockFactoryBuilder.build(null);
        return remoteFactoryBuilder.build(null);
    }

}

