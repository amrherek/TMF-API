package com.orange.bscs.model.usagedata;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <pre>{@code
 * USAGE_TYPE.READ {
 *   CONTRACT_TYPE_ID     =  : java.lang.Long
 *   USAGE_TYPE_ID        =  : java.lang.Long
 *   USAGE_TYPE_SHNAME    =  : java.lang.String
 * }
 * => {
 *   LIST_OF_USAGE_TYPES  = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
 *  -[0]DEFAULT_FOR_CREDIT_MEMO =  : java.lang.Boolean
 *  -[0]DEFAULT_FOR_INVOICE  =  : java.lang.Boolean
 *  -[0]DEFAULT_FOR_RECONCILIATION =  : java.lang.Boolean
 *  -[0]DEF_CASHFLOW_DIRECTION =  : java.lang.Character
 *  -[0]FALLBACK_USAGE_TYPE_ID =  : java.lang.Long
 *  -[0]IS_RATING_SKIPPED    =  : java.lang.Boolean
 *  -[0]PREPAID_USAGE_TYPE_ID =  : java.lang.Long
 *  -[0]USAGE_TYPE_DES       =  : java.lang.String
 *  -[0]USAGE_TYPE_ID        =  : java.lang.Long
 *  -[0]USAGE_TYPE_SHNAME    =  : java.lang.String
 * }
 * }</pre>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSUsageType extends BSCSModel {

    private static final String DEF_CASHFLOW_DIRECTION = "DEF_CASHFLOW_DIRECTION";

    /**
     * <p>getId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getId() {
        return getLongValue(Constants.P_USAGE_TYPE_ID);
    }

    /**
     * <p>setId.</p>
     *
     * @param id a {@link java.lang.Long} object.
     */
    public void setId(Long id) {
        setLongValue(Constants.P_USAGE_TYPE_ID, id);
    }

    /**
     * <p>getShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return getStringValue(Constants.P_USAGE_TYPE_SHNAME);
    }

    /**
     * <p>setShortName.</p>
     *
     * @param shname a {@link java.lang.String} object.
     */
    public void setShortName(String shname) {
        setStringValue(Constants.P_USAGE_TYPE_SHNAME, shname);
    }

    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue(Constants.P_USAGE_TYPE_DES);
    }

    /**
     * <p>setDescription.</p>
     *
     * @param des a {@link java.lang.String} object.
     */
    public void setDescription(String des) {
        setStringValue(Constants.P_USAGE_TYPE_DES, des);
    }

    /**
     * <p>getDefaultCashflowDirection.</p>
     *
     * @return a {@link com.orange.bscs.model.usagedata.EnumUsageTypeDirection} object.
     */
    public EnumUsageTypeDirection getDefaultCashflowDirection() {
        return EnumUsageTypeDirection.fromChar(getCharacterValue(DEF_CASHFLOW_DIRECTION));
    }

    /**
     * <p>setDefaultCashflowDirection.</p>
     *
     * @param dir a {@link com.orange.bscs.model.usagedata.EnumUsageTypeDirection} object.
     */
    public void setDefaultCashflowDirection(EnumUsageTypeDirection dir) {
        setCharacterValue(DEF_CASHFLOW_DIRECTION, EnumUsageTypeDirection.getChar(dir));
    }

}
