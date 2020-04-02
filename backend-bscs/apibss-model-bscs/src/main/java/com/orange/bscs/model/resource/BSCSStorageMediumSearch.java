package com.orange.bscs.model.resource;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * execute (STORAGE_MEDIUM.SEARCH, { HLCODE = 1 : java.lang.Long SUBM_ID = 1 :
 * java.lang.Long STMEDNO = 895550591210013848 : java.lang.String PLCODE = 1001
 * : java.lang.Long })
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSStorageMediumSearch extends BSCSModel {

    /**
     * <p>getICCNumber.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getICCNumber() {
        return getStringValue(Constants.P_STMEDNO);
    }

    /**
     * <p>setICCNumber.</p>
     *
     * @param dirnum a {@link java.lang.String} object.
     */
    public void setICCNumber(String dirnum) {
        setStringValue(Constants.P_STMEDNO, dirnum);
    }

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
     * <p>getNetworkId.</p>
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
     * <p>getSearchCount.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSearchCount() {
        return getIntegerValue(Constants.P_SRCH_COUNT);
    }

    /**
     * <p>setSearchCount.</p>
     *
     * @param limit a {@link java.lang.Integer} object.
     */
    public void setSearchCount(Integer limit) {
        setIntegerValue(Constants.P_SRCH_COUNT, limit);
    }

    
    public Boolean isReservation(){ return getBooleanValue(Constants.P_RESERVATION);}
    
    public void setReservation(boolean flag) {
        setBooleanValue(Constants.P_RESERVATION, flag);
    }
    /**
     * <p>getNetworkId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getSmId() {
        return getLongValue(Constants.P_SM_ID);
    }

    /**
     * <p>setNetworkId.</p>
     *
     * @param plcode a {@link java.lang.Long} object.
     */
    public void setSmId(Long smId) {
        setLongValue(Constants.P_SM_ID, smId);
    }

}
