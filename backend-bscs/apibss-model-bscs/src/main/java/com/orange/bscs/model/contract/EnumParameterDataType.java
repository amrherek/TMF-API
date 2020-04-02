package com.orange.bscs.model.contract;

/**
 * <p>EnumParameterDataType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumParameterDataType {

    DATETIME("DT"),

    NUMBER("NU"),

    // Liste de Choix à prendre dans "NUM_CH_PRM" et non pas dans "N_VALUES"
    OTHER("OT"),

    STRING("ST");

    private String value;

    private EnumParameterDataType(String s) {
        value = s;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (null != value ? value : name());
    }

    /**
     * <p>parseString.</p>
     *
     * @param v a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.contract.EnumParameterDataType} object.
     */
    public static EnumParameterDataType parseString(String v) {
        for (EnumParameterDataType e : EnumParameterDataType.values()) {
            if (e.toString().equals(v)) {
                return e;
            }
        }
        return null;
    }
}
