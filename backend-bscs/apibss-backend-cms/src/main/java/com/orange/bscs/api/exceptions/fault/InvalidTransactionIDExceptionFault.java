
package com.orange.bscs.api.exceptions.fault;



public class InvalidTransactionIDExceptionFault extends Exception {

    private static final long serialVersionUID = -1L;


    public InvalidTransactionIDExceptionFault() {
        super();
    }

    public InvalidTransactionIDExceptionFault(String message) {
        super(message);
    }

    public InvalidTransactionIDExceptionFault(String message, Throwable cause) {
        super(message, cause);
    }

}
