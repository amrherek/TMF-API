package com.orange.bscs.api.model.exception;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hide Ericsson specific Exceptions and it is an RuntimeException so simplify
 * development and prevent to use APIException for this types of Exception.
 * 
 * @author IT&Labs
 * 
 */
public class SOIException extends RuntimeException {

    private static final long serialVersionUID = 2327037827375342165L;

	private String code;

    private ArrayList<Object> args;

    /**
     * @param message
     * @param e
     *            initial exception, should be com.lhs.... exceptions.
     */
    public SOIException(String message, Exception e) {
        super(message, e);
    }

    /**
     * @param e
     *            initiale exception, should be com.lhs... exceptions.
     */
    public SOIException(Exception e) {
        super(e);
    }

    /**
     * @param message
     *            specify error message
     */
    public SOIException(String message) {
        super(message);
    }

	public SOIException(CMSException exception) {
		super(exception.getMessage(), exception);
        if (exception.getDetailedCMSInternalErrors() != null && exception.getDetailedCMSInternalErrors().length > 0) {
            this.code = exception.getDetailedCMSInternalErrors()[0].getErrorCode();
            this.args = new ArrayList<>(Arrays.asList(exception.getDetailedCMSInternalErrors()[0].getErrorArgs()));
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

    public Object[] getArgs() {
        return args.toArray();
    }

    public String getFirstArg() {
        return getArg(0);
    }

    public String getArg(int index) {
        if (args != null && args.size() > index) {
            return args.get(index).toString();
        }
        return null;
    }

    public String getAllArgs() {
        StringBuffer res = new StringBuffer();
        boolean first = true;
        if (args != null) {
            for (int i = 0; i < args.size(); i++) {
                if (!first) {
                    res.append(", ");
                }
                res.append(args.get(i).toString());
                first = false;
            }
        }
        return res.toString();
    }

}
