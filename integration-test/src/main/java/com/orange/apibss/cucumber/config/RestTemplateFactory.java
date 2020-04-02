package com.orange.apibss.cucumber.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.cucumber.tools.LoggingRequestInterceptor;

/**
 * Rest template factory for using logging interceptor and spring object mapper
 * @author Thibault Duperron
 */
@Configuration
public class RestTemplateFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${http.proxy.host:}")
    private String proxyHost;

    @Value("${http.proxy.port:}")
    private String proxyPort;

    @Bean
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate;
        // Managing an http proxy
        if (!this.proxyHost.isEmpty()) {
            this.logger.debug("Using proxy {}:{}", this.proxyHost, this.proxyPort);

            final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

            final Proxy proxy = new Proxy(Type.HTTP,
                    new InetSocketAddress(this.proxyHost, Integer.parseInt(this.proxyPort)));
            requestFactory.setProxy(proxy);

            restTemplate = new RestTemplate(requestFactory);
        } else {
            restTemplate = new RestTemplate();
        }
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();

        jsonMessageConverter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);

        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());

        return restTemplate;
    }
}
