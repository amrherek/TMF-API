package com.orange.apibss.common.audit.dao;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class HistoricLog {

    private String platform;

    private String login;

    private String originHost;

    private String api;

    private String method;

    private String msisdn;

    private String transactionId;

    private Timestamp callDate;

    private Timestamp responseDate;

    private Long responseTime;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOriginHost() {
        return originHost;
    }

    public void setOriginHost(String originHost) {
        this.originHost = originHost;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getCallDate() {
        return callDate;
    }

    public void setCallDate(Timestamp callDate) {
        this.callDate = callDate;
    }

    public Timestamp getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Timestamp responseDate) {
        this.responseDate = responseDate;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

}
