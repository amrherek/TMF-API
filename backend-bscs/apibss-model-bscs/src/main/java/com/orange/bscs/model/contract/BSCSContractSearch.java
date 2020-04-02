package com.orange.bscs.model.contract;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.model.businesspartner.EnumContractStatus;

/**
 * Criterias for a CONTRACTS.SEARCH CMS 
 * 
 * WARN all parameters are not present
 * Add as needed
 * 
 * <pre>{@code
 *    ADR_CITY             =  : java.lang.String
 *  ADR_FNAME            =  : java.lang.String
 *  ADR_LNAME            =  : java.lang.String
 *  ADR_NAME             =  : java.lang.String
 *  ADR_STREET           =  : java.lang.String
 *  ADR_STREETNO         =  : java.lang.String
 *  ADR_ZIP              =  : java.lang.String
 *  BILL_NO              =  : java.lang.String
 *  CONTRACT_TYPE_ID     =  : java.lang.Long
 *  CO_ID                =  : java.lang.Long
 *  CO_ID_PUB            =  : java.lang.String
 *  CO_LEC               =  : java.lang.Long
 *  CO_LEC_PUB           =  : java.lang.String
 *  CO_POFU              =  : java.lang.Boolean
 *  CO_STATUS            =  : java.lang.Integer
 *  CO_USER              =  : java.lang.String
 *  CS_CODE              =  : java.lang.String
 *  CS_DEALERID          =  : java.lang.Long
 *  CS_DEALERID_PUB      =  : java.lang.String
 *  CS_ID                =  : java.lang.Long
 *  CS_ID_PUB            =  : java.lang.String
 *  DESCRIPTION          =  : java.lang.String
 *  DEV_PORT_NUM         =  : java.lang.String
 *  DIRNUM               =  : java.lang.String
 *  EXCLUDE_SUBSCRIBER_CONTRACTS =  : java.lang.Boolean
 *  EXTERNALIND          =  : java.lang.Boolean
 *  FAMILY_ID            =  : java.lang.Long
 *  FLAG_CASE            =  : java.lang.Boolean
 *  FLAG_MATCHCODE       =  : java.lang.Boolean
 *  FOREIGN_PLCODE       =  : java.lang.Long
 *  FOREIGN_PLCODE_PUB   =  : java.lang.String
 *  IGNORE_BU_IND        =  : java.lang.Boolean
 *  LA_MEMBER_CONTRACT   =  : java.lang.Boolean
 *  LINKED_PUBLIC_DIRNUM =  : java.lang.String
 *  LOWER_EXT            =  : java.lang.String
 *  MAIN_DIRNUM          =  : java.lang.String
 *  NON_PENDING_ONLY     =  : java.lang.Boolean
 *  PARTY_TYPE           =  : java.lang.String
 *  PLCODE               =  : java.lang.Long
 *  PLCODE_PUB           =  : java.lang.String
 *  PORT_NUM             =  : java.lang.String
 *  RPCODE               =  : java.lang.Long
 *  RPCODE_PUB           =  : java.lang.String
 *  SCCODE               =  : java.lang.Long
 *  SCCODE_PUB           =  : java.lang.String
 *  SEARCHER             =  : java.lang.String
 *  SEARCH_AL_CONTRACTS  =  : java.lang.Boolean
 *  SM_NUM               =  : java.lang.String
 *  SRCH_COUNT           =  : java.lang.Long
 *  START_INDEX          =  : java.lang.Long
 *  SUBM_ID              =  : java.lang.Long
 *  SUBM_ID_PUB          =  : java.lang.String
 *  UPPER_EXT            =  : java.lang.String
 *  USE_INSTALLATION_ADDRESS =  : java.lang.Boolean
 *  VPN_ID               =  : java.lang.Long
 *  VPN_ID_PUB           =  : java.lang.String
 *  VPN_SUBSCRIBER       =  : java.lang.Boolean
 * 
 * }</pre>
 * 
 * 
 * @author IT&Labs
 *
 */
public class BSCSContractSearch extends BSCSModel {

    public static final String SEARCH_EVEN_WITHOUT_DN = "SearchContractsEvenWithoutDN";

    /**
     * <p>getFirstName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getFirstName() {
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
    
    /**
     * <p>getLastName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getLastName() {
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
    
    /**
     * <p>getAddressName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getAddressName() {
        return getStringValue(Constants.P_ADR_NAME);
    }
    /**
     * <p>setAddressName.</p>
     *
     * @param organisationTradingName a {@link java.lang.String} object.
     */
    public void setAddressName(String organisationTradingName) {
        setStringValue(Constants.P_ADR_NAME, organisationTradingName);
    }
    
    /**
     * <p>getStreetNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getStreetNumber() {
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

    /**
     * <p>getStreet.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getStreet() {
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
    /**
     * <p>getPostalCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getPostalCode() {
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

    /**
     * <p>getCity.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public java.lang.String getCity() {
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
    
    /**
     * <p>getContractId.</p>
     *
     * @return CO_ID : 99999 : java.lang.Long
     */
    public Long getContractId() {
        return getLongValue(Constants.P_CO_ID);
    }

    /**
     * <p>setContractId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setContractId(Long id) {
        setLongValue(Constants.P_CO_ID, id);
    }

    /**
     * CO_ID_PUB = CONTR0000000999 : java.lang.String
     *
     * @return a {@link java.lang.String} object.
     */
    public String getContractIdPub() {
        return getStringValue(Constants.P_CO_ID_PUB);
    }

    /**
     * <p>setContractIdPub.</p>
     *
     * @param idpub a {@link java.lang.String} object.
     */
    public void setContractIdPub(String idpub) {
        setStringValue(Constants.P_CO_ID_PUB, idpub);
    }

