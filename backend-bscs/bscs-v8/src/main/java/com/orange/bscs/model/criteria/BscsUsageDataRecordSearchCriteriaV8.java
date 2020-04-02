package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;

public class BscsUsageDataRecordSearchCriteriaV8 extends BscsUsageDataRecordSearchCriteria {

    public BscsUsageDataRecordSearchCriteriaV8() {
        super();
    }

    @Override
    public void setContractId(String contractId) {
        // CO_ID
        recordCriteria.setLongValue("CO_ID", Long.valueOf(contractId));
    }

    @Override
    public void setCallType(Long callType) {
        // CALL_TYPE
        recordCriteria.setCallType(callType);
    }

    @Override
    public void setStartFromDate(Date startFromDate) {
        // START_FROM_DATE
        recordCriteria.setStartFromDate(startFromDate);
    }

    @Override
    public void setSearchLimit(int searchLimit) {
        // SEARCH_LIMIT
        recordCriteria.setSearchLimit(searchLimit);
    }

    @Override
    public void setStartToDate(Date startToDate) {
        // START_TO_DATE
        recordCriteria.setDateTimeValue(Constants.P_START_TO_DATE, startToDate);
    }

}
