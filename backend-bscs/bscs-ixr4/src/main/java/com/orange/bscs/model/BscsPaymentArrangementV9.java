package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;

public class BscsPaymentArrangementV9 extends BscsPaymentArrangement {

    public BscsPaymentArrangementV9(BSCSPaymentArrangement paymentArrangement) {
        super(paymentArrangement);
    }

    @Override
    public String getPaymentMethodId() {
        // CSP_PMNT_ID_PUB
        return paymentArrangement.getPaymentMethodIdPub();
    }

}
