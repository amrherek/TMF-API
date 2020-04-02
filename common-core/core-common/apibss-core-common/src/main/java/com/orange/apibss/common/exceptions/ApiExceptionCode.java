package com.orange.apibss.common.exceptions;

public class ApiExceptionCode {

    private ApiExceptionCode() {
    }

    // technical
    public static Integer TC_GENERIC = 5000;
    public static Integer TC_ENUM = 5001;
    public static Integer TC_PENDING_REQUEST = 5002;
    public static Integer TC_NOT_IMPLEMENTED = 5003;
    public static Integer TC_WEBSERVICE = 5300;

    // functionnal
    public static Integer FC_GENERIC = 4000;
    public static Integer FC_NOT_FOUND = 4040;
    public static Integer FC_EMPTY_PARAMETER = 4001;
    public static Integer FC_BAD_FORMAT_PARAMETER = 4002;
    public static Integer FC_BAD_VALUE_PARAMETER = 4003;
    public static Integer FC_BAD_PARAMETERS = 4005;
    public static Integer FC_TOO_MANY_PARAMETERS = 4006;

}
