package com.orange.bscs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.MarketSegmentServiceAdapterI;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract class BscsMarketSegmentServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MarketSegmentServiceAdapterI marketSegmentServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;


    /**
     * Call to CUSTOMER_INFO.READ
     * 
     * @param customerId
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsCustomerInfo getCustomerInfo(String customerId) throws BscsInvalidIdException;

    /**
     * Call to CUSTOMER_INFO.WRITE and optionally commit
     * 
     * @param info
     * @param commit
     * @throws BscsInvalidIdException
     */
    public void writeCustomerInfo(BscsCustomerInfo info, boolean commit) throws BscsInvalidIdException {
        try {
            marketSegmentServiceAdapter.customerInfoWrite(info.getBscsModel());
            if (commit) {
                ConnectionHolder.getCurrentConnection().commit();
            }
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
