package com.orange.apibss.common.audit;

/**
 * Interface to log audit event
 *
 * @author Denis Borscia(deyb6792)
 */
public interface AuditLogger {


    /**
     * Log event.
     * The event end time and the duration are computed by the log operation
     *
     * @param event the event ot log
     */
    void log(AuditEvent event);
}
