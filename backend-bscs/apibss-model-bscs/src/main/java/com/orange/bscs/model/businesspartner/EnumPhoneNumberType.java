package com.orange.bscs.model.businesspartner;

import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>EnumPhoneNumberType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumPhoneNumberType {
    MAIN_NUMBER(Constants.P_ADR_PHN1), 
    SECONDARY_NUMBER(Constants.P_ADR_PHN2), 
    FAX(Constants.P_ADR_FAX),
    MOBILE(Constants.P_ADR_SMSNO);

    private String bscsFieldName;

    private EnumPhoneNumberType(String bscsName) {
        this.bscsFieldName = bscsName;
    }

    /**
     * <p>parse.</p>
     *
     * @param type a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumPhoneNumberType} object.
     */
    public static EnumPhoneNumberType parse(String type) {
        for (EnumPhoneNumberType numberType : EnumPhoneNumberType.values()) {
            if (numberType.name().equals(type)) {
                return numberType;
            }
        }
        throw new IllegalArgumentException("Unknown phone number type: " + type);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.bscsFieldName;
    }

    public static String getBscsFieldName(EnumPhoneNumberType numberType) {
        return null==numberType ? null : numberType.bscsFieldName;
    }
    
}
