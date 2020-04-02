package com.orange.bscs.model.accounting;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSPaymentMethod class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSPaymentMethod extends BSCSModel {

    private static final String P_PAYMETH_CREDITCARDCOMPANY = "PAYMETH_CREDITCARDCOMPANY";
    private static final String P_PAYMETH_PREPAY_DEF = "PAYMETH_PREPAY_DEF";
    private static final String P_PAYMETH_BANKSUBACCT_NUM = "PAYMETH_BANKSUBACCT_NUM";
    private static final String P_PAYMETH_BANKSTREET_NUM = "PAYMETH_BANKSTREET_NUM";
    private static final String P_PAYMETH_BANKSTREET = "PAYMETH_BANKSTREET";
    private static final String P_PAYMETH_BANKZIP = "PAYMETH_BANKZIP";
    private static final String P_PAYMETH_PAYMENT_AUTHORIZATION = "PAYMETH_PAYMENT_AUTHORIZATION";
    private static final String P_PAYMETH_PAYMENT_DEFAULT = "PAYMETH_PAYMENT_DEFAULT";
    private static final String P_PAYMETH_SWIFTCODE = "PAYMETH_SWIFTCODE";
    private static final String P_PAYMETH_ACCT_VALIDATION = "PAYMETH_ACCT_VALIDATION";
    private static final String P_PAYMETH_ACCTOWNER = "PAYMETH_ACCTOWNER";
    private static final String P_PAYMETH_BANKACCT_NUM = "PAYMETH_BANKACCT_NUM";
    private static final String P_PAYMETH_BANKCITY = "PAYMETH_BANKCITY";
    private static final String P_PAYMETH_BANKCOUNTRY = "PAYMETH_BANKCOUNTRY";
    private static final String P_PAYMETH_BANKNAME = "PAYMETH_BANKNAME";
    private static final String P_PAYMETH_BANKSTATE = "PAYMETH_BANKSTATE";
    private static final String P_PAYMETH_VALIDTHROUGHDATE = "PAYMETH_VALIDTHROUGHDATE";

    /**
     * payment method id
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_PAYMETH_ID);
    }

    /**
     * Public key of the payment method
     *
     * @return a {@link java.lang.String} object.
     */
    public String getIdPub() {
        return getStringValue(Constants.P_PAYMETH_ID_PUB);
    }

    /**
     * payment method description
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_PAYMETH_DESC);
    }

    /**
     * payment method account validation mandatory
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getIsAccountValidationMandatory() {
        return getCharacterValue(P_PAYMETH_ACCT_VALIDATION);
    }

    /**
     * payment method account owner mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isAccountOwnerMandatory() {
        return getBooleanValue(P_PAYMETH_ACCTOWNER);
    }

    /**
     * payment method bank account number mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankAccountNumberMandatory() {
        return getBooleanValue(P_PAYMETH_BANKACCT_NUM);
    }

    /**
     * payment method bank city mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankCityMandatory() {
        return getBooleanValue(P_PAYMETH_BANKCITY);
    }

    /**
     * payment method bank country mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankCountryMandatory() {
        return getBooleanValue(P_PAYMETH_BANKCOUNTRY);
    }

    /**
     * payment method bank name mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankNameMandatory() {
        return getBooleanValue(P_PAYMETH_BANKNAME);
    }

    /**
     * payment method bank state mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankStateMandatory() {
        return getBooleanValue(P_PAYMETH_BANKSTATE);
    }

    /**
     * payment method bank street
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankStreetMandatory() {
        return getBooleanValue(P_PAYMETH_BANKSTREET);
    }

    /**
     * payment method bank street number mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankStreetNumberMandatory() {
        return getBooleanValue(P_PAYMETH_BANKSTREET_NUM);
    }

    /**
     * payment method bank sub account number mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankSubAccountMandatory() {
        return getBooleanValue(P_PAYMETH_BANKSUBACCT_NUM);
    }

    /**
     * payment method bank zip mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isBankZipMandatory() {
        return getBooleanValue(P_PAYMETH_BANKZIP);
    }

    /**
     * payment method code
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCode() {
        return getStringValue(Constants.P_PAYMETH_CODE);
    }

    /**
     * payment method creditcard company mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isCreditCardCompanyMandatory() {
        return getBooleanValue(P_PAYMETH_CREDITCARDCOMPANY);
    }

    /**
     * payment method payment authorization
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getIsPaymentAuthorization() {
        return getCharacterValue(P_PAYMETH_PAYMENT_AUTHORIZATION);
    }

    /**
     * payment method default payment
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getIsPaymentDefault() {
        return getCharacterValue(P_PAYMETH_PAYMENT_DEFAULT);
    }

    /**
     * payment method prepay default
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getIsPaymentPrepaidDefault() {
        return getCharacterValue(P_PAYMETH_PREPAY_DEF);
    }

    /**
     * payment method swift code mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isSwiftCodeMandatory() {
        return getBooleanValue(P_PAYMETH_SWIFTCODE);
    }

    /**
     * payment method valid through date mandatory? true if mandatory
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isValidThroughDateMandatory() {
        return getBooleanValue(P_PAYMETH_VALIDTHROUGHDATE);
    }

}
