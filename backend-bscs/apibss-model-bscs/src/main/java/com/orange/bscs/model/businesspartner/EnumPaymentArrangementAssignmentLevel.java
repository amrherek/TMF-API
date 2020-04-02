package com.orange.bscs.model.businesspartner;

/**
 * Criteria of PAYMENT_ARRANGEMENT_ASSIGNMENT.READ
 *
 * Critria. 'U' - Read user/billing account specific payment arrangements 'D' -
 * Read document type specific payment arrangements NULL - Read all payment
 * arrangements
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumPaymentArrangementAssignmentLevel {
    USER_OR_BA('U'), DOCUMENT_TYPE('D'), ALL;

    private Character level;

    private EnumPaymentArrangementAssignmentLevel() {
    }

    private EnumPaymentArrangementAssignmentLevel(char c) {
        level = c;
    }

    /**
     * <p>getCharacter.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character getCharacter() {
        return level;
    }

    /**
     * <p>fromChar.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumPaymentArrangementAssignmentLevel} object.
     */
    public EnumPaymentArrangementAssignmentLevel fromChar(Character c) {
        if (null == c) {
            return ALL;
        } else if ('U' == c || 'u' == c) {
            return USER_OR_BA;
        } else if ('D' == c || 'd' == c) {
            return DOCUMENT_TYPE;
        }
        return null;
    }
}
