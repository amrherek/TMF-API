package com.orange.mea.apisi.bucketbalance.obw.transformer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.Bucket;
import com.orange.apibss.bucketBalance.model.BucketBalance;
import com.orange.apibss.bucketBalance.model.TimePeriod;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.mea.apisi.bucketbalance.transformer.UsageReportTransformer;
import com.orange.mea.apisi.obw.webservice.TBalDto;
import com.orange.mea.apisi.obw.webservice.TQueryProfileAndBalForCRMResponse;

@Component
public class UsageReportTransformerOBW {

    @Value("${currencyCode}")
    private String currencyCode;

    @Autowired
    UsageReportTransformer usageReportTransformer;

    /**
     * UC19
     * 
     * @param response
     * @param msisdn
     * @param units
     * @return
     */
    public List<UsageReport> transform(TQueryProfileAndBalForCRMResponse response, String msisdn, List<String> units) {
        UsageReport usageReport = usageReportTransformer.buildUsageReport(msisdn);

        if (response != null && response.getBalDtoList() != null && response.getBalDtoList().getBalDto() != null) {
            for (TBalDto balDto : response.getBalDtoList().getBalDto()) {
                Bucket bucket = new Bucket();
                bucket.setId(balDto.getBalID());
                bucket.setName(balDto.getAcctResName());

                TimePeriod validFor = new TimePeriod();
                if (balDto.getEffDate() != null) {
                    validFor.setStartDateTime(new DateTime(balDto.getEffDate()));
                }
                if (balDto.getExpDate() != null) {
                    validFor.setEndDateTime(new DateTime(balDto.getExpDate()));
                }
                bucket.setValidFor(validFor);

                BucketBalance bucketBalance = new BucketBalance();
                if (balDto.getAcctResCode().equals("0")) {
                    // If the subscriber is prepaid/Hybrid, If AcctResCode=0, it
                    // means main balance. If subscriber is postpaid and
                    // AcctResCode=0, it mean remaining credit limit.
                    if (response.getPreFlag().equals("1") || response.getPreFlag().equals("3")) {
                        // prepaid or hybrid
                        bucketBalance.setUnit(currencyCode);
                        bucket.setUsageType("money");
                    } else if (response.getPreFlag().equals("2")) {
                        // postpaid
                        // TODO: which usageType should be used for "credit limit" ?
                        // bucket.setUsageType("data");
                    }
                }
                String amount = balDto.getBalance();
                try {
                    // convert and reverse sign
                    BigDecimal currentBalance = new BigDecimal(amount);
                    bucketBalance.setRemainingValue(currentBalance.negate().floatValue());
                } catch (NumberFormatException e) {
                    bucketBalance.setRemainingValueText(amount);
                }
                bucketBalance.setLastUpdateDate(balDto.getUpdateDate());

                bucket.addBucketBalancesItem(bucketBalance);

                if (units == null || units.contains(bucketBalance.getUnit())) {
                    usageReport.addBucketsItem(bucket);
                }

            }
        }
        return Arrays.asList(usageReport);
    }
	

}
