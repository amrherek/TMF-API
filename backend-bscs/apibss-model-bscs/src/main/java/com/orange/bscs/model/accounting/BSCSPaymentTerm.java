package com.orange.bscs.model.accounting;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;

/**
 * PAYMENT_TERMS.READ { } => { paymentterms = sub element :
 * com.lhs.ccb.common.soiimpl.NamedValueContainerList *-[0]TERM_CODE = :
 * java.lang.Long *-[0]TERM_DEFAULT = : java.lang.Boolean -[0]TERM_DISDAYS = :
 * java.lang.Integer -[0]TERM_DISRATE = : java.lang.Float *-[0]TERM_NAME = :
 * java.lang.String -[0]TERM_NET = : java.lang.Integer *-[0]TERM_TYPE = :
 * java.lang.String }
 *
 * @author IT&Labs
 *
 *         Warning, Change in BSCS R3 (no default, contains Term Item
 *         List)
 * @version $Id: $
 */
public class BSCSPaymentTerm extends BSCSModel {

    private static final String P_TERM_TYPE = "TERM_TYPE";
    private static final String P_TERM_DEFAULT = "TERM_DEFAULT";
    private static final String P_TERM_DISRATE = "TERM_DISRATE";

    /**
     * Term code
     *
     * @return a {@link java.lang.Long} object.
     */
    public Long getCode() {
        return getLongValue(Constants.P_TERM_CODE);
    }

    /**
     * Term code
     *
     * @param termCode a {@link java.lang.Long} object.
     */
    public void setCode(Long termCode) {
        setLongValue(Constants.P_TERM_CODE, termCode);
    }

    /**
     * Term Name
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName() {
        return getStringValue(Constants.P_TERM_NAME);
    }

    /**
     * Term type (char(1): 'N') - (TERM_TYPE)
     *
     * @return a {@link java.lang.String} object.
     */
    public String getType() {
        return getStringValue(P_TERM_TYPE);
    }

    /**
     * Term days until due (TERM_NET)
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getDaysUntilDue() {
        return getIntegerValue(Constants.P_TERM_NET);
    }

    /**
     * Doesn't exist in R3+
     *
     * @return a boolean.
     */
    @Deprecated
    public boolean isDefault() {
        return getBooleanValue(P_TERM_DEFAULT);
    }

    /**
     * Term discount rate Doesn't exist in R3+
     *
     * @return a {@link java.lang.Double} object.
     */
    @Deprecated
    public Double getDiscountRate() {
        return getDoubleValue(P_TERM_DISRATE);
    }

}
