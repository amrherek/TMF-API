package com.orange.bscs.model;

import java.util.Date;
import java.util.Objects;

import com.orange.bscs.model.contract.BSCSContractFreeUnit;

public class BscsContractFreeUnitAccountV8 extends BscsContractFreeUnitAccount {

    public BscsContractFreeUnitAccountV8(BSCSContractFreeUnit fua) {
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
        // FU_UOM
        return Objects.toString(fua.getLongValue("FU_UOM"), null);
    }

    @Override
    public String getCurrency() {
        // CURR
        return Objects.toString(fua.getLongValue("CURR"), null);
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
