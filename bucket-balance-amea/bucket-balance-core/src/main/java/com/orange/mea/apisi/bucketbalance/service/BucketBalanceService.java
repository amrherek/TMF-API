package com.orange.mea.apisi.bucketbalance.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByRetailerTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByVoucher;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.DebitRetailerBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.apibss.bucketBalance.model.UsageReport;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.mea.apisi.bucketbalance.backend.CreditByRetailerTransferBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByTransferBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByValueBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByVoucherBackend;
import com.orange.mea.apisi.bucketbalance.backend.DebitRetailerByValueBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindRefillHistoryBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPostpaidBackend;
import com.orange.mea.apisi.bucketbalance.backend.FindUsageReportForPrepaidHybridBackend;
import com.orange.mea.apisi.bucketbalance.service.validation.BucketBalanceValidator;
import com.orange.mea.apisi.bucketbalance.transformer.BucketBalanceTransformer;

/**
 * Service for all bucket balance use cases
 * 
 * @author JWCK2987
 *
 */
@Service
public class BucketBalanceService {

	@Autowired
    private CreditByValueBackend creditByValueBackend;

	@Autowired
    private CreditByVoucherBackend creditByVoucherBackend;

    @Autowired
    private CreditByTransferBackend creditByTransferBackend;

    @Autowired
    private FindRefillHistoryBackend findRefillHistoryBackend;

    @Autowired
    private FindUsageReportForPostpaidBackend findUsageReportForPostpaidBackend;

    @Autowired
    private FindUsageReportForPrepaidHybridBackend findUsageReportForPrepaidHybridBackend;

    @Autowired
    private CreditByRetailerTransferBackend creditByRetailerTransferBackend;

    @Autowired
    private DebitRetailerByValueBackend debitRetailerByValueBackend;

	@Autowired
	private BucketBalanceTransformer bucketBalanceTransformer;

    @Autowired
    protected BucketBalanceValidator bucketBalanceValidator;

    @Value("${creditBucketBalanceTransaction.searchLimit:50}")
    private Integer searchLimit;

    /**
     * UC19
     * 
     * @param msisdn
     * @param ratingType
     * @param units
     * @return
     * @throws ApiException
     */
    public List<UsageReport> findUsageReport(final String msisdn, final RatingType ratingType, List<String> units)
            throws ApiException {

		List<UsageReport> res = null;

        switch (ratingType) {
        case POSTPAID:
            res = findUsageReportForPostpaidBackend.findUsageReportForPostpaid(msisdn, units);
            break;
        case HYBRID:
        case PREPAID:
            res = findUsageReportForPrepaidHybridBackend.findUsageReportForPrepaidHybrid(msisdn, units);
            break;
        default:
            throw new BadParameterValueException("ratingType", ratingType.toString());
        }

		return res;
	}

    /**
     * UC92
     * 
     * @param msisdn
     * @param ratingType
     * @param startDate
     * @param endDate
     * @param limit
     * @param type
     * @param pinCode
     * @return
     * @throws ApiException
     */
    public PartialResult<CreditBucketBalanceTransaction> findRefillHistoric(final String msisdn,
            RatingType ratingType, final LocalDate startDate, final LocalDate endDate, Integer limit, Type type,
            String pinCode) throws ApiException {
        // validation
        bucketBalanceValidator.validate(startDate, endDate, limit);

        // transform limit
        limit = transformLimit(limit);

        return findRefillHistoryBackend.findRefillHistory(msisdn, ratingType, startDate, endDate, limit, type);
	}

    protected Integer transformLimit(Integer limit) {
        // limit from request
        Integer res = limit;
        if (limit == null) {
            // default limit
            res = searchLimit;
        } else if (limit == -1) {
            // no limit => max_int (limit is mandatory)
            res = Integer.MAX_VALUE;
        }
        return res;
    }

    /**
     * UM22
     * 
     * @param creditBucketBalanceByVoucher
     * @return
     * @throws ApiException
     */
    public CreditBucketBalanceByVoucher creditByVoucher(final CreditBucketBalanceByVoucher creditBucketBalanceByVoucher)
            throws ApiException {

        // validation
        bucketBalanceValidator.validateCreditBucketBalanceByVoucher(creditBucketBalanceByVoucher);

		// processing
        creditByVoucherBackend.creditByVoucher(creditBucketBalanceByVoucher.getId(),
                creditBucketBalanceByVoucher.getPublicKey().getId(),
				creditBucketBalanceByVoucher.getNumber());

		// build answer
		return bucketBalanceTransformer.buildCreditBucketBalanceByVoucherResponse(creditBucketBalanceByVoucher);
	}

