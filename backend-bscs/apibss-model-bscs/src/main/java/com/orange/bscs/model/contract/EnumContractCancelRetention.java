package com.orange.bscs.model.contract;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;


/**
 * Enumeration of value values for RETENTION parameters in CONTRACT.CANCEL command.
 * 
 * @author IT&Labs
 *
 */
public enum EnumContractCancelRetention {

    //0 -> No retention
    NO_RETENTION,
    //1 -> With hold
    WITH_HOLD,
    //2 -> Return to same dealer
    SAME_DEALER,
    //3 -> Return to different dealer
    OTHER_DEALER;
    

    // Accept an ordinal (0-3) or name (WITH_HOLD)...
    public static EnumContractCancelRetention fromString(String propertyValue){
        if(StringUtils.isBlank(propertyValue)){
            return null;
        }
        if(1==propertyValue.length() && NumberUtils.isDigits(propertyValue)){
            return EnumContractCancelRetention.values()[NumberUtils.toInt(propertyValue, 2)];
        } 
        return EnumContractCancelRetention.fromString(propertyValue);
    }

    public static Integer toInteger(EnumContractCancelRetention value) {
        if(null==value){
            return null;
        }
        return value.ordinal();
    }
    
    public static Integer toInteger(String property) {
        return toInteger(fromString(property));
    }
    
}
