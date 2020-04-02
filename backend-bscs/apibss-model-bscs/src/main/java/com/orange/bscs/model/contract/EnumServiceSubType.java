package com.orange.bscs.model.contract;

/**
 * Used in FagCIB, BSCSService
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumServiceSubType {
    COFU('C'), 
    POFU('P'), 
    POFUL('L');

    private Character character;

    private EnumServiceSubType(Character c) {
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
     * @param subtype a {@link com.orange.bscs.model.contract.EnumServiceSubType} object.
     * @return a {@link java.lang.Character} object.
     */
    public static Character toChar(EnumServiceSubType subtype) {
        return null == subtype ? null : subtype.character;
    }

    /**
     * <p>parseChar.</p>
     *
     * @param c a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.contract.EnumServiceSubType} object.
     */
    public static EnumServiceSubType parseChar(Character c) {
        EnumServiceSubType subtype = null;

        if (null != c) {
            for (EnumServiceSubType st : EnumServiceSubType.values()) {
                if (c.equals(st.character)) {
                    subtype = st;
                    break;
                }
            }
        }
        return subtype;
    }

}
