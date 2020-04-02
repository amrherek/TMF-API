package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;

public class BscsBillingAssignmentV9 extends BscsBillingAssignment {

    public BscsBillingAssignmentV9(BSCSBillingAssignment assignment) {
        super(assignment);
    }

    @Override
    public String getBillingAccountCode() {
        // BILLING_ACCOUNT_CODE
        return assignment.getBillingAccountCode();
    }

}
