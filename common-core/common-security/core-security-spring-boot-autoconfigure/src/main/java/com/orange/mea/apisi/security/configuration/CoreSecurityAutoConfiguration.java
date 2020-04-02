package com.orange.mea.apisi.security.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.orange.mea.apisi.security.service.ApiAuthenticationExceptionHandler;
import com.orange.mea.apisi.security.service.ApiAuthenticationFilter;
import com.orange.mea.apisi.security.service.ApiUserDetailsService;
import com.orange.mea.apisi.security.service.OriginSenderService;

/**
 * Auto configuration for common security
 *
 * @author Thibault Duperron
 *
 */
@Configuration
@EnableConfigurationProperties(CommonSecurityProperties.class)
public class CoreSecurityAutoConfiguration {

    /**
     * Database configuration
     *
     * @author Thibault Duperron
     *
     */
    @Configuration
    @ConditionalOnProperty(prefix = "common.security", value = "useH2", havingValue = "true", matchIfMissing = false)
    public class DataSourceConfiguration {

        @Bean(destroyMethod = "shutdown")
        @ConditionalOnMissingBean
        public EmbeddedDatabase dataSource() {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                    .addScripts("schema-h2.sql", "data-h2.sql").build();
        }
    }

    /**
     * Date : 13/12/2016.
     *
     * @author Denis Borscia (deyb6792)
     */
    @Configuration
    public class GlobalApiConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            // To allow h2 console in dev mode
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }
    }

    /**
     * Date : 13/12/2016.
     *
     * @author Denis Borscia (deyb6792)
     */
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(jsr250Enabled = true)
    @Configuration
    @ConditionalOnProperty(prefix = "common.security", value = "disabled", havingValue = "false", matchIfMissing = true)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @EnableJpaRepositories(basePackages = { "com.orange.mea.apisi.security.repository" })
    @EntityScan(basePackages = { "com.orange.mea.apisi.security.model" })
    public class SecuredApiConfiguration extends GlobalApiConfiguration {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            super.configure(http);
            http.userDetailsService(userDetailsService());
            http.addFilterAfter(authentFilter(), BasicAuthenticationFilter.class).exceptionHandling()
            .authenticationEntryPoint(apiAuthenticationExceptionHandler())
            .accessDeniedHandler(apiAuthenticationExceptionHandler());
        }

        @Bean
        public ApiAuthenticationFilter authentFilter() throws Exception {
            return new ApiAuthenticationFilter();
        }

        @Bean
        public ApiAuthenticationExceptionHandler apiAuthenticationExceptionHandler() {
            return new ApiAuthenticationExceptionHandler();
        }

        @Override
        @Bean
        public UserDetailsService userDetailsService() {
            return new ApiUserDetailsService();
        }

        @Bean
        public OriginSenderService originSenderService() {
            return new OriginSenderService();
        }

    }
}
