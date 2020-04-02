package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.orange.bscs.api.connection.IConnectionBackendFactory;

import java.security.NoSuchAlgorithmException;

/**
 * Test that this factory really implements IConnectionBackendFactory and IConnectionBackend
 * but can't test LOG results. 
 * 
 * @author IT&Labs
 *
 */
public class ConnectionBackendFactoryLogTest {

	@BeforeClass
	public static void initExchange() throws NoSuchAlgorithmException {
		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}

	/** compile time check default constructor */
	@Test
	public void defaultConstructor(){
		Assert.assertNotNull(new ConnectionBackendFactoryLog());
	}
	
	/**
	 *  compile time check constructor with source factory
	 */
	@Test
	public void constructorWithSrcFactory(){
		IConnectionBackendFactory srcFactory = new ConnectionBackendFactoryMock();
		Assert.assertNotNull(new ConnectionBackendFactoryLog(srcFactory));
	}
	
	
	@Test
	public void checkDelegates(){
		
		ConnectionBackendFactoryMock mockFactory = new ConnectionBackendFactoryMock();
		ConnectionBackendFactoryLog factory = new ConnectionBackendFactoryLog(mockFactory);

		Assert.assertNotSame("should be different because of source",factory, factory.newInstance());

		ConnectionBackendTestMethodDelegation.callBackendMethods(factory);
		
	}
	
}
