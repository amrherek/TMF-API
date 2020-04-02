package com.orange.mea.apisi.bucketbalance.backend.bscs.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.Bucket;
import com.orange.apibss.bucketBalance.model.BucketBalance;
import com.orange.apibss.bucketBalance.model.TimePeriod;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.mea.apisi.bucketbalance.transformer.UsageReportTransformer;

@Component
public class UsageReportTransformerBscs {

    @Autowired
    UsageReportTransformer usageReportTransformer;

    public List<UsageReport> transform(List<BscsContractFreeUnitAccount> freeUnits, List<BscsBalance> balances,
            String msisdn, List<String> units) {

        UsageReport usageReport = usageReportTransformer.buildUsageReport(msisdn);

        List<Bucket> bucketsFromFreeUnits = transformFreeUnits(freeUnits, usageReport.getDate().toDate(), units);
        List<Bucket> bucketsFromBalances = transformBalances(balances, usageReport.getDate().toDate(), units);

        // build usage report
        List<UsageReport> res = new ArrayList<>();
        usageReport.setBuckets(bucketsFromFreeUnits);
        usageReport.getBuckets().addAll(bucketsFromBalances);
        res.add(usageReport);
        return res;
    }

    public List<Bucket> transformFreeUnits(List<BscsContractFreeUnitAccount> freeUnits, Date filterDate,
            List<String> units) {
        List<Bucket> res = new ArrayList<>();
        for (BscsContractFreeUnitAccount freeUnit : freeUnits) {
            // keep only current free units
            Date periodStartDate = freeUnit.getPeriodFrom();
            Date periodEndDate = freeUnit.getPeriodTo();
            if ((periodStartDate != null && periodStartDate.compareTo(filterDate) <= 0)
                    && (periodEndDate != null && periodEndDate.compareTo(filterDate) >= 0)) {
                transformCurrentFreeUnit(freeUnit, units, res);
            }
        }
        return res;
    }

    protected void transformCurrentFreeUnit(BscsContractFreeUnitAccount freeUnit, List<String> units,
            List<Bucket> result) {
        // filter on units
        String unit = freeUnit.getUnitMeasurement();
        // specific treatment for monetary units
        if ("M".equals(unit)) {
            // replace unit by the currency
            unit = freeUnit.getCurrency();
        }
        if (units == null || units.contains(unit)) {
            Bucket bucket = new Bucket();

            TimePeriod validFor = new TimePeriod();
            if (freeUnit.getPeriodFrom() != null) {
                validFor.setStartDateTime(new DateTime(freeUnit.getPeriodFrom()));
            }
            if (freeUnit.getPeriodTo() != null) {
                validFor.setEndDateTime(new DateTime(freeUnit.getPeriodTo()));
            }
            bucket.setValidFor(validFor);

            Double initialAmount = freeUnit.getGrantedUnits();
            Double usedAmount = freeUnit.getAppliedUnits();

            BucketBalance bucketBalance = new BucketBalance();
            if (initialAmount != null) {
                bucketBalance.setMaxValue(initialAmount.floatValue());
            }
            if (usedAmount != null) {
                bucketBalance.setUsedValue(usedAmount.floatValue());
            }
            if (initialAmount != null && usedAmount != null) {
                // BigDecimal is used to avoid floating point arithmetic
                // issue
                BigDecimal currentBalance = BigDecimal.valueOf(initialAmount).subtract(BigDecimal.valueOf(usedAmount));
                bucketBalance.setRemainingValue(currentBalance.floatValue());
            }
            bucketBalance.setUnit(unit);
            if (unit == freeUnit.getCurrency()) {
                bucket.setUsageType("money");
            }
            bucket.addBucketBalancesItem(bucketBalance);
            result.add(bucket);
        }
    }

    public List<Bucket> transformBalances(List<BscsBalance> balances, Date filterDate, List<String> units) {
        List<Bucket> res = new ArrayList<>();
        for (BscsBalance balance : balances) {
            // keep only current balances
            Date periodEndDate = balance.getNextFreeResetDate();
            if ((periodEndDate != null && periodEndDate.compareTo(filterDate) >= 0)) {
                transformCurrentBalance(balance, units, res);
            }
        }
        return res;
    }

    protected void transformCurrentBalance(BscsBalance balance, List<String> units, List<Bucket> result) {
        String unit = balance.getUnitMeasurement();
        // specific treatment for monetary units
        if ("M".equals(unit)) {
            // replace unit by the currency
            unit = balance.getCurrencyId();
        }
        // filter on units
        if (units == null || units.contains(unit)) {
            Bucket bucket = new Bucket();

            TimePeriod validFor = new TimePeriod();
            if (balance.getNextFreeResetDate() != null) {
                validFor.setEndDateTime(new DateTime(balance.getNextFreeResetDate()));
            }
            bucket.setValidFor(validFor);

            Double initialAmount = balance.getCreditValue();
            Double usedAmount = balance.getActualValue();

            BucketBalance bucketBalance = new BucketBalance();
            if (initialAmount != null) {
                bucketBalance.setMaxValue(initialAmount.floatValue());
            }
            if (usedAmount != null) {
                bucketBalance.setUsedValue(usedAmount.floatValue());
            }
            if (initialAmount != null && usedAmount != null) {
                // BigDecimal is used to avoid floating point arithmetic
                // issue
                BigDecimal currentBalance = BigDecimal.valueOf(initialAmount).subtract(BigDecimal.valueOf(usedAmount));
                bucketBalance.setRemainingValue(currentBalance.floatValue());
            }
            bucketBalance.setUnit(unit);
            if (unit == balance.getCurrencyId()) {
                bucket.setUsageType("money");
            }
            bucket.addBucketBalancesItem(bucketBalance);
            result.add(bucket);
        }
    }

}
