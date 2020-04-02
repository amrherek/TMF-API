package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;

/**
 * Model used for result of PAYMENT_ARRANGEMENTS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsPaymentArrangement {

    protected BSCSPaymentArrangement paymentArrangement;

    public BscsPaymentArrangement(BSCSPaymentArrangement paymentArrangement) {
        this.paymentArrangement = paymentArrangement;
    }

    public abstract String getPaymentMethodId();

}
