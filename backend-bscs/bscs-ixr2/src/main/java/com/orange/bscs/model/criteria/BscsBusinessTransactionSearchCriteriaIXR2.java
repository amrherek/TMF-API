package com.orange.bscs.model.criteria;

public class BscsBusinessTransactionSearchCriteriaIXR2 extends BscsBusinessTransactionSearchCriteriaV9 {

    private Integer wishedResults;

    public BscsBusinessTransactionSearchCriteriaIXR2() {
        super();
    }

    @Override
    public void setWishedRecords(Integer wishedResults) {
        // this parameter does not exist in IXR2
        this.wishedResults = wishedResults;
    }

    @Override
    public Integer getWishedRecords() {
        // this parameter does not exist in IXR2
        return wishedResults;
    }

}
