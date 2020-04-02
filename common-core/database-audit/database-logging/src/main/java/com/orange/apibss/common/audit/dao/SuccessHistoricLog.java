package com.orange.apibss.common.audit.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "successLog")
@Table(name = "histo_success_log")
public class SuccessHistoricLog extends HistoricLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "HISTO_SEQ")
    private Integer histoSuccessLogId;

    public Integer getHistoSuccessLogId() {
        return histoSuccessLogId;
    }

    public void setHistoSuccessLogId(Integer histoSuccessLogId) {
        this.histoSuccessLogId = histoSuccessLogId;
    }

}
