package com.orange.bscs.service;

import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsMarketSegmentServiceAdapterV8 extends BscsMarketSegmentServiceAdapter {

    @Override
    public BscsCustomerInfo getCustomerInfo(String customerId) {
        BSCSModel customerInfoRead = marketSegmentServiceAdapter.customerInfoRead(Long.valueOf(customerId), null);
        return bscsObjectFactory.createBscsCustomerInfo(customerInfoRead);
    }

    @Override
    public void writeCustomerInfo(BscsCustomerInfo info, boolean commit) throws BscsInvalidIdException {
        try {
            super.writeCustomerInfo(info, commit);
        } catch (final SOIException exception) {
            if (exception.getCode() != null && exception.getCode().contains("RC9001-003")) {
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, info.getCustomerId(),
                        exception.getFirstArg());
            }
            throw exception;
        }
    }

}
