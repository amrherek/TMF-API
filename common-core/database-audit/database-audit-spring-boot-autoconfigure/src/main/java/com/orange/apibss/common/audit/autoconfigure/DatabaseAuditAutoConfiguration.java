package com.orange.apibss.common.audit.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.orange.apibss.common.audit.DataBaseAuditLogger;

/**
 * Auto configuration for database audit
 * 
 * @author jwck2987
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "audit.database", value = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(DatabaseAuditProperties.class)
@EnableJpaRepositories(basePackages = { "com.orange.apibss.common.audit.dao" })
@EntityScan(basePackages = { "com.orange.apibss.common.audit.dao" })
public class DatabaseAuditAutoConfiguration {

    @Bean
    public DataBaseAuditLogger dataBaseAuditLogger() {
        return new DataBaseAuditLogger();
    }

}
