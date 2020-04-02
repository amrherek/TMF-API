package com.orange.bscs.model.billing;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>
 * {@code
 * LIST_OF_ALL_DOCUMENTS{
 * CS_ID 						=  : java.lang.Long
 * CS_ID_PUB					=  : java.lang.String
 * CS_CODE						=  : java.lang.String
 * BILLING_ACCOUNT_ID			=  : java.lang.Long
 * BILLING_ACCOUNT_CODE			=  : java.lang.String
 * DOCUMENT_CODE				=  : java.lang.String
 * DOCUMENT_STATUS 				=  : java.lang.String //Added for now nned to modify
 * }
 * =>{
 *
 * DOCUMENT_ID 					=  : java.lang.Long
 * DOCUMENT_CODE				=  : java.lang.String
 * EXT_DOCUMENT_CODE			=  : java.lang.String
 * CS_ID						=  : java.lang.Long
 * CS_ID_PUB					=  : java.lang.String
 * CS_CODE						=  : java.lang.String
 * CO_ID						=  : java.lang.Long
 * CO_ID_PUB					=  : java.lang.String
 * BILLING_ACCOUNT_ID			=  : java.lang.Long
 * DOC_TYPE						=  : java.lang.String
 * BU_ID						=  : java.lang.Long
 * ENTRY_DATE					=  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 * DUE_DATE						=  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl //need to modify
 * DOCUMENT_AMOUNT_DOC			=  : com.lhs.ccb.soi.types.MoneyI
 * GL_CURRENCY					=  : java.lang.Long
 * DOCUMENT_PROCESSING_STATUS	=  : java.lang.String
 * SEARCH_IS_COMPLETE			= : java.lang.Boolean
 * }
 * }
 * * </pre>
 *
 * @author shaiji.babu
 */

public class BSCSDocuments extends BSCSModel {


    /*
     * @param P_CS_CODE
     */
    public void setCustCode(String custCode) {
        setStringValue(Constants.P_CS_CODE, custCode);
    }

    /**
     * @return P_CS_CODE
     */
    public String getsetCustCode() {
        return getStringValue(Constants.P_CS_CODE);
    }

    /*
     * @param P_CS_ID_PUB
     */
    public void setCsIdPub(String coIdPub) {
        setStringValue(Constants.P_CS_ID_PUB, coIdPub);
    }

    /**
     * @return P_CS_ID_PUB
     */
    public String getCsIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * @return P_DOC_INVOICEDATE
     */
    public Date getOhRefDate() {
        return getDateValue(Constants.P_DOC_REFDATE);
    }

    /**
     * @param P_DOC_INVOICEDATE
     * @return
     */
    public BSCSDocuments setOhRefDate(Date ohRefDate) {
        setDateValue(Constants.P_DOC_REFDATE, ohRefDate);
        return this;
    }

    /**
     * @return P_DOC_INVOICEDUEDATE
     */
    public Date getOhDueDate() {
        return getDateValue(Constants.P_DOC_DUEDATE);
    }

    /**
     * @param P_DOC_INVOICEDUEDATE
     * @return
     */
    public BSCSDocuments setOhDueDate(Date ohDueDate) {
        setDateValue(Constants.P_DOC_DUEDATE, ohDueDate);
        return this;
    }

    /**
     * @param P_DOC_INVOICESTATUS
     */
    public void setOhStatut(String ohStatus) {
        setStringValue(Constants.P_DOC_INVOICESTATUS, ohStatus);
    }

    /**
     * @return P_DOC_INVOICESTATUS
     */
    public String getOhStatut() {
        return getStringValue(Constants.P_DOC_INVOICESTATUS);
    }

    /**
     * @param P_DOC_INVOICEOPENAMOUNT
     */
    public void setOhOpenAmtDoc(Double ohOpenAmtDoc) {
        setDoubleValue(Constants.P_DOC_INVOICEOPENAMOUNT, ohOpenAmtDoc);
    }

