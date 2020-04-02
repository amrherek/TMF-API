package com.orange.bscs.model.criteria;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.utils.config.Constants;

public class BscsFinancialDocumentSearchCriteriaV9 extends BscsFinancialDocumentSearchCriteria {

    public BscsFinancialDocumentSearchCriteriaV9() {
        super();
    }

    @Override
    public void setIgnoreBusinessUnitIndicator(boolean ignoreBu) {
        // IGNORE_BU_IND
        documentSearchCriteria.setIgnoreBunisnessUnit(ignoreBu);
    }

    @Override
    public void setDocumentTypes(List<String> documentTypes) {
        // DOC_TYPES / DOC_TYPE
        documentSearchCriteria.setDocumentTypes(documentTypes);
    }

    @Override
    public void setOrder(Character order) {
        // ORDR_REF_DATE
        documentSearchCriteria.setCharacterValue(Constants.P_ORDR_REF_DATE, order);
    }

    @Override
    public void setCustomerId(String customerId) {
        // CS_ID_PUB
        documentSearchCriteria.setCsIdPub(customerId);
    }

    @Override
    public void setStartDate(Date startDate) {
        // REF_DATE_FROM
        documentSearchCriteria.setStartOhRefDate(startDate);
    }

    @Override
    public void setEndDate(Date endDate) {
        // REF_DATE_UNTIL
        documentSearchCriteria.setEndOhRefDate(endDate);
    }

    @Override
    public void setWishedRecords(Integer wishedResults) {
        // RESULT_LIMIT
        documentSearchCriteria.setWishedRecords(wishedResults);
    }

    @Override
    public void setCustomerCode(String customerCode) {
        // CS_CODE
        documentSearchCriteria.setCustCode(customerCode);
    }

}
