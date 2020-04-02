package com.orange.bscs.model;

import java.util.Objects;

import com.orange.bscs.model.accounting.BSCSPaymentMethod;

public class BscsPaymentMethodV8 extends BscsPaymentMethod {

    public BscsPaymentMethodV8(BSCSPaymentMethod method) {
        super(method);
    }

    @Override
    public String getId() {
        // PAYMETH_ID
        return Objects.toString(method.getId(), null);
    }

    @Override
    public String getDescription() {
        // PAYMETH_DESC
        return method.getDescription();
    }

}
