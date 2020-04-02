package com.orange.bscs.model.billing;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

public class BSCSCreditCardCompagnie extends BSCSModel {

    /** BANK_ID */
    public Long getId() {
        return getLongValue(Constants.P_BANK_ID);
    }
    /** BANK_ID */
    public void setId(Long id) {
        setLongValue(Constants.P_BANK_ID, id);
    }
    
    /** BANK_ID_PUB */
    public String getIdPub() {
        return getStringValue(Constants.P_BANK_ID_PUB);
    }
    
    /** BANK_ID_PUB */
    public void setIdPub(String idPub) {
        setStringValue(Constants.P_BANK_ID_PUB, idPub);
    }
    
    /** BANK_NAME */
    public String getName() {
        return getStringValue(Constants.P_BANK_NAME);
    }
    /** BANK_NAME */
    public void setName(String name) {
        setStringValue(Constants.P_BANK_NAME, name);
    }

    /** BANK_DEF */
    public boolean isDefineAsDefault() {
        Character c= getCharacterValue(Constants.P_BANK_DEF);
        return null!=c && 'Y'==c;
                
    }
    /** BANK_DEF */
    public void setDefineAsDefault(boolean defineAsDefault) {
        setCharacterValue(Constants.P_BANK_DEF, defineAsDefault ? 'Y':'N');
    }

    /** BANK_STREET */
    public String getStreet() {
        return getStringValue(Constants.P_BANK_STREET);
    }
    /** BANK_STREET */
    public void setStreet(String street) {
        setStringValue(Constants.P_BANK_STREET,street);
    }
    
    /** BANK_STREET_NUM */
    public String getStreetNum() {
        return getStringValue(Constants.P_BANK_STREET_NUM);
    }
    /** BANK_STREET_NUM */
    public void setStreetNum(String streetNum) {
        setStringValue(Constants.P_BANK_STREET, streetNum);
    }
    
    /** BANK_ZIP */
    public String getPostalCode() {
        return getStringValue(Constants.P_BANK_ZIP);
    }
    /** BANK_ZIP */
    public void setPostalCode(String postalCode) {
        setStringValue(Constants.P_BANK_ZIP,postalCode);
    }
    
    /** BANK_COUNTY */
    public String getCounty() {
        return getStringValue(Constants.P_BANK_COUNTY);
    }
    /** BANK_COUNTY */
    public void setCounty(String county) {
        setStringValue(Constants.P_BANK_COUNTY,county);
    }

    /** BANK_STATE */
    public String getState() {
        return getStringValue(Constants.P_BANK_STATE);
    }
    /** BANK_STATE */
    public void setState(String state) {
        setStringValue(Constants.P_BANK_STATE,state);
    }

    /** BANK_COUNTRY */
    public Long getCountryId() {
        return getLongValue(Constants.P_BANK_COUNTRY);
    }
    /** BANK_COUNTRY */
    public void setCountryId(Long countryId) {
        setLongValue(Constants.P_BANK_COUNTRY,countryId);
    }

    /** BANK_COUNTRY_PUB */
    public String getCountryIdPub() {
        return getStringValue(Constants.P_BANK_COUNTRY_PUB);
    }
    /** BANK_COUNTRY_PUB */
    public void setCountryIdPub(String countryIdPub) {
        setStringValue(Constants.P_BANK_COUNTRY_PUB,countryIdPub);
    }
    
}
