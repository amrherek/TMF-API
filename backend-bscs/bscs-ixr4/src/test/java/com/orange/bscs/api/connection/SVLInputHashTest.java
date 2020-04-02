package com.orange.bscs.api.connection;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class SVLInputHashTest {

	@BeforeClass
	public static void initExchangeFactory(){
		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}

	@Test 
	public void unixToDos(){
		String unix="AZERTY\nQWERTY\n";
		String expected = unix.replace("\n", "\r\n");
		
		Assert.assertEquals(expected, new String(SVLInputHash.unixToDos(unix)));
	}
	
	/*
	 *  MD5 known on Windows plateform.
	 *  Should by the same on Unix plateform
	 */
	@Test
	public void computeMD5(){

		
		final String expected = "1dR3ijDJM-PBdnfalKQ-aA__";
			
		SVLObjectWrapper input = AbstractSVLObjectFactory.createSVLObject();
		input.setValue("CO_ID_PUB", "CONTR0000000101");
		
		Assert.assertEquals(expected, SVLInputHash.encodeInput(input));

		Assert.assertEquals(expected, SVLInputHash.computeMD5(SVLInputHash.unixToDos(input.toString())));
		
	}

}
