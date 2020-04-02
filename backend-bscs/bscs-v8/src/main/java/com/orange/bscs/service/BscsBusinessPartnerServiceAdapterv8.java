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
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsBusinessPartnerServiceAdapterv8 extends BscsBusinessPartnerServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsAddress getAddress(String customerId, EnumAddressRole role) throws BscsInvalidIdException {
        try {
            BSCSAddress addressRead = businessPartnerServiceAdapter.addressRead(Long.valueOf(customerId), null, role);
            return bscsObjectFactory.createBscsAddress(addressRead);
        } catch (final SOIException exception) {
            logger.debug("BSCS ADDRESS.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && exception.getCode().contains("RC9001-003")) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, customerId,
                        exception.getMessage());
            }
            throw exception;
        }
    }

    @Override
    public BscsCustomer getCustomer(String customerId) throws BscsInvalidIdException {
        try {
            // parameter SYNC_WITH_DB does not exist in V8
            BSCSCustomer customer = businessPartnerServiceAdapter.customerRead(Long.valueOf(customerId), null, true);
            return bscsObjectFactory.createBscsCustomer(customer);
        } catch (final SOIException exception) {
            logger.debug("BSCS CUSTOMER.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && exception.getCode().contains("RC9001-003")) {
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
                    .paymentArrangementsRead(Long.valueOf(customerId), null, true);
            if (paymentArrangementList.isEmpty()) {
                return null;
            }
            return bscsObjectFactory.createBscsPaymentArrangement(paymentArrangementList.get(0));
        } catch (final SOIException exception) {
            logger.debug("BSCS PAYMENT_ARRANGEMENTS.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && exception.getCode().contains("RC9001-003")) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, customerId,
                        exception.getMessage());
            }
            throw exception;
        }
    }

    @Override
    public void writeAddress(BscsAddress address, boolean commit)
            throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            super.writeAddress(address, commit);
        } catch (final SOIException exception) {
            if (exception.getCode() != null) {
                if (exception.getCode().contains("RC9001-003")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, address.getCustomerId(),
                            "Invalid customer id: {" + address.getCustomerId() + "}");
                }
                if (exception.getCode().contains("RC2014")) {
                    // bad value e.g for identificationType
                    throw new BscsInvalidFieldException(
                            BscsFieldExceptionEnum.buildBscsFieldExceptionEnum(exception.getFirstArg()),
                            exception.getArg(1), "One of the field has an incorrect value");
                }
                if (exception.getCode().contains("RC2026")) {
                    // error during commit on field validation e.g. missing
                    // '@' in email address
                    throw new BscsInvalidFieldException(
                            BscsFieldExceptionEnum.buildBscsFieldExceptionEnum(exception.getArg(1)), null,
                            "Bad field in individual content");
                }
                if (exception.getCode().contains("RC9100")
                        && "BusinessPartner.GenderTitleNotMatch".equals(exception.getFirstArg())) {
                    // error during commit on field validation: title not compatible with gender
                    throw new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE, null,
                            "Gender and title are not compatible");
                }
            }
            throw exception;
        }
    }

}
