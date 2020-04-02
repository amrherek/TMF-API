package com.orange.bscs.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.orange.bscs.api.connection.ISOIConnectionProvider;
import com.orange.bscs.api.connection.SOIConnectionPool;
import com.orange.bscs.api.connection.SOITransactionsManager;
import com.orange.bscs.api.connection.factory.SOIConnectionFactory;

/**
 * Auto configuration for bscs. Basic configuration (a provider must also be
 * set)
 *
 * @author Stéphanie Gerbaud
 *
 */
@Configuration
@EnableConfigurationProperties({ BscsProperties.class, BscsPoolProperties.class,
        BscsStatefullConnectionProperties.class })
@ComponentScan(basePackages = { "com.orange.bscs.api.connection.factory, " // apibss-backend-cms
        + "com.orange.bscs.api.connection.service, " // apibss-backend-cms
        + "com.orange.bscs.api.audit, " // apibss-backend-cms
        + "com.orange.bscs.api.aop, " // apibss-backend-cms
        + "com.orange.bscs.cms.servicelayeradapter, " // apibss-adapter-cms
        + "com.orange.bscs.service, " // apibss-adapter-cms
        + "com.orange.bscs.mysoi, " // apibss-adapter-cms
        + "com.orange.bscs.model.factory"// bscs-XX
})
@PropertySources({ @PropertySource(value = "classpath:bscs.properties", ignoreResourceNotFound = true) })
public class BscsAutoConfiguration {

    @Autowired
    private BscsPoolProperties poolProperties;

    @Autowired
    private BscsProperties bscsProperties;

    @Autowired
    private BscsStatefullConnectionProperties statefullConnectionProperties;

    /**
     * Simple connection factory
     */
    @Autowired
    @Qualifier("soiConnectionFactory")
    private SOIConnectionFactory soiConnectionFactory;

    /**
     * Connection provider used by the TransactionManager
     * 
     * @return
     */
    @Bean
    public ISOIConnectionProvider connectionProvider() {
        if (bscsProperties.getUseConnectionPool()) {
            return new SOIConnectionPool(soiConnectionFactory, poolProperties.getTimeout(),
                    poolProperties.getTimeBetweenEvictionThreadRuns(), poolProperties.getMaxWait(),
                    poolProperties.getSize(), poolProperties.getInitialSize());
        }

        return soiConnectionFactory;
    }

    /**
     * SOITransactionsManager
     * 
     * @return SOITransactionsManager
     */
    @Bean
    public SOITransactionsManager soiTransactionsManager(ISOIConnectionProvider connectionProvider) {

        SOITransactionsManager soiTransactionsManager = new SOITransactionsManager(connectionProvider,
                statefullConnectionProperties.getTimeout(), statefullConnectionProperties.getControl().getDelay(),
                statefullConnectionProperties.getControl().getPeriod());

        return soiTransactionsManager;
    }
}
