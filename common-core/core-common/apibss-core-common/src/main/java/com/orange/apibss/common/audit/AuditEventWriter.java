package com.orange.apibss.common.audit;

public interface AuditEventWriter {

    void write(AuditEvent auditEvent);
}
