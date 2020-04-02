package com.orange.bscs.api.model.exception;

import org.junit.Assert;

import org.junit.Test;

import com.orange.bscs.api.exceptions.APIException;

public class APIExceptionTest {

	@Test(expected=APIException.class)
	public void constructorExTest(){
		throw new APIException(new RuntimeException("runtimeException"),"CODE","LABEL");
	}
	
	@Test
	public void accessorsTest(){
		APIException ex = new APIException(new RuntimeException("runtimeException"),"CODE","LABEL");
		Assert.assertEquals("CODE", ex.getFaultCode());
		Assert.assertEquals("LABEL", ex.getFaultLabel());
		Assert.assertEquals(RuntimeException.class,ex.getFault().getClass());
	}

}
