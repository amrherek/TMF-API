package com.orange.bscs.model;

import com.orange.bscs.model.accounting.BSCSPaymentMethod;

/**
 * Model used for partial result of PAYMENT_METHODS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsPaymentMethod {

    protected BSCSPaymentMethod method;

    public BscsPaymentMethod(BSCSPaymentMethod method) {
        this.method = method;
    }

    public abstract String getId();

    public abstract String getDescription();

}
