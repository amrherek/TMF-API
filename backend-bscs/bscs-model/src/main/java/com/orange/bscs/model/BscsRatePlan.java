package com.orange.bscs.model;

import com.orange.bscs.model.product.BSCSRatePlan;

/**
 * Model used for result of RATEPLANS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsRatePlan {

    protected BSCSRatePlan ratePlan;

    public BscsRatePlan(BSCSRatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public abstract String getCode();

    public abstract Long getNumericCode();

    public abstract String getDescription();

    public abstract String getShortDescription();

    public abstract Character getPaymentMethodInd();

    public abstract Long getVersion();

}
