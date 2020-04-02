package com.orange.bscs.api.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holder containing the current transaction in a ThreadLocal.
 * 
 * Each DAO may use this class to retreive the current SOIConnection : <pre>{@code 
 * SOIConnection conn = ConnectionHolder.getCurrentConnection();
 * }</pre>
 * 
 * Framework use setConnection() and unset(). theses methods should not be
 * called.
 * 
 * @author IT&Labs
 * 
 */
public final class ConnectionHolder {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionHolder.class);

    /**
     * Sonar: Hide Utility Class Constructor
     */
    private ConnectionHolder() {
    }

    private static final ThreadLocal<SOIConnection> CONNECTION_HOLDER = new ThreadLocal<SOIConnection>();


    private static boolean isSingletonMode;
    private static SOIConnection singletonInstance;

    /**
     * @return the SOIConnection initialised by the framework and valid in this
     *         thread
     */
    public static SOIConnection getCurrentConnection() {
        return isSingletonMode ? singletonInstance: CONNECTION_HOLDER.get();
    }

    /**
     * Reserved to the framework, store a SOIConnection in LocalThread.
     * 
     * @param conn
     *            the current SOIConnection
     */
    public static void setConnection(SOIConnection conn) {
        if (null != conn) {
            LOG.debug("Setting this Connection in LocalThread : SOIConnection(C:{}, T:{}", conn.getConnectionId(),
                    conn.getTransactionId());
            if(isSingletonMode){
                singletonInstance=conn;
            } else {
                CONNECTION_HOLDER.set(conn);
            }
        } else {
            LOG.warn("Setting NULL Connection in LocalThread, Should call unset()");
            CONNECTION_HOLDER.remove();
            singletonInstance=null;
        }
    }

    /**
     * Reserved to the framework, release the connection.
     */
    public static void unset() {
        if (LOG.isDebugEnabled()) {
            SOIConnection conn = getCurrentConnection();
            if (null != conn) {
                LOG.debug("Clear LocalThread, remove SOIConnection(C:{}, T:{}", conn.getConnectionId(), conn.getTransactionId());
            }
        }
        CONNECTION_HOLDER.remove();
        singletonInstance=null;
    }

    /**
     * ONLY FOR UNIT-TEST (api-rest)
     * 
     * Don't store Connection on ThreadLocal but in static field
     * because WebServer doesn't share thread with test.
     */
    public static void setSingleton() {
        isSingletonMode=true;        
    }

}
