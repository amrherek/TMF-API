package com.orange.mea.apisi.productordering.obw.backend.zteIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.mea.apisi.obw.webservice.EmergencyWebService;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;
import com.orange.mea.apisi.productordering.backend.BundleBackend;
import com.orange.mea.apisi.productordering.backend.EmergencyBackend;
import com.orange.mea.apisi.productordering.backend.TransferCreditBackend;
import com.orange.mea.apisi.productordering.exception.BadMsisdnException;

@Component
@Primary
public class InServiceBackend implements TransferCreditBackend, BundleBackend, EmergencyBackend {

    @Autowired
    private ZteWebService webService;

    @Autowired
    private EmergencyWebService emergencyWebService;

    public static final String EMERGENCY_CREDIT_OPERATION_ID = "emergencyCreditFee";

    /**
     * Add a bundle of data/voice/sms
     * 
     */
    @Override
    public void addBundle(String msisdn, String productOfferingId) throws ApiException {
        try {
            webService.addPricePlan(msisdn, productOfferingId);
        } catch (InvalidMsisdnException e) {
            throw new BadMsisdnException(e.getMsisdn());
        }
    }

    @Override
    public void transfer(String msisdn, String targetMsisdn, String amount) throws ApiException {
        try {
            // remove credit from msisdn
            webService.modifyBalance(msisdn, "-" + amount);
            // add credit to targetMsisdn
            webService.modifyBalance(targetMsisdn, amount);
        } catch (InvalidMsisdnException e) {
            throw new BadMsisdnException(e.getMsisdn());
        }
    }

    @Override
    public void addEmergencyCredit(String msisdn, String productOfferingId, String amount) throws ApiException {
        // validation: amount must not be null and productOfferingId must be
        // fixed
        if (amount == null) {
            throw new MissingParameterException("orderItem.product.productCharacteristic[volume]");
        }
        if (!EMERGENCY_CREDIT_OPERATION_ID.equals(productOfferingId)) {
            throw new BadParameterValueException("orderItem.productOffering.id", productOfferingId,
                    EMERGENCY_CREDIT_OPERATION_ID);
        }

        emergencyWebService.emergencyCredit(msisdn, amount);
    }

    @Override
    public void addEmergencyData(String msisdn, String productOfferingId, String amount) throws ApiException {
        throw new NotImplementedException("Add emergency data");
    }

    @Override
    public void addEmergencyVoice(String msisdn, String productOfferingId, String amount) throws ApiException {
        throw new NotImplementedException("Add emergency voice");
    }

}
