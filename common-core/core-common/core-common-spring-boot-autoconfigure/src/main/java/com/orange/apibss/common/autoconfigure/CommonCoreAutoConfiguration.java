package com.orange.apibss.common.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.apibss.common.audit.XmlAuditLogger;

/**
 * Auto configuration for core common
 * 
 * @author jwck2987
 *
 */
@Configuration
@EnableConfigurationProperties(AuditProperties.class)
@ComponentScan(basePackages = { "com.orange.apibss.common.configuration",
        "com.orange.apibss.common.audit.configuration", "com.orange.apibss.common.ws" })
public class CommonCoreAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "audit.xml", value = "enabled", havingValue = "true", matchIfMissing = false)
    public XmlAuditLogger xmlAuditLogger() {
        return new XmlAuditLogger();
    }

}
