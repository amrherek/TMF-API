package com.orange.bscs.autoconfigure;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.builders.file.FileFactoryBuilder;

/**
 * BSCS rec configuration. Use it to activate recorder backend: commands are
 * executed on a real corba env and recorded in a file. Directory is configured
 * in fileBackendFactory.racine.
 * 
 * Warning: the records made for BSCS v8 are not valid
 * 
 * @author xbbs3851
 *
 */
@Configuration
@ComponentScan(basePackages = {
        "com.orange.bscs.api.connection.builders.file, com.orange.bscs.api.connection.corba" })
@ConditionalOnProperty(prefix = "bscs", value = "provider", havingValue = "rec", matchIfMissing = false)
public class BscsRecAutoConfiguration {

    @Autowired
    @Qualifier("corbaBackendFactory")
    private IConnectionBackendFactory corbaFactory;

    @Autowired
    @Qualifier("fileFactoryBuilder")
    private FileFactoryBuilder fileFactoryBuilder;

    public BscsRecAutoConfiguration() {
        // locale is fixed because it is used in serialization for V8 money
        Locale.setDefault(new Locale("en", "US"));
    }

    /**
     * Builds a ConnectionBackendFactoryRec decorating a fileBackendFactory
     * decorating a mockBackendFactory
     * 
     * @return
     */
    @Bean
    public IConnectionBackendFactory backendFactory() {
        // write results to file
        fileFactoryBuilder.setMode("write");
        // add corba factory
        return fileFactoryBuilder.build(corbaFactory);
    }

}
