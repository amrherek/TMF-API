package com.orange.bscs.model.criteria;

import java.util.Date;

public class BscsBusinessTransactionSearchCriteriaV9 extends BscsBusinessTransactionSearchCriteria {

    public BscsBusinessTransactionSearchCriteriaV9() {
        super();
    }

    @Override
    public void setCustomerId(String customerId) {
        // CS_ID_PUB
        transactionSearchCriteria.setCsIdPub(customerId);
    }

    @Override
    public void setStartDate(Date startDate) {
        // START_BT_OHREFDATE
        transactionSearchCriteria.setStartBtOhRefDate(startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        // END_BT_OHREFDATE
        transactionSearchCriteria.setEndBtOhRefDate(endDate);
    }

    @Override
    public void setWishedRecords(Integer wishedResults) {
        // MAX_NUMBER_OF_FETCHED_RECORDS
        if (wishedResults != null) {
            transactionSearchCriteria.setMaxNumOfFetchRecords(wishedResults.longValue());
        }
    }

    @Override
    public Integer getWishedRecords() {
        // MAX_NUMBER_OF_FETCHED_RECORDS
        if (transactionSearchCriteria.getMaxNumOfFetchRecords() != null) {
            return transactionSearchCriteria.getMaxNumOfFetchRecords().intValue();
        }
        return null;
    }

    @Override
    public void setTransactionType(String type) {
        // BT_OHSTATUS
        transactionSearchCriteria.setBtOhStatut(type);
    }

}
