package com.orange.bscs.model.businesspartner;

import java.util.Arrays;
import java.util.Date;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * Result of a CUSTOMERS.SEARCH
 *
 * @author IT&L@bs
 * @version $Id: $
 */
public class BSCSCustomer extends BSCSModel {

    
    // Allow to temporary add some addresses in customer 
    // Object.
    // WARN : if this BSCSCustomer is store in other BSCSModel, this 
    //        list is lost.
    transient BSCSAddresses addresses;
    
    
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

    // ADR_BIRTHDT
    /**
     * <p>getBirthDate.</p>
     *
     * @return a {@link java.util.Date} object.
     */
    public Date getBirthDate() {
        return getDateValue(Constants.P_ADR_BIRTHDT);
    }

    /**
     * <p>setBirthDate.</p>
     *
     * @param value a {@link java.util.Date} object.
     */
    public void setBirthDate(Date value) {
        setDateValue(Constants.P_ADR_BIRTHDT, value);
    }

    // ADR_CITY
    /**
     * <p>getCity.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCity() {
        return getStringValue(Constants.P_ADR_CITY);
    }

    /**
     * <p>setCity.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCity(String value) {
        setStringValue(Constants.P_ADR_CITY, value);
    }

    // ADR_FNAME
    /**
     * <p>getFirstName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFirstName() {
        return getStringValue(Constants.P_ADR_FNAME);
    }

    /**
     * <p>setFirstName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setFirstName(String value) {
        setStringValue(Constants.P_ADR_FNAME, value);
    }

    // ADR_LNAME
    /**
     * <p>getLastName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLastName() {
        return getStringValue(Constants.P_ADR_LNAME);
    }

    /**
     * <p>setLastName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setLastName(String value) {
        setStringValue(Constants.P_ADR_LNAME, value);
    }

    // ADR_NAME
    /**
     * <p>getAddressName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAddressName() {
        return getStringValue(Constants.P_ADR_NAME);
    }

    /**
     * <p>setAddressName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setAddressName(String value) {
        setStringValue(Constants.P_ADR_NAME, value);
    }

    // ADR_STREET
    /**
     * <p>getStreet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStreet() {
        return getStringValue(Constants.P_ADR_STREET);
    }

    /**
     * <p>setStreet.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setStreet(String value) {
        setStringValue(Constants.P_ADR_STREET, value);
    }

    // ADR_STREETNO
    /**
     * <p>getStreetNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getStreetNumber() {
        return getStringValue(Constants.P_ADR_STREETNO);
    }

    /**
     * <p>setStreetNumber.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setStreetNumber(String value) {
        setStringValue(Constants.P_ADR_STREETNO, value);
    }

    // ADR_ZIP
    /**
     * <p>getPostalCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPostalCode() {
        return getStringValue(Constants.P_ADR_ZIP);
    }

    /**
     * <p>setPostalCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPostalCode(String value) {
        setStringValue(Constants.P_ADR_ZIP, value);
    }

    // BU_ID
    /**
     * <p>getBusinessUnit.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBusinessUnit() {
        return getStringValue(Constants.P_BU_ID);
    }

    /**
     * <p>setBusinessUnit.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setBusinessUnit(String value) {
        setStringValue(Constants.P_BU_ID, value);
    }

    // CS_CODE
    /**
     * <p>getCustomerCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerCode() {
        return getStringValue(Constants.P_CS_CODE);
    }

    /**
     * <p>setCustomerCode.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCustomerCode(String value) {
        setStringValue(Constants.P_CS_CODE, value);
    }

    // CS_CONTR_RESP
    /**
     * <p>getContractResponsibility.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getContractResponsibility() {
        return getCharacterValue(Constants.P_CS_CONTR_RESP);
    }

    /**
     * <p>setContractResponsibility.</p>
     *
     * @param value a {@link java.lang.Character} object.
     */
    public void setContractResponsibility(Character value) {
        setCharacterValue(Constants.P_CS_CONTR_RESP, value);
    }

