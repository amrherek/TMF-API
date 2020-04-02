package com.orange.bscs.model.businesspartner;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * <p>BSCSPartyRoleAssignment class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public class BSCSPartyRoleAssignment extends BSCSModel {

    // PARTY_ROLE_ASSIGNMENTS.PARTY_ROLE_ID
    /**
     * <p>getPartyRoleId.</p>
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getPartyRoleId() {
        return getLongValue(Constants.P_PARTY_ROLE_ID);
    }

    /**
     * <p>setPartyRoleId.</p>
     *
     * @param value a {@link java.lang.Long} object.
     */
    public void setPartyRoleId(Long value) {
        setLongValue(Constants.P_PARTY_ROLE_ID, value);
    }

    // PARTY_ROLE_ASSIGNMENTS.PARTY_ROLE_SHNAME
    /**
     * <p>getPartyRoleShortName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPartyRoleShortName() {
        return getStringValue(Constants.P_PART_ROLE_SHNAME);
    }

    /**
     * <p>setPartyRoleShortName.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public void setPartyRoleShortName(String value) {
        setStringValue(Constants.P_PART_ROLE_SHNAME, value);
    }

}
