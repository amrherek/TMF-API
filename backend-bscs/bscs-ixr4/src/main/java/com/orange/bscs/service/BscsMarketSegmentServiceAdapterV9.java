package com.orange.bscs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsMarketSegmentServiceAdapterV9 extends BscsMarketSegmentServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public BscsCustomerInfo getCustomerInfo(String customerId) throws BscsInvalidIdException {
        try {
            BSCSModel customerInfoRead = marketSegmentServiceAdapter.customerInfoRead(null, customerId);
            return bscsObjectFactory.createBscsCustomerInfo(customerInfoRead);
        } catch (final SOIException e) {
            logger.debug("BSCS CUSTOMER_INFO.WRITE error with code: " + e.getCode());
            if (e.getCode() != null) {
                if (e.getCode().contains("FUNC_FRMWK_SRV.id0468") || e.getCode().contains("RC6701")) {
                    throw new BscsInvalidIdException(BscsFieldExceptionEnum.CUSTOMER_ID, e.getFirstArg(),
                            e.getMessage());
                }
            }
            throw e;
        }
    }

}
