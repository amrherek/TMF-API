package com.orange.bscs.model.businesspartner;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <code>
 * ADDRESSES.READ {
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 * }
 * => {
 *   CS_ID                =  : java.lang.Long
 *   CS_ID_PUB            =  : java.lang.String
 *   LIST_OF_ALL_ADDRESSES = sub element : com.lhs.ccb.common.soiimpl.NamedValueCon
 * tainerList
 *   BSCSAddress
 * }
 * </code>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSAddresses extends BSCSModel {

    /** Constant <code>key="LIST_OF_ALL_ADDRESSES"</code> */
    private static final String KEY = "LIST_OF_ALL_ADDRESSES";

    /**
     * <p>getCustomerId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCustomerId() {
        return getLongValue(Constants.P_CS_ID);
    }

    public void setCustomerId(Long csid){
        setLongValue(Constants.P_CS_ID, csid);
    }
    
    /**
     * <p>getCustomerIdPub.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCustomerIdPub() {
        return getStringValue(Constants.P_CS_ID_PUB);
    }
    public void setCustomerIdPub(String partyIdPub) {
        setStringValue(Constants.P_CS_ID_PUB,partyIdPub);
    }
    

    /**
     * <p>getAddresses.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<BSCSAddress> getAddresses() {
        List<BSCSAddress> res = this.getListOfBSCSModelValue(KEY, BSCSAddress.class);
        if (null != res) {
            for (BSCSAddress ad : res) {
                ad.setCustomerId(getCustomerId());
                ad.setCustomerIdPub(getCustomerIdPub());
            }
        }
        return res;
    }

    /**
     * <p>setAddresses.</p>
     *
     * @param addresses a {@link java.util.List} object.
     */
    public void setAddresses(List<BSCSAddress> addresses) {
        this.setListOfBSCSModelValue(KEY, addresses);
    }


}
