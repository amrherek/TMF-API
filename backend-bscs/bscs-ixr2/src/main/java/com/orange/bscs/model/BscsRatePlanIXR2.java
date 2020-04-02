package com.orange.bscs.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.product.BSCSRatePlan;

public class BscsRatePlanIXR2 extends BscsRatePlanV9 {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsRatePlanIXR2(BSCSRatePlan ratePlan) {
        super(ratePlan);
    }

    @Override
    public Character getPaymentMethodInd() {
        // not in BSCS model
        logger.error("PaymentMethodInd Does not exist in BSCS model for rate plans  in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

}
