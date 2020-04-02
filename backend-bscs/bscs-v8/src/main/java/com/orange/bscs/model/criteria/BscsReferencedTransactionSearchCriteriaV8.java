package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;

public class BscsReferencedTransactionSearchCriteriaV8 extends BscsReferencedTransactionSearchCriteria {

    public BscsReferencedTransactionSearchCriteriaV8() {
        super();
    }

    @Override
    public void setCustomerId(String customerId) {
        if (customerId != null) {
            setCustomerNumericId(Long.parseLong(customerId));
        }
    }

    @Override
    public void setCustomerNumericId(Long customerId) {
        model.setLongValue("RT_CUSTOMER_ID", customerId);

    }

    @Override
    public void setStartDate(Date startDate) {
        // RT_STARTDATE
        model.setDateTimeValue(Constants.P_RT_STARTDATE, startDate);

    }

    @Override
    public void setEndDate(Date endDate) {
        // RT_ENDDATE
        model.setDateTimeValue(Constants.P_RT_ENDDATE, endDate);

    }

    @Override
    public void setReferenceNumber(String referenceNumber) {
        // RT_CACHKNUM
        model.setStringValue(Constants.P_RT_CACHKNUM, referenceNumber);

    }

}
