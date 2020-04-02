package com.orange.bscs.api.exceptions.factory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.orange.bscs.api.exceptions.APIException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;

/**
 * Default Instance of IFaultFactory.
 * <p>
 * Ignore specific FaultCause depending on WSDL version.
 * </p>
 * 
 * @author It&Labs
 * 
 */
public class FaultFactorySimple implements IFaultFactory {
    private static final String ERROR_DICTIONNARY_BUNDLE = "errors_bscs";
    private static ResourceBundle bundle = PropertyResourceBundle.getBundle(ERROR_DICTIONNARY_BUNDLE, Locale.getDefault());

    
    public APIException newAPIException(Class<? extends Exception> faultClass, String faultCode, String faultLabel, Exception cause) {

        return new APIException(cause, faultCode, faultLabel);
    }

    
    public APIException newAPIException(Class<? extends Exception> faultClass, String faultCode, Exception cause,
            String... faultLabelArgs) {
        String faultLabel = getFaultLabel(faultCode, faultLabelArgs);
        return new APIException(cause, faultCode, faultLabel);
    }

    private static synchronized String getFaultLabel(String faultCode, String... args) {
        return MessageFormat.format(bundle.getString(faultCode), (Object[]) args);
    }


    public APIException newAPIException(Exception ce) {
        return new APIException(ce, AbstractSVLObjectFactory.getErrorCode(ce), ce.getMessage());
    }

    
    public APIException newInvalidInputException(String errorCode) {
        return newAPIException(IllegalArgumentException.class, errorCode, null);
    }

    
    public APIException newInvalidInputException(String errorCode, Exception cause, String... args) {
        return newAPIException(IllegalArgumentException.class, errorCode, cause, args);
    }

    
    public APIException newInconsistentInputParametersException(String errorCode, Exception cause, String... argsLabel) {
        return newAPIException(IllegalArgumentException.class, errorCode, cause, argsLabel);
    }

    
    public APIException newInconsistentStatusException(String errorCode) {
        return newAPIException(IllegalArgumentException.class, errorCode, null);
    }

    
    public APIException newInconsistentStatusException(String errorCode, Exception cause, String... argsLabel) {
        return newAPIException(IllegalArgumentException.class, errorCode, cause, argsLabel);
    }

    
    public APIException newInconsistentDatabaseException(String errorCode, Exception cause, String... argsLabel) {
        return newAPIException(IllegalArgumentException.class, errorCode, cause, argsLabel);
    }

    
    public APIException newMissingConfigurationException(String errorCode, Exception cause, String argsLabel) {
        return newAPIException(IllegalArgumentException.class, errorCode, cause, argsLabel);
    }

	
	public APIException newTechnicalExceptionFault() {
        return newAPIException(IllegalArgumentException.class, "API_1700", null,"Internal server error");
	}

}
