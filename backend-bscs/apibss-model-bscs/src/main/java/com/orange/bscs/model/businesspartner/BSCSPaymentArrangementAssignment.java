package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSPaymentArrangementAssignment class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSPaymentArrangementAssignment extends BSCSModel {

    /**
     * Billing account code
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingAccountCode() {
        return getStringValue(Constants.P_BILLING_ACCOUNT_CODE);
    }

    /**
     * Billing account code
     *
     * @param billingAccountCode a {@link java.lang.String} object.
     */
    public void setBillingAccountCode(String billingAccountCode) {
        setStringValue(Constants.P_BILLING_ACCOUNT_CODE, billingAccountCode);
    }

    /**
     * Billing account identifier
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBillingAccountId() {
        return getLongValue(Constants.P_BILLING_ACCOUNT_ID);
    }

    /**
     * Billing account identifier
     *
     * @param billingAccountId a {@link java.lang.Long} object.
     */
    public void setBillingAccountId(Long billingAccountId) {
        setLongValue(Constants.P_BILLING_ACCOUNT_ID, billingAccountId);
    }

    /**
     * <p>getCustomerIDPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIDPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }

    /**
     * <p>setCustomerIDPub.</p>
     *
     * @param idpub a {@link java.lang.String} object.
     */
    public void setCustomerIDPub(String idpub) {
        setStringValue(Constants.P_CS_ID_PUB, idpub);
    }

    /**
     * <p>getCustomerID.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerID() {
        return getLongValue(Constants.P_CS_ID);
    }

    /**
     * <p>setCustomerID.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setCustomerID(Long id) {
        setLongValue(Constants.P_CS_ID, id);
    }

    /**
     * <p>getLevel.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getLevel() {
        return getCharacterValue(Constants.P_LEVEL);
    }

    /**
     * <p>setLevel.</p>
     *
     * @param level a {@link java.lang.Character} object.
     */
    public void setLevel(Character level) {
        setCharacterValue(Constants.P_LEVEL, level);
    }

    /**
     * Account number.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccountNumber() {
        return getStringValue(Constants.P_ACCNO);
    }

    /**
     * Account number.
     *
     * @param accno a {@link java.lang.String} object.
     */
    public void setAccountNumber(String accno) {
        setStringValue(Constants.P_ACCNO, accno);
    }

    /**
     * Account owner.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccountOwner() {
        return getStringValue(Constants.P_ACCOWNER);
    }

    /**
     * Account owner.
     *
     * @param owner a {@link java.lang.String} object.
     */
    public void setAccountOwner(String owner) {
        setStringValue(Constants.P_ACCOWNER, owner);
    }

    /**
     * Flag to indicate whether the payment arrangment is currently valid.
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isCurrent() {
        return getBooleanValue(Constants.P_CURRENT);
    }

    /**
     * Flag to indicate whether the payment arrangment is currently valid.
     *
     * @param current a {@link java.lang.Boolean} object.
     */
    public void setIsCurrent(Boolean current) {
        setBooleanValue(Constants.P_CURRENT, current);
    }

    /**
     * Document type
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getDocumentType() {
        return getLongValue(Constants.P_DOCUMENT_TYPE);
    }

    /**
     * Document type
     *
     * @param docType a {@link java.lang.Long} object.
     */
    public void setDocumentType(Long docType) {
        setLongValue(Constants.P_DOCUMENT_TYPE, docType);
    }

    /**
     * Customer respectively billing account payment arrangement identifier.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPaymentArrangementId() {
        return getLongValue(Constants.P_PAY_ARR_ID);
    }

    /**
     * Customer respectively billing account payment arrangement identifier.
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setPaymentArrangementId(Long id) {
        setLongValue(Constants.P_PAY_ARR_ID, id);
    }

    /**
     * Payment identifier.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPaymentId() {
        return getLongValue(Constants.P_PAYMENT_ID);
    }

    /**
     * Payment identifier.
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setPaymentId(Long id) {
        setLongValue(Constants.P_PAYMENT_ID, id);
    }

    /**
     * Payment mode.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPaymentMode() {
        return getLongValue(Constants.P_PAYMENT_MODE);
    }

    /**
     * Payment mode.
     *
     * @param mode a {@link java.lang.Long} object.
     */
    public void setPaymentMode(Long mode) {
        setLongValue(Constants.P_PAYMENT_MODE, mode);
    }

    /**
     * Term code.
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getTermeCode() {
        return getLongValue(Constants.P_TERM_CODE);
    }

    /**
     * Term code.
     *
     * @param terme a {@link java.lang.Long} object.
     */
    public void setTermeCode(Long terme) {
        setLongValue(Constants.P_TERM_CODE, terme);
    }

    /**
     * Valid from date. Mandatory if a new payment arrangement should be
     * created.
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getValidFrom() {
        return getDateValue(Constants.P_VALID_FROM);
    }

    /**
     * Valid from date. Mandatory if a new payment arrangement should be
     * created.
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setValidFrom(Date dt) {
        setDateValue(Constants.P_VALID_FROM, dt);
    }

    /**
     * Valid until date.
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getValidUtil() {
        return getDateValue(Constants.P_VALID_UNTIL);
    }

    /**
     * Valid until date.
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setValidUtil(Date dt) {
        setDateValue(Constants.P_VALID_UNTIL, dt);
    }

}
