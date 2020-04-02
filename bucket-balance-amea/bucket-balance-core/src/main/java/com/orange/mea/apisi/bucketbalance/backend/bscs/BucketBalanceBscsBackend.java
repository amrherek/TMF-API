package com.orange.mea.apisi.bucketbalance.backend.bscs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsBalance;
import com.orange.bscs.model.BscsContractFreeUnitAccount;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.mea.apisi.bucketbalance.backend.FindRefillHistoryBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPostpaidBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPrepaidHybridBackend;
import com.orange.mea.apisi.bucketbalance.backend.bscs.transformer.CreditBucketBalanceTransformerBscs;
import com.orange.mea.apisi.bucketbalance.backend.bscs.transformer.UsageReportTransformerBscs;

public class BucketBalanceBscsBackend
        implements FindRefillHistoryBackend, FindUsageReportForPostpaidBackend, FindUsageReportForPrepaidHybridBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${usageDataRecords.read.callType:}")
    private Long callType;

    @Autowired
    protected BucketBalanceBscsService bscsService;

    @Autowired
    private CreditBucketBalanceTransformerBscs creditBucketBalanceTransformerBscs;

    @Autowired
    private UsageReportTransformerBscs usageReportTransformerBscs;

    @Override
    @TransactionalBscs
    public List<UsageReport> findUsageReportForPostpaid(String msisdn, List<String> units)
            throws TechnicalException, BadRequestException {
        return findUsageReport(msisdn, units);
    }

    @Override
    @TransactionalBscs
    public List<UsageReport> findUsageReportForPrepaidHybrid(String msisdn, List<String> units)
            throws TechnicalException, BadRequestException {
        return findUsageReport(msisdn, units);
    }

    private List<UsageReport> findUsageReport(String msisdn, List<String> units) {
        BscsContractSearch contract;
        try {
            contract = bscsService.findContractByMsisdn(msisdn);
        } catch (BscsInvalidFieldException e) {
            logger.debug("no contract found with this msisdn", e);
            return new ArrayList<>();
        }

        List<BscsContractFreeUnitAccount> freeUnits = bscsService.readFreeUnits(contract.getId());
        List<BscsBalance> balances = bscsService.readBalances(contract.getId());

        // build answer
        return usageReportTransformerBscs.transform(freeUnits, balances, msisdn, units);
    }

    @Override
    @TransactionalBscs
    public PartialResult<CreditBucketBalanceTransaction> findRefillHistory(String msisdn, RatingType ratingType,
            LocalDate startDate, LocalDate endDate, int limit, Type type)
            throws TechnicalException, BadRequestException {
        // Note: parameter ratingType is ignored

        // find contract
        BscsContractSearch contract;
        try {
            contract = bscsService.findContractByMsisdn(msisdn);
        } catch (BscsInvalidFieldException e) {
            logger.debug("no contract found with this msisdn", e);
            return new PartialResult<>();
        }

        // find usage data records
        PartialResult<BscsUsageDataRecord> usageDataRecords = findUsageDataRecords(contract.getId(), startDate, endDate,
                limit);

        // build answer
        List<CreditBucketBalanceTransaction> transactions = creditBucketBalanceTransformerBscs
                .transform(usageDataRecords.getPartialResultList());

        // apply filters
        if (type != null) {
            transactions = filterTransactionsWithType(transactions, type);
        }
        return new PartialResult<>(transactions, usageDataRecords.getTotalResultCount());
    }

    private List<CreditBucketBalanceTransaction> filterTransactionsWithType(
            List<CreditBucketBalanceTransaction> transactions, Type type) {
        List<CreditBucketBalanceTransaction> res = new ArrayList<>();
        for (CreditBucketBalanceTransaction transaction : transactions) {
            if (transaction.getType() == type) {
                res.add(transaction);
            }
        }
        return res;
    }

    private PartialResult<BscsUsageDataRecord> findUsageDataRecords(String contractId, LocalDate startDate,
            LocalDate endDate, int limit) {
        // calculate begin date
        Date beginDate = null;
        if (startDate != null) {
            // convert LocalDate to UTC midnight Date
            beginDate = startDate.toDateTimeAtStartOfDay(DateTimeZone.UTC).toDate();
        }
        Date finishDate = null;
        if (endDate != null) {
            // convert LocalDate to UTC 23:59:59 Date (add 1 day and remove 1 msec)
            finishDate = endDate.plusDays(1).toDateTimeAtStartOfDay(DateTimeZone.UTC).minusMillis(1).toDate();
        }

        // BSCS call: do not use limit to get the total number of items
        List<BscsUsageDataRecord> dataRecords = bscsService.findUsageDataRecords(contractId, beginDate, finishDate,
                callType, Integer.MAX_VALUE);

        int nbRes = dataRecords.size();
        if (nbRes > limit) {
            nbRes = limit;
        }
        return new PartialResult<>(dataRecords.subList(0, nbRes), dataRecords.size());
    }


}