    // CS_DEALERID
    /**
     * <p>getDealerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getDealerId() {
        return getLongValue(Constants.P_CS_DEALERID);
    }

    /**
     * <p>setDealerId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setDealerId(Long value) {
        setLongValue(Constants.P_CS_DEALERID, value);
    }

    /**
     * <p>(CS_ID_HIGH).</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getParentCustomerId() {
        return getLongValue(Constants.P_CS_ID_HIGH);
    }

    /**
     * <p>(CS_ID_HIGH).</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setParentCustomerId(Long value) {
        setLongValue(Constants.P_CS_ID_HIGH, value);
    }

    
    /**
     * <p>(CS_ID_HIGH_PUB).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParentCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_HIGH_PUB);
    }

    /**
     * <p>(CS_ID_HIGH_PUB)</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setParentCustomerIdPub(String value) {
        setStringValue(Constants.P_CS_ID_HIGH_PUB, value);
    }

    /**
     * CS_LEVEL_CODE (10, 20, 30 or 40)
     *
     * @return a {@link java.lang.String} object.
     */
    public EnumCustomerLevelCode getCustomerLevelCode() {
        return EnumCustomerLevelCode.parse(getStringValue(Constants.P_CS_LEVEL_CODE));
    }

    /**
     * CS_LEVEL_CODE (10, 20, 30 or 40)
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setCustomerLevelCode(EnumCustomerLevelCode value) {
        setStringValue(Constants.P_CS_LEVEL_CODE, EnumCustomerLevelCode.toString(value));
    }

    // CS_STATUS
    /**
     * <p>(CS_STATUS).</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getStatusCharacter() {
        return getCharacterValue(Constants.P_CS_STATUS);
    }

    /**
     * <p>(CS_STATUS).</p>
     *
     * @param value a {@link java.lang.Character} object.
     */
    public void setStatusCharacter(Character value) {
        setCharacterValue(Constants.P_CS_STATUS, value);
    }

    /**
     * <p>(CS_STATUS).</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     */
    public EnumCustomerStatus getStatus() {
        return EnumCustomerStatus.parse(getStatusCharacter());
    }

    // RS_CODE
    /**
     * <p>getReasonCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getReasonCode() {
        return getLongValue(Constants.P_RS_CODE);
    }

    /**
     * <p>setReasonCode.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setReasonCode(Long value) {
        setLongValue(Constants.P_RS_CODE, value);
    }

    // EXTERNAL_CUSTOMER_ID
    /**
     * <p>getExternalCustomerId.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalCustomerId() {
        return getStringValue(Constants.P_EXTERNAL_CUSTOMER_ID);
    }

    /**
     * <p>setExternalCustomerId.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setExternalCustomerId(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_ID, value);
    }

    // EXTERNAL_CUSTOMER_SET_ID
    /**
     * <p>getExternalCustomerIdSet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getExternalCustomerIdSet() {
        return getStringValue(Constants.P_EXTERNAL_CUSTOMER_SET_ID);
    }

    /**
     * <p>setExternalCustomerIdSet.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setExternalCustomerIdSet(String value) {
        setStringValue(Constants.P_EXTERNAL_CUSTOMER_SET_ID, value);
    }

    /**
     * <p>getPartyID.</p>
     *
     * @return value of PartyId, actually, same as getExternalCustomerId()
     */
    public String getPartyID() {
        return getExternalCustomerId();
    }

    // PARTY_TYPE
    /**
     * <p>getPartyType.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPartyType() {
        return getStringValue(Constants.P_PARTY_TYPE);
    }

    /**
     * <p>setPartyType.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPartyType(String value) {
        setStringValue(Constants.P_PARTY_TYPE, value);
    }

    // PAYMENT_RESP
    /**
     * <p>getIsPaymentResponsible.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIsPaymentResponsible() {
        return getBooleanValue(Constants.P_PAYMENT_RESP);
        
    }

    /**
     * <p>setIsPaymentResponsible.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     */
    public void setIsPaymentResponsible(Boolean value) {
        setBooleanValue(Constants.P_PAYMENT_RESP, value);
    }

