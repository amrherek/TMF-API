package com.orange.bscs.model;

import com.orange.bscs.model.product.BSCSRatePlan;

public class BscsRatePlanV9 extends BscsRatePlan {

    public BscsRatePlanV9(BSCSRatePlan ratePlan) {
        super(ratePlan);
    }

    @Override
    public String getCode() {
        // RPCODE_PUB
        return ratePlan.getCodePublic();
    }

    @Override
    public Long getNumericCode() {
        // RPCODE
        return ratePlan.getCode();
    }

    @Override
    public String getDescription() {
        // RP_DES
        return ratePlan.getDescription();
    }

    @Override
    public Character getPaymentMethodInd() {
        // PAYMENT_METHOD_IND
        return ratePlan.getPaymentMethodInd();
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
