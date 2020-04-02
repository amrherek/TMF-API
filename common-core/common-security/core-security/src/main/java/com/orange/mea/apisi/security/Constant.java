package com.orange.mea.apisi.security;

public class Constant {


    private Constant() {
        //  To avoid instantiation
    }

    /**
     * login
     */
    public static final String LOGIN_HEADER = "login";

    /**
     * password
     */
    public static final String PASSWORD_HEADER = "password";

    /**
     * PLATFORM
     */
    public static final String PLATFORM_HEADER = "platform";

    /**
     * ENCODERFORMAT
     */
    public static final String ENCODERFORMAT = "SHA-256";

    /**
     * PLATFORM_NOT_ACTIVE
     */
    public static final String PLATFORM_NOT_ACTIVE = "The platform that has been entered is not enabled for this account";

    /**
     * NOT_PRIVILEGE
     */
    public static final String NOT_PRIVILEGE = "That account has no privilege to access this method of the service";

    /**
     * WRONG_LOGIN_PASSWORD
     */
    public static final String WRONG_LOGIN_PASSWORD = "The login, password or platform is wrong";

    /**
     * MISSING_LOGIN_PASSWORD
     */
    public static final String MISSING_LOGIN_PASSWORD = "The login, password or platform is missing";

}
