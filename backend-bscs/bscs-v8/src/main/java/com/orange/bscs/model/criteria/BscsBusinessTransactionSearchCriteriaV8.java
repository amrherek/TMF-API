package com.orange.bscs.model.criteria;

import java.util.Date;

public class BscsBusinessTransactionSearchCriteriaV8 extends BscsBusinessTransactionSearchCriteria {

    private Integer wishedResults;

    public BscsBusinessTransactionSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCustomerId(String customerId) {
        // BT_CUSTOMER_ID
        transactionSearchCriteria.setLongValue("BT_CUSTOMER_ID", Long.valueOf(customerId));
    }

    @Override
    public void setStartDate(Date startDate) {
        // START_BT_OHREFDATE but as date (not datetime)
        transactionSearchCriteria.setDateValue("START_BT_OHREFDATE", startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        // END_BT_OHREFDATE but as date (not datetime)
        transactionSearchCriteria.setDateValue("END_BT_OHREFDATE", endDate);
    }

    @Override
    public void setWishedRecords(Integer wishedResults) {
        // this parameter does not exist in V8
        this.wishedResults = wishedResults;
    }

    @Override
    public Integer getWishedRecords() {
        // this parameter does not exist in V8
        return wishedResults;
    }

    @Override
    public void setTransactionType(String type) {
        // BT_OHSTATUS
        transactionSearchCriteria.setBtOhStatut(type);
    }

}
