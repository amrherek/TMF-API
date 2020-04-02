package com.orange.bscs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.businesspartner.BSCSAddress;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsBusinessPartnerServiceAdapterv9 extends BscsBusinessPartnerServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsAddress getAddress(String customerId, EnumAddressRole role) throws BscsInvalidIdException {
        try {
            BSCSAddress addressRead = businessPartnerServiceAdapter.addressRead(null, customerId, role);
            return bscsObjectFactory.createBscsAddress(addressRead);
        } catch (final SOIException exception) {
            logger.debug("BSCS ADDRESS.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && (exception.getCode().contains("FUNC_FRMWK_SRV.id0468")
                    || exception.getCode().contains("RC6701"))) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, customerId,
                        exception.getMessage());
            }
            throw exception;
        }
    }

    /**
     * Call to CUSTOMER.READ with SYNC_WITH_DB set to true
     * 
     * @return
     * @throws BscsInvalidIdException
     */
    @Override
    public BscsCustomer getCustomer(String customerId) throws BscsInvalidIdException {
        try {
            // SYNC_WITH_DB is set to true
            BSCSCustomer customer = businessPartnerServiceAdapter.customerRead(null, customerId, true);
            return bscsObjectFactory.createBscsCustomer(customer);
        } catch (final SOIException exception) {
            logger.debug("BSCS CUSTOMER.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && (exception.getCode().contains("FUNC_FRMWK_SRV.id0468")
                    || exception.getCode().contains("RC6701"))) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, customerId,
                        exception.getMessage());
            }
            throw exception;
        }
    }

    @Override
    public BscsPaymentArrangement getCurrentPaymentArrangement(String customerId) throws BscsInvalidIdException {
        try {
            final List<BSCSPaymentArrangement> paymentArrangementList = businessPartnerServiceAdapter
                    .paymentArrangementsRead(null, customerId, true);
            if (paymentArrangementList.isEmpty()) {
                return null;
            }
            return bscsObjectFactory.createBscsPaymentArrangement(paymentArrangementList.get(0));
        } catch (final SOIException exception) {
            logger.debug("BSCS PAYMENT_ARRANGEMENTS.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && (exception.getCode().contains("FUNC_FRMWK_SRV.id0468")
                    || exception.getCode().contains("RC6701"))) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, customerId,
                        exception.getMessage());
            }
            throw exception;
        }
    }

}
