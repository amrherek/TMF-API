package com.orange.apibss.common.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.orange.apibss.common.configuration.impl.APIMessageResolverDefault;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Value("${defaultLocale:en_US}")
    private Locale defaultLocale;

    @Value("${defaultEncoding:UTF-8}")
    private String defaultEncoding;

    @Value("${api.error.file:}")
    private String[] apiErrorFiles;

    @Value("${debug.payload.size:1000}")
    private int debugPayloadSize;

    @Bean
    public APIMessageResolverDefault apiMessageResolver() {
        APIMessageResolverDefault apiMessageResolver = new APIMessageResolverDefault();
        apiMessageResolver.setDefaultLocale(defaultLocale);
        apiMessageResolver.setMessageSource(i18nMessageSource());
        return apiMessageResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource i18nMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(defaultEncoding);
        // to prevent from using properties with default system locale
        messageSource.setFallbackToSystemLocale(false);
        messageSource.addBasenames(apiErrorFiles);
        messageSource.addBasenames("classpath:/errors/messages/errors-internal");
        return messageSource;
    }

    /**
     * Used to enable request logging
     * 
     * @return
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
        crlf.setIncludeClientInfo(true);
        crlf.setIncludeQueryString(true);
        crlf.setIncludePayload(true);
        crlf.setMaxPayloadLength(debugPayloadSize);
        crlf.setIncludeHeaders(true);
        return crlf;
    }

}
