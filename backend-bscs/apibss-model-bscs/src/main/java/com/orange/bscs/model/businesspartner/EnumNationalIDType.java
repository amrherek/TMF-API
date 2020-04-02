package com.orange.bscs.model.businesspartner;

/**
 * <p>EnumNationalIDType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumNationalIDType {
    DRIVINGLICENCE("DRIVING_LICENCE","ADR_DRIVELICENCE"), 
    SECURITYSOCIALCARD("SECURITY_SOCIAL_CARD","ADR_SOCIALSENO"), 
    NATIONALORGANISATIONIDENTIFIER("NATIONAL_ORGANISATION_IDENTIFIER","ADR_COMPNO"), 
    PASSPORT("PASSPORT","ADR_PASSPORTNO");

    private String stringValue;
    private String bscsFieldName;

    /**
     * <p>parse.</p>
     *
     * @param labelIgnoreCase
     *            may be null, can be "PASSPORT" or "Passport"
     * @return null or found value
     */
    public static EnumNationalIDType parse(String labelIgnoreCase) {
        if (null == labelIgnoreCase) {
            return null;
        }

        for (EnumNationalIDType c : EnumNationalIDType.values()) {
            if (c.name().equalsIgnoreCase(labelIgnoreCase)) {
                return c;
            }
            if (c.toString().equals(labelIgnoreCase)) {
                return c;
            }
        }
        return null;
    }

    private EnumNationalIDType(String stringValue, String bscsFieldName) {
        this.stringValue = stringValue;
        this.bscsFieldName = bscsFieldName;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.stringValue;
    }

    public static String getBscsFieldName(EnumNationalIDType type) {
        if(null==type){
            return null;
        }
        
        return type.bscsFieldName;
    }

}
