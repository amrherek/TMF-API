package com.orange.mea.apisi.security.exception;

public final class ApiSecurityExceptionCode {

    private ApiSecurityExceptionCode() {
    }

    // ---------------------------
    // 401 (Unauthorized) errors
    // ---------------------------
    // Missing required authentication headers
    public static final Integer SC_MISSING_INFO = 4010;
    // authentication failed (bad credentials)
    public static final Integer SC_BAD_CREDENTIALS = 4011;

    // ---------------------------
    // 403 (Forbidden) errors
    // ---------------------------
    // Role is not allowed
    public static final Integer SC_BAD_ROLE = 4030;
    // User is not active
    public static final Integer SC_DISABLED = 4031;

    // ---------------------------
    // 500 (Internal server error) errors
    // ---------------------------
    // Technical exception
    public static final Integer TC_SECURITY = 5403;

}
