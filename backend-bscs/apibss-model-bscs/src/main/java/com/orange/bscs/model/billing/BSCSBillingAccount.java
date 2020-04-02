package com.orange.bscs.model.billing;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 *
 *<pre>{@code
 * BILLING_ACCOUNT.READ {
 *   BILLING_ACCOUNT_CODE =  : java.lang.String
 *   BILLING_ACCOUNT_ID   =  : java.lang.Long
 * * MODE                 =  : java.lang.Character
 * }
 * => {
 *   BALANCE_GLACODE      =  : java.lang.String
 *   BILLING_ACCOUNT_CODE =  : java.lang.String
 *   BILLING_ACCOUNT_NAME =  : java.lang.String
 *   BM_ID                =  : java.lang.Long
 *   BM_ID_PUB            =  : java.lang.String
 *   CALL_DETAILS_FLAG    =  : java.lang.Boolean
 *   CONTACT_SEQNO        =  : java.lang.Long
 *   CONTACT_SEQNO_TEMP   =  : java.lang.Long
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   FOREIGN_CURCY_LVL    =  : java.lang.Long
 *   GLCODE               =  : java.lang.String
 *   INVOICING_IND        =  : java.lang.Character
 *   LAST_BILLED_DATE     =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   LIST_OF_BILLING_ACCOUNT_VERSIONS = sub element : com.lhs.ccb.common.soiimpl.Na
 * medValueContainerList
 *  -[0]BILLING_ACCOUNT_VERSION =  : java.lang.Long
 *  -[0]INCORPORATED_IND     =  : java.lang.Character
 *  -[0]INDIVIDUALTAX_FROM_IND =  : java.lang.Character
 *  -[0]JURISDICTION_ID      =  : java.lang.Long
 *  -[0]STATUS               =  : java.lang.Character
 *  -[0]TAX_EXEMPTION_FROM_IND =  : java.lang.Character
 *  -[0]VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   NUMBER_COPIES        =  : java.lang.Long
 *   PAYMENT_SEQNO        =  : java.lang.Long
 *   PRIMARY_CONVRATETYPE_ID =  : java.lang.Long
 *   PRIMARY_DOCUMENT_CURRENCY_ID =  : java.lang.Long
 *   PRIMARY_DOCUMENT_CURRENCY_ID_PUB =  : java.lang.String
 *   PRIMARY_FLAG         =  : java.lang.Boolean
 *   SECONDARY_CONVRATETYPE_ID =  : java.lang.Long
 *   SECONDARY_DOCUMENT_CURRENCY_ID =  : java.lang.Long
 *   SECONDARY_DOCUMENT_CURRENCY_ID_PUB =  : java.lang.String
 *   TERMCODE             =  : java.lang.Long
 * }
 * }</pre>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSBillingAccount extends BSCSModel {

    private boolean isPayer;
    private boolean isReceiver;

    /**
     * Input of BILLING_ACCOUNT.READ, parameter BILLING_ACCOUNT_ID
     *
     * @param billingAccountId a {@link java.lang.Long} object.
     */
    public void setBillingAccountId(Long billingAccountId) {
        setLongValue(Constants.P_BILLING_ACCOUNT_ID, billingAccountId);
    }

    /**
     * Fluent setter on the account ID.
     *
     * @param billingAccountId
     *            the account ID to set
     * @return the current instance
     */
    public BSCSBillingAccount withId(Long billingAccountId) {
        this.setBillingAccountId(billingAccountId);
        return this;
    }

    /**
     * Returned by BILLING_ACCOUNT.SEARCH but not BILLING_ACCOUNT.READ
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBillingAccountId() {
        return getLongValue(Constants.P_BILLING_ACCOUNT_ID);
    }

    /**
     * Input of BILLING_ACCOUNT.READ, parameter MODE
     *
     * @param mode a char.
     */
    public void setMode(EnumBillingAccountReadMod mode) {
        setCharacterValue(Constants.P_MODE, null==mode ? null : mode.toCharacter());
    }

    /**
     * Input of BILLING_ACCOUNT.SEARCH, parameter MODE
     *
     * @param mode a char.
     */
    public void setMode(EnumBillingAccountSearchMod mode) {
        setCharacterValue(Constants.P_MODE, null==mode ? null : mode.toCharacter());
    }

    /**
     * <p>getCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerId() {
        return getLongValue(Constants.P_CS_ID);
    }

    /**
     * <p>setCustomerId.</p>
     *
     * @param customerId a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long customerId) {
        setLongValue(Constants.P_CS_ID, customerId);
    }

    /**
     * <p>getCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * <p>setCustomerIdPub.</p>
     *
     * @param csIdPub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String csIdPub) {
        setStringValue(Constants.P_CS_ID_PUB, csIdPub);
    }

    /**
     * <p>getBillingAccountCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingAccountCode() {
        return getStringValue(Constants.P_BILLING_ACCOUNT_CODE);
    }

    /**
     * <p>setBillingAccountCode.</p>
     *
     * @param billingAccountCode a {@link java.lang.String} object.
     */
    public void setBillingAccountCode(String billingAccountCode) {
        setStringValue(Constants.P_BILLING_ACCOUNT_CODE, billingAccountCode);
    }

    /**
     * <p>getBillingAccountName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingAccountName() {
        return getStringValue(Constants.P_BILLING_ACCOUNT_NAME);
    }

    /**
     * <p>setBillingAccountName.</p>
     *
     * @param billingAccountName a {@link java.lang.String} object.
     */
    public void setBillingAccountName(String billingAccountName) {
        setStringValue(Constants.P_BILLING_ACCOUNT_NAME, billingAccountName);
    }

    /**
     * <p>getContactSequenceNumber.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getContactSequenceNumber() {
        return getLongValue(Constants.P_CONTACT_SEQNO);
    }

    /**
     * <p>setContactSequenceNumber.</p>
     *
     * @param contactSeqNo a {@link java.lang.Long} object.
     */
    public void setContactSequenceNumber(Long contactSeqNo) {
        setLongValue(Constants.P_CONTACT_SEQNO, contactSeqNo);
    }

    /**
     * <p>getContactSequenceNumberTemp.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getContactSequenceNumberTemp() {
        return getLongValue(Constants.P_CONTACT_SEQNO_TEMP);
    }

    /**
     * <p>setContactSequenceNumberTemp.</p>
     *
     * @param contactSeqNoTemp a {@link java.lang.Long} object.
     */
    public void setContactSequenceNumberTemp(Long contactSeqNoTemp) {
        setLongValue(Constants.P_CONTACT_SEQNO_TEMP, contactSeqNoTemp);
    }

    /**
     * <p>getLedgerSegment (GLCODE).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLedgerSegment() {
        return getStringValue(Constants.P_GLCODE);
    }

    /**
     * <p>setLedgerSegment (GLCODE).</p>
     *
     * @param glcode a {@link java.lang.String} object.
     */
    public void setLedgerSegment(String glcode) {
        setStringValue(Constants.P_GLCODE, glcode);
    }

    /**
     * Medium for the monthly-bill of the customer
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBillMediumId() {
        return getLongValue(Constants.P_BM_ID);
    }

    /**
     * Medium for the monthly-bill of the customer
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillMediumIdPub() {
        return getStringValue(Constants.P_BM_ID_PUB);
    }
    
    /**
     * Medium for the monthly-bill of the customer
     *
     */
    public void setBillMediumId(Long mediumId) {
        setLongValue(Constants.P_BM_ID, mediumId);
    }

    /**
     * Medium for the monthly-bill of the customer
     *
     */
    public void setBillMediumIdPub(String mediumIdPub) {
        setStringValue(Constants.P_BM_ID_PUB, mediumIdPub);
    }

    // WARN: NEED in R2 but DOESN'T EXIST IN R3
    /**
     * <p>getPaymentSequenceNum.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    @Deprecated
    public Long getPaymentSequenceNum() {
        return getLongValue(Constants.P_PAYMENT_SEQNO);
    }
    @Deprecated
    public void setPaymentSequenceNum(Long cspseqno) {
        setLongValue(Constants.P_PAYMENT_SEQNO,cspseqno);
    }

    /**
     * Used by BILLING_ACCOUNT.SEARCH
     *
     * @param withInvoicing a {@link java.lang.Character} object.
     */
    public void setInvoicingIndicator(EnumInvoicingIndicator withInvoicing) {
        setCharacterValue(Constants.P_INVOICING_IND, EnumInvoicingIndicator.toCharacter(withInvoicing));
    }
    
    public EnumInvoicingIndicator getInvoicingIndicator() {
        return EnumInvoicingIndicator.parse(getCharacterValue(Constants.P_INVOICING_IND));
    }

    /**
     * Used by BILLING_ACCOUNT.SEARCH
     *
     * @param count a {@link java.lang.Integer} object.
     */
    public void setSearchCount(Integer count) {
        setIntegerValue(Constants.P_SRCH_COUNT, count);
    }

    /**
     * set by BillingAccountService.getBillingAccountOfPartyId Warn : is is not
     * a field, lost if this object is store under another BSCSModel
     *
     * @return isReceiver
     */
    public boolean isPartyRoleReceiver() {
        return isReceiver;
    }

    /**
     * used by BillingAccountService.getBillingAccountOfPartyId Warn : is is not
     * a field, lost if this object is store under another BSCSModel
     *
     * @param isReceiver
     *            le isReceiver à définir
     */
    public void setPartyRoleReceiver(boolean isReceiver) {
        this.isReceiver = isReceiver;
    }

    /**
     * set by BillingAccountService.getBillingAccountOfPartyId Warn : is is not
     * a field, lost if this object is store under another BSCSModel
     *
     * @return le isPayer
     */
    public boolean isPartyRolePayer() {
        return isPayer;
    }

    /**
     * used by BillingAccountService.getBillingAccountOfPartyId Warn : is is not
     * a field, lost if this object is store under another BSCSModel
     *
     * @param isPayer a boolean.
     */
    public void setPartyRolePayer(boolean isPayer) {
        this.isPayer = isPayer;
    }

    public Long getTermCode(){ return getLongValue(Constants.P_TERMCODE);}
    public void setTermCode(Long termCode){ setLongValue(Constants.P_TERMCODE, termCode);}
    
    
    
    /**
     * <p>getVersions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BAVersion> getVersions() {
        return getListOfBSCSModelValue(Constants.P_LIST_OF_BILLING_ACCOUNT_VERSIONS, BAVersion.class);
    }

    /**
     * <pre>{@code
     * -[0]BILLING_ACCOUNT_VERSION = : java.lang.Long 
     * -[0]INCORPORATED_IND = : java.lang.Character 
     * -[0]INDIVIDUALTAX_FROM_IND = : java.lang.Character
     * -[0]JURISDICTION_ID = : java.lang.Long 
     * -[0]STATUS = : java.lang.Character
     * -[0]TAX_EXEMPTION_FROM_IND = : java.lang.Character 
     * -[0]VALID_FROM = : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     * }</pre>
     */
    public static class BAVersion extends BSCSModel {

        public EnumBillingAccountStatus getStatus() {
            return EnumBillingAccountStatus.fromCharacter(getCharacterValue(Constants.P_STATUS));
        }

        public void setStatus(EnumBillingAccountStatus status) {
            setCharacterValue(Constants.P_STATUS, EnumBillingAccountStatus.toCharacter(status));
        }

        public Long getVersion() {
            return getLongValue(Constants.P_BILLING_ACCOUNT_VERSION);
        }

        public void setVersion(Long ver) {
            setLongValue(Constants.P_BILLING_ACCOUNT_VERSION, ver);
        }
        
        public Date getValidFrom(){ return getDateTimeValue(Constants.P_VALID_FROM); }
        public void setValidFrom(Date dt){ setDateTimeValue(Constants.P_VALID_FROM, dt); }

    }

    /**
     * <p>getLastBilledDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getLastBilledDate() {
        return getDateTimeValue(Constants.P_LAST_BILLED_DATE);
    }
    
    
    public Boolean isPrimaryFlag(){ return getBooleanValue(Constants.P_PRIMARY_FLAG);}
    public void setPrimaryFlag(Boolean primaryForCustomer){ setBooleanValue(Constants.P_PRIMARY_FLAG, primaryForCustomer);}
    
    
    /**
     * @return Status to be write with BA.WRITE
     */
    public EnumBillingAccountStatus getWriteStatus() {
        return EnumBillingAccountStatus.fromCharacter(getCharacterValue(Constants.P_STATUS));
    }

    /**
     * Set Status (Only with BA.WRITE)
     */
    public void setWriteStatus(EnumBillingAccountStatus status) {
        setCharacterValue(Constants.P_STATUS, EnumBillingAccountStatus.toCharacter(status));
    }
    
}
