package com.orange.bscs.model.resource;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>{@code
 * PORTS.SEARCH {
 *   HLCODE               =  : java.lang.Long
 *   HLCODE_PUB           =  : java.lang.String
 *   PLCODE               =  : java.lang.Long
 *   PLCODE_PUB           =  : java.lang.String
 *   RESERVATION          =  : java.lang.Boolean
 *   SRCH_COUNT           =  : java.lang.Integer
 *   PORT_NP_PUB              =  : java.lang.String
 *   SUBM_ID              =  : java.lang.Long
 *   SUBM_ID_PUB          =  : java.lang.String
 * }
 * => {
 *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
 *   ALL STORAGEMEDIUMs   = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]DL_CODE              =  : java.lang.Long
 *  -[0]HLCODE               =  : java.lang.Long
 *  -[0]PORT_NUM      =  : java.lang.String
 *  -[0]PORT_NPCODE          =  : java.lang.Long
 *  -[0]SM_ID                =  : java.lang.Long
 *  -[0]SM_STATUS            =  : java.lang.Character
 * *-[0]STMEDNO              =  : java.lang.String
 * } * 
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSPortSearch extends BSCSModel {

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
	
    private static final String SEARCH_COUNT = "SEARCH_COUNT";

    
    public Integer getSearchCount() {
        return getIntegerValue(SEARCH_COUNT);
    }

    /**
     * <p>setSearchCount.</p>
     *
     * @param limit a {@link java.lang.Integer} object.
     */
    public void setSearchCount(Integer limit) {
        setIntegerValue(SEARCH_COUNT, limit);
    }
    
    
    public Long getId() {
        return getLongValue(Constants.P_PORT_ID);
    }
    /**
     * <p>setId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public BSCSPortSearch setId(Long id) {
        setLongValue(Constants.P_PORT_ID, id);
        return this;
    }


    
    public String getNPCodePub() {
        return getStringValue(Constants.PORT_NP_PUB);
    }

    /**
     * <p>setId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public BSCSPortSearch setNPCodePub(String npcode) {
        setStringValue(Constants.PORT_NP_PUB, npcode);
        return this;
    }


   
    public String getPortNum() {
        return getStringValue(Constants.P_PORTNUM);
    }

    /**
     * <p>setICCNumber.</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public BSCSPortSearch setPortNum(String portNum) {
        setStringValue(Constants.P_PORTNUM, portNum);
        return this;
    }
    
    public String getPortStatus() {
        return getStringValue(Constants.P_PORT_STATUS);
    }

    /**
     * <p>setICCNumber.</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public BSCSPortSearch setPortStatus(String portStatus) {
        setStringValue(Constants.P_PORT_STATUS, portStatus);
        return this;
    }

    
    public void setReservation(Boolean flag) {
        setBooleanValue(Constants.P_RESERVATION, flag);
    }
    public Boolean isReservation(){ return getBooleanValue(Constants.P_RESERVATION);}
    
    /**
     * <p>getSubmarketId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSubmarketId() {
        return getLongValue(Constants.P_SUBM_ID);
    }

    /**
     * <p>setSubmarketId.</p>
     *
     * @param submId a {@link java.lang.Long} object.
     */
    public void setSubmarketId(Long submId) {
        setLongValue(Constants.P_SUBM_ID, submId);
    }

    /**
     * <p>get NetworkId (PLCODE).</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getNetworkId() {
        return getLongValue(Constants.P_PLCODE);
    }

    /**
     * <p>set NetworkId (PLCODE).</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    
    /**
     * <p>getHLRCode.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getHLRCode() {
        return getLongValue(Constants.P_HLCODE);
    }

    /**
     * <p>setHLRCode.</p>
     *
     * @param hlcode a {@link java.lang.Long} object.
     */
    public void setHLRCode(Long hlcode) {
        setLongValue(Constants.P_HLCODE, hlcode);
    }


    /**
     * <p>setNetworkId.</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    public void setNetworkId(Long plcode) {
        setLongValue(Constants.P_PLCODE, plcode);
    }


}
