package com.orange.bscs.api.connection.backend;

import com.orange.bscs.api.model.exception.SOIException;
import org.junit.Assert;

import org.junit.Test;

import com.orange.bscs.api.connection.IConnectionBackendFactory;

public class ConnectionBackendFactoryFileTest {

    /** compile time check default constructor */
    @Test
    public void defaultConstructor(){
        ConnectionBackendFactoryFile factory = new ConnectionBackendFactoryFile();
        Assert.assertNotNull(factory);
        
        factory.setSourceBackendFactory(new ConnectionBackendFactoryMock());
        factory.setRacinePath("/");
        factory.postInitialisation();
        
        Assert.assertNotNull(factory.newInstance());
    }
    
    /**
     *  compile time check constructor with source factory
     */
    @Test
    public void constructorWithSrcFactory(){
        IConnectionBackendFactory srcFactory = new ConnectionBackendFactoryMock();
        ConnectionBackendFactoryFile factory = new ConnectionBackendFactoryFile(srcFactory,"readonly","/");
        Assert.assertNotNull(factory);
        factory.postInitialisation();
    }
    

    @Test
    public void setModes(){
        ConnectionBackendFactoryFile factory = getFactory();

        for(String mode : new String[]{"readonly","read","readwrite","write"}){
            factory.setMode(mode);
            Assert.assertNotNull(factory.getMode());
        }
    }
    
    @Test
    public void setModeInvalid(){
        ConnectionBackendFactoryFile factory = getFactory();
        factory.setMode("TOTO");
        Assert.assertNull(factory.getMode());
    }
    
    @Test
    public void setOutputPath(){
         ConnectionBackendFactoryFile factory = getFactory();
        factory.setOutputPath("/");
        Assert.assertNotNull(factory.getOutputPath());
        
    }
    
    
    @Test(expected = SOIException.class)
    public void execute(){
        ConnectionBackendFactoryFile factory = getFactory();
        factory.setRacinePath("/mock");
        
        ConnectionBackendFile conn = factory.newInstance();
        Assert.assertNotNull("Connection is null", conn);

        conn.execute("CUSTOMER.READ", null);
        
    }
    
    
    private ConnectionBackendFactoryFile getFactory(){
        IConnectionBackendFactory srcFactory = new ConnectionBackendFactoryMock();
        return new ConnectionBackendFactoryFile(srcFactory,"readonly","/");
    }
    
    
    
}
