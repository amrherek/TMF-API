package com.orange.bscs.model.billing;

import org.apache.commons.lang.StringUtils;

/**
 * <p>EnumBillingAccountStatus class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumBillingAccountStatus {
    ACTIVE('A'), INACTIVE('I'), CLOSED('C');

    private char bscs;

    private EnumBillingAccountStatus(char status) {
        bscs = status;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character toCharacter() {
        return bscs;
    }

    
    /**
     * From SOA
     */
    public static EnumBillingAccountStatus parse(String codeOrLabel){
        if(StringUtils.isBlank(codeOrLabel)){
            return null;
        }
        
        EnumBillingAccountStatus result=null;
        for(EnumBillingAccountStatus s : values()){
            if(StringUtils.equalsIgnoreCase(s.name(),codeOrLabel) 
              || s.bscs==codeOrLabel.charAt(0)){
                result=s;
                break;
            }
        }
        
        if(null==result){
            throw new IllegalArgumentException(String.format("%s is not a valid BillingAccountStatus ", codeOrLabel));
        }
        return result;
    }
    
    /**
     * <p>toCharacter.</p>
     *
     * @param value a {@link com.orange.bscs.model.billing.EnumBillingAccountStatus} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toCharacter(EnumBillingAccountStatus value) {
        if (null == value) {
            return null;
        } else {
            return value.toCharacter();
        }
    }

    /**
     * <p>fromCharacter.</p>
     *
     * @param status a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.billing.EnumBillingAccountStatus} object.
     */
    public static EnumBillingAccountStatus fromCharacter(Character status) {
        if (null == status) {
            return null;
        }

        EnumBillingAccountStatus result;
        switch (status) {
        case 'A':
            result = ACTIVE;
            break;
        case 'I':
            result = INACTIVE;
            break;
        case 'C':
            result = CLOSED;
            break;
        default:
            result = null;
        }
        return result;
    }

}