    /**
     * RPCODE = 99 : java.lang.Long
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getRatePlanCode() {
        return getLongValue(Constants.P_RPCODE);
    }

    /**
     * <p>setRatePlanCode.</p>
     *
     * @param rpCode a {@link java.lang.Long} object.
     */
    public void setRatePlanCode(Long rpCode) {
        setLongValue(Constants.P_RPCODE, rpCode);
    }

    /**
     * RPCODE_PUB : xxx : java.lang.String
     *
     * Not returned by CONTRACT.SEARCH
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRatePlanPublicCode() {
        return getStringValue(Constants.P_RPCODE_PUB);
    }

    /**
     * <p>setRatePlanPublicCode.</p>
     *
     * @param rpCodePub a {@link java.lang.String} object.
     */
    public void setRatePlanPublicCode(String rpCodePub) {
        setStringValue(Constants.P_RPCODE_PUB, rpCodePub);
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
     * @param csid a {@link java.lang.Long} object.
     */
    public void setCustomerId(Long csid) {
        setLongValue(Constants.P_CS_ID, csid);
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
     * @param csidpub a {@link java.lang.String} object.
     */
    public void setCustomerIdPub(String csidpub) {
        setStringValue(Constants.P_CS_ID_PUB, csidpub);
    }

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
     * @param cscode a {@link java.lang.String} object.
     */
    public void setCustomerCode(String cscode) {
        setStringValue(Constants.P_CS_CODE, cscode);
    }
    
    /**
     * DESCRIPTION = : java.lang.String
     */
    public String getDescription(){ return getStringValue(Constants.P_DESCRIPTION);}
    /**
     * DESCRIPTION = : java.lang.String
     */
    public void setDescription(String descr){ setStringValue(Constants.P_DESCRIPTION, descr);}

    /**
     * <p>getStatus.</p>
     *
     * @return a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public EnumContractStatus getStatus() {
        return EnumContractStatus.fromInt(getIntegerValue(Constants.P_CO_STATUS));
    }

    /**
     * <p>setStatus.</p>
     *
     * @param status a int.
     */
    public void setStatus(EnumContractStatus status) {
        setIntegerValue(Constants.P_CO_STATUS, EnumContractStatus.toInt(status));
    }
    
    /**
     * DIRNUM = : java.lang.String
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDirectoryNumber() {
        return getStringValue(Constants.P_DIRNUM);
    }

    /**
     * <p>setDirectoryNumber (DIRNUM) and not main_dirnum.</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public void setDirectoryNumber(String dirnum) {
        setStringValue(Constants.P_DIRNUM, dirnum);
    }
    
    /** SM_NUM */
    public String getStorageMedium() {return getStringValue(Constants.P_SM_NUM); }
    /** SM_NUM */
    public void setStorageMedium(String iccNumber) { setStringValue(Constants.P_SM_NUM, iccNumber); }

    /** search with MAIN_DIRNUM  (and not DIRNUM)*/
    public String getMainDirectoryNumber() {return getStringValue(Constants.P_MAIN_DIRNUM); }
    /** search with MAIN_DIRNUM  (and not DIRNUM)*/
    public void setMainDirectoryNumber(String msisdn) { setStringValue(Constants.P_MAIN_DIRNUM, msisdn); }
   
    
    /**
     * <p>getDealerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDealerIdPub() {
        return getStringValue(Constants.P_DEALER_ID_PUB);
    }

    /**
     * <p>setDealerIdPub (DEALER_ID_PUB).</p>
     *
     * @param dealerIdPub a {@link java.lang.String} object.
     */
    public void setDealerIdPub(String dealerIdPub) {
        setStringValue(Constants.P_DEALER_ID_PUB, dealerIdPub);
    }
    
    /**
     * <p>getMarketId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getMarketId() {
        return getLongValue(Constants.P_SCCODE);
    }

    /**
     * <p>setMarketId.</p>
     *
     * @param scCode a {@link java.lang.Long} object.
     */
    public void setMarketId(Long scCode) {
        setLongValue(Constants.P_SCCODE, scCode);
    }

    /**
     * <p>getMarketIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getMarketIdPub() {
        return getStringValue(Constants.P_SCCODE_PUB);
    }

    /**
     * <p>setMarketIdPub.</p>
     *
     * @param sccodepub a {@link java.lang.String} object.
     */
    public void setMarketIdPub(String sccodepub) {
        setStringValue(Constants.P_SCCODE_PUB, sccodepub);
    }
    
    /**
     * PLCODE : 1001 : java.lang.Long
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNetworkId() {
        return getLongValue(Constants.P_PLCODE);
    }

    /**
     * <p>setNetworkId.</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    public void setNetworkId(Long plcode) {
        setLongValue(Constants.P_PLCODE, plcode);
    }
    
    
    /**
     * cf SEARCH_EVEN_WITHOUT_DN
     *
     * @param search a {@link java.lang.String} object.
     */
    public void setSearcher(String search) {
        setStringValue(Constants.P_SEARCHER, search);
    }
    
    
    public Long getStartIndex(){ return getLongValue(Constants.P_START_INDEX); }
    public void setStartIndex(Long start){ setLongValue(Constants.P_START_INDEX, start);}
    
    
    /**
     * @return SRCH_COUNT
     */
    public Long getSearchCount(){ return getLongValue(Constants.P_SRCH_COUNT);}
    
    /**
     * <p>setSearchCount.</p>
     *
     * @param maxWishedResults a {@link java.lang.Long} object.
     */
    public void setSearchCount(Long maxWishedResults) {
        setLongValue(Constants.P_SRCH_COUNT, maxWishedResults);
    }

    

    
}
