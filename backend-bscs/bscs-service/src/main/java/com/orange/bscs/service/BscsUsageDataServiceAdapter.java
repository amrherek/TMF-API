package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.cms.servicelayeradapter.UsageDataServiceAdapterI;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.model.usagedata.BSCSUsageDataRecord;

public class BscsUsageDataServiceAdapter {

    @Autowired
    private UsageDataServiceAdapterI usageDataServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * execute USAGE_DATA_RECORDS.READ
     * 
     * @param criteria
     * @return
     */
    public List<BscsUsageDataRecord> getUsageDataRecords(BscsUsageDataRecordSearchCriteria criteria) {
        List<BSCSUsageDataRecord> usageDataRecords = usageDataServiceAdapter
                .usageDataRecordsRead(criteria.getBscsModel());
        List<BscsUsageDataRecord> res = new ArrayList<>();
        for (BSCSUsageDataRecord record : usageDataRecords) {
            res.add(bscsObjectFactory.createBscsUsageDataRecord(record));
        }
        return res;
    }

}
