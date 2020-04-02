package com.orange.bscs.api.connection.builders.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryFile;
import com.orange.bscs.api.connection.builders.FactoryBuilder;

/**
 * Builds a ConnectionBackendFactoryFile using provided properties
 * 
 * @author xbbs3851
 *
 */
@Service
@Qualifier("fileFactoryBuilder")
public class FileFactoryBuilder implements FactoryBuilder {

	private static final Logger logger = LoggerFactory.getLogger(FileFactoryBuilder.class);

	@Value("${fileBackendFactory.racine:/backend/bscs/mock}")
	private String racine;

	@Value("${fileBackendFactory.mode:ReadOnly}")
	private String mode;



	@Override
	public IConnectionBackendFactory build(IConnectionBackendFactory source) {

		logger.info("Building ConnectionBackendFactoryFile");

		ConnectionBackendFactoryFile factory = new ConnectionBackendFactoryFile(source, mode, racine);

		return factory;
	}


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
