package com.orange.bscs.model;

import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.BSCSBillCycle.EnumBillCycleIntervalType;

/**
 * Model used for result of BILL_CYCLES.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillCycle {

    protected BSCSBillCycle billCycle;

    public BscsBillCycle(BSCSBillCycle billCycle) {
        this.billCycle = billCycle;
    }

    public abstract String getNumber();

    public abstract Integer getLength();

    public abstract EnumBillCycleIntervalType getIntervalType();

}
