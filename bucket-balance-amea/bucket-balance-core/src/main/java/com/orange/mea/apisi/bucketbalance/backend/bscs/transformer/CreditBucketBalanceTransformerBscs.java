package com.orange.mea.apisi.bucketbalance.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.bscs.model.BscsUsageDataRecord;

@Component
public class CreditBucketBalanceTransformerBscs {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * UC92: get top-up history
     * 
     * @param usageDataRecords
     * @return
     */
    public List<CreditBucketBalanceTransaction> transform(List<BscsUsageDataRecord> usageDataRecords) {
		List<CreditBucketBalanceTransaction> res = new ArrayList<>();
        for (BscsUsageDataRecord usageDataRecord : usageDataRecords) {
			CreditBucketBalanceTransaction transaction = new CreditBucketBalanceTransaction();
            if (usageDataRecord.getStartDate() != null) {
                transaction.setDate(new DateTime(usageDataRecord.getStartDate()));
            }
            if (usageDataRecord.getRatedFlatAmount() != null) {
                transaction.setValue(usageDataRecord.getRatedFlatAmount().floatValue());
            }
            String unit = usageDataRecord.getUnitMeasurement();
            if ("M".equals(unit)) {
                // replace unit by the currency
                unit = usageDataRecord.getCurrencyId();
            }
            transaction.setUnit(unit);
            transaction.setType(transformVasCodeToType(usageDataRecord));
            res.add(transaction);
		}
		return res;
	}

    private Type transformVasCodeToType(BscsUsageDataRecord usageDataRecord) {
        String vasCode = usageDataRecord.getVasCode();
        if (vasCode == null) {
            return null;
        }
        if (vasCode.startsWith("CF")) {
            // Refill
            return Type.VALUE;
        }
        if (vasCode.startsWith("EC") || vasCode.startsWith("EV")) {
            // Evoucher
            return Type.VOUCHER;
        }
        if (vasCode.startsWith("S") || StringUtils.isNumeric(vasCode)) {
            // Scratch
            return Type.VOUCHER;
        }
        if (vasCode.startsWith("ET")) {
            // Express
            return Type.TRANSFER;
        }
        logger.debug("unknwon type for VAS: " + vasCode);
        return null;
    }

}
