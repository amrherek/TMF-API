package com.orange.bscs.model;

import java.util.Date;

import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;

public class BscsUsageDataRecordV9 extends BscsUsageDataRecord {

    public BscsUsageDataRecordV9(BSCSUsageDataRecord record) {
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
        // UMCODE_PUB
        return record.getUomtPub();
    }

    @Override
    public String getVasCode() {
        // VAS_CODE
        return record.getVasCode();
    }

    @Override
    public String getCurrencyId() {
        // CURRENCY_ID_PUB
        String res = record.getCurrencyIdPub();
        if (res == null) {
            // RATED_FLAT_AMOUNT#currency
            res = record.getRatedFlatAmountCurrencyCode();
        }
        return res;
    }

}
