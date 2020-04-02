package com.orange.bscs.model.businesspartner;

/**
 * Possible status and transition of Customer
 * <p>
 * (cf specifications Manage Party, U_PA_010)
 * </p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumCustomerStatus {

    INITIALISED('i', 'a', 'd'), 
    VALIDATED('a', 'i', 's', 'd'), 
    SUSPENDED('s', 'a', 'd'), 
    DEACTIVATED('d');

    private char code;
    private char[] valideTransitions;

    private EnumCustomerStatus(char code, char... nextStatus) {
        this.code = code;
        this.valideTransitions = nextStatus;
    }

    /**
     * <p>parse.</p>
     *
     * @param any
     *            char or name() or null
     * @return null or found status
     */
    public static EnumCustomerStatus parse(String any) {
        if (null == any) {
            return null;
        }

        EnumCustomerStatus res = null;

        if (1 == any.length()) {
            res = parse(any.charAt(0));
        } else {
            for (EnumCustomerStatus s : EnumCustomerStatus.values()) {
                if (s.name().equalsIgnoreCase(any)) {
                    res = s;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * <p>getCharacter.</p>
     *
     * @return a char.
     */
    public char getCharacter() {
        return code;
    }

    /**
     * <p>isValideNewStatus.</p>
     *
     * @param newStatus a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     * @return a boolean.
     */
    public boolean isValideNewStatus(EnumCustomerStatus newStatus) {
        if (null == valideTransitions) {
            // deactivated
            return false;
        }
        boolean res = false;
        char n = newStatus.code;
        for (char c : valideTransitions) {
            if (c == n) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * <p>parse.</p>
     *
     * @param status a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumCustomerStatus} object.
     */
    public static EnumCustomerStatus parse(Character status) {
        EnumCustomerStatus res = null;
        if (null != status) {
            switch (status) {
            case 'i':
                res = INITIALISED;
                break;
            case 'a':
                res = VALIDATED;
                break;
            case 's':
                res = SUSPENDED;
                break;
            case 'd':
                res = DEACTIVATED;
                break;
            default:
                res = null;
                break;
            }
        }
        return res;
    }

}
