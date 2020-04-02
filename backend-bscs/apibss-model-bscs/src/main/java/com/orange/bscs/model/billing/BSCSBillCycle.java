package com.orange.bscs.model.billing;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <code>
 * BILL_CYCLES.READ {
 *   PARTY_TYPE           =  : java.lang.String
 * }
 * => {
 *   bill cycles          = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
 * ainerList
 * *-[0]BILLCYCLE            =  : java.lang.String
 * *-[0]BILL_INFO_AVAILABLE  =  : java.lang.Boolean
 * *-[0]DESCRIPTION          =  : java.lang.String
 * *-[0]DUMMY_BILLCYCLE      =  : java.lang.Boolean
 * *-[0]INTERVAL_TYPE        =  : java.lang.Character
 * *-[0]LENGTH               =  : java.lang.Integer
 * }
 * </code>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSBillCycle extends BSCSModel {

    private static final String P_BILL_INFO_AVAILABLE = "BILL_INFO_AVAILABLE";
    private static final String P_DUMMY_BILLCYCLE = "DUMMY_BILLCYCLE";

    /**
     * Indicates if billing information is available or not. 'true' if
     * BILL_INFO_NUM>0, else 'false'
     *
     * @return a boolean.
     */
    public boolean isBillInfoAvaible() {
        return getBooleanValue(P_BILL_INFO_AVAILABLE);
    }

    /**
     * Number of each billing cycle (2 car)
     *
     * @return a {@link java.lang.String} object.
     */
    public String getBillCycle() {
        return getStringValue(Constants.P_BILLCYCLE);
    }

    /* Unique textual description of the billing cycle (30 car.) */
    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_DESCRIPTION);
    }

    /**
     * Indicates if the billcycle is a dummy bill cycle. 'true' if dummy bill
     * cycle>0, else 'false'
     *
     * @return a boolean.
     */
    public boolean isDummyCycle() {
        return getBooleanValue(P_DUMMY_BILLCYCLE);
    }

    /**
     * Type of the default billing interval D=days, M=months
     *
     * @return a {@link com.orange.bscs.model.billing.BSCSBillCycle.EnumBillCycleIntervalType} object.
     */
    public EnumBillCycleIntervalType getIntervalType() {
        return EnumBillCycleIntervalType.fromCharacter(getCharacterValue(Constants.P_INTERVAL_TYPE));
    }

    /**
     * Length of the default billing interval (in days or months)
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getLength() {
        return getIntegerValue(Constants.P_LENGTH);
    }

    public enum EnumBillCycleIntervalType {
        DAYS('D'), MONTHS('M');

        private char interval;

        private EnumBillCycleIntervalType(char c) {
            interval = c;
        }

        public char getCaracter() {
            return interval;
        }

        public static Character getCharacter(EnumBillCycleIntervalType en) {
            return (null == en ? null : en.getCaracter());
        }

        public static EnumBillCycleIntervalType fromCharacter(Character intervalType) {
            if (null == intervalType) {
                return null;
            }
            EnumBillCycleIntervalType result = null;
            for (EnumBillCycleIntervalType en : values()) {
                if (en.interval == intervalType) {
                    result = en;
                    break;
                }
            }
            return result;
        }
    }
}
