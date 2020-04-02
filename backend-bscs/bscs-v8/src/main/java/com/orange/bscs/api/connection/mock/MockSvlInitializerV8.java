package com.orange.bscs.api.connection.mock;

import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.factory.SVLObjectFactoryV8;

/**
 * Tool for initializing AbstractSVLObjectFactory when using
 * ConnectionBackendFactoryMock with V8 backend
 * 
 * @author xbbs3851
 *
 */
@Service
public class MockSvlInitializerV8 implements MockSvlInitializer  {

	@Override
	public void initAbstractSVLObjectFactory() {

		AbstractSVLObjectFactory.init(new SVLObjectFactoryV8(0));
	}
	
	
}
