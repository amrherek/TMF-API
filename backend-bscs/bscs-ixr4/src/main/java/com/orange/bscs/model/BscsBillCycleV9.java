package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.BSCSBillCycle.EnumBillCycleIntervalType;

public class BscsBillCycleV9 extends BscsBillCycle {

    public BscsBillCycleV9(BSCSBillCycle billCycle) {
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
