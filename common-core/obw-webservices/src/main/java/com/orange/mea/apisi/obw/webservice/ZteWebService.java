package com.orange.mea.apisi.obw.webservice;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.SOAPFaultException;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.WebserviceTechnicalException;
import com.orange.apibss.common.ws.WebServiceInitializer;
import com.orange.mea.apisi.obw.webservice.exception.FafServiceNotConfiguredException;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;

/**
 * Web service to connect to ZTE IN
 * 
 * @author jwck2987
 *
 */
@Service
public class ZteWebService {

    private final static String WS_NAME = "ZteWebService";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${zte.url}")
    private String url;

    @Value("${zte.login}")
    private String login;

    @Value("${zte.password}")
    private String password;

    @Value("${zte.faf.fellowType:1}")
    private int fafFellowType;

    @Autowired
    private WebServiceInitializer webServiceInitializer;

    private ObwWebservicePortType obwWebservice;

    private final ObjectFactory objectFactory;

    public ZteWebService() {
        objectFactory = new ObjectFactory();
    }

    @PostConstruct
    protected final void init() {
        // login and password are not used in WS security headers
        obwWebservice = (ObwWebservicePortType) webServiceInitializer.init(ObwWebservicePortType.class, url, null, null,
                logger);
    }

    /**
     * List existing Friends and family numbers
     * 
     * @param msisdn
     * @return
     * @throws WebserviceTechnicalException
     * @throws FafServiceNotConfiguredException
     * @throws InvalidMsisdnException
     */
    public TArrayOfFellowISDNDto listFriendsAndFamily(String msisdn)
            throws WebserviceTechnicalException, FafServiceNotConfiguredException, InvalidMsisdnException {
        TQueryFellowISDNRequest parameters = objectFactory.createTQueryFellowISDNRequest();
        parameters.setMSISDN(msisdn);
        TQueryFellowISDNResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.queryFellowISDN(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFaultForFaf(e, "queryFellowISDN", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "queryFellowISDN", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "queryFellowISDN", "no response");
        }
        return response.getFellowISDNDtoList();
    }

