/*
 * Filename : SOIConnection.java							8 juil. 2011
 * Author : Michaël LADOWICHX
 */

package com.orange.bscs.api.connection;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import com.orange.bscs.api.connection.factory.SOIConnectionFactory;
import com.orange.bscs.api.exceptions.ErrorDictionary;
import com.orange.bscs.api.exceptions.factory.FaultFactory;
import com.orange.bscs.api.exceptions.fault.TechnicalExceptionFault;

/**
 * This class permits to manage a pool of SOIConnection which contains the
 * connection to the CIL component of BSCS.<br />
 * This class has the package level. To use this class, it's necessary to use
 * the SOITransactionsManager.
 * @author Michaël LADOWICHX
 */
public class SOIConnectionPool extends GenericObjectPool implements ISOIConnectionProvider, DisposableBean {

    /** Static variable used to log execution informations of this class */
    private static final Logger LOG = LoggerFactory.getLogger(SOIConnectionPool.class);


    private static final int MSPERSECOND = 1000;

    private static final int MSPERMINUTE = 60 * MSPERSECOND;


    private static final int NUMTESTSPEREVICTIONRUN =3;

    /**
     * This variable must be used to notify potentials observers on some change
     * which occurred in this pool.
     */
    private Observable notifier;


    /**
     * Constructor with default values
     * 
     * PoolSize : 10
     * No initial connection in pool
     */
    public SOIConnectionPool(PoolableObjectFactory factory){
        this(factory, 10, 1, 5, 10, 0);
    }


    /**
     * Constructor with args which permits to create an new SOIConnectionPool
     * with a factory needed to control the make/passivate/activate/validate/
     * destroy treatment when the pool execute some internal managing
     * operations. Others parameters permits to control the size of the pool
     * and some timeout to manage life cycle of SOIConnection managed by the
     * pool.
     * @param factory the factory used to control the make/passivate/activate/
     * validate/destroy treatment when the pool execute some internal managing
     * operations.
     * @param timeoutBeforeEviction timeout when a SOIConnection can be evicted
     * from the pool. 
     * @param timeBetweenEvictionRuns time between each run of eviction process.
     * @param poolSize the maximum SOIConnection allowed in the pool.
     * @param poolInitSize the number of SOIConnection to make when the pool
     * is created.
     */
    public SOIConnectionPool(
            PoolableObjectFactory factory,
            long timeoutBeforeEviction,
            long timeBetweenEvictionRuns,
            int poolMaxWait,
            int poolMaxSize,
            int poolInitialSize) {
        super(factory,
                // maxActive
                poolMaxSize,			

                // whenExhaustedAction
                WHEN_EXHAUSTED_BLOCK,					

                // maxWait
                (long)poolMaxWait * MSPERSECOND,						

                // maxIdle
                poolMaxSize,							

                // minIdle
                poolInitialSize,						

                // testOnBorrow
                false,									

                // testOnReturn
                false,									

                // timeBetweenEvictionRunsMillis
                timeBetweenEvictionRuns * MSPERMINUTE,	

                // numTestsPerEvictionRun
                NUMTESTSPEREVICTIONRUN,										

                // minEvictableIdleTimeMillis
                timeoutBeforeEviction * MSPERMINUTE,	

                // testWhileIdle
                false,									

                // softMinEvictableIdleTimeMillis
                -1, 									

                // fifo
                false);

        notifier = new Observable();

        // initialization of idle soiConnection with the same value as minIdle
        initPool(poolInitialSize);
        LOG.info("Transaction pool created [size={}, timeoutBeforeEviction={} minute(s), timeBetweenEvictionRuns={} minute(s)]",
                poolMaxSize, timeoutBeforeEviction,timeBetweenEvictionRuns);
    }

    /**
     * Initialization of a set of soiConnection ready to use.
     * @param nbInitialTransaction number of soiConnection to initialize. 
     */
    private void initPool(int nbInitialTransaction) {
        try {
            for (int i = 1; i <= nbInitialTransaction; i++) {
                addObject();
            }
        } catch (Exception e) {
            LOG.warn("Unknow what to do", e);
        }
    }


    /* =====================================================================
     * 					ISOIConnectionProvider implementation
     * =====================================================================
     */

     public SOIConnectionImpl retreiveConnection() throws Exception{
    	 LOG.debug(" : The number of instances currently borrowed from this pool is  " + this.getNumActive() + " NumActive"
        + " and the number of instances currently idle in this pool  "+ this.getNumIdle()+ " NumIdle"  
    	+ " and the cap on the total number of object instances managed by the pool " + this.getMaxActive() + " MaxActive");
        return(SOIConnectionImpl) this.borrowObject(); 
    }

    public void releaseConnection(SOIConnectionImpl connection) throws Exception{
        returnObject(connection);
        LOG.debug(" : The number of instances currently borrowed from this pool is  " + this.getNumActive() + " NumActive"
                + " and the number of instances currently idle in this pool  "+ this.getNumIdle()+ " NumIdle"  
            	+ " and the cap on the total number of object instances managed by the pool " + this.getMaxActive() + " MaxActive");
    }

    public void destroyConnection(SOIConnectionImpl connection) throws Exception{    	 
        invalidateObject(connection);
        LOG.debug(" : The number of instances currently borrowed from this pool is  " + this.getNumActive() + " NumActive"
                + " and the number of instances currently idle in this pool  "+ this.getNumIdle()+ " NumIdle"  
            	+ " and the cap on the total number of object instances managed by the pool " + this.getMaxActive() + " MaxActive");
    }





    /**
     * {@inheritDoc}
     * We have override this method to add the possibility to notify potentials
     * observers when an object is invalidated from this pool. This is useful
     * when the factory return false on the
     * {@link SOIConnectionFactory#validateObject(Object)} method which
     * triggers the destroy of the object given in parameter.
     * 
     * @throws com.orange.bscs.api.exceptions.APIException 1202 (TechnicalExceptionFault)
     */
    @Override
    public void invalidateObject(Object obj) {
        SOIConnectionImpl soiConnection = null;
        try {
            super.invalidateObject(obj);
            soiConnection = (SOIConnectionImpl) obj;
            LOG.trace("Notify observer that the pool is currently invalidating a connection whose the transaction ID is {}" ,
                    soiConnection.getTransactionId());
            DataNotificationWrapper dataNotificationWrapper =
                    new DataNotificationWrapper(DataNotificationWrapper.TypeOfNotification.INVALIDATE_OBJECT, soiConnection);
            notifier.notifyObservers(dataNotificationWrapper);
        } catch(Exception e){
            throw FaultFactory.getInstance().newAPIException(TechnicalExceptionFault.class, ErrorDictionary.API_1202, e);
        } finally {
            if(null!=soiConnection){
                soiConnection.close();
            }
        }
    }

    /**
     * Adds an observer to the set of observers for this object, provided 
     * that it is not the same as some observer already in the set. 
     * The order in which notifications will be delivered to multiple 
     * observers is not specified. See the class comment.
     * @param o an observer to be added.
     * @throws NullPointerException   if the parameter o is null.
     */
    public void addObserver(Observer o) {
        notifier.addObserver(o);
    }

    public void removeObserver(Observer o){
        notifier.deleteObserver(o);
    }

    /**
     * @throws com.orange.bscs.api.exceptions.APIException (TechnicalExceptionFault)
     */
    @Override
    public void destroy() {
        LOG.info("Destroying pool ...");
        try {
            super.close();
        } catch(Exception e){
            throw FaultFactory.getInstance().newAPIException(TechnicalExceptionFault.class, ErrorDictionary.API_1202, e);
        }
    }

}
