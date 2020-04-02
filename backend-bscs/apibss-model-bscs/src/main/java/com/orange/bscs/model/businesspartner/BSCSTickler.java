package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>
 * {@code
 * TICKLER.READ {
 * * TICK_NUMBER          =  : java.lang.Long
 * }
 * => {
 *   CLOSED_BY            =  : java.lang.String
 *   CLOSED_DATE          =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   CO_ID                =  : java.lang.Long
 *   CO_ID_PUB            =  : java.lang.String
 *   CREATION_DATE        =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   CREATION_USER        =  : java.lang.String
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   FIRST_DISTRIB_USER   =  : java.lang.String
 *   FOLLOW_UP_CODE       =  : java.lang.String
 *   FOLLOW_UP_DATE       =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   FOLLOW_UP_USER       =  : java.lang.String
 *   LAST_SEND_DATE       =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   LAST_SEND_USER       =  : java.lang.String
 *   MODIFIED_DATE        =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   MODIFIED_USER        =  : java.lang.String
 *   SECOND_DISTRIB_USER  =  : java.lang.String
 *   THIRD_DISTRIB_USER   =  : java.lang.String
 *   TICK_CODE            =  : java.lang.String
 *   TICK_LDES            =  : java.lang.String
 *   TICK_PRIORITY        =  : java.lang.Integer
 *   TICK_SHDES           =  : java.lang.String
 *   TICK_STATUS          =  : java.lang.String
 *   TICK_XCOORD          =  : java.lang.String
 *   TICK_YCOORD          =  : java.lang.String
 *   problem types        = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]TR_ID                =  : java.lang.Long
 * }
 * }
 * </pre>
 * 
 * @author IT&Labs
 * 
 */
public class BSCSTickler extends BSCSTicklerNew {

    private static final long serialVersionUID = -7127753431240167960L;

    public static final String P_TICK_NUMBER = "TICK_NUMBER";
    public static final String P_PROBLEM_TYPES = "problem types";
    public static final String P_TR_ID = "TR_ID";

    
    public Long getTickNumber(){ return getLongValue(P_TICK_NUMBER);}
    public void setTickNumber(Long value){ setLongValue(P_TICK_NUMBER, value);}
    
    
    
    /**  BU_ID (only on result of TICKLERS.SEARCH, not with TICKLER.READ) */
    public Long getBusinessUnit(){ return getLongValue(Constants.P_BU_ID);}
    /**  BU_ID (only on result of TICKLERS.SEARCH, not with TICKLER.READ) */
    public void setBusinessUnit(Long buid){ setLongValue(Constants.P_BU_ID, buid);}
    
    
    public String getCloseBy() {
        return getStringValue("CLOSED_BY");
    }

    public void setCloseBy(String closeBy) {
        setStringValue("CLOSED_BY", closeBy);
    }

    public Date getClosedDate() {
        return getDateTimeValue("CLOSED_DATE");
    }

    public void setClosedDate(Date closedDate) {
        setDateTimeValue("CLOSED_DATE", closedDate);
    }


    
    public Date getCreationDate() {
        return getDateTimeValue("CREATION_DATE");
    }

    public void setCreationDate(Date creationDate) {
        setDateTimeValue("CREATION_DATE", creationDate);
    }

    public String getCreationUser() {
        return getStringValue("CREATION_USER");
    }

    public void setCreationUser(String creationUser) {
        setStringValue("CREATION_USER", creationUser);
    }


    /**  CS_CODE (only with TICKLERS.SEARCH, not with TICKLER.READ) */
    public String getCustomerCode() {
        return getStringValue("CS_CODE");
    }

    /**  CS_CODE (only with TICKLERS.SEARCH, not with TICKLER.READ) */
    public void setCustomerCode(String customerCode) {
        setStringValue("CS_CODE", customerCode);
    }
    

    public Date getLastSendDate() {
        return getDateTimeValue("LAST_SEND_DATE");
    }

    public void setLastSendDate(Date lastSendDate) {
        setDateTimeValue("LAST_SEND_DATE", lastSendDate);
    }

    public String getLastSendUser() {
        return getStringValue("LAST_SEND_USER");
    }

    public void setLastSendUser(String lastSendUser) {
        setStringValue("LAST_SEND_USER", lastSendUser);
    }

    public Date getModifiedDate() {
        return getDateTimeValue("MODIFIED_DATE");
    }

    public void setModifiedDate(Date modifiedDate) {
        setDateTimeValue("MODIFIED_DATE", modifiedDate);
    }

    public String getModifiedUser() {
        return getStringValue("MODIFIED_USER");
    }

    public void setModifiedUser(String modifiedUser) {
        setStringValue("MODIFIED_USER", modifiedUser);
    }


    /** TICK_XCOORD */
    public String getTickXCoordonnees() {
        return getStringValue("TICK_XCOORD");
    }

    /** TICK_XCOORD */
    public void setTickXCoordonnees(String tickXCoordonnees) {
        setStringValue("TICK_XCOORD", tickXCoordonnees);
    }

    /** TICK_YCOORD */
    public String getTickYCoordonnees() {
        return getStringValue("TICK_YCOORD");
    }

    /** TICK_YCOORD */
    public void setTickYCoordonnees(String tickYCoordonnees) {
        setStringValue("TICK_YCOORD", tickYCoordonnees);
    }


}