    /**
     * @return P_DOC_INVOICEOPENAMOUNT
     */
    public Double getOhOpenAmtDoc() {
        return getMoneyAmountValue(Constants.P_DOC_INVOICEOPENAMOUNT);
    }

    /**
     * @return P_DOC_INVOICEAMOUNT
     */
    public Double getOhInvAmtDoc() {
        return getMoneyAmountValue(Constants.P_DOC_INVOICEAMOUNT);
    }

    public String getOhInvAmtDoc_currency() {
        return getMoneyCurrencyCodeValue(Constants.P_DOC_INVOICEAMOUNT);
    }

    /**
     * @param P_DOC_INVOICECURRENCY
     */
    public void setCurrencyId(Long currencyId) {
        setLongValue(Constants.P_DOC_INVOICECURRENCY, currencyId);
    }

    /**
     * @return P_DOC_INVOICECURRENCY
     */
    public Long getCurrencyId() {
        return getLongValue(Constants.P_DOC_INVOICECURRENCY);
    }


    /*
     * @param MAX_NUMBER_OF_FETCHED_RECORDS
     */
    public void setWishedRecords(Integer wishResult) {
        setIntegerValue(Constants.P_RESULT_LIMIT, wishResult);
    }

    /**
     * @return P_MAX_NUMBER_OF_FETCHED_RECORDS
     */

    public Integer getWishedRecords() {
        return getIntegerValue(Constants.P_RESULT_LIMIT);
    }

    /**
     * @param P_END_DOC_OHREFDATE
     */
    public void setEndOhRefDate(Date endDate) {
        setDateValue(Constants.P_END_DOC_OHREFDATE, endDate);
    }

    /**
     * @return P_END_DOC_OHREFDATE
     */

    public Date getEndOhRefDate() {
        return getDateValue(Constants.P_END_DOC_OHREFDATE);
    }


    /**
     * @param P_START_DOC_OHREFDATE
     */
    public void setStartOhRefDate(Date startDate) {
        setDateValue(Constants.P_START_DOC_OHREFDATE, startDate);
    }

    /**
     * @return P_START_DOC_OHREFDATE
     */

    public Date getStartOhRefDate() {
        return getDateValue(Constants.P_START_DOC_OHREFDATE);
    }

    public Long getInvoiceId() {
        return getLongValue(Constants.P_DOC_ID);
    }

    public void setInvoiceId(Long invoiceId) {
        setLongValue(Constants.P_DOC_ID, invoiceId);
    }

    /**
     * @return List<BSCSDocuments>
     */
    public List<BSCSDocuments> getFinancialTransactionSearch() {
        List<BSCSDocuments> res = this.getListOfBSCSModelValue(
                Constants.P_DOCUMENTS, BSCSDocuments.class);
        return res;
    }

    /**
     * @return true
     */

    public void setIgnoreBunisnessUnit(Boolean bunisnessUnit) {
        setBooleanValue(Constants.P_IGNORE_BU_IND, bunisnessUnit);
    }

    /**
     * @return Character
     */

    public void setDocStatus(Character status) {
        setCharacterValue(Constants.P_DOCUMENT_STATUS, status);
    }

    /**
     * @return Amount
     */

    public void setMinimumAmt(Double amt) {
        setDoubleValue(Constants.P_AMOUNT_DOC_MIN, 0.01);

    }

    public void setDocumentTypes(List<String> invoiceTypes) {
        SVLObjectListWrapper docTypes = AbstractSVLObjectFactory.createSVLObjectList();
        for (String invoiceTypeIn : invoiceTypes) {
            SVLObjectWrapper docType = AbstractSVLObjectFactory.createSVLObject();
            docType.setValue("DOC_TYPE", invoiceTypeIn);
            docTypes.add(docType);
        }
        getSVLObject().setValue("DOC_TYPES", docTypes);
    }

}
