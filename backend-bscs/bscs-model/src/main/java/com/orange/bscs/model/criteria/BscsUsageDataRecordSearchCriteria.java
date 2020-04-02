package com.orange.bscs.model.criteria;

import java.util.Date;

import com.orange.bscs.model.usagedata.BSCSUsageDataRecordRead;

/**
 * Model used to set criteria for USAGE_DATA_RECORDS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsUsageDataRecordSearchCriteria {

    protected BSCSUsageDataRecordRead recordCriteria;

    public BscsUsageDataRecordSearchCriteria() {
        recordCriteria = new BSCSUsageDataRecordRead();
    }

    public BSCSUsageDataRecordRead getBscsModel() {
        return recordCriteria;
    }

    public abstract void setContractId(String contractId);

    public abstract void setCallType(Long callType);

    public abstract void setStartFromDate(Date startFromDate);

    public abstract void setSearchLimit(int searchLimit);

    public abstract void setStartToDate(Date startToDate);

}
