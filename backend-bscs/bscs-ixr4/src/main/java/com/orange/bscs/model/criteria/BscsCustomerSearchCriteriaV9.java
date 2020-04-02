package com.orange.bscs.model.criteria;

public class BscsCustomerSearchCriteriaV9 extends BscsCustomerSearchCriteria {

    public BscsCustomerSearchCriteriaV9() {
        super();
    }

    @Override
    public void setFirstName(String name) {
        // ADR_FNAME
        customerSearchCriteria.setFirstName(name);
    }

    @Override
    public void setLastName(String name) {
        // ADR_LNAME
        customerSearchCriteria.setLastName(name);
    }

    @Override
    public void setEmail(String email) {
        // ADR_EMAIL
        customerSearchCriteria.setEmail(email);
    }

    @Override
    public void setFlagCase(boolean caseSensitiveSearch) {
        // FLAG_CASE
        customerSearchCriteria.setFlagMatchCase(caseSensitiveSearch);
    }

    @Override
    public void setCompanyName(String companyName) {
        // ADR_NAME
        customerSearchCriteria.setAddressName(companyName);
    }

}
