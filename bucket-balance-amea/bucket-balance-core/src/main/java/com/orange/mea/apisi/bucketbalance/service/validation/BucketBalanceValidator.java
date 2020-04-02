package com.orange.mea.apisi.bucketbalance.service.validation;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByRetailerTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByVoucher;
import com.orange.apibss.bucketBalance.model.DebitRetailerBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.PublicKey;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;

@Service
public class BucketBalanceValidator {

	public void validateCreditBucketBalanceByVoucher(CreditBucketBalanceByVoucher creditBucketBalanceByVoucher)
			throws BadRequestException {
		//validate msisdn
        validatePublicKey(creditBucketBalanceByVoucher.getPublicKey(), "publicKey");
		//validate voucher number
		if (StringUtils.isBlank(creditBucketBalanceByVoucher.getNumber())) {
            throw new MissingParameterException("number");
		}
        // validate id
        validateIdForCreditBucketBalanceByVoucher(creditBucketBalanceByVoucher.getId());
	}

    protected void validateIdForCreditBucketBalanceByVoucher(String id) throws MissingParameterException {
        validateId(id);
    }

    protected void validatePublicKey(PublicKey publicKey, String fieldName) throws BadRequestException {
		if (publicKey == null || StringUtils.isBlank(publicKey.getName())) {
            throw new MissingParameterException(fieldName + ".name");
		}
		if (StringUtils.isBlank(publicKey.getId())) {
            throw new MissingParameterException(fieldName + ".id");
		}
        if (!"msisdn".equalsIgnoreCase(publicKey.getName())) {
            throw new BadParameterValueException(fieldName + ".name", publicKey.getName(), "msisdn");
		}
	}

	public void validateCreditBucketBalanceByValue(CreditBucketBalanceByValue creditBucketBalanceByValue)
			throws BadRequestException {
		// validate msisdn
        validatePublicKey(creditBucketBalanceByValue.getPublicKey(), "publicKey");
		// validate value
        if (creditBucketBalanceByValue.getValue() == null) {
            throw new MissingParameterException("value");
		}
        // validate unit
        validateUnit(creditBucketBalanceByValue.getUnit());
        // validate characteristics
        validateCharacteristicsForCreditBucketBalanceByValue(creditBucketBalanceByValue.getCharacteristic());
        // validate id
        validateIdForCreditBucketBalanceByValue(creditBucketBalanceByValue.getId());
	}

    protected void validateUnit(String unit) throws BadRequestException {
        // nothing to validate
    }

    protected void validateCharacteristicsForCreditBucketBalanceByValue(List<Characteristic> characteristic)
            throws BadRequestException {
        // nothing to validate
    }

    protected void validateIdForCreditBucketBalanceByValue(String id) throws BadRequestException {
        validateId(id);
    }

    public void validateCreditBucketBalanceByTransfer(CreditBucketBalanceByTransfer creditBucketBalanceByTransfer)
            throws BadRequestException {
        // validate origin msisdn
        validatePublicKey(creditBucketBalanceByTransfer.getOriginPublicKey(), "originPublicKey");
        // validate target msisdn
        validatePublicKey(creditBucketBalanceByTransfer.getTargetPublicKey(), "targetPublicKey");
        // validate value
        if (creditBucketBalanceByTransfer.getValue() == null) {
            throw new MissingParameterException("value");
        }
        // validate unit
        validateUnit(creditBucketBalanceByTransfer.getUnit());
    }

    public void validateCreditBucketBalanceByRetailerTransfer(
            CreditBucketBalanceByRetailerTransfer creditBucketBalanceByRetailerTransfer) throws BadRequestException {
        // validate retailer and target msisdn
        validatePublicKey(creditBucketBalanceByRetailerTransfer.getRetailerPublicKey(), "retailerPublicKey");
        validatePublicKey(creditBucketBalanceByRetailerTransfer.getTargetPublicKey(), "targetPublicKey");
        // validate value
        if (creditBucketBalanceByRetailerTransfer.getValue() == null) {
            throw new MissingParameterException("value");
        }
        // validate unit
        validateUnit(creditBucketBalanceByRetailerTransfer.getUnit());
        // validate pin
        validatePin(creditBucketBalanceByRetailerTransfer.getPin());
        // validate id
        validateIdForCreditBucketBalanceByRetailerTransfer(creditBucketBalanceByRetailerTransfer.getId());
    }

    protected void validatePin(String pin) throws BadRequestException {
        // nothing to validate
    }

    protected void validateIdForCreditBucketBalanceByRetailerTransfer(String id) throws MissingParameterException {
        validateId(id);
    }

    public void validateDebitRetailerBucketBalanceByValue(
            DebitRetailerBucketBalanceByValue debitRetailerBucketBalanceByValue) throws BadRequestException {
        // validate msisdn
        validatePublicKey(debitRetailerBucketBalanceByValue.getPublicKey(), "publicKey");
        // validate value
        if (debitRetailerBucketBalanceByValue.getValue() == null) {
            throw new MissingParameterException("value");
        }
        // validate unit
        validateUnit(debitRetailerBucketBalanceByValue.getUnit());
        // validate pin
        validatePin(debitRetailerBucketBalanceByValue.getPin());
        // validate id
        validateIdForDebitRetailerBucketBalanceByValue(debitRetailerBucketBalanceByValue.getId());
    }

    protected void validateIdForDebitRetailerBucketBalanceByValue(String id) throws MissingParameterException {
        validateId(id);
    }

    protected void validateId(String id) throws MissingParameterException {
        // nothing to validate
    }

    public void validate(LocalDate startDate, LocalDate endDate, Integer limit) throws BadParameterValueException {
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new BadParameterValueException("date.lte", endDate.toString(), "after " + startDate);
        }
        if (limit != null && limit != -1 && limit < 0) {
            throw new BadParameterValueException("limit", limit.toString(), "a positive integer or -1");
        }
    }

}
