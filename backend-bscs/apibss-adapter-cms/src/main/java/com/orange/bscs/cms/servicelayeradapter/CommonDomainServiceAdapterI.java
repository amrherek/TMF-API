package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
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
 * 
 * @author ITLabs
  */
public interface CommonDomainServiceAdapterI {

    /**
     * Returns a list of countries.
     * <p>
     * <pre>{@code
     * COUNTRIES.READ {
     * }
     * => {
     *   COUNTRY_ID           =  : java.lang.Long
     *   COUNTRY_ID_PUB       =  : java.lang.String
     *   countries            = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]CC                   =  : java.lang.String
     * *-[0]CNTR_DES             =  : java.lang.String
     * *-[0]COUNTRY_CODE         =  : java.lang.String
     *  -[0]COUNTRY_ID           =  : java.lang.Long
     *  -[0]COUNTRY_ID_PUB       =  : java.lang.String
     * *-[0]DISPLAY_FORMAT       =  : java.lang.String
     * *-[0]ISO                  =  : java.lang.String
     * *-[0]ISO2_3166            =  : java.lang.String
     * *-[0]POSTAL_CODE_FORMAT   =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> countriesRead();

    /**
     * Returns a list of reasons.
     * <p>
     * <pre>{@code
     * REASONS.READ {
     * }
     * => {
     *   reasons              = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]RS_CODE              =  : java.lang.Long
     *  -[0]RS_DEFAULT           =  : java.lang.Boolean
     * *-[0]RS_DES               =  : java.lang.String
     * *-[0]RS_STATE             =  : java.lang.Character
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> reasonsRead();

    /**
     * Returns a list of all foreign currencies set up in BSCS.
     * <p>
     * <pre>{@code
     * CURRENCIES.READ {
     * }
     * => {
     *   currencies           = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]CURRENCY_ID          =  : java.lang.Long
     *  -[0]CURRENCY_ID_PUB      =  : java.lang.String
     * *-[0]FC_CODE              =  : java.lang.String
     *  -[0]FC_DESC              =  : java.lang.String
     *  -[0]INVALID_FROM         =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *  -[0]POST_DIGITS          =  : java.lang.Long
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> currenciesRead();

}
