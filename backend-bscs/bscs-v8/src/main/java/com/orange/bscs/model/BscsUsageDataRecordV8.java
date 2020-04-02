package com.orange.bscs.model;

import java.util.Date;
import java.util.Objects;

import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;

public class BscsUsageDataRecordV8 extends BscsUsageDataRecord {

    public BscsUsageDataRecordV8(BSCSUsageDataRecord record) {
        super(record);
    }

    @Override
    public Date getStartDate() {
        // ORIG_START
        return record.getStartDate();
    }

    @Override
    public Double getRatedFlatAmount() {
        // RATED_FLAT_AMOUNT
        return record.getRatedFlatAmount();
    }

    @Override
    public String getUnitMeasurement() {
        // UMCODE
        return Objects.toString(record.getLongValue("UMCODE"), null);
    }

    @Override
    public String getVasCode() {
        // VAS_CODE
        return record.getVasCode();
    }

    @Override
    public String getCurrencyId() {
        // RATED_FLAT_AMOUNT#currency
        return record.getRatedFlatAmountCurrencyCode();
    }

}
