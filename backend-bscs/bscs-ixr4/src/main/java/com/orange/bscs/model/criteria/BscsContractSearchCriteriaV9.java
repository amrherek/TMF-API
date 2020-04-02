package com.orange.bscs.model.criteria;

import com.orange.bscs.model.criteria.BscsContractSearchCriteria;

public class BscsContractSearchCriteriaV9 extends BscsContractSearchCriteria {

    public BscsContractSearchCriteriaV9() {
        super();
    }

    @Override
    public void setId(String contractId) {
        // CO_ID_PUB
        contractSearchCriteria.setContractIdPub(contractId);
    }

    @Override
    public void setDirectoryNumber(String msisdn) {
        // DIRNUM
        contractSearchCriteria.setDirectoryNumber(msisdn);
    }

    @Override
    public void setStorageMedium(String iccId) {
        // SM_NUM
        contractSearchCriteria.setStorageMedium(iccId);
    }

    @Override
    public void setCustomerNumericId(Long partyId) {
        // CS_ID
        contractSearchCriteria.setCustomerId(partyId);
    }

    @Override
    public void setSearcher(String searcher) {
        // SEARCHER
        contractSearchCriteria.setSearcher(searcher);
    }

}
