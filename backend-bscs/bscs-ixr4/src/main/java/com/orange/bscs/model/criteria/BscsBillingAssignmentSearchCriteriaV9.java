package com.orange.bscs.model.criteria;

public class BscsBillingAssignmentSearchCriteriaV9 extends BscsBillingAssignmentSearchCriteria {

    public BscsBillingAssignmentSearchCriteriaV9() {
        super();
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID_PUB
        billingAssignment.setContractIdPub(contractId);
    }

}
