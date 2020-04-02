package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.BSCSBillCycle.EnumBillCycleIntervalType;

public class BscsBillCycleV8 extends BscsBillCycle {

    public BscsBillCycleV8(BSCSBillCycle billCycle) {
        super(billCycle);
    }

    @Override
    public String getNumber() {
        // BILLCYCLE
        return billCycle.getBillCycle();
    }

    @Override
    public Integer getLength() {
        // LENGTH
        return billCycle.getLength();
    }

    @Override
    public EnumBillCycleIntervalType getIntervalType() {
        // INTERVAL_TYPE
        return billCycle.getIntervalType();
    }

}
