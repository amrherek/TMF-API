package com.orange.bscs.model.businesspartner;

import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>EnumContractStatus class.</p>
 *
 * @author ITlabs
 */
public enum EnumContractStatus {
    UNKNOWN(0), 
    ON_HOLD(1, Constants.CST_INITIALISED), 
    ACTIVATED(2, Constants.CST_IN_ORDER), 
    SUSPENDED(3, Constants.CST_SUSPENDED), 
    DEACTIVATED(4, Constants.CST_DEACTIVATED),

    // Don't exist in BSCS but in SOA
    REMOVED(-5, Constants.CST_REMOVED);

    private int value;
    private String label;

    private EnumContractStatus(int val) {
        value = val;
    }

    private EnumContractStatus(int val, String result) {
        value = val;
        label = result;
    }

    /**
     * <p>fromInt.</p>
     *
     * @return may return null if status is invalide.
     * @param status a int.
     */
    public static EnumContractStatus fromInt(Integer status) {
        if(null==status){
           return null;
        }
        for (EnumContractStatus s : EnumContractStatus.values()) {
            if (s.value == status) {
                return s;
            }
        }
        return null;
    }

    /**
     * <p>toInt.</p>
     *
     * @return a int.
     */
    public int toInt() {
        return value;
    }

    /**
     * Compare this status value with the parameter value.
     *
     * @param status
     *            value to compare (may be null)
     * @return true if parameter is not null and equal to this Status value.
     */
    public boolean equalsInt(Integer status) {
        return null != status && status.intValue() == value;
    }

    /**
     * {@inheritDoc}
     *
     * Return the label (SOA) and not the name of this Status
     */
    @Override
    public String toString() {
        return (null == label) ? name() : label;
    }

    /**
     * Parse SOA value wsdl v2: equals, wsdl v3 : codes in UPPERCASE without
     * space.
     *
     * @param status a {@link java.lang.String} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public static EnumContractStatus parseString(String status) {
        if (null == status){ return null; }
        
        for (EnumContractStatus s : EnumContractStatus.values()) {
            if (null != s.label && s.label.equals(status)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Convert Status char (from Contract_history.read) to Enum value.
     */
    /**
     * <p>fromCharacter.</p>
     *
     * @param characterValue a {@link java.lang.Character} object.
     * @return a {@link com.orange.bscs.model.businesspartner.EnumContractStatus} object.
     */
    public static EnumContractStatus fromCharacter(Character characterValue) {
        if (null == characterValue) {
            return null;
        }

        EnumContractStatus status;
        switch (characterValue) {
        case 'o':
            status = ON_HOLD;
            break;
        case 'a':
            status = ACTIVATED;
            break;
        case 's':
            status = SUSPENDED;
            break;
        case 'd':
            status = DEACTIVATED;
            break;
        default:
            status = null;
        }
        return status;
    }

    public static Integer toInt(EnumContractStatus status) {
        if(null==status){ return null;}
        
        return status.toInt();
    }

}
