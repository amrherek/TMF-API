package com.orange.apibss.common.audit.configuration;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.audit.ApiCaller;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.audit.AuditEvent;

/**
 * @author Denis Borscia (deyb6792)
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class AuditContextImpl implements AuditContext {

    private AuditEvent event;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Value("${api.name}")
    private String apiName;

    @PostConstruct
    public void init() {
        event = new AuditEvent(apiName);
    }

    @Override
    public void open(String method, String msisdn) {
        event.updateUseCase(method, msisdn);
    }

    @Override
    public void close() {
        ApiCaller apiCaller = new ApiCaller();
        apiCaller.setIp(httpServletRequest.getRemoteHost());
        apiCaller.setPort(String.valueOf(httpServletRequest.getRemotePort()));
        apiCaller.setPlatform(httpServletRequest.getHeader("platform"));
        apiCaller.setLogin(httpServletRequest.getHeader("login"));
        event.setCaller(apiCaller);
        event.setExitDate(Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public AuditEvent getAuditEvent() {
        return event;
    }
}
