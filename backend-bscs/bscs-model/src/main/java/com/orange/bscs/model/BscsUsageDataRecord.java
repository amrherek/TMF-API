package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;

/**
 * Model used for result of USAGE_DATA_RECORDS.READ command
 * 
 * @author JWCK2987
 *
 */
public abstract class BscsUsageDataRecord {

    protected BSCSUsageDataRecord record;

    public BscsUsageDataRecord(BSCSUsageDataRecord record) {
        this.record = record;
    }

    public abstract Date getStartDate();

    public abstract Double getRatedFlatAmount();

    public abstract String getUnitMeasurement();

    public abstract String getVasCode();

    public abstract String getCurrencyId();

}
