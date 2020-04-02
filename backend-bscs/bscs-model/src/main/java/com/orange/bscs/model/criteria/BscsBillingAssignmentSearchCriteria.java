package com.orange.bscs.model.criteria;

import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;

/**
 * Model used to set criteria for SBILLING_ACCOUNT_ASSIGNMENT.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsBillingAssignmentSearchCriteria {

    protected BSCSBillingAssignment billingAssignment;

    public BscsBillingAssignmentSearchCriteria() {
        billingAssignment = new BSCSBillingAssignment();
    }

    public BSCSBillingAssignment getBscsModel() {
        return billingAssignment;
    }

    public abstract void setContractId(String contractId);

}
