package com.orange.bscs.api.connection;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class ConnectionHolderTest {

	/**
	 * ConnectionHolder may be initialised by other tests
	 */
	@BeforeClass
	public static void resetConnectionHolder(){
		ConnectionHolder.unset();
	}
	
	@Test
	public void currentConnectionTest(){
		
		Assert.assertNull(ConnectionHolder.getCurrentConnection());

		ConnectionHolder.setConnection(new SOIConnectionMock());
		
		Assert.assertNotNull(ConnectionHolder.getCurrentConnection());
		
	}
	
	@Test
	public void unsetTest(){
	
		ConnectionHolder.setConnection(new SOIConnectionMock());
		Assert.assertNotNull(ConnectionHolder.getCurrentConnection());

		ConnectionHolder.unset();
		Assert.assertNull(ConnectionHolder.getCurrentConnection());

		ConnectionHolder.setConnection(new SOIConnectionMock());
		ConnectionHolder.setConnection(null);
		Assert.assertNull(ConnectionHolder.getCurrentConnection());
	}
	
	
	@Test
	public void singletonMode(){
	    ConnectionHolder.setSingleton();

	    ConnectionHolder.setConnection(new SOIConnectionMock());
        Assert.assertNotNull(ConnectionHolder.getCurrentConnection());

        ConnectionHolder.unset();
        Assert.assertNull(ConnectionHolder.getCurrentConnection());
	    
	}
	
}
