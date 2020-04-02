package com.orange.bscs.model.businesspartner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.SVLObjectListWrapper;
import com.orange.bscs.api.model.SVLObjectWrapper;

/**
 * Update/Alter a Tickler
 * 
 * 
 * <pre>{@code
 * TICKLER.WRITE {
 *   CLOSE_TICKLER        =  : java.lang.Boolean
 *   CREATION_USER        =  : java.lang.String
 *   FIRST_DISTRIB_USER   =  : java.lang.String
 *   FOLLOW_UP_CODE       =  : java.lang.String
 *   FOLLOW_UP_DATE       =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
 *   FOLLOW_UP_USER       =  : java.lang.String
 *   LAST_SEND_DATE       =  : java.lang.Boolean
 *   LAST_SEND_USER       =  : java.lang.String
 *   SECOND_DISTRIB_USER  =  : java.lang.String
 *   THIRD_DISTRIB_USER   =  : java.lang.String
 *   TICK_CODE            =  : java.lang.String
 *   TICK_LDES            =  : java.lang.String
 * * TICK_NUMBER          =  : java.lang.Long
 *   TICK_PRIORITY        =  : java.lang.Integer
 *   TICK_SHDES           =  : java.lang.String
 *   TICK_STATUS          =  : java.lang.String
 *   TICK_XCOORD          =  : java.lang.String
 *   TICK_YCOORD          =  : java.lang.String
 *   problem types        = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 * *-[0]TR_ID                =  : java.lang.Long
 * }
 * => {
 * }
 * }</pre>
 * @author IT&Labs
 *
 */
public class BSCSTicklerWrite extends BSCSModel {

    public Long getTickNumber(){ return getLongValue(BSCSTickler.P_TICK_NUMBER);}
    public void setTickNumber(Long value){ setLongValue(BSCSTickler.P_TICK_NUMBER, value);}

    
    public String getCreationUser() {
        return getStringValue("CREATION_USER");
    }

    public void setCreationUser(String creationUser) {
        setStringValue("CREATION_USER", creationUser);
    }    

    public String getFirstDistribUser() {
        return getStringValue("FIRST_DISTRIB_USER");
    }

    public void setFirstDistribUser(String firstDistribUser) {
        setStringValue("FIRST_DISTRIB_USER", firstDistribUser);
    }

    public String getFollowUpCode() {
        return getStringValue("FOLLOW_UP_CODE");
    }

    public void setFollowUpCode(String followUpCode) {
        setStringValue("FOLLOW_UP_CODE", followUpCode);
    }

    public Date getFollowUpDate() {
        return getDateTimeValue("FOLLOW_UP_DATE");
    }

    public void setFollowUpDate(Date followUpDate) {
        setDateTimeValue("FOLLOW_UP_DATE", followUpDate);
    }

    public String getFollowUpUser() {
        return getStringValue("FOLLOW_UP_USER");
    }

    public void setFollowUpUser(String followUpUser) {
        setStringValue("FOLLOW_UP_USER", followUpUser);
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
    public String getSecondDistribUser() {
        return getStringValue("SECOND_DISTRIB_USER");
    }

    public void setSecondDistribUser(String secondDistribUser) {
        setStringValue("SECOND_DISTRIB_USER", secondDistribUser);
    }

    public String getThirdDistribUser() {
        return getStringValue("THIRD_DISTRIB_USER");
    }

    public void setThirdDistribUser(String thirdDistribUser) {
        setStringValue("THIRD_DISTRIB_USER", thirdDistribUser);
    }

    public String getTickCode() {
        return getStringValue("TICK_CODE");
    }

    public void setTickCode(String tickCode) {
        setStringValue("TICK_CODE", tickCode);
    }

    /** TICK_LDES */
    public String getTickDescription() {
        return getStringValue("TICK_LDES");
    }

    /** TICK_LDES */
    public void setTickDescription(String tickDescription) {
        setStringValue("TICK_LDES", tickDescription);
    }

    /** TICK_PRIORITY */
    public String getTickPriority() {
        return getStringValue("TICK_PRIORITY");
    }

    /** TICK_PRIORITY */
    public void setTickPriority(String tickPriority) {
        setStringValue("TICK_PRIORITY", tickPriority);
    }

    /** TICK_SHDES */
    public String getTickShortDescription() {
        return getStringValue("TICK_SHDES");
    }

    /** TICK_SHDES */
    public void setTickShortDescription(String tickShortDescription) {
        setStringValue("TICK_SHDES", tickShortDescription);
    }

    /** TICK_STATUS */
    public String getStatus() {
        return getStringValue("TICK_STATUS");
    }

    /** TICK_STATUS */
    public void setStatus(String status) {
        setStringValue("TICK_STATUS", status);
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
    

    public List<Long> getProblemTypes() {
        List<BSCSModel> l = getListOfBSCSModelValue(BSCSTickler.P_PROBLEM_TYPES);
        List<Long> result = new ArrayList<Long>();
        for (BSCSModel elt : l) {
            result.add(elt.getLongValue(BSCSTickler.P_TR_ID));
        }
        return result;
    }

    public void setProblemTypes(List<Long> problemTypes) {
        SVLObjectListWrapper svlol = AbstractSVLObjectFactory.createSVLObjectList();
        for (Long lg : problemTypes) {
            SVLObjectWrapper obj = AbstractSVLObjectFactory.createSVLObject();
            obj.setValue(BSCSTickler.P_TR_ID, lg);
            svlol.add(obj);
        }
        getSVLObject().setValue(BSCSTickler.P_PROBLEM_TYPES, svlol);
    }    
}
