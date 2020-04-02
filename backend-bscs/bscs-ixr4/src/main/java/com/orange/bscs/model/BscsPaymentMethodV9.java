package com.orange.bscs.model;

import com.orange.bscs.model.accounting.BSCSPaymentMethod;

public class BscsPaymentMethodV9 extends BscsPaymentMethod {

    public BscsPaymentMethodV9(BSCSPaymentMethod method) {
        super(method);
    }

    @Override
    public String getId() {
        // PAYMETH_ID_PUB
        return method.getIdPub();
    }

    @Override
    public String getDescription() {
        // PAYMETH_DESC
        return method.getDescription();
    }

}
