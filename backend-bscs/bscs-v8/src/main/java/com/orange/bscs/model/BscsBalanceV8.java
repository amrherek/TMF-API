package com.orange.bscs.model;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.bscs.model.usagedata.Balances;

public class BscsBalanceV8 extends BscsBalance {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BscsBalanceV8(Balances balance) {
        super(balance);
    }

    @Override
    public Date getNextFreeResetDate() {
        logger.error("NextFreeResetDate Does not exist in BSCS model for rate plans  in IXR2");
        throw new RuntimeException("Not available in V8");
    }

    @Override
    public String getUnitMeasurement() {
        // UMCODE
        return Objects.toString(balance.getLongValue("UMCODE"), null);
    }

    @Override
    public String getCurrencyId() {
        // CURRENCY_ID
        return Objects.toString(balance.getLongValue("CURRENCY_ID"), null);
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
