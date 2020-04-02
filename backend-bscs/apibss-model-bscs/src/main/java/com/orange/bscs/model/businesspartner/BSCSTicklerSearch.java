package com.orange.bscs.model.businesspartner;

import java.util.Date;

import com.orange.bscs.api.utils.config.Constants;

public class BSCSTicklerSearch extends BSCSTickler {
    
    private static final long serialVersionUID = 9192206654683413020L;
    
    public String getDistributionUser(){ return getStringValue("DISTRIBUTION_USER");}
    public void setDistributionUser(String user){ setStringValue("DISTRIBUTION_USER", user);}
    
    public Date getFromCreationDate(){ return getDateTimeValue("FROM_CREATION_DATE");}
    public void setFromCreationDate(Date dt){ setDateTimeValue("FROM_CREATION_DATE",dt);}
    
    public Date getFromFollowUpDate(){ return getDateTimeValue("FROM_FOLLOW_UP_DATE");}
    public void setFromFollowUpDate(Date dt){ setDateTimeValue("FROM_FOLLOW_UP_DATE",dt);}

    public Date getToCreationDate(){ return getDateTimeValue("TO_CREATION_DATE");}
    public void setToCreationDate(Date dt){ setDateTimeValue("TO_CREATION_DATE",dt);}
    
    public Date getToFollowUpDate(){ return getDateTimeValue("TO_FOLLOW_UP_DATE");}
    public void setToFollowUpDate(Date dt){ setDateTimeValue("TO_FOLLOW_UP_DATE",dt);}
   
    
    public Boolean isIgnoreBU(){ return getBooleanValue("IGNORE_BU_IND");}
    public void setIgnoreBU(boolean flag){ setBooleanValue("IGNORE_BU_IND",flag);}
    
    public String getNotTickCode(){ return getStringValue("NOT_TICK_CODE");}
    public void setNotTickCode(String ntc){ setStringValue("NOT_TICK_CODE",ntc);}
    
    public String getPartyType(){ return getStringValue("PARTY_TYPE");}
    public void setPartyType(String value){ setStringValue("PARTY_TYPE",value);}

    public String getPartyRoleID(){ return getStringValue("PARTY_ROLE_ID");}
    public void setPartyRoleID(String value){ setStringValue("PARTY_ROLE_ID",value);}

    public String getPartyRoleShortName(){ return getStringValue("PARTY_ROLE_SHNAME");}
    public void setPartyRoleShortName(String value){ setStringValue("PARTY_ROLE_SHNAME",value);}

    
    public Long getSearchCount(){ return getLongValue(Constants.P_SRCH_COUNT);}
    public void setSearchCount(Long max){ setLongValue(Constants.P_SRCH_COUNT, max);}
    
    public Long getStartIndex(){ return getLongValue("START_INDEX");}
    public void setStartIndex(Long start){ setLongValue("START_INDEX", start);}
    
    
    public Character getOrderCreationDate(){ return getCharacterValue("ORDR_CREATION_DATE");}
    public void setOrderCreationDate(Character c){ setCharacterValue("ORDR_CREATION_DATE", c); }

    
    /** TR_ID */
    public Long getProblemTypeID(){ return getLongValue("TR_ID");}
    /** TR_ID */
    public void setProblemTypeID(Long trid){ setLongValue("TR_ID", trid); }

    /** TR_MODE */
    public Long getTickMode(){ return getLongValue("TR_MODE");}
    /** TR_MODE */
    public void setTickMode(Long trmode){ setLongValue("TR_MODE", trmode); }

}
