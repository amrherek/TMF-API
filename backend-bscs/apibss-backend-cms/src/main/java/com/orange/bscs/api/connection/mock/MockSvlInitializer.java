package com.orange.bscs.api.connection.mock;

/**
 * Interface for initializing AbstractSVLObjectFactory when using
 * ConnectionBackendFactoryMock
 * 
 * @author xbbs3851
 *
 */
public interface MockSvlInitializer {

	/**
	 * Calls AbstractSVLObjectFactory init method with the appropriate
	 * SVLObjectFactory
	 */
	void initAbstractSVLObjectFactory();

}
