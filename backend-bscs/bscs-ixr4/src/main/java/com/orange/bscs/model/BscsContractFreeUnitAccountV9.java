package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.contract.BSCSContractFreeUnit;

public class BscsContractFreeUnitAccountV9 extends BscsContractFreeUnitAccount {

    public BscsContractFreeUnitAccountV9(BSCSContractFreeUnit fua) {
        super(fua);
    }

    @Override
    public Date getPeriodFrom() {
        // PERIOD_FROM
        return fua.getPeriodFrom();
    }

    @Override
    public Date getPeriodTo() {
        // PERIOD_TO
        return fua.getPeriodTo();
    }

    @Override
    public String getUnitMeasurement() {
        // FU_UOM_PUB
        return fua.getUomtPub();
    }

    @Override
    public String getCurrency() {
        // CURR_PUB
        return fua.getCurrencyPub();
    }

    @Override
    public Double getGrantedUnits() {
        // FU_GRANT_INT
        return fua.getGrantedUnits();
    }

    @Override
    public Double getAppliedUnits() {
        // FU_APPL_INT
        return fua.getAppliedUnits();
    }

}
