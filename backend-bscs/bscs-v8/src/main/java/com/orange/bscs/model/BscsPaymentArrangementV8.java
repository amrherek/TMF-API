package com.orange.bscs.model;

import java.util.Objects;

import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;

public class BscsPaymentArrangementV8 extends BscsPaymentArrangement {

    public BscsPaymentArrangementV8(BSCSPaymentArrangement paymentArrangement) {
        super(paymentArrangement);
    }

    @Override
    public String getPaymentMethodId() {
        // CSP_PMNT_ID
        return Objects.toString(paymentArrangement.getPaymentMethodId(), null);
    }

}
