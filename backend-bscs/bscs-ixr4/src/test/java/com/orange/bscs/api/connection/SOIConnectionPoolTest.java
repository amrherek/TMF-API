package com.orange.bscs.api.connection;

import org.junit.Assert;

import org.apache.commons.pool.PoolableObjectFactory;
import org.junit.Test;

import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryMock;
import com.orange.bscs.api.connection.factory.SOIConnectionFactory;

public class SOIConnectionPoolTest {

    
    @Test
    public void defaultConstructor(){
        Assert.assertNotNull("pool",getPool());
    }
    
    @Test
    public void longConstructor(){
        SOIConnectionPool pool=new  SOIConnectionPool(
                new SOIConnectionFactory(new ConnectionBackendFactoryMock()),
                10, 1, 5, 10, 3);
        
        Assert.assertNotNull(pool);
        
        pool.destroy();
    }
    
    
    @Test
    public void getConnection() throws Exception{
        SOIConnectionPool pool = getPool();
        SOIConnectionImpl conn = pool.retreiveConnection();
        
        pool.releaseConnection(conn);
        
        SOIConnectionImpl conn2 = pool.retreiveConnection();
        
        Assert.assertEquals(conn, conn2);
        
        pool.destroyConnection(conn);

        SOIConnectionImpl conn3 = pool.retreiveConnection();

        Assert.assertNotSame(conn2, conn3);

        pool.destroy();        
    }
    
    
    private SOIConnectionPool getPool(){
        PoolableObjectFactory factory = new SOIConnectionFactory(new ConnectionBackendFactoryMock());
        SOIConnectionPool pool = new SOIConnectionPool(factory);
        Assert.assertNotNull("pool",pool);
        
        return pool;
    }
}
