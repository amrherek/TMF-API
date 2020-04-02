package com.orange.bscs.api.model.exception;

import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.exceptions.factory.FaultFactory;
import com.orange.bscs.api.exceptions.factory.FaultFactorySimple;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.junit.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lhs.ccb.func.ect.ComponentException;

import java.security.NoSuchAlgorithmException;

public class FaultFactorySimpleTest {

	@BeforeClass
	public static void initExchange() throws NoSuchAlgorithmException {
		AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
	}


	@Test
    public void accessors(){
        FaultFactorySimple simpleFactory = new FaultFactorySimple();
        FaultFactory.setInstance(simpleFactory);
        
        Assert.assertEquals("getFactory instance", simpleFactory, FaultFactory.getInstance());
    }
	
	@Test
	public void newAPIExceptionTest(){
		
		APIException ex = FaultFactory.getInstance().newAPIException(IllegalArgumentException.class, "ERR_2000", null, "CODE");
		
		Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
	}
	
	
	@Test
	public void newInvalidInputExceptionTest(){

		
		APIException ex = FaultFactory.getInstance().newInvalidInputException("ERR_2000",null,"CODE");
		Assert.assertEquals("ERR_2000", ex.getFaultCode());
		Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
		
		
		ex = FaultFactory.getInstance().newInvalidInputException("ERR_2000");
	}
	
	@Test
	public void newInconsistentStatusExceptionTest(){
        APIException ex = FaultFactory.getInstance().newInconsistentStatusException("ERR_2000");
        Assert.assertEquals("ERR_2000", ex.getFaultCode());

        ex = FaultFactory.getInstance().newInconsistentStatusException("ERR_2000",null,"CODE");
		Assert.assertEquals("ERR_2000", ex.getFaultCode());
		Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
	}
	
	@Test
	public void newGenericAPIExceptionTest(){
		APIException ex;
		
		// Use bundle to retrieve Label
		ex= FaultFactory.getInstance().newAPIException(ComponentException.class, "ERR_2000",null,"Error");
		Assert.assertEquals("ERR_2000", ex.getFaultCode());
		
		ex= FaultFactory.getInstance().newAPIException(ComponentException.class, "ERR","Error", new RuntimeException("RT"));
		Assert.assertEquals("ERR", ex.getFaultCode());
		Assert.assertEquals("Error", ex.getFaultLabel());
		

	}
	
	@Test
	public void newAPIFromComponentExceptionTest(){
		ComponentException ce = new ComponentException("ERR");
		APIException ex = FaultFactory.getInstance().newAPIException(ce);
		Assert.assertNotNull(ex);
	}
	
	@Test
	public void newMissingConfigurationException(){
        APIException ex = FaultFactory.getInstance().newMissingConfigurationException("ERR_2000",null,"CODE");
        Assert.assertEquals("ERR_2000", ex.getFaultCode());
        Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
	}

    @Test
    public void newInconsistenInputException(){
        APIException ex = FaultFactory.getInstance().newInconsistentInputParametersException("ERR_2000",null,"CODE");
        Assert.assertEquals("ERR_2000", ex.getFaultCode());
        Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
    }
	
    @Test
    public void newInconsistenDatabaseException(){
        APIException ex = FaultFactory.getInstance().newInconsistentDatabaseException("ERR_2000",null,"CODE");
        Assert.assertEquals("ERR_2000", ex.getFaultCode());
        Assert.assertEquals("Le champ CODE est obligatoire", ex.getFaultLabel());
    }
	
	@Test
	public void newTechnicalException(){
	    APIException ex = FaultFactory.getInstance().newTechnicalExceptionFault();
        Assert.assertEquals("API_1700", ex.getFaultCode());
	}
}
