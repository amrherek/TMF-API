package com.orange.bscs.model.accounting;

/**
 * <p>EnumBookingFeeType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumBookingFeeType {
    RECURRING('R'), NON_RECURRING('N');

    private char bscs;

    private EnumBookingFeeType(char c) {
        bscs = c;
    }

    /**
     * convert a string to an enum value.
     *
     * @param s "R" or "N"
     * @return RECURRING, NON_RECURRING or Null
     */
    public static EnumBookingFeeType fromString(String s) {
        if ("R".equals(s)) {
            return RECURRING;
        } else if ("N".equals(s)) {
            return NON_RECURRING;
        }
        return null;
    }

    /**
     * <p>fromChar.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.accounting.EnumBookingFeeType} object.
     */
    public static EnumBookingFeeType fromChar(Character c) {
        if (null == c) {
            return null;
        }
        EnumBookingFeeType res = null;
        switch (c) {
            case 'R':
                res = RECURRING;
                break;
            case 'N':
                res = NON_RECURRING;
                break;
            default:
                res = null;
        }
        return res;
    }

    /**
     * <p>getChar.</p>
     *
     * @return a char.
     */
    public char getChar() {
        return bscs;
    }

    public String getString() {
        return this == RECURRING ? "R" : "N";
    }

    /**
     * <p>toChar.</p>
     *
     * @param type a {@link com.orange.bscs.model.accounting.EnumBookingFeeType} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toChar(EnumBookingFeeType type) {
        if (null == type) {
            return null;
        }
        return type.getChar();
    }

    public static String toString(EnumBookingFeeType type) {
        return (null == type) ? null : type.getString();
    }

}
