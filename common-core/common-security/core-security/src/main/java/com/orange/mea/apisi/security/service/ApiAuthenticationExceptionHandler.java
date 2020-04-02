package com.orange.mea.apisi.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.common.configuration.ApiExceptionTranslator;
import com.orange.mea.apisi.security.Constant;
import com.orange.mea.apisi.security.exception.ApiSecurityException;
import com.orange.mea.apisi.security.exception.ApiSecurityExceptionCode;
import com.orange.mea.apisi.security.exception.ApiTechnicalSecurityException;

/**
 * Date : 13/12/2016.
 *
 * @author Denis Borscia (deyb6792)
 */
public class ApiAuthenticationExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Autowired
    private ApiExceptionTranslator apiExceptionTranslator;

    @Autowired
    private ObjectMapper mapper;

    /**
     * Static variable used to log execution informations of this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ApiAuthenticationExceptionHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (authException instanceof DisabledException) {
            // active = false
            logger.error(Constant.PLATFORM_NOT_ACTIVE);
            ApiSecurityException ex = new ApiSecurityException(ApiSecurityExceptionCode.SC_DISABLED, "Not active",
                    Constant.PLATFORM_NOT_ACTIVE, authException);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().println(mapper.writeValueAsString(apiExceptionTranslator.buildError(ex)));
        } else if (authException instanceof BadCredentialsException) {
            // bad login/user/platform
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ApiSecurityException ex = new ApiSecurityException(ApiSecurityExceptionCode.SC_BAD_CREDENTIALS,
                    "Bad credentials", Constant.WRONG_LOGIN_PASSWORD, authException);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().println(mapper.writeValueAsString(apiExceptionTranslator.buildError(ex)));
        } else if (authException instanceof InsufficientAuthenticationException) {
            // missing header info for authentication
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ApiSecurityException ex = new ApiSecurityException(ApiSecurityExceptionCode.SC_MISSING_INFO,
                    "Missing authentication information", Constant.MISSING_LOGIN_PASSWORD, authException);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().println(mapper.writeValueAsString(apiExceptionTranslator.buildError(ex)));
        } else {
            // other exception, eg: InternalAuthenticationServiceException
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ApiTechnicalSecurityException ex = new ApiTechnicalSecurityException(authException);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getOutputStream().println(mapper.writeValueAsString(apiExceptionTranslator.buildError(ex)));
        }

    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // role is not allowed
        ApiSecurityException ex = new ApiSecurityException(ApiSecurityExceptionCode.SC_BAD_ROLE, "Role not allowed",
                Constant.NOT_PRIVILEGE, accessDeniedException);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(mapper.writeValueAsString(apiExceptionTranslator.buildError(ex)));
    }

}
