package com.orange.bscs.model.usagedata;

/**
 * <p>EnumUsageTypeDirection class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumUsageTypeDirection {
    RECEIVABLE('R'), 
    PAYABLE('P');

    private char direction;

    EnumUsageTypeDirection(char d) {
        direction = d;
    }

    /**
     * <p>getChar.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getChar() {
        return direction;
    }

    /**
     * <p>getChar.</p>
     *
     * @param dir a {@link com.orange.bscs.model.usagedata.EnumUsageTypeDirection} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character getChar(EnumUsageTypeDirection dir) {
        return null == dir ? null : dir.getChar();
    }

    /**
     * <p>fromChar.</p>
     *
     * @param d a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.usagedata.EnumUsageTypeDirection} object.
     */
    public static EnumUsageTypeDirection fromChar(Character d) {
        if (null == d) {
            return null;
        }
        EnumUsageTypeDirection res = null;
        switch (d) {
        case 'R':
            res = RECEIVABLE;
            break;
        case 'P':
            res = PAYABLE;
            break;
        default:
            res = null;
        }
        return res;
    }

}
