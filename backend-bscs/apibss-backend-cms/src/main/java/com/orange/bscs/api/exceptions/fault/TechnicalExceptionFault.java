
package com.orange.bscs.api.exceptions.fault;



public class TechnicalExceptionFault extends Exception {

    private static final long serialVersionUID = -1L;


    public TechnicalExceptionFault() {
        super();
    }

    public TechnicalExceptionFault(String message) {
        super(message);
    }

    public TechnicalExceptionFault(String message, Throwable cause) {
        super(message, cause);
    }

}
