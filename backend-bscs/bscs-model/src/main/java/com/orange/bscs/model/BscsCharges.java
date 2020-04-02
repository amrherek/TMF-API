package com.orange.bscs.model;

import com.orange.bscs.model.contract.BSCSCharges;

/**
 * Model used for partial result of SERVICES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCharges {

    protected BSCSCharges charges;

    public BscsCharges(BSCSCharges charges) {
        this.charges = charges;
    }

    public abstract Long getChargeType();

    public abstract Double getChargeAmount();

    public abstract String getChargeCurrency();

    public abstract Double getChargeAmountOverwritten();

    public abstract String getChargeCurrencyOverwritten();

}
