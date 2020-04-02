package com.orange.bscs.model.resource;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>{@code
 * STORAGE_MEDIUM.SEARCH {
 *   DIRNUM               =  : java.lang.String
 *   HLCODE               =  : java.lang.Long
 *   HLCODE_PUB           =  : java.lang.String
 *   PLCODE               =  : java.lang.Long
 *   PLCODE_PUB           =  : java.lang.String
 *   RESERVATION          =  : java.lang.Boolean
 *   SMC_ID               =  : java.lang.Long
 *   SRCH_COUNT           =  : java.lang.Integer
 *   STMEDNO              =  : java.lang.String
 *   SUBM_ID              =  : java.lang.Long
 *   SUBM_ID_PUB          =  : java.lang.String
 * }
 * => {
 *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
 *   ALL STORAGEMEDIUMs   = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 *  -[0]DL_CODE              =  : java.lang.Long
 *  -[0]HLCODE               =  : java.lang.Long
 *  -[0]INITIAL_CREDIT       =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
 *  -[0]LINKED_DN_ID         =  : java.lang.Long
 *  -[0]LINKED_PORT_NUM      =  : java.lang.String
 *  -[0]PORT_NPCODE          =  : java.lang.Long
 *  -[0]SMC_ID               =  : java.lang.Long
 *  -[0]SM_ID                =  : java.lang.Long
 *  -[0]SM_STATUS            =  : java.lang.Character
 * *-[0]STMEDNO              =  : java.lang.String
 * }
 * }</pre>
 * 
 * <pre>{@code
 * STORAGE_MEDIUM.CREATE {
 *   DEALER_ID            =  : java.lang.Long
 *   DEALER_ID_PUB        =  : java.lang.String
 *   HLCODE               =  : java.lang.Long
 *   HLCODE_PUB           =  : java.lang.String
 *   PI_ID                =  : java.lang.Long
 *   PLCODE               =  : java.lang.Long
 *   PLCODE_PUB           =  : java.lang.String
 *   PORT_ID              =  : java.lang.Long
 *   SMC_ID               =  : java.lang.Long
 *   SM_SERIALNUM         =  : java.lang.String
 *   SUBM_ID              =  : java.lang.Long
 *   SUBM_ID_PUB          =  : java.lang.String
 * }
 * => {
 *   SM_ID                =  : java.lang.Long
 *   SM_SERIALNUM         =  : java.lang.String
 * }
 * }</pre>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSStorageMedium extends BSCSModel {

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_SM_ID);
    }

    /**
     * <p>setId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public BSCSStorageMedium setId(Long id) {
        setLongValue(Constants.P_SM_ID, id);
        return this;
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
    public BSCSStorageMedium setHLRCode(Long hlcode) {
        setLongValue(Constants.P_HLCODE, hlcode);
        return this;
    }

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
    public BSCSStorageMedium setICCNumber(String dirnum) {
        setStringValue(Constants.P_STMEDNO, dirnum);
        return this;
    }

    /**
     * <p>getStatusChar.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getStatusChar() {
        return getCharacterValue(Constants.P_SM_STATUS);
    }

    /**
     * <p>setStatusChar.</p>
     *
     * @param status a {@link java.lang.Character} object.
     */
    public BSCSStorageMedium setStatusChar(Character status) {
        setCharacterValue(Constants.P_SM_STATUS, status);
        return this;
    }

}
