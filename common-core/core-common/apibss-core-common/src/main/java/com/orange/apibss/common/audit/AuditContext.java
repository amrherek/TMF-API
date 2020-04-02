package com.orange.apibss.common.audit;

/**
 * An interface to inject audit context per request.
 *
 * @author Denis Borscia (deyb6792)
 */
public interface AuditContext {

    void open(String method, String msisdn);

    void close();

    AuditEvent getAuditEvent();
}
