/*
 * Filename : DataNotificationWrapper.java						13 juil. 2011
 * Author : Michaël LADOWICHX
 */
package com.orange.bscs.api.connection;

/**
 * This class permits to send informations about the notification sent from
 * an {@link java.util.Observable} to {@link java.util.Observer}. 
 * @author Michaël LADOWICHX
 */
class DataNotificationWrapper {

    /**
     * Below is specified all types of notification which is possible to send.
     */
    public static enum TypeOfNotification {
        INVALIDATE_OBJECT
    };

    /** The type of the notification */
    private TypeOfNotification type;

    /** The SOI connection to send */
    private SOIConnectionImpl soiConnection;

    /**
     * 
     * @param type the type of the notification.
     * @param soiConnection the SOI Connection to send to the observer.
     */
    DataNotificationWrapper(
            TypeOfNotification type, SOIConnectionImpl soiConnection) {
        this.type = type;
        this.soiConnection = soiConnection;
    }

    /**
     * @return the type
     */
    public TypeOfNotification getType() {
        return type;
    }

    /**
     * @return the SOI connection
     */
    public SOIConnectionImpl getSOIConnection() {
        return soiConnection;
    }

}
