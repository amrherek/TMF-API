package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;

/**
 *<pre>
 *{@code
 * CASH_COLLECTORS.READ
 * CONVERSION_RATE_TYPES.READ
 * COUNTRIES.READ
 * BALANCE_ADJUSTMENT_REASON.READ
 * CURRENCY_HISTORY.READ
 * CURRENCY_ROLES.READ
 * REASONS.READ
 * REQUEST_PRIORITIES.READ
 * USER_DEALERS.READ
 * }</pre> 
 * Others commands not declared in servicelayeradapter classes.
 * 
 * <pre>
 * {@code
 * AmountFunctionRead
 * BalanceAdjustmentReasonRead
 * BusinessUnitRead
 * CurrenciesRead
 * RateplanMassChange
 * SessionChange
 * SystemSettingRead
 * TimeZonesRead
 * UserContentProvidersRead
 * UserSettingRead
 * }
 * </pre>
  */
@Repository
public class CommonDomainServiceAdapter extends BaseDAO implements CommonDomainServiceAdapterI {

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_COUNTRIES_READ + QUOTE)
    @Override
    public List<BSCSModel> countriesRead() {
        return execute(Constants.CMD_COUNTRIES_READ).getListOfBSCSModelValue(Constants.P_COUNTRIES);
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_REASONS_READ + QUOTE)
    @Override
    public List<BSCSModel> reasonsRead() {
        return execute(Constants.CMD_REASONS_READ).getListOfBSCSModelValue(Constants.P_REASONS);
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_CURRENCIES_READ + QUOTE)
    @Override
    public List<BSCSModel> currenciesRead() {
        return execute(Constants.CMD_CURRENCIES_READ).getListOfBSCSModelValue(Constants.P_CURRENCIES);
    }

}