    /**
     * Add a friend and family number
     * 
     * @param msisdn
     * @param friendAndFamilyNumber
     * @throws WebserviceTechnicalException
     * @throws FafServiceNotConfiguredException
     * @throws InvalidMsisdnException
     */
    public void addFriendsAndFamily(String msisdn, String friendAndFamilyNumber)
            throws WebserviceTechnicalException, FafServiceNotConfiguredException, InvalidMsisdnException {
        TAddFellowISDNRequest parameters = objectFactory.createTAddFellowISDNRequest();
        parameters.setMSISDN(msisdn);
        parameters.setFellowISDN(friendAndFamilyNumber);
        parameters.setFellowType(fafFellowType);
        parameters.setChargeFlag("0"); // no charge
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            obwWebservice.addFellowISDN(parameters, header, null, null);
        } catch (final SOAPFaultException e) {
            handleSoapFaultForFaf(e, "addFellowISDN", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "addFellowISDN", e.getMessage(), e);
        }
    }

    /**
     * Delete a friend and family number
     * 
     * @param msisdn
     * @param friendAndFamilyNumber
     * @throws FafServiceNotConfiguredException
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public void deleteFriendsAndFamily(String msisdn, String friendAndFamilyNumber)
            throws WebserviceTechnicalException, FafServiceNotConfiguredException, InvalidMsisdnException {
        TDelFellowISDNRequest parameters = objectFactory.createTDelFellowISDNRequest();
        parameters.setMSISDN(msisdn);
        parameters.setFellowISDN(friendAndFamilyNumber);
        parameters.setFellowType(fafFellowType);
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            obwWebservice.delFellowISDN(parameters, header, null, null);
        } catch (final SOAPFaultException e) {
            handleSoapFaultForFaf(e, "delFellowISDN", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "delFellowISDN", e.getMessage(), e);
        }
    }

    /**
     * Modify a friend and family number
     * 
     * @param msisdn
     * @param currentFriendAndFamilyNumber
     * @param newFriendAndFamilyNumber
     * @throws FafServiceNotConfiguredException
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public void modifyFriendsAndFamily(String msisdn, String currentFriendAndFamilyNumber,
            String newFriendAndFamilyNumber)
            throws WebserviceTechnicalException, FafServiceNotConfiguredException, InvalidMsisdnException {
        TModFellowISDNRequest parameters = objectFactory.createTModFellowISDNRequest();
        parameters.setMSISDN(msisdn);
        parameters.setFellowISDN(currentFriendAndFamilyNumber);
        parameters.setNewFellowISDN(newFriendAndFamilyNumber);
        parameters.setFellowType(fafFellowType);
        parameters.setChargeFlag("0"); // no charge
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            obwWebservice.modFellowISDN(parameters, header, null, null);
        } catch (final SOAPFaultException e) {
            handleSoapFaultForFaf(e, "modFellowISDN", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "modFellowISDN", e.getMessage(), e);
        }
    }

    private void handleSoapFaultForFaf(SOAPFaultException e, String methodName, String msisdn)
            throws FafServiceNotConfiguredException, WebserviceTechnicalException, InvalidMsisdnException {
        if (e.getFault() != null && e.getFault().getFaultCode() != null) {
            switch (e.getFault().getFaultCode()) {
            case "1030": // the subscriber does't have the FnF service
                throw new FafServiceNotConfiguredException(msisdn, e.getFault().getFaultString());
            }
        }
        handleSoapFault(e, methodName, msisdn);
    }

    private void handleSoapFault(SOAPFaultException e, String methodName, String msisdn)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        if (e.getFault() != null && e.getFault().getFaultCode() != null) {
            switch (e.getFault().getFaultCode()) {
            case "1001": // The subscriber does not exist.
                throw new InvalidMsisdnException(msisdn, e.getFault().getFaultString());
            default:
                throw new WebserviceTechnicalException(WS_NAME, methodName, e.getFault().getFaultCode(),
                        e.getFault().getFaultString());
            }
        }
        throw new WebserviceTechnicalException(WS_NAME, methodName, e.getMessage());
    }

    private void handleSoapFault(SOAPFaultException e, String methodName) throws WebserviceTechnicalException {
        if (e.getFault() != null && e.getFault().getFaultCode() != null) {
            throw new WebserviceTechnicalException(WS_NAME, methodName, e.getFault().getFaultCode(),
                    e.getFault().getFaultString());
        }
        throw new WebserviceTechnicalException(WS_NAME, methodName, e.getMessage());
    }

    /**
     * Query profile and balances
     * 
     * @param msisdn
     * @return
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public TQueryProfileAndBalForCRMResponse getBalances(String msisdn)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TQueryProfileAndBalForCRMRequest parameters = objectFactory.createTQueryProfileAndBalForCRMRequest();
        parameters.setMSISDN(msisdn);
        TQueryProfileAndBalForCRMResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.queryProfileAndBalForCRM(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "queryProfileAndBalForCRM", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "queryProfileAndBalForCRM", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "queryProfileAndBalForCRM", "no response");
        }
        return response;
    }

    /**
     * Get history of topups
     * 
     * @param msisdn
     * @param startDate
     * @param endDate
     * @param limit
     * @return
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public TQueryOCSCDRResponse getTopupHistory(String msisdn, LocalDate startDate, LocalDate endDate, Integer limit)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TQueryOCSCDRRequest parameters = objectFactory.createTQueryOCSCDRRequest();
        parameters.setMSISDN(msisdn);
        // 4: recharge
        parameters.setEventType(4l);
        if (limit != null) {
            parameters.setQuantity(limit.longValue());
        }
        parameters.setStartTime(startDate);
        parameters.setEndTime(endDate);
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        TQueryOCSCDRResponse response = null;
        try {
            response = obwWebservice.queryOCSCDR(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "queryOCSCDR", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "queryOCSCDR", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "queryOCSCDR", "no response");
        }
        return response;
    }

    /**
     * Credit account by value
     * 
     * @param id
     * @param msisdn
     * @param value
     * @return
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public TRechargingResponse creditByValue(String id, String msisdn, Double value)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TRechargingRequest parameters = objectFactory.createTRechargingRequest();
        parameters.setTransactionSN(id);
        parameters.setMSISDN(msisdn);
        // value must be negative (then remove unnecessary 0)
        parameters.setAddBalance(BigDecimal.valueOf(value).negate().stripTrailingZeros().toPlainString());
        TRechargingResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.recharging(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "recharging", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "recharging", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "recharging", "no response");
        }
        return response;
    }

    /**
     * Query all price plan
     * 
     * @param productCode
     * 
     * @return
     * @throws WebserviceTechnicalException
     */
    public TQueryAllPricePlanResponse queryAllPricePlan(String productCode) throws WebserviceTechnicalException {
        TQueryAllPricePlanRequest parameters = objectFactory.createTQueryAllPricePlanRequest();
        parameters.setProductCode(productCode);
        TQueryAllPricePlanResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.queryAllPricePlan(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "queryAllPricePlan");
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "queryAllPricePlan", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "queryAllPricePlan", "no response");
        }
        return response;
    }

    /**
     * Add a price plan (ModUserIndiPricePlan)
     * 
     * @param msisdn
     * @param productOfferingId
     * @throws InvalidMsisdnException
     * @throws WebserviceTechnicalException
     */
    public void addPricePlan(String msisdn, String productOfferingId)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TModUserIndiPricePlanRequest parameters = objectFactory.createTModUserIndiPricePlanRequest();
        parameters.setMSISDN(msisdn);
        TArrayOfPricePlanChgDto pricePlanList = objectFactory.createTArrayOfPricePlanChgDto();
        TPricePlanChgDto pricePlan = objectFactory.createTPricePlanChgDto();
        pricePlan.setPricePlanIndex(productOfferingId);
        // activation action
        pricePlan.setAction(1);
        // effect immediately
        pricePlan.setEffType(2L);
        pricePlanList.getPricePlanChgDto().add(pricePlan);
        parameters.setPricePlanChgDtoList(pricePlanList);
        // value must be negative (then remove unnecessary 0)
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            obwWebservice.modUserIndiPricePlan(parameters, header, null, null);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "modUserIndiPricePlan", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "modUserIndiPricePlan", e.getMessage(), e);
        }
    }

    /**
     * Get individual price plans
     * 
     * @param msisdn
     * @return
     * @throws InvalidMsisdnException
     * @throws WebserviceTechnicalException
     */
    public TQueryIndividualPricePlanResponse getPricePlans(String msisdn)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TQueryIndividualPricePlanRequest parameters = objectFactory.createTQueryIndividualPricePlanRequest();
        parameters.setMSISDN(msisdn);
        TQueryIndividualPricePlanResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.queryIndividualPricePlan(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "queryIndividualPricePlan", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "queryIndividualPricePlan", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "queryIndividualPricePlan", "no response");
        }
        return response;
    }

    /**
     * Modify balance
     * 
     * @param msisdn
     * @param amount
     * @return
     * @throws WebserviceTechnicalException
     * @throws InvalidMsisdnException
     */
    public TModifyAllBalReturnAllBalResponse modifyBalance(String msisdn, String amount)
            throws WebserviceTechnicalException, InvalidMsisdnException {
        TModifyAllBalReturnAllBalRequest parameters = objectFactory.createTModifyAllBalReturnAllBalRequest();
        parameters.setMSISDN(msisdn);
        parameters.setAddBalance(amount);
        parameters.setTransactionSN(UUID.randomUUID().toString());
        parameters.setAddDays(0L);
        TModifyAllBalReturnAllBalResponse response = null;
        TAuthHeader header = objectFactory.createTAuthHeader();
        header.setUsername(login);
        header.setPassword(password);
        header.setRequestID(generateRequestId());
        try {
            response = obwWebservice.modifyAllBalReturnAllBal(parameters, header);
        } catch (final SOAPFaultException e) {
            handleSoapFault(e, "modifyAllBalReturnAllBal", msisdn);
        } catch (final Exception e) {
            throw new WebserviceTechnicalException(WS_NAME, "modifyAllBalReturnAllBal", e.getMessage(), e);
        }
        if (response == null) {
            throw new WebserviceTechnicalException(WS_NAME, "modifyAllBalReturnAllBal", "no response");
        }
        return response;
    }

    private String generateRequestId() {
        // random 15 character number
        Random rnd = new Random();
        int n1 = 1000000 + rnd.nextInt(9000000);
        int n2 = 10000000 + rnd.nextInt(90000000);
        return "" + n1 + n2;
    }

}
