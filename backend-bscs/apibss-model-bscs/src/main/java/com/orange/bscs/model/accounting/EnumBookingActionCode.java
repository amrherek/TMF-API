package com.orange.bscs.model.accounting;

/**
 * <p>EnumBookingActionCode class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumBookingActionCode {
    INSERT('I'), DELETE('D');

    private char action;

    private EnumBookingActionCode(char a) {
        action = a;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a char.
     */
    public char toCharacter() {
        return action;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @param en a {@link com.orange.bscs.model.accounting.EnumBookingActionCode} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toCharacter(EnumBookingActionCode en) {
        return (null == en ? null : en.toCharacter());
    }

    /**
     * <p>fromCaracter.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.accounting.EnumBookingActionCode} object.
     */
    public static EnumBookingActionCode fromCaracter(Character c) {
        if (null == c) {
            return null;
        }
        EnumBookingActionCode result;
        switch (c) {
        case 'I':
            result = INSERT;
            break;
        case 'D':
            result = DELETE;
            break;
        default:
            result = null;
        }
        return result;
    }

}
