package com.orange.bscs.api.exceptions.factory;

import com.orange.bscs.api.exceptions.APIException;

/**
 * Factory creating specialised APIException (may use bundle to retreive message
 * associate to a Code)
 * 
 * @author IT&Labs
 * 
 */
public interface IFaultFactory {

    /**
     * Creates a new APIException that will encapsulate a Fault
     * 
     * @param faultClass
     * @param faultCode
     * @param faultLabel
     * @param cause
     * @return
     */
    APIException newAPIException(Class<? extends Exception> faultClass, String faultCode, String faultLabel, Exception cause);

    /**
     * Creates a new APIException that will encapsulate a Fault
     * 
     * @param faultClass
     * @param faultCode
     * @param cause
     * @param faultLabelArgs
     * @return
     */
    APIException newAPIException(Class<? extends Exception> faultClass, String faultCode, Exception cause, String... faultLabelArgs);

    /**
     * Convert ComponentException into more specialised exception encapsulate
     * under APIException
     * 
     * @param ce
     * @return
     */
    APIException newAPIException(Exception ce);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * argument value
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @return a well formed APIException
     */
    APIException newInvalidInputException(String errorCode);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * parameter
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @param args
     *            argument used to form the message described in a bunble
     * 
     * @return a well formed APIException
     */
    APIException newInvalidInputException(String errorCode, Exception cause, String... args);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * statement in the current contexte (example : remove a removed service)
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @param argsLabel
     *            argument used to form the message described in a bunble
     * 
     * @return a well formed APIException
     */
    APIException newInconsistentStatusException(String errorCode, Exception cause, String... argsLabel);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * statement in the current contexte (example : remove a removed service)
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @param argsLabel
     *            argument used to form the message described in a bunble
     * 
     * @return a well formed APIException
     */
    APIException newInconsistentDatabaseException(String errorCode, Exception cause, String... argsLabel);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * statement in the current contexte (example : remove a removed service)
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * 
     * @return a well formed APIException
     */
    APIException newInconsistentStatusException(String errorCode);

    /**
     * May create an APIException with a FaultClass representing invalid/Illegal
     * parameter
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @param argsLabel
     *            argument used to form the message described in a bunble
     * 
     * @return a well formed APIException
     */
    APIException newInconsistentInputParametersException(String errorCode, Exception cause, String... argsLabel);

    /**
     * May create an APIException with a FaultClass representing error in config
     * file
     * 
     * @param errorCode
     *            May be a Code associated with a label in a bundle
     * @param args
     *            argument used to form the message described in a bunble
     * 
     * @return a well formed APIException
     */
    APIException newMissingConfigurationException(String errorCode, Exception cause, String args);

    /**
     * APIException : API_1700=Internal server error
     * 
     * @return a well formed APIException for error 1700
     */
    APIException newTechnicalExceptionFault();

}
