package com.orange.bscs.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteriaIXR2;

@Service
@Primary
public class BscsBillingServiceAdapterIXR2 extends BscsBillingServiceAdapterv9 {

    @Override
    public List<BscsBillingAssignment> getBillingAssignmentByContract(String contractId) {
        BscsBillingAssignmentSearchCriteriaIXR2 criteria = new BscsBillingAssignmentSearchCriteriaIXR2();
        criteria.setContractId(contractId);
        // parameter specific to IXR2
        criteria.setSearchAll(true);
        return getBscsBillingAssignments(criteria);
    }


}
