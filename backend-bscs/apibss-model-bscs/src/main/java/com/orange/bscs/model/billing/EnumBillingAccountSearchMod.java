package com.orange.bscs.model.billing;

/**
 * Flag used in CMS Command BILLING_ACCOUNT.SEARCH.
 *
 * O: Return data of all billing accounts owned. U: Return data of all
 * potentially usable (i.e. only active) billing accounts.
 *
 * @author IT&Labs
 * @version $Id: $
 */
/**
 * ALL : All Status and in All members of a LA.
 * USABLE : can be use as an explicit assignment 
 * 
 * @author IT&Labs
 *
 */
public enum EnumBillingAccountSearchMod {
    ALL('O'), USABLE('U');

    private char mod;

    private EnumBillingAccountSearchMod(char c) {
        mod = c;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a char.
     */
    public char toCharacter() {
        return mod;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @param en a {@link com.orange.bscs.model.billing.EnumBillingAccountSearchMod} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toCharacter(EnumBillingAccountSearchMod en) {
        return (null == en ? null : en.toCharacter());
    }

    /**
     * <p>fromCaracter.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.billing.EnumBillingAccountSearchMod} object.
     */
    public static EnumBillingAccountSearchMod fromCaracter(Character c) {
        if (null == c) {
            return null;
        }
        EnumBillingAccountSearchMod result;
        switch (c) {
        case 'O':
            result = ALL;
            break;
        case 'U':
            result = USABLE;
            break;
        default:
            result = null;
        }
        return result;
    }

}
