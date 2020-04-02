package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSPaymentArrangement class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSPaymentArrangement extends BSCSModel {

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
     * flag to limit search (in PA.READ parameters)
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isSearchCurrent() {
        return getBooleanValue(Constants.P_FLAG_CURRENT);
    }

    /**
     * flag to limit search (in PA.READ parameters)
     *
     * @param flag a {@link java.lang.Boolean} object.
     */
    public void setSearchCurrent(Boolean flag) {
        setBooleanValue(Constants.P_FLAG_CURRENT, flag);
    }

    /**
     * Credit for authorization
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getAuthorizationCredit() {
        return getMoneyAmountValue(Constants.P_AUTH_CREDIT);
    }

    /**
     * Credit for authorization
     *
     * @param amount a {@link java.lang.Double} object.
     */
    public void setAuthorizationCredit(Double amount) {
        setMoneyValue(Constants.P_AUTH_CREDIT, amount);
    }

    /**
     * Date for authorization
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getAuthorizationDate() {
        return getDateTimeValue(Constants.P_AUTH_DATE);
    }

    /**
     * Credit card authorization code
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAuthorizationNumber() {
        return getStringValue(Constants.P_AUTH_NO);
    }

    /**
     * Credit card authorization code
     *
     * @param authno a {@link java.lang.String} object.
     */
    public void setAuthorizationNumber(String authno) {
        setStringValue(Constants.P_AUTH_NO, authno);
    }

    /**
     * authorization is ok
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isAuthorizationOK() {
        return getBooleanValue(Constants.P_AUTH_OK);
    }

    /**
     * authorization is ok
     *
     * @param flag a {@link java.lang.Boolean} object.
     */
    public void setAuthorizationOK(Boolean flag) {
        setBooleanValue(Constants.P_AUTH_OK, flag);
    }

    /**
     * Authorization remark
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAuthorizationRemark() {
        return getStringValue(Constants.P_AUTH_REMARK);
    }

    /**
     * Authorization remark
     *
     * @param remark a {@link java.lang.String} object.
     */
    public void setAuthorizationRemark(String remark) {
        setStringValue(Constants.P_AUTH_REMARK, remark);
    }

    /**
     * Phone number of authorization
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAuthorizationPhoneNumber() {
        return getStringValue(Constants.P_AUTH_TN);
    }

    /**
     * Phone number of authorization
     *
     * @param authtn a {@link java.lang.String} object.
     */
    public void setAuthorizationPhoneNumber(String authtn) {
        setStringValue(Constants.P_AUTH_TN, authtn);
    }

    /**
     * account number
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccountNumber() {
        return getStringValue(Constants.P_CSP_ACCNO);
    }

    /**
     * account number
     *
     * @param accno a {@link java.lang.String} object.
     */
    public void setAccountNumber(String accno) {
        setStringValue(Constants.P_CSP_ACCNO, accno);
    }

    /**
     * account owner
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAccountOwner() {
        return getStringValue(Constants.P_CSP_ACCOWNER);
    }

    /**
     * account owner
     *
     * @param owner a {@link java.lang.String} object.
     */
    public void setAccountOwner(String owner) {
        setStringValue(Constants.P_CSP_ACCOWNER, owner);
    }

    /**
     * Flag indicating, that this is the actually used payment arrangement
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isCurrent() {
        return getBooleanValue(Constants.P_CSP_ACT_USED);
    }

    /**
     * Flag indicating, that this is the actually used payment arrangement
     *
     * @param used a boolean.
     */
    public void setIsCurrent(boolean used) {
        setBooleanValue(Constants.P_CSP_ACT_USED, used);
    }

    /**
     * city of the bank-address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankCity() {
        return getStringValue(Constants.P_CSP_BANKCITY);
    }

    /**
     * city of the bank-address
     *
     * @param city a {@link java.lang.String} object.
     */
    public void setBankCity(String city) {
        setStringValue(Constants.P_CSP_BANKCITY, city);
    }

    /**
     * subaccount number
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankCode() {
        return getStringValue(Constants.P_CSP_BANKCODE);
    }

    /**
     * subaccount number (CSP_BANKCODE)
     *
     * @param bankCode a {@link java.lang.String} object.
     */
    public void setBankCode(String bankCode) {
        setStringValue(Constants.P_CSP_BANKCODE, bankCode);
    }

    /**
     * Identifies a country by the public or private key. country of the
     * bank/ccag
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getBankCountryId() {
        return getLongValue(Constants.P_CSP_BANKCOUNTRY);
    }

    /**
     * Identifies a country by the public or private key. country of the
     * bank/ccag
     *
     * @param countryId a {@link java.lang.Long} object.
     */
    public void setBankCountryId(Long countryId) {
        setLongValue(Constants.P_CSP_BANKCOUNTRY, countryId);
    }

    /**
     * Public key of the country of the bank/ccag
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankContryIdPub() {
        return getStringValue(Constants.P_CSP_BANKCOUNTRY_PUB);
    }

    /**
     * Public key of the country of the bank/ccag
     *
     * @param countryIdPub a {@link java.lang.String} object.
     */
    public void setBankContryIdPub(String countryIdPub) {
        setStringValue(Constants.P_CSP_BANKCOUNTRY_PUB, countryIdPub);
    }

    /**
     * county of bank address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankCounty() {
        return getStringValue(Constants.P_CSP_BANKCOUNTY);
    }

    /**
     * county of bank address
     *
     * @param district a {@link java.lang.String} object.
     */
    public void setBankCounty(String district) {
        setStringValue(Constants.P_CSP_BANKCOUNTY, district);
    }

    /**
     * bank name
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankName() {
        return getStringValue(Constants.P_CSP_BANKNAME);
    }

    /**
     * bank name
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setBankName(String name) {
        setStringValue(Constants.P_CSP_BANKNAME, name);
    }

    /**
     * state of bank address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankState() {
        return getStringValue(Constants.P_CSP_BANKSTATE);
    }

    /**
     * state of bank address
     *
     * @param state a {@link java.lang.String} object.
     */
    public void setBankState(String state) {
        setStringValue(Constants.P_CSP_BANKSTATE, state);
    }

    /**
     * street name of the bank address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankStreet() {
        return getStringValue(Constants.P_CSP_BANKSTREET);
    }

    /**
     * street name of the bank address
     *
     * @param street a {@link java.lang.String} object.
     */
    public void setBankStreet(String street) {
        setStringValue(Constants.P_CSP_BANKSTREET, street);
    }

    /**
     * street number of the bank address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankStringNumber() {
        return getStringValue(Constants.P_CSP_BANKSTREETNO);
    }

    /**
     * street number of the bank address
     *
     * @param streetnum a {@link java.lang.String} object.
     */
    public void setBankStringNumber(String streetnum) {
        setStringValue(Constants.P_CSP_BANKSTREETNO, streetnum);
    }

    /**
     * zipcode of the bank-address
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBankZipCode() {
        return getStringValue(Constants.P_CSP_BANKZIP);
    }

    /**
     * zipcode of the bank-address
     *
     * @param zipcode a {@link java.lang.String} object.
     */
    public void setBankZipCode(String zipcode) {
        setStringValue(Constants.P_CSP_BANKZIP, zipcode);
    }

    /**
     * Identifies a financial institute by the public or private key. credit
     * card agency code (CSP_CCAG_CODE)
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getFinancialInstituteId() {
        return getLongValue(Constants.P_CSP_CCAG_CODE);
    }

    /**
     * Identifies a financial institute by the public or private key. 
     * credit card agency code (CSP_CCAG_CODE)
     *
     * @param ccagCode a {@link java.lang.Long} object.
     */
    public void setFinancialInstituteId(Long ccagCode) {
        setLongValue(Constants.P_CSP_CCAG_CODE, ccagCode);
    }

    /**
     * Credit Card Agency Code (CSP_CCAG_CODE_PUB).
     * 
     * Identifies a financial institute by the public or private key. Public key
     * of the financial institute.
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFinancialInstituteIdPub() {
        return getStringValue(Constants.P_CSP_CCAG_CODE_PUB);
    }

    /**
     * Identifies a financial institute by the public or private key. Public key
     * of the financial institute (CSP_CCAG_CODE_PUB).
     *
     * @param ccagcodepub a {@link java.lang.String} object.
     */
    public void setFinancialInstituteIdPub(String ccagcodepub) {
        setStringValue(Constants.P_CSP_CCAG_CODE_PUB, ccagcodepub);
    }

    /**
     * valid date of the credit card (MMYY) (CSP_CCVALIDDT)
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCreditCardValidity() {
        return getStringValue(Constants.P_CSP_CCVALIDDT);
    }

    /**
     * valid date of the credit card (MMYY) (CSP_CCVALIDDT)
     *
     * @param mmyy a {@link java.lang.String} object.
     */
    public void setCreditCardValidity(String mmyy) {
        setStringValue(Constants.P_CSP_CCVALIDDT, mmyy);
    }

    /**
     * ceiling amount for credit card payments
     *
     * @return a {@link java.lang.Double} object.
     */
    public Double getCreditCardPaymentsCeilingAmount() {
        return getMoneyAmountValue(Constants.P_CSP_CEILING);
    }

    /**
     * ceiling amount for credit card payments
     *
     * @param ceiling a {@link java.lang.Double} object.
     */
    public void setCreditCardPaymentsCeilingAmount(Double ceiling) {
        setMoneyValue(Constants.P_CSP_CEILING, ceiling);
    }

    /**
     * Payment arrangement (payment) identifier. (P_CSP_ID)
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_CSP_ID);
    }

    /**
     * Payment arrangement (payment) identifier. (P_CSP_ID)
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setId(Long id) {
        setLongValue(Constants.P_CSP_ID, id);
    }

    /**
     * order number
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOrderNumber() {
        return getStringValue(Constants.P_CSP_ORDERNUMBER);
    }

    /**
     * order number
     *
     * @param orderNum a {@link java.lang.String} object.
     */
    public void setOrderNumber(String orderNum) {
        setStringValue(Constants.P_CSP_ORDERNUMBER, orderNum);
    }

    /**
     * Identifies a payment method. payment-type id (CSP_PMNT_ID)
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPaymentMethodId() {
        return getLongValue(Constants.P_CSP_PMNT_ID);
    }

    /**
     * Identifies a payment method payment-type id (CSP_PMNT_ID)
     *
     * @param type a {@link java.lang.Long} object.
     */
    public void setPaymentMethodId(Long type) {
        setLongValue(Constants.P_CSP_PMNT_ID, type);
    }

    /**
     * Identifies a payment method Public key of the payment method
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPaymentMethodIdPub() {
        return getStringValue(Constants.P_CSP_PMNT_ID_PUB);
    }

    /**
     * <p>setPaymentMethodIdPub.</p>
     *
     * @param pmtidpub a {@link java.lang.String} object.
     */
    public void setPaymentMethodIdPub(String pmtidpub) {
        setStringValue(Constants.P_CSP_PMNT_ID_PUB, pmtidpub);
    }

    /**
     * sequence number
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSequenceNumber() {
        return getLongValue(Constants.P_CSP_SEQNO);
    }

    /**
     * sequence number
     *
     * @param seqno a {@link java.lang.Long} object.
     */
    public void setSequenceNumber(Long seqno) {
        setLongValue(Constants.P_CSP_SEQNO, seqno);
    }

    /**
     * international bank identifier (CSP_SWIFTCODE).
     *
     * @return a {@link java.lang.String} object.
     */
    public String getInternationalBankIdentifier() {
        return getStringValue(Constants.P_CSP_SWIFTCODE);
    }

    /**
     * international bank identifier (SWIFTCODE)
     *
     * @param swiftCode a {@link java.lang.String} object.
     */
    public void setInternationalBankIdentifier(String swiftCode) {
        setStringValue(Constants.P_CSP_SWIFTCODE, swiftCode);
    }

    /**
     * Valid from date.
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getValidFrom() {
        return getDateValue(Constants.P_CSP_VALID_FROM);
    }

    /**
     * Valid from date.
     *
     * @param dt a {@link java.util.Date} object.
     */
    public void setValidFrom(Date dt) {
        setDateValue(Constants.P_CSP_VALID_FROM, dt);
    }

}
