package com.orange.bscs.api.connection;

import org.junit.Assert;
import org.junit.Test;

import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryMock;
import com.orange.bscs.api.connection.factory.SOIConnectionFactory;

public class SOIConnectionFactoryTest {

    
    @Test 
    public void executeDefaultConstructor(){
        IConnectionBackendFactory backendFactory = new ConnectionBackendFactoryMock();
        SOIConnectionFactory factory = new SOIConnectionFactory();
        factory.setConnectionBackendFactory(backendFactory);
        factory.postInitialisation();
    }

    @Test(expected=IllegalArgumentException.class)
    public void failDefaultConstructor(){
        SOIConnectionFactory factory = new SOIConnectionFactory();
        factory.postInitialisation();
    }
    
    @Test
    public void nominalUCs() throws Exception{
        
        
        IConnectionBackendFactory backendFactory = new ConnectionBackendFactoryMock();
        SOIConnectionFactory factory = new SOIConnectionFactory(backendFactory);
        factory.postInitialisation();
       
        SOIConnectionImpl conn = factory.retreiveConnection();
        Assert.assertNotNull("retreiveConnection",conn);
        
        factory.releaseConnection(conn);
        
        conn = factory.retreiveConnection();
        factory.destroyConnection(conn);
        
    }
    
    @Test
    public void modeInPool() throws Exception{
        IConnectionBackendFactory backendFactory = new ConnectionBackendFactoryMock();
        SOIConnectionFactory factory = new SOIConnectionFactory(backendFactory);
        factory.postInitialisation();

        SOIConnectionImpl conn = factory.retreiveConnection();
        
        factory.validateObject(conn);

        factory.passivateObject(conn);
        
        factory.activateObject(conn);
        
        factory.destroyObject(conn);
        
    }
}
