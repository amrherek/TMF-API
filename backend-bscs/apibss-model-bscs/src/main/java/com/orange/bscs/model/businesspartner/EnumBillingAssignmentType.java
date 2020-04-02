package com.orange.bscs.model.businesspartner;

/**
 * <p>EnumBillingAssignmentType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumBillingAssignmentType {

    IMPLICIT('I'),
    EXPLICIT('E');

    private char c;

    private EnumBillingAssignmentType(char type) {
        this.c = type;
    }

    /**
     * <p>toCharacter.</p>
     *
     * @return a char.
     */
    public char toCharacter() {
        return c;
    }

    /**
     * <p>fromChar.</p>
     *
     * @param assignmentTypeChar a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumBillingAssignmentType} object.
     */
    public static EnumBillingAssignmentType fromChar(Character assignmentTypeChar) {
        if (null == assignmentTypeChar) {
            return null;
        }
        if ('I' == assignmentTypeChar) {
            return IMPLICIT;
        } else if ('E' == assignmentTypeChar) {
            return EXPLICIT;
        }
        return null;
    }

}
