package com.orange.bscs.api.connection.builders.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.connection.IConnectionBackendFactory;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryMock;
import com.orange.bscs.api.connection.builders.FactoryBuilder;
import com.orange.bscs.api.connection.mock.MockSvlInitializer;

/**
 * Builds a ConnectionBackendFactoryMock using provided properties
 * 
 * @author xbbs3851
 *
 */
@Service
@Qualifier("mockFactoryBuilder")
public class MockFactoryBuilder implements FactoryBuilder {

	private static final Logger logger = LoggerFactory.getLogger(MockFactoryBuilder.class);

	@Autowired
	private MockSvlInitializer mockSvlInitalizer;

	@Override
	public IConnectionBackendFactory build(IConnectionBackendFactory source) {

		logger.info("Building ConnectionBackendFactoryMock for bscs version [{}]", mockSvlInitalizer.getClass());
		mockSvlInitalizer.initAbstractSVLObjectFactory();

		return new ConnectionBackendFactoryMock();

	}

}
