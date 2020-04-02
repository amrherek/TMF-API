package com.orange.bscs.model.criteria;

import com.orange.bscs.model.contract.BSCSContractSearch;

/**
 * Model used to set criteria for CONTRACTS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsContractSearchCriteria {

    protected BSCSContractSearch contractSearchCriteria;

    public BscsContractSearchCriteria() {
        this.contractSearchCriteria = new BSCSContractSearch();
    }

    public BSCSContractSearch getBscsModel() {
        return contractSearchCriteria;
    }

    public abstract void setId(String contractId);

    public abstract void setStorageMedium(String iccId);

    public abstract void setCustomerNumericId(Long partyId);

    public abstract void setDirectoryNumber(String msisdn);

    public abstract void setSearcher(String searcher);

}
