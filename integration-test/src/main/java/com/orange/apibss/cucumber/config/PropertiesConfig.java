package com.orange.apibss.cucumber.config;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * Created by czfd3494 on 21/11/2016.
 */
@Configuration
public class PropertiesConfig {
    @Bean
    public static ConversionService  conversionService() {
        DefaultFormattingConversionService cs = new DefaultFormattingConversionService();
        cs.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                return LocalDate.parse(source);
            }
        });
        cs.addConverter(new Converter<String, DateTime>() {
            @Override
            public DateTime convert(String source) {
                return DateTime.parse(source);
            }
        });
        return cs;
    }

}