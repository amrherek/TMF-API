package com.orange.apibss.common.audit.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.audit.AuditLogger;

/**
 * Filter to log audit context
 * 
 * <pre>
 * Order is important because the filter must be executed before
 * ApiAuthenticationFilter but he must be executed whit a request scope set (so
 * after RequestContextFilter)
 * 
 * @author JWCK2987
 *
 */
@Component
@Order(-101)
public class AuditFilter extends GenericFilterBean {

    @Autowired
    private AuditContext auditContext;

    @Autowired(required = false)
    private List<AuditLogger> auditLoggers;

    public AuditFilter() {
        auditLoggers = new ArrayList<>();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // add generated uuid to MDC to be used in log
            MDC.put("RequestId", UUID.randomUUID());
            chain.doFilter(request, response);
        } finally {
            auditContext.close();
            for (AuditLogger auditLogger : auditLoggers) {
                auditLogger.log(auditContext.getAuditEvent());
            }
            MDC.clear();
        }
    }

}
