package com.orange.bscs.model.billing;

/**
 * <p>EnumBillingAccountReadMod class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumBillingAccountReadMod {
    ALL('A'), LATEST_VERSION('L');

    private char readMode;

    private EnumBillingAccountReadMod(char mode) {
        readMode = mode;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character toCharacter() {
        return readMode;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @param value a {@link com.orange.bscs.model.billing.EnumBillingAccountReadMod} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toCharacter(EnumBillingAccountReadMod value) {
        if (null == value) {
            return ALL.toCharacter();
        } else {
            return value.toCharacter();
        }
    }
}
