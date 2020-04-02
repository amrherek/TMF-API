package com.orange.bscs.model.businesspartner;

/**
 * <p>EnumContactMethodRole class.</p>
 *
 * @author IT&Labs
 * @version $Id: $
 */
public enum EnumContactMethodRole {

    BILLDETAIL, 
    MAGAZINE, 
    SHIPMENT;

    /**
     * <p>parse.</p>
     *
     * @param labelIgnoreCase
     *            may be null, can be "BILLDETAIL" or "BillDetail"
     * @return null or found value
     */
    public static EnumContactMethodRole parse(String labelIgnoreCase) {
        if (null == labelIgnoreCase) {
            return null;
        }

        EnumContactMethodRole res = null;
        for (EnumContactMethodRole c : EnumContactMethodRole.values()) {
            if (c.name().equalsIgnoreCase(labelIgnoreCase)) {
                res = c;
                break;
            }
        }
        return res;
    }

}
