package com.orange.bscs.model.businesspartner;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.product.BSCSRatePlan;

/**
 * <p>BSCSCustomerPriceGroup class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSCustomerPriceGroup extends BSCSModel {


    /**
     * <p>getCode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCode() {
        return getStringValue("PRG_CODE");
    }

    /**
     * <p>setCode.</p>
     *
     * @param code a {@link java.lang.String} object.
     */
    public void setCode(String code) {
        setStringValue("PRG_CODE", code);
    }

    /**
     * <p>getDescription.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDescription() {
        return getStringValue("PRG_DES");
    }

    /**
     * <p>setDescription.</p>
     *
     * @param des a {@link java.lang.String} object.
     */
    public void setDescription(String des) {
        setStringValue("PRG_DES", des);
    }

    /**
     * <p>getReleasedRatePlans.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSRatePlan> getReleasedRatePlans() {
        return getListOfBSCSModelValue("LIST_OF_RELEASED_RATEPLANS", BSCSRatePlan.class);
    }

}