    /**
     * Only in creation, rpcode is mandatory.
     *
     * @param ratePlan a {@link java.lang.String} object.
     */
    public void setRatePlan(String ratePlan) {
        setStringValue(Constants.P_RPCODE_PUB, ratePlan);
    }

    // Used in FagCib
    /**
     * <p>getRatePlan.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlan() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>getBillingCycle.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillingCycle() {
        return getStringValue(Constants.P_CS_BILLCYCLE);
    }

    /**
     * <p>setBillingCycle.</p>
     *
     * @param bc a {@link java.lang.String} object.
     */
    public void setBillingCycle(String bc) {
        setStringValue(Constants.P_CS_BILLCYCLE, bc);
    }

    /**
     * <p>getCurrencyForeignCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCurrencyForeignCode() {
        return getLongValue(Constants.P_CS_FC_ID);
    }

    /**
     * <p>getCurrencyForeignCodePub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCurrencyForeignCodePub() {
        return getStringValue(Constants.P_CS_FC_ID_PUB);
    }

    /**
     * <p>getPriceGroupCode (PRG_CODE).</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPriceGroupCode() {
        return getStringValue(Constants.P_PRG_CODE);
    }

    /**
     * <p>setPriceGroupCode (PRG_CODE).</p>
     *
     * @param prgcode a {@link java.lang.String} object.
     */
    public void setPriceGroupCode(String prgcode) {
        setStringValue(Constants.P_PRG_CODE, prgcode);
    }

    /** (CS_CREATION_DATE) */
    public Date getCustomerCreationDate(){
        return getDateTimeValue(Constants.P_CS_CREATION_DATE);
    }

    public Date getStatusDate() {
        return getDateTimeValue(Constants.P_CS_STATUS_DATE);
    }

    
    public String getDealerIdPub() {
        return getStringValue(Constants.P_CS_DEALERID_PUB);
    }
    
    /** CUSTCAT_CODE */
    public Long getCustCategorieID(){
        return getLongValue(Constants.P_CUSTCAT_CODE);
    }
    /** CUSTCAT_CODE */
    public void setCustCategorieID(Long custCatCode){ setLongValue(Constants.P_CUSTCAT_CODE, custCatCode);}
    /** CUSTCAT_CODE_PUB */
    public String getCustCategorieCode(){
        return getStringValue(Constants.P_CUSTCAT_CODE_PUB);
    }
    /** CUSTCAT_CODE_PUB */
    public void setCustCategorieCode(String custCatCodePub){ setStringValue(Constants.P_CUSTCAT_CODE, custCatCodePub);}


    /**
     * Allow to temporary add some addresses in customer 
     * Object.
     * WARN : if this BSCSCustomer is store in other BSCSModel, this 
     *        list is lost.
     * 
     * @param adrs list of addresses to keep join with this object.
     */
    public void setTransientAddresses(BSCSAddress... adrs) {
        if(null==adrs){
            addresses=null;
            return;
        }
        
        if(null==addresses){
            addresses = new BSCSAddresses();
        }

        addresses.setAddresses(Arrays.asList(adrs));
    }
    
    /**
     * Allow to temporary add some addresses in customer 
     * Object.
     * WARN : if this BSCSCustomer is store in other BSCSModel, this 
     *        list is lost.
     * 
     * @param adrs list of addresses to keep join with this object.
     */
    public void setTransientAddresses(BSCSAddresses adrs) {
        addresses = adrs;
    }

    /**
     * @return Empty BSCSAddresses or addresses stores with setTransientAddresses
     */
    public BSCSAddresses getTransientAddresses(){
        if(null==addresses){
            addresses = new  BSCSAddresses();
        }
        addresses.setCustomerId(getCustomerID());
        addresses.setCustomerIdPub(getCustomerIDPub());
        return addresses;
    }

    /**
     * @return last billing cycle date
     */
    public Date getLastBillingCycleDate() {
        return getDateValue(Constants.P_CS_LAST_BC_DATE);
    }
}
