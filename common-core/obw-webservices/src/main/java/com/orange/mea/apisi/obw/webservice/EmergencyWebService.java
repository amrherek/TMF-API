package com.orange.mea.apisi.obw.webservice;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.WebserviceTechnicalException;
import com.orange.apibss.common.ws.WebServiceInitializer;
import com.orange.mea.apisi.obw.webservice.emergency.ObjectFactory;
import com.orange.mea.apisi.obw.webservice.emergency.ObwWebservicePortType;
import com.orange.mea.apisi.obw.webservice.emergency.TDebitAuthRequest;
import com.orange.mea.apisi.obw.webservice.emergency.TDebitAuthResponse;
import com.orange.mea.apisi.obw.webservice.emergency.TEmergencyCreditRequest;
import com.orange.mea.apisi.obw.webservice.emergency.TEmergencyCreditResponse;

@Service
public class EmergencyWebService {

    private final static String WS_NAME = "EmergencyCreditWebService";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${emergencyCredit.url}")
    private String url;

    @Value("${emergencyCredit.login}")
    private String login;

    @Value("${emergencyCredit.password}")
    private String password;

    @Autowired
    private WebServiceInitializer webServiceInitializer;

    private ObwWebservicePortType obwWebservice;

    private final ObjectFactory objectFactory;

    public EmergencyWebService() {
        objectFactory = new ObjectFactory();
    }

    @PostConstruct
    protected final void init() {
        // TODO: verify that this WS supports WSE authentication
        obwWebservice = (ObwWebservicePortType) webServiceInitializer.init(ObwWebservicePortType.class, url, login,
                password, logger);
    }

    public TEmergencyCreditResponse emergencyCredit(String msisdn, String amount)
            throws WebserviceTechnicalException {
        TEmergencyCreditRequest parameters = objectFactory.createTEmergencyCreditRequest();
        parameters.setMSISDN(msisdn);
        parameters.setAmount(amount);
        // TODO: fill transactionID (mandatory)
        parameters.setTransactionID("");
        TEmergencyCreditResponse response = null;
        try {
            response = obwWebservice.emergencyCredit(parameters);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "emergencyCredit", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "emergencyCredit", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "emergencyCredit", "no response");
        }
        // TODO: check resultCode?
        return response;
    }

    private void handleSoapFault(SOAPFaultException e, String methodName, String msisdn)
            throws WebserviceTechnicalException {
        if (e.getFault() != null && e.getFault().getFaultCode() != null) {
            switch (e.getFault().getFaultCode()) {
            // TODO: handle errors
            default:
                throw new WebserviceTechnicalException(WS_NAME, methodName, e.getFault().getFaultCode(),
                        e.getFault().getFaultString());
            }
        }
        throw new WebserviceTechnicalException(WS_NAME, methodName, e.getMessage());
    }

    public TDebitAuthResponse debitAuth(String msisdn) throws WebserviceTechnicalException {
        TDebitAuthRequest parameters = objectFactory.createTDebitAuthRequest();
        parameters.setMSISDN(msisdn);
        // TODO: fill transactionID (mandatory)
        parameters.setTransactionID("");
        TDebitAuthResponse response = null;
        try {
            response = obwWebservice.debitAuth(parameters);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "debitAuth", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "debitAuth", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "debitAuth", "no response");
        }
        // TODO: check resultCode?
        return response;
    }

}
