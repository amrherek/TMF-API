package com.orange.mea.apisi.bucketbalance.obw.backend;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.exceptions.technical.WebserviceTechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.mea.apisi.bucketbalance.backend.CreditByTransferBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByValueBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByVoucherBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindRefillHistoryBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPostpaidBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPrepaidHybridBackend;
import com.orange.mea.apisi.bucketbalance.backend.notimplemented.BucketBalanceNotImplementedBackend;
import com.orange.mea.apisi.bucketbalance.exception.BadMsisdnException;
import com.orange.mea.apisi.bucketbalance.obw.transformer.CreditBucketBalanceTransactionTransformerOBW;
import com.orange.mea.apisi.bucketbalance.obw.transformer.UsageReportTransformerOBW;
import com.orange.mea.apisi.obw.webservice.TQueryOCSCDRResponse;
import com.orange.mea.apisi.obw.webservice.TQueryProfileAndBalForCRMResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;

@Service
public class BucketBalanceZteInBackendOBW extends BucketBalanceNotImplementedBackend
        implements FindRefillHistoryBackend, FindUsageReportForPostpaidBackend,
        FindUsageReportForPrepaidHybridBackend, CreditByValueBackend, CreditByVoucherBackend, CreditByTransferBackend {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String INVALID_MSISDN = "invalid msisdn";

    @Value("${currencyCode}")
    private String currencyCode;

    @Autowired
    private ZteWebService webService;

    @Autowired
    private UsageReportTransformerOBW usageReportTransformerOBW;

    @Autowired
    private CreditBucketBalanceTransactionTransformerOBW creditBucketBalanceTransactionTransformerOBW;

    @Override
    public List<UsageReport> findUsageReportForPostpaid(String msisdn, List<String> units)
            throws TechnicalException, BadRequestException {
        return findUsageReport(msisdn, units);
    }

    @Override
    public List<UsageReport> findUsageReportForPrepaidHybrid(String msisdn, List<String> units)
            throws TechnicalException, BadRequestException {
        return findUsageReport(msisdn, units);
    }

    private List<UsageReport> findUsageReport(String msisdn, List<String> units) throws WebserviceTechnicalException {
        try {
            TQueryProfileAndBalForCRMResponse balances = webService.getBalances(msisdn);
            return usageReportTransformerOBW.transform(balances, msisdn, units);
        } catch (InvalidMsisdnException e) {
            logger.debug(INVALID_MSISDN, e);
            return new ArrayList<>();
        }
    }

    @Override
    public void creditByVoucher(String id, String msisdn, String voucherNumber)
            throws TechnicalException, BadRequestException {
        throw new NotImplementedException("creditByVoucher");
	}

    @Override
    public void creditByValue(String id, String msisdn, Float value, String unit, String comment,
            List<Characteristic> characteristics) throws TechnicalException, BadRequestException {
        // use BigDecimal to avoid floating point arithmetic issue
        BigDecimal amount = new BigDecimal(value.toString());
        if (unit != null && unit.equalsIgnoreCase(currencyCode)) {
            // client gave BWP, we must convert them to thousandth of BWP
            amount = amount.multiply(BigDecimal.valueOf(1000));
            logger.debug("BWP value converted to thousandths of BWP: " + amount.stripTrailingZeros());
        }
        try {
            webService.creditByValue(id, msisdn, amount.doubleValue());
        } catch (InvalidMsisdnException e) {
            logger.debug(INVALID_MSISDN, e);
            throw new BadMsisdnException(e.getMsisdn());
        }
	}

    @Override
    public PartialResult<CreditBucketBalanceTransaction> findRefillHistory(String msisdn, RatingType ratingType,
            LocalDate startDate, LocalDate endDate, int limit, Type type)
            throws TechnicalException, BadRequestException {
        // Note: type and ratingType are ignored
        try {
            // do not use limit to get the total number of items
            TQueryOCSCDRResponse topupHistory = webService.getTopupHistory(msisdn, startDate, endDate, null);
            return creditBucketBalanceTransactionTransformerOBW.transform(topupHistory, msisdn, limit);
        } catch (InvalidMsisdnException e) {
            logger.debug("no contract found with this msisdn", e);
            return new PartialResult<>();
        }
    }

    @Override
    public void creditByTransfer(String originMsisdn, String targetMsisdn, Float value, String unit, String comment,
            List<Characteristic> characteristic) throws TechnicalException, BadRequestException {
        throw new NotImplementedException("creditByTransfer");
    }

}
