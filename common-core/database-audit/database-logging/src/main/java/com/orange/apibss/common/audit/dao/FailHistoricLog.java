package com.orange.apibss.common.audit.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "failLog")
@Table(name = "histo_fail_log")
public class FailHistoricLog extends HistoricLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "HISTO_SEQ")
    private Integer histoFailLogId;

    private Integer errorCode;

    private String errorType;

    private String errorMessage;

    public Integer getHistoFailLogId() {
        return histoFailLogId;
    }

    public void setHistoFailLogId(Integer histoFailLogId) {
        this.histoFailLogId = histoFailLogId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
