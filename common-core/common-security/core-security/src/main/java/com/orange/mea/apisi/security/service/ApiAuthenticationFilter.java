package com.orange.mea.apisi.security.service;

import static com.orange.mea.apisi.security.Constant.LOGIN_HEADER;
import static com.orange.mea.apisi.security.Constant.PASSWORD_HEADER;
import static com.orange.mea.apisi.security.Constant.PLATFORM_HEADER;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Data;


/**
 * Date : 13/12/2016.
 *
 * @author Denis Borscia (deyb6792)
 */
public class ApiAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        ApiPrincipal principal = extractInfoHeader(request);

        try {
            if (isEmpty(principal.getLogin()) ||
                    isEmpty(principal.getPassword()) ||
                    isEmpty(principal.getPlatform())) {
                // do not throw error since we could be outside api URL
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    principal, principal.getPassword());
            authRequest.setDetails(authenticationDetailsSource
                    .buildDetails(request));
            Authentication authResult = authenticationManager
                    .authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            rememberMeServices.loginSuccess(request, response, authResult);
            onSuccessfulAuthentication(request, response, authResult);

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();
            rememberMeServices.loginFail(request, response);
            onUnsuccessfulAuthentication(request, response, failed);
            authenticationEntryPoint.commence(request, response, failed);
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * @param string the string to test
     * @return true is null or ""
     */
    private boolean isEmpty(String string) {
        return (string == null || string.isEmpty());
    }


    @Override
    public void afterPropertiesSet() {
        Assert.notNull(authenticationManager,
                "An AuthenticationManager is required");
        Assert.notNull(this.authenticationEntryPoint,
                "An AuthenticationEntryPoint is required");
    }

    /**
     * @param request the request
     * @return 
     */
    private ApiPrincipal extractInfoHeader(HttpServletRequest request) {
        ApiPrincipal principal = new ApiPrincipal();
        principal.setLogin(request.getHeader(LOGIN_HEADER));
        principal.setPassword(request.getHeader(PASSWORD_HEADER));
        principal.setPlatform(request.getHeader(PLATFORM_HEADER));

        return principal;
    }

    protected void onSuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, Authentication authResult) throws IOException {
        // nothing to do
    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request,
                                                HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        // nothing to do
    }

    @Data
    public static class ApiPrincipal {
        public static final String SEPARATOR = "::";
        private String login;
        private String password;
        private String platform;

        @Override
        public String toString() {
            return login + SEPARATOR +
                    password + SEPARATOR +
                    platform + SEPARATOR;
        }

        public ApiPrincipal() {
        }

        public ApiPrincipal(String principal) {
            String[] elements = principal.split(SEPARATOR);
            login = elements[0];
            password = elements[1];
            platform = elements[2];
        }
    }
}
