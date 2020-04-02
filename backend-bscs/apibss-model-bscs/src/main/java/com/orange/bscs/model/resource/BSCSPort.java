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
 *   PORT_NP              =  : java.lang.Integer
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
public class BSCSPort extends BSCSModel {

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_PORT_ID);
    }

    /**
     * <p>setId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public BSCSPort setId(Long id) {
        setLongValue(Constants.P_PORT_ID, id);
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
    public BSCSPort setPortNum(String portNum) {
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
    public BSCSPort setPortStatus(String portStatus) {
        setStringValue(Constants.P_PORT_STATUS, portStatus);
        return this;
    }

    
    /**
     * <p>getStatusChar.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
   /* public Character getStatusChar() {
        return getCharacterValue(Constants.P_SM_STATUS);
    }


    public BSCSPort setStatusChar(Character status) {
        setCharacterValue(Constants.P_SM_STATUS, status);
        *return this;
    }*/

}
