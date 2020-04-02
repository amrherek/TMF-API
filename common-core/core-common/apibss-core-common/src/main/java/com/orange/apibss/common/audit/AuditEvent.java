package com.orange.apibss.common.audit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.MDC;

@XmlRootElement(name="event")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuditEvent {

    /**
     * idTransaction
     */
    private String transactionId;

    @XmlElement
    private ApiCaller caller;

    @XmlElement
    private ApiStats stats;

    @XmlElement
    private ApiUseCase useCase;

    @XmlElement
    private ApiContextError error;

    public AuditEvent() {
        transactionId = MDC.get("RequestId");
        stats = new ApiStats();
        useCase = new ApiUseCase();
    }

    public AuditEvent(String apiName) {
        transactionId = MDC.get("RequestId");
        stats = new ApiStats();
        useCase = new ApiUseCase(apiName);
    }

    public String getTransactionID() {
        return transactionId;
    }

    public ApiUseCase getUseCase() {
        return useCase;
    }

    public ApiCaller getCaller() {
        return caller;
    }

    public void setCaller(ApiCaller caller) {
        this.caller = caller;
    }

    public ApiStats getStats() {
        return stats;
    }

    public ApiContextError getError() {
        return error;
    }

    public void setError(ApiContextError error) {
        this.error = error;
    }

    public void setExitDate(long exitDate) {
        stats.setExitDate(exitDate);
    }

    public void updateUseCase(String method, String msisdn) {
        useCase.setMethod(method);
        useCase.setMsisdn(msisdn);
    }
}
