package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.usagedata.Balances;

/**
 * Model used for result of BALANCES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBalance {

    protected Balances balance;

    public BscsBalance(Balances balance) {
        this.balance = balance;
    }

    public abstract Date getNextFreeResetDate();

    public abstract String getUnitMeasurement();

    public abstract String getCurrencyId();

    public abstract Double getCreditValue();

    public abstract Double getActualValue();

}
