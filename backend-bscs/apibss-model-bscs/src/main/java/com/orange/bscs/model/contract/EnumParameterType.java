package com.orange.bscs.model.contract;

/**
 * <p>EnumParameterType class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumParameterType {

    /** Values in 'Y' / 'N') */
    CHECKBOX("CB"),

    /**
     * Values describes in NUM_CH_PRM. value is a Long (CH_PRM_ID), with
     * description in (CH_PRM_DES)
     */
    COMPLEX("CO"),

    DB,

    DATAFIELD("DF"),

    /**
     * Values describes in N_VALUES value is a String (VALUE) description
     * (VALUE_DES) Order in listbox (VALUE_SEQNO)
     */
    LISTBOX("LB"),

    SEARCHER("Searcher");

    private String value;

    private EnumParameterType() {
    }

    private EnumParameterType(String s) {
        value = s;
    }

    /**
     * <p>parseString.</p>
     *
     * @param v a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.contract.EnumParameterType} object.
     */
    public static EnumParameterType parseString(String v) {
        for (EnumParameterType e : EnumParameterType.values()) {
            if (e.toString().equals(v)) {
                return e;
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (null != value ? value : name());
    }
}
