package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecordRead;
import com.orange.bscs.model.usagedata.BSCSUsageType;

/**
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
@Repository
public class UsageDataServiceAdapter extends BaseDAO implements UsageDataServiceAdapterI {
    
    
    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_USAGE_TYPE_READ + QUOTE)
    @Override
    public List<BSCSUsageType> usageTypeRead() {
        BSCSModel output = execute(Constants.CMD_USAGE_TYPE_READ);
        return output.getListOfBSCSModelValue(Constants.P_LIST_OF_USAGE_TYPES, BSCSUsageType.class);
    }

    /** {@inheritDoc} */
    @Override
    public List<BSCSUsageDataRecord> usageDataRecordsRead(BSCSUsageDataRecordRead criterias) {
        BSCSModel out = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_USAGE_DATA_RECORDS_READ, criterias);
        if (null != out) {
            return out.getListOfBSCSModelValue(Constants.P_OUTPUT, BSCSUsageDataRecord.class);
        }
        return null;
    }

}
