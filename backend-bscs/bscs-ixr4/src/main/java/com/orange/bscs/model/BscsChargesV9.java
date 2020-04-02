package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSCharges;

public class BscsChargesV9 extends BscsCharges {

    public BscsChargesV9(BSCSCharges charges) {
        super(charges);
    }

    @Override
    public Long getChargeType() {
        // CHARGE_TYPE_ID
        return charges.getChargeId();
    }

    @Override
    public Double getChargeAmount() {
        // CHARGE_AMOUNT
        return charges.getChargeAmount();
    }

    @Override
    public String getChargeCurrency() {
        // CHARGE_AMOUNT#currency
        return charges.getChargeCurrencyCode();
    }

    @Override
    public Double getChargeAmountOverwritten() {
        // CHARGE_AMOUNT_OVW
        return charges.getChargeAmountOVW();
    }

    @Override
    public String getChargeCurrencyOverwritten() {
        // CHARGE_AMOUNT_OVW#currency
        return charges.getChargeCurrencyCodeOVW();
    }

}
