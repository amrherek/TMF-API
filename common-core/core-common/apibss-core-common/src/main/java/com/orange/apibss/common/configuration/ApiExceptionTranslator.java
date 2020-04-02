package com.orange.apibss.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.audit.ApiContextError;
import com.orange.apibss.common.audit.ApiContextError.ErrorType;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.audit.AuditEvent;
import com.orange.apibss.common.configuration.impl.APIMessageResolverDefault;
import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;

@Component
public class ApiExceptionTranslator {

    @Autowired(required = false)
    private AuditContext auditContext;

    @Autowired
    private APIMessageResolverDefault apiMessageResolver;

    public ApiError buildError(ApiException exception) {
        final ApiError result = new ApiError();
        String code = String.valueOf(exception.getCode());
        result.setCode(exception.getCode());
        result.setMessage(apiMessageResolver.getMessage(code + ".msg", exception.getArguments(),
                exception.getGenericMessage()));
        result.setDescription(apiMessageResolver.getMessage(
                code + ".desc" + exception.getSuffix(), exception.getArguments(),
                exception.getMessage()));
        fillErrorInContext(result, exception);
        return result;
    }

    private void fillErrorInContext(ApiError error, ApiException exception) {
        if (auditContext != null) {
            AuditEvent auditEvent = auditContext.getAuditEvent();
            ApiContextError contextError = new ApiContextError();
            contextError.setCode(error.getCode());
            contextError.setMessage(error.getDescription());
            if (exception instanceof BadRequestException) {
                contextError.setType(ErrorType.FUNCT);
            } else {
                contextError.setType(ErrorType.TECH);
            }
            auditEvent.setError(contextError);
        }
    }

}
