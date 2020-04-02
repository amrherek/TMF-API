package com.orange.bscs.api.connection.mock;

import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

/**
 * Tool for initializing AbstractSVLObjectFactory when using
 * ConnectionBackendFactoryMock with IXR4 backend
 * 
 * @author xbbs3851
 *
 */
@Service
public class MockSvlInitializerIxr4 implements MockSvlInitializer  {

	@Override
	public void initAbstractSVLObjectFactory() {

		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}
	
	
}
