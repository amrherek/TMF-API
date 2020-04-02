package com.orange.mea.apisi.bucketbalance.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByRetailerTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByTransfer;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByValue;
import com.orange.apibss.bucketBalance.model.CreditBucketBalanceByVoucher;
import com.orange.apibss.bucketBalance.model.DebitRetailerBucketBalanceByValue;

@Component
public class BucketBalanceTransformer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * UM22: credit prepaid account by voucher
     * 
     * @param creditBucketBalanceByVoucher
     * @return
     */
    public CreditBucketBalanceByVoucher buildCreditBucketBalanceByVoucherResponse(
			CreditBucketBalanceByVoucher creditBucketBalanceByVoucher) {
		// no modification
		return creditBucketBalanceByVoucher;
	}

    /**
     * UM45: credit prepaid account using credit card
     * 
     * @param creditBucketBalanceByValue
     * @return
     */
    public CreditBucketBalanceByValue buildCreditBucketBalanceByValueResponse(
			CreditBucketBalanceByValue creditBucketBalanceByValue) {
		// no modification
		return creditBucketBalanceByValue;
	}

    public CreditBucketBalanceByTransfer buildCreditBucketBalanceByTranferResponse(
            CreditBucketBalanceByTransfer creditBucketBalanceByTransfer) {
        // no modification
        return creditBucketBalanceByTransfer;
    }

    public CreditBucketBalanceByRetailerTransfer buildCreditBucketBalanceByRetailerTransfertResponse(
            CreditBucketBalanceByRetailerTransfer creditBucketBalanceByRetailerTransfer) {
        // no modification
        return creditBucketBalanceByRetailerTransfer;
    }

    public DebitRetailerBucketBalanceByValue buildDebitRetailerBucketBalanceByValueResponse(
            DebitRetailerBucketBalanceByValue debitRetailerBucketBalanceByValue) {
        // no modification
        return debitRetailerBucketBalanceByValue;
    }

}
