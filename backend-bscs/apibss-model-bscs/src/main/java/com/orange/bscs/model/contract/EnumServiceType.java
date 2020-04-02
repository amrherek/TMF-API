package com.orange.bscs.model.contract;

/**
 * Used in FagCIB / BSCSService
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumServiceType {

    COSTCONTROL('B');

    private Character character;

    private EnumServiceType(Character c) {
        this.character = c;
    }

    /**
     * <p>toChar.</p>
     *
     * @return a {@link java.lang.Character} object.
     */
    public Character toChar() {
        return character;
    }

    /**
     * <p>toChar.</p>
     *
     * @param subtype a {@link com.orange.bscs.model.contract.EnumServiceType} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toChar(EnumServiceType subtype) {
        return null == subtype ? null : subtype.character;
    }

    /**
     * <p>parseChar.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.contract.EnumServiceType} object.
     */
    public static EnumServiceType parseChar(Character c) {
        EnumServiceType subtype = null;

        if (null != c) {
            for (EnumServiceType st : EnumServiceType.values()) {
                if (c.equals(st.character)) {
                    subtype = st;
                    break;
                }
            }
        }
        return subtype;
    }

}
