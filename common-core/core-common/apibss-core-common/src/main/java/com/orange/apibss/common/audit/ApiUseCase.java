package com.orange.apibss.common.audit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class to log api use case
 * author: Denis Borscia (deyb6792)
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiUseCase {

    private String api;

    private String method;

    private String msisdn;

    public ApiUseCase() {
    }

    public ApiUseCase(String apiName) {
        this.api = apiName;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

}
