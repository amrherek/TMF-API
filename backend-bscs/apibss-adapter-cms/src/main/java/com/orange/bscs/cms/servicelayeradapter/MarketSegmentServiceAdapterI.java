package com.orange.bscs.cms.servicelayeradapter;

import java.util.Map;

import com.orange.bscs.api.model.BSCSModel;

/**
 * <pre>
 * {@code
 * WELCOMEPROCS.READ
 * JOBTITLES.READ
 * NUMBER_OF_EMPLOYEES.READ
 * INFO_FIELDS_CONTR.READ
 * INFO_FIELDS.READ
 * INFO_COMBO_VALUES.READ
 * INFO_COMBO_VALUES.READ
 * INFO_CHANNELS.READ
 * PHONE_USAGE.READ
 * PHONE_TYPES.READ
 * CUSTOMER_INFO.READ
 * CUSTOMER_INFO.WRITE
 * MAIL_ITEM.READ
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
public interface MarketSegmentServiceAdapterI {

    BSCSModel customerInfoRead(Long csId, String csIdPub);

    /**
     * Create and modify additional non-contract related data about the
     * customer.
     *
     * <pre>{@code
     * CUSTOMER_INFO.WRITE {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   CHECK01              =  : java.lang.Boolean
     *   ...
     *   CHECK20              =  : java.lang.Boolean
     *   COMBO01              =  : java.lang.String
     *   ...
     *   COMBO20              =  : java.lang.String
     *   TEXT01               =  : java.lang.String
     *   ...
     *   TEXT30               =  : java.lang.String
     * }
     * => {
     * }
     * }</pre>
     *
     * @param input a {@link com.orange.bscs.api.model.BSCSModel} containing infofields to save.
     *  This BSCSModel must have a CS_ID or CS_ID_PUB field.
     */
    void customerInfoWrite(BSCSModel input);

    
    /**
     * List Phone usages
     * <ul>
     * <li>1 - Business
     * <li>2 - Personal
     * <li>3- Combination of the two
     * </ul>
     * 
     * <pre>{@code
     * execute PHONE_USAGE.READ, {
     *  => {
     * usage = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * -[0]PU_CODE = 1 : java.lang.Integer
     * -[0]PU_DES = Business : java.lang.String
     * }</pre>
     * 
     * @return a map of usages
     */
    Map<Integer,String> phoneUsageRead();
    
}
