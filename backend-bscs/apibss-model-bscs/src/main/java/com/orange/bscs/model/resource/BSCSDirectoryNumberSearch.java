package com.orange.bscs.model.resource;

import java.util.ArrayList;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * Required fields may be :
 *
 * Numbering plan NPCODE SubmarketId SUBM_ID NetworkId PLCODE PhoneNumber DIRNUM
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSDirectoryNumberSearch extends BSCSDirectoryNumber {

    private static final String SEARCH_COUNT = "SEARCH_COUNT";
    private static final String STATUSES = "STATUSES";

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
    public BSCSDirectoryNumberSearch setNetworkId(Long plcode) {
        setLongValue(Constants.P_PLCODE, plcode);
        return this;
    }

    /**
     * <p>getSearchCount.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
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

    /**
     * <p>getFilterStatus.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFilterStatus() {
        List<BSCSModel> dnStatus = getListOfBSCSModelValue(STATUSES);
        if (null != dnStatus) {
            StringBuilder result = new StringBuilder();
            for (BSCSModel bscsStatus : dnStatus) {
                Character c = bscsStatus.getCharacterValue(Constants.P_DN_STATUS);
                result.append(c);
            }
            return result.toString();
        }
        return null;
    }

    /**
     * <p>setFilterStatus.</p>
     *
     * @param status a {@link java.lang.String} object.
     */
    public void setFilterStatus(String status) {
        if (null == status || 0 == status.length()) {
            setListOfBSCSModelValue(STATUSES, null);
        } else {
            char[] aStatus = status.toCharArray();
            List<BSCSModel> statuses = new ArrayList<BSCSModel>();
            for (char cStatus : aStatus) {
                if (null != EnumDirectoryNumberStatus.parseChar(cStatus)) {
                    BSCSModel bscsStatus = new BSCSModel();
                    bscsStatus.setCharacterValue(Constants.P_DN_STATUS, cStatus);
                    statuses.add(bscsStatus);
                }
            }
            setListOfBSCSModelValue(STATUSES, statuses);
        }
    }
    
    public void setFilterStatus(EnumDirectoryNumberStatus... status){
        List<BSCSModel> statuses = new ArrayList<BSCSModel>();
        for (EnumDirectoryNumberStatus cStatus : status) {
            BSCSModel bscsStatus = new BSCSModel();
            bscsStatus.setCharacterValue(Constants.P_DN_STATUS, cStatus.getCode());
            statuses.add(bscsStatus);
        }
        setListOfBSCSModelValue(STATUSES, statuses);
    }

    public void setReservation(Boolean flag) {
        setBooleanValue(Constants.P_RESERVATION, flag);
    }
    public Boolean isReservation(){ return getBooleanValue(Constants.P_RESERVATION);}

    public void setSearchBlock(boolean flag) {
        setBooleanValue(Constants.P_SEARCH_BLOCK, flag);
    }
    public Boolean isSearchBlock(){ return getBooleanValue(Constants.P_SEARCH_BLOCK);}
    
    public Integer getMinBlockSize() { return getIntegerValue(Constants.P_MIN_BLK_SIZE);}
    public void setMinBlockSize(Integer size) { setIntegerValue(Constants.P_MIN_BLK_SIZE, size);}
    
    public Integer getMaxBlockSize() { return getIntegerValue(Constants.P_MAX_BLK_SIZE);}
    public void setMaxBlockSize(Integer size) { setIntegerValue(Constants.P_MAX_BLK_SIZE, size);}
    
}
