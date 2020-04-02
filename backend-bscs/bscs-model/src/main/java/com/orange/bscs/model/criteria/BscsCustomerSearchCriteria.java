package com.orange.bscs.model.criteria;

import com.orange.bscs.model.businesspartner.BSCSCustomersSearchRequest;

/**
 * Model used to set criteria for CUSTOMERS.SEARCH command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsCustomerSearchCriteria {

    protected BSCSCustomersSearchRequest customerSearchCriteria;

    public BscsCustomerSearchCriteria() {
        this.customerSearchCriteria = new BSCSCustomersSearchRequest();
    }

    public BSCSCustomersSearchRequest getBscsModel() {
        return customerSearchCriteria;
    }

    public abstract void setFirstName(String name);

    public abstract void setLastName(String name);

    public abstract void setEmail(String email);

    /**
     * If true, the search is case sensitive
     * 
     * @param caseSensitiveSearch
     */
    public abstract void setFlagCase(boolean caseSensitiveSearch);

    public abstract void setCompanyName(String companyName);

}
