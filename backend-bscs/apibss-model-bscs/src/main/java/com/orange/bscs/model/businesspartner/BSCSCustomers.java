package com.orange.bscs.model.businesspartner;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSCustomers class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSCustomers extends BSCSModel {

    /** Constant <code>key="SEARCH_RESULT"</code> */
    private static final String KEY = "SEARCH_RESULT";

    /**
     * <p>getSearchIsComplete.</p>
     *
     * @return a boolean.
     */
    public boolean getSearchIsComplete() {
        return getBooleanValue(Constants.P_SEARCH_IS_COMPLETE);
    }

    /**
     * <p>getCustomers.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSCustomer> getCustomers() {
        return this.getListOfBSCSModelValue(KEY, BSCSCustomer.class);
    }

    /**
     * <p>setCustomers.</p>
     *
     * @param customers a {@link java.util.List} object.
     */
    public void setCustomers(List<BSCSCustomer> customers) {
        this.setListOfBSCSModelValue(KEY, customers);
    }

}
