package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.contract.BSCSContractFreeUnit;

/**
 * Model used for result of CONTRACT_FUA.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractFreeUnitAccount {

    protected BSCSContractFreeUnit fua;

    public BscsContractFreeUnitAccount(BSCSContractFreeUnit fua) {
        this.fua = fua;
    }

    public abstract Date getPeriodFrom();

    public abstract Date getPeriodTo();

    public abstract String getUnitMeasurement();

    public abstract String getCurrency();

    public abstract Double getGrantedUnits();

    public abstract Double getAppliedUnits();

}
