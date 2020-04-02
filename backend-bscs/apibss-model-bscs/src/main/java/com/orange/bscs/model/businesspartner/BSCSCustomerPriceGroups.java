package com.orange.bscs.model.businesspartner;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;

/**
 * <p>BSCSCustomerPriceGroups class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSCustomerPriceGroups extends BSCSModel {

    /**
     * <p>getCustomerGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSCustomerPriceGroup> getCustomerGroups() {
        return getListOfBSCSModelValue("LIST_OF_CUSTOMER_GROUPS", BSCSCustomerPriceGroup.class);
    }
}
