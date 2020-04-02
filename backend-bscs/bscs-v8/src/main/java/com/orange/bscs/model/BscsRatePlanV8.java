package com.orange.bscs.model;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.product.BSCSRatePlan;

public class BscsRatePlanV8 extends BscsRatePlan {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsRatePlanV8(BSCSRatePlan ratePlan) {
        super(ratePlan);
    }

    @Override
    public String getCode() {
        // RP_CODE
        return Objects.toString(getNumericCode(), null);
    }

    @Override
    public Long getNumericCode() {
        // RP_CODE
        return ratePlan.getLongValue("RP_CODE");
    }

    @Override
    public String getDescription() {
        // RP_DES
        return ratePlan.getDescription();
    }

    @Override
    public Character getPaymentMethodInd() {
        logger.error("PaymentMethodInd Does not exist in BSCS model for rate plans  in IXR2");
        throw new RuntimeException("Not available in IXR2");
    }

    @Override
    public String getShortDescription() {
        // RP_SHDES
        return ratePlan.getShortDescription();
    }

    @Override
    public Long getVersion() {
        // RP_VSCODE
        return ratePlan.getVersion();
    }

}
