package com.orange.bscs.cms.servicelayeradapter;

import org.springframework.stereotype.Repository;

import com.orange.bscs.commands.BaseDAO;

/**
 * <pre>
 * {@code
 * PAYMENT_ARRANGEMENT.AUTHORIZE
 * THRESHOLDS.READ
 * CUSTOMER.CREDITSCORE
 * BLACKLIST_ENTRIES.SEARCH
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
@Repository
public class RiskManagementServiceAdapter extends BaseDAO implements RiskManagementServiceAdapterI {

}
