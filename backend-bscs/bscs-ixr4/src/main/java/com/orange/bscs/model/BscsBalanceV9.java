package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.usagedata.Balances;

public class BscsBalanceV9 extends BscsBalance {

    public BscsBalanceV9(Balances balance) {
        super(balance);
    }

    @Override
    public Date getNextFreeResetDate() {
        // NEXT_RESET_DATE
        return balance.getNextFreeResetDate();
    }

    @Override
    public String getUnitMeasurement() {
        // UMCODE_PUB
        return balance.getUmCodePub();
    }

    @Override
    public String getCurrencyId() {
        // CURRENCY_ID_PUB
        return balance.getCurrencyIdPub();
    }

    @Override
    public Double getCreditValue() {
        // CREDIT_VALUE
        return balance.getCreditValue();
    }

    @Override
    public Double getActualValue() {
        // ACTUAL_VALUE
        return balance.getActualValue();
    }

}
