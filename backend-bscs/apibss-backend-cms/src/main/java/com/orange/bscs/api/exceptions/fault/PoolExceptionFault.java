
package com.orange.bscs.api.exceptions.fault;



public class PoolExceptionFault extends Exception {

    private static final long serialVersionUID = -1L;


    public PoolExceptionFault() {
        super();
    }

    public PoolExceptionFault(String message) {
        super(message);
    }

    public PoolExceptionFault(String message, Throwable cause) {
        super(message, cause);
    }

}
