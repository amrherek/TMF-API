package com.orange.mea.apisi.party.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class for Party
 *
 * @author xbbs3851
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.mea.apisi.party.rest, com.orange.mea.apisi.party.service, com.orange.mea.apisi.party.backend"})
@PropertySources({@PropertySource(value = "classpath:party.properties", ignoreResourceNotFound = true),
        @PropertySource("classpath:party-model.properties")})
public class PartyConfiguration {

}

