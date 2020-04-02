package com.orange.bscs.api.connection;

import java.util.Observer;

/**
 * Manage creation/release of SOIConnection, used by SOITransactionManager.
 * 
 * @see SOIConnectionFactory.
 * @see SOIConnectionPool.
 * 
 * @author IT&L@bs
 *
 */
public interface ISOIConnectionProvider  {


    /**
     * 
     * @return a SOIConnection (recycled or new own)
     * 
     * @throws Exception
     */
    SOIConnectionImpl retreiveConnection() throws Exception;

    /**
     * Return connection to the pool or close it.
     * 
     * @param connection
     * @throws Exception
     */
    void releaseConnection(SOIConnectionImpl connection) throws Exception;


    /**
     * close/free the connection.
     * 
     * @param connection
     * 
     * @throws Exception
     */
    void destroyConnection(SOIConnectionImpl connection) throws Exception;



    /**
     * SOITransactionManager register itself to the pool to known when a connection is destroy.
     * 
     * @param obs Should be the SOITransactionManager.
     */
    void addObserver(Observer obs);

    /**
     * 
     * @param obs the SOITransactionManager
     */
    void removeObserver(Observer obs);
}
