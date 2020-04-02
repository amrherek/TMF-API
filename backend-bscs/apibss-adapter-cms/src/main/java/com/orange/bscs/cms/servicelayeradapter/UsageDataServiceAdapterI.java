package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecordRead;
import com.orange.bscs.model.usagedata.BSCSUsageType;

/**
 * <pre>{@code
 * UDR_DISP_ITEM.READ
 * UDR_DISP_ITEM_DETAILS.READ
 * UNIT_OF_MEASUREMENTS.READ
 * }</pre>
 * 
 * <pre>{@code
 * BusinessScenariosRead
 * CIBERValidationRulesRead
 * DataExchangeFormatsRead
 * TAPRecordTypesRead
 * TAPValidationRulesRead
 * UDCLogicalQuantityRead
 * UDSMembersRead
 * UsageDataRecordsRead
 * UsageTypeRead
 * }</pre>
 * 
 * @author IT&Labs
 *
 */
public interface UsageDataServiceAdapterI {

    /**
     * Retrieve a list of usage types or a single usage type by primary key.
     * <p>
     * <pre>{@code
     * USAGE_TYPE.READ {
     *   CONTRACT_TYPE_ID     =  : java.lang.Long
     *   USAGE_TYPE_ID        =  : java.lang.Long
     *   USAGE_TYPE_SHNAME    =  : java.lang.String
     * }
     * => {
     *   LIST_OF_USAGE_TYPES  = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]DEFAULT_FOR_CREDIT_MEMO =  : java.lang.Boolean
     *  -[0]DEFAULT_FOR_INVOICE  =  : java.lang.Boolean
     *  -[0]DEFAULT_FOR_RECONCILIATION =  : java.lang.Boolean
     *  -[0]DEF_CASHFLOW_DIRECTION =  : java.lang.Character
     *  -[0]FALLBACK_USAGE_TYPE_ID =  : java.lang.Long
     *  -[0]IS_RATING_SKIPPED    =  : java.lang.Boolean
     *  -[0]PREPAID_USAGE_TYPE_ID =  : java.lang.Long
     *  -[0]USAGE_TYPE_DES       =  : java.lang.String
     *  -[0]USAGE_TYPE_ID        =  : java.lang.Long
     *  -[0]USAGE_TYPE_SHNAME    =  : java.lang.String
     * }
     *
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSUsageType> usageTypeRead();

    /**
     * Returns basic Usage Data Information.
     * <p>
     * Gets a list of Charge Parts belonging to CDRs which fulfill the specified
     * criteria.
     * <p>
     * Input Arguments : UDR search Criteria.
     *
     * <code>
     * USAGE_DATA_RECORDS.READ {
     *   BILL_DATE            =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     *   BILL_IND             =  : java.lang.Integer
     *   BOP_ALTERNATIVE_IND  =  : java.lang.Boolean
     *   CALLEDPARTY          =  : java.lang.String
     *   CALLINGPARTY         =  : java.lang.String
     *   CALL_TYPE            =  : java.lang.Long
     *   CDR_ID               =  : java.lang.Long
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   DEALER_ID            =  : java.lang.Long
     *   DEALER_ID_PUB        =  : java.lang.String
     *   DEANONYM_ALLOWED     =  : java.lang.Boolean
     *   DESTINATION          =  : java.lang.String
     *   DISCOUNTED           =  : java.lang.Boolean
     *   ENTRY_FROM_DATE      =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     *   ENTRY_TO_DATE        =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     * * SEARCH_LIMIT         =  : java.lang.Integer
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   START_FROM_DATE      =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     *   START_TO_DATE        =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     * }
     * => {
     *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
     *   OUTPUT               = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *   -[0]BSCSUsageDataRecord
     * }
     * </code>
     *
     * @param criterias a {@link com.orange.bscs.model.usagedata.BSCSUsageDataRecordRead} object.
     * @return a {@link java.util.List} object.
     */
    List<BSCSUsageDataRecord> usageDataRecordsRead(BSCSUsageDataRecordRead criterias);

}
