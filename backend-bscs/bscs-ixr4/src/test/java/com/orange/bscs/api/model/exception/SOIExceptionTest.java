package com.orange.bscs.api.model.exception;

import org.junit.Assert;

import org.junit.Test;

public class SOIExceptionTest {
	
	/**
	 * Compile time test, check that constructors exists
	 */
	@Test
	public void constructorsTest(){
		SOIException soiEx;
		
		soiEx = new SOIException(new RuntimeException("RT"));
		soiEx = new SOIException("SOIException");
		soiEx = new SOIException("SOIException", new RuntimeException("RT"));
		
		Assert.assertNotNull(soiEx);
		Assert.assertEquals("Label", "SOIException",soiEx.getMessage());
		
	}

}
