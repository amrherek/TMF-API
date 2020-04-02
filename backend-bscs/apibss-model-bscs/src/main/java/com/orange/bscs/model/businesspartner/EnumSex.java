package com.orange.bscs.model.businesspartner;

/**
 * <p>EnumSex class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumSex {
    MALE('M'), 
    FEMALE('F'), 
    UNKNOWN('U');

    private char code;

    private EnumSex(char sex) {
        code = sex;
    }

    /**
     * <p>parse.</p>
     *
     * @param any a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumSex} object.
     */
    public static EnumSex parse(String any) {
        if (null == any) {
            return null;
        }

        if ("M".equals(any) || "MALE".equalsIgnoreCase(any)) {
            return MALE;
        } else if ("F".equals(any) || "FEMALE".equalsIgnoreCase(any)) {
            return FEMALE;
        } else if ("U".equals(any) || "UNKNOWN".equalsIgnoreCase(any)) {
            return UNKNOWN;
        }
        return null;
    }

    /**
     * <p>getCharacter.</p>
     *
     * @return a char.
     */
    public char getCharacter() {
        return code;
    }

}
