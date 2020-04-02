package com.orange.bscs.model;

import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;

/**
 * Model used for partial result of BILLING_ACCOUNT_ASSIGNMENT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillingAssignment {

    protected BSCSBillingAssignment assignment;

    public BscsBillingAssignment(BSCSBillingAssignment assignment) {
        this.assignment = assignment;
    }

    public abstract String getBillingAccountCode();

}
