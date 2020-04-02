
package com.orange.bscs.api.exceptions.fault;

public class AuthenticationExceptionFault extends Exception {

    private static final long serialVersionUID = -1L;


    public AuthenticationExceptionFault() {
        super();
    }

    public AuthenticationExceptionFault(String message) {
        super(message);
    }

    public AuthenticationExceptionFault(String message, Throwable cause) {
        super(message, cause);
    }

}
