package com.orange.bscs.model.criteria;

public class BscsContractSearchCriteriaV8 extends BscsContractSearchCriteria {

    public BscsContractSearchCriteriaV8() {
        super();
    }

    @Override
    public void setId(String contractId) {
        // CO_ID
        contractSearchCriteria.setContractId(Long.valueOf(contractId));
    }

    @Override
    public void setDirectoryNumber(String msisdn) {
        // DN_NUM
        contractSearchCriteria.setStringValue("DN_NUM", msisdn);
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
