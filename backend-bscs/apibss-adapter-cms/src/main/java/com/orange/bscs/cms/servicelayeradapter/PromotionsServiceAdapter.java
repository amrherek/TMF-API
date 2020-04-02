package com.orange.bscs.cms.servicelayeradapter;

import org.springframework.stereotype.Repository;

import com.orange.bscs.commands.BaseDAO;

/**
 * 
 * <pre>
 * {@code
 * PROMOTION_PACKAGES.READ
 * CONTRACT_PROMOTIONS.READ
 * CONTRACT_PROMOTIONS.WRITE
 * CUSTOMER_PROMOTIONS.READ
 * CUSTOMER_PROMOTIONS.WRITE
 * SUBSCRIPTIONFEE_REDUCTIONS.READ
 * PROMOTION_PACKAGE.DELETE
 * PROMOTION_STATE.DELETE
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
@Repository
public class PromotionsServiceAdapter extends BaseDAO implements PromotionsServiceAdapterI {

}
