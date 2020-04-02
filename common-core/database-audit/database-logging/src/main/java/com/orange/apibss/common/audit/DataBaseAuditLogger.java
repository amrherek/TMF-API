package com.orange.apibss.common.audit;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.common.audit.dao.FailHistoricLog;
import com.orange.apibss.common.audit.dao.FailLogRepository;
import com.orange.apibss.common.audit.dao.HistoricLog;
import com.orange.apibss.common.audit.dao.SuccessHistoricLog;
import com.orange.apibss.common.audit.dao.SuccessLogRepository;
import com.orange.apibss.common.configuration.ApiErrorsHandler;

/**
 * Add an entry in a event database
 *
 * @author Denis Borscia (deyb6792)
 */
public class DataBaseAuditLogger implements AuditLogger {

    protected final static Logger logger = LoggerFactory.getLogger(ApiErrorsHandler.class);

    @Autowired
    private SuccessLogRepository successLogDao;

    @Autowired
    private FailLogRepository failLogDao;

    @Override
    public void log(AuditEvent event) {
        if (event.getUseCase().getMethod() != null || event.getError() != null) {
            buildAndSaveHistoricLog(event);
        }
    }

    private void buildAndSaveHistoricLog(AuditEvent event) {
        try {
            if (event.getError() == null) {
                SuccessHistoricLog historicLog = buildSuccessHistoricLog(event);
                successLogDao.save(historicLog);
            } else {
                FailHistoricLog historicLog = buildFailHistoricLog(event);
                failLogDao.save(historicLog);
            }
        } catch (Exception e) {
            logger.error("Problem writing log in DB", e);
        }
    }

    private FailHistoricLog buildFailHistoricLog(AuditEvent event) {
        FailHistoricLog historicLog = new FailHistoricLog();
        buildLog(historicLog, event);
        ApiContextError error = event.getError();
        historicLog.setErrorCode(error.getCode());
        if (error.getMessage() != null && error.getMessage().length() > 500) {
            historicLog.setErrorMessage(error.getMessage().substring(0, 500));
        } else {
            historicLog.setErrorMessage(error.getMessage());
        }
        historicLog.setErrorType(error.getType().toString());
        return historicLog;
    }

    private SuccessHistoricLog buildSuccessHistoricLog(AuditEvent event) {
        SuccessHistoricLog historicLog = new SuccessHistoricLog();
        buildLog(historicLog, event);
        return historicLog;
    }

    private void buildLog(HistoricLog historicLog, AuditEvent auditEvent) {
        historicLog.setTransactionId(auditEvent.getTransactionID());

        ApiCaller caller = auditEvent.getCaller();
        historicLog.setPlatform(caller.getPlatform());
        historicLog.setLogin(caller.getLogin());
        historicLog.setOriginHost(caller.getIp());

        ApiUseCase useCase = auditEvent.getUseCase();
        historicLog.setApi(useCase.getApi());
        historicLog.setMethod(useCase.getMethod());
        historicLog.setMsisdn(useCase.getMsisdn());

        ApiStats stats = auditEvent.getStats();
        Long entryDate = stats.getEntryDateTime();
        if (entryDate != null) {
            historicLog.setCallDate(new java.sql.Timestamp(entryDate));

            long now = Calendar.getInstance().getTimeInMillis();
            historicLog.setResponseDate(new java.sql.Timestamp(now));
            historicLog.setResponseTime(now - entryDate);
        }

    }
}
