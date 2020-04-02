package com.orange.apibss.common.audit;

/**
 * Simple logger in xml format
 * 
 * @author Denis Borscia (deyub6792)
 */
public class XmlAuditLogger implements AuditLogger {

    @Override
    public void log(AuditEvent event) {
        if (event.getUseCase().getMethod() != null || event.getError() != null) {
            getAuditWriter().write(event);
        }
    }

    protected AuditEventXMLWriter getAuditWriter() {
        return new AuditEventXMLWriter();
    }

}
