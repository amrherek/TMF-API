package com.orange.bscs.model.criteria;

import com.orange.bscs.model.resource.BSCSStorageMediumSearch;

/**
 * Model used to set criteria for STORAGE_MEDIUM.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsStorageMediumSearchCriteria {

    protected BSCSStorageMediumSearch searchCriteria;

    public BscsStorageMediumSearchCriteria() {
        this.searchCriteria = new BSCSStorageMediumSearch();
    }

    public BSCSStorageMediumSearch getBscsModel() {
        return searchCriteria;
    }

    public abstract void setSubmarketId(Long submId);

    public abstract void setNetworkId(Long plCode);

    public abstract void setType(Long smcId);

    public abstract void setSerialNumber(String number);

}
