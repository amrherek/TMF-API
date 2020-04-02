package com.orange.bscs.model.criteria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BscsFinancialTransactionSearchCriteriaV9 extends BscsFinancialTransactionSearchCriteria {

    @Override
    public void setStartDate(Date startDate) {
        transactionSearchCriteria.setDateValue("REF_DATE_FROM", startDate);
    }

    @Override
    public void setCustomerNumericId(Long csId) {
        transactionSearchCriteria.setLongValue(Constants.P_CS_ID, csId);
    }

    @Override
    public void setEndDate(Date endDate) {
        transactionSearchCriteria.setDateValue("REF_DATE_UNTIL", endDate);
    }

    @Override
    public void setMinAmount(Double minAmount) {
        transactionSearchCriteria.setDoubleValue("MIN_PAYMENT_AMOUNT", minAmount);
    }

    @Override
    public void setLimit(Integer limit) {
        transactionSearchCriteria.setIntegerValue("RESULT_LIMIT", limit);
    }

    @Override
    public void setTransactionCodes(List<String> transactionCodes) {
        List<BSCSModel> values = new ArrayList<>();
        for (String transactionCode : transactionCodes) {
            BSCSModel trans = new BSCSModel();
            trans.setStringValue("TRANS_CODE_PUB", transactionCode);
            values.add(trans);
        }
        transactionSearchCriteria.setListOfBSCSModelValue("TRANS_CODES", values);
    }

}
