package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import org.junit.Assert;

import com.orange.bscs.api.connection.IConnectionBackend;
import com.orange.bscs.api.connection.IConnectionBackendFactory;

/**
 * Test methods on Backend (commit/rollback, execute, close, etc..)
 * 
 * @author itlabs
 *
 */
public  final class ConnectionBackendTestMethodDelegation {

	private ConnectionBackendTestMethodDelegation(){
	    // Hide default constructor in utility class
	}
	
	public static void callBackendMethods(IConnectionBackendFactory factory){
		callBackendMethods(factory.newInstance());
	}
	
    public static void callBackendMethods(IConnectionBackend backend){
		
		SVLObjectWrapper svlObj = backend.createSVLObject();
		Assert.assertNotNull("CreateSVLObject",svlObj);
		
		SVLObjectListWrapper svlList = backend.createSVLObjectList();
		Assert.assertNotNull("CreateSVLObjectList",svlList);
		
		backend.commit();
		backend.rollback();

		Assert.assertTrue("Should be alive",backend.isAlive());
		
		backend.close();
		
		Assert.assertFalse("Should be closed",backend.isAlive());
	
		backend.reopen();
		
		Assert.assertTrue("Should be re-open",backend.isAlive());
		
	}
	
	
}