    /**
     * UM45
     * 
     * @param creditBucketBalanceByValue
     * @return
     * @throws ApiException
     */
    public CreditBucketBalanceByValue creditByValue(final CreditBucketBalanceByValue creditBucketBalanceByValue)
            throws ApiException {

        // validation
        bucketBalanceValidator.validateCreditBucketBalanceByValue(creditBucketBalanceByValue);

		// processing
        creditByValueBackend.creditByValue(creditBucketBalanceByValue.getId(),
                creditBucketBalanceByValue.getPublicKey().getId(),
                creditBucketBalanceByValue.getValue(), creditBucketBalanceByValue.getUnit(),
                creditBucketBalanceByValue.getComment(), creditBucketBalanceByValue.getCharacteristic());

		// build answer
		return bucketBalanceTransformer.buildCreditBucketBalanceByValueResponse(creditBucketBalanceByValue);
	}

    /**
     * UM24
     * 
     * @param creditBucketBalanceByTransfer
     * @return
     * @throws ApiException
     */
    public CreditBucketBalanceByTransfer creditByTransfer(CreditBucketBalanceByTransfer creditBucketBalanceByTransfer)
            throws ApiException {
        // validation
        bucketBalanceValidator.validateCreditBucketBalanceByTransfer(creditBucketBalanceByTransfer);

        // processing
        creditByTransferBackend.creditByTransfer(creditBucketBalanceByTransfer.getOriginPublicKey().getId(),
                creditBucketBalanceByTransfer.getTargetPublicKey().getId(), creditBucketBalanceByTransfer.getValue(),
                creditBucketBalanceByTransfer.getUnit(), creditBucketBalanceByTransfer.getComment(),
                creditBucketBalanceByTransfer.getCharacteristic());

        // build answer
        return bucketBalanceTransformer.buildCreditBucketBalanceByTranferResponse(creditBucketBalanceByTransfer);
    }

    /**
     * 
     * @param creditBucketBalanceByRetailerTransfer
     * @return
     * @throws ApiException
     */
    public CreditBucketBalanceByRetailerTransfer creditBucketBalanceByRetailerTransfer(
            CreditBucketBalanceByRetailerTransfer creditBucketBalanceByRetailerTransfer) throws ApiException {
        // validation
        bucketBalanceValidator.validateCreditBucketBalanceByRetailerTransfer(creditBucketBalanceByRetailerTransfer);

        creditByRetailerTransferBackend.creditBucketBalanceByRetailerTransfert(
                creditBucketBalanceByRetailerTransfer.getId(),
                creditBucketBalanceByRetailerTransfer.getRetailerPublicKey().getId(),
                creditBucketBalanceByRetailerTransfer.getTargetPublicKey().getId(),
                creditBucketBalanceByRetailerTransfer.getValue(), creditBucketBalanceByRetailerTransfer.getUnit(),
                creditBucketBalanceByRetailerTransfer.getPin());

        // build answer
        return bucketBalanceTransformer
                .buildCreditBucketBalanceByRetailerTransfertResponse(creditBucketBalanceByRetailerTransfer);
    }

    public DebitRetailerBucketBalanceByValue debitRetailerBucketBalanceByValue(
            DebitRetailerBucketBalanceByValue debitRetailerBucketBalanceByValue) throws ApiException {
        // validation
        bucketBalanceValidator.validateDebitRetailerBucketBalanceByValue(debitRetailerBucketBalanceByValue);

        debitRetailerByValueBackend.debitRetailerByValue(debitRetailerBucketBalanceByValue.getId(),
                debitRetailerBucketBalanceByValue.getPublicKey().getId(), debitRetailerBucketBalanceByValue.getValue(),
                debitRetailerBucketBalanceByValue.getUnit(), debitRetailerBucketBalanceByValue.getPin());

        // build answer
        return bucketBalanceTransformer
                .buildDebitRetailerBucketBalanceByValueResponse(debitRetailerBucketBalanceByValue);
    }

}
