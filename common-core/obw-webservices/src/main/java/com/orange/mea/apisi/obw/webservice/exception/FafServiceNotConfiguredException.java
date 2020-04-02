package com.orange.mea.apisi.obw.webservice.exception;

public class FafServiceNotConfiguredException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5880426745121310135L;

    private String msisdn;

    public FafServiceNotConfiguredException(String msisdn, String message) {
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
