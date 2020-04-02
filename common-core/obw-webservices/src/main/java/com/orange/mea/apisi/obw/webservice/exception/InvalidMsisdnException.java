package com.orange.mea.apisi.obw.webservice.exception;

public class InvalidMsisdnException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5880426745121310135L;


    private String msisdn;

    public InvalidMsisdnException(String msisdn, String message) {
        super(message);
        this.msisdn = msisdn;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

}
