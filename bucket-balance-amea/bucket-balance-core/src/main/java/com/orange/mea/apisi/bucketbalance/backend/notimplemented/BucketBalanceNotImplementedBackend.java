package com.orange.mea.apisi.bucketbalance.backend.notimplemented;

import java.util.List;

import com.orange.apibss.bucketBalance.model.Characteristic;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.mea.apisi.bucketbalance.backend.CreditByRetailerTransferBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByTransferBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByValueBackend;
import com.orange.mea.apisi.bucketbalance.backend.CreditByVoucherBackend;
import com.orange.mea.apisi.bucketbalance.backend.DebitRetailerByValueBackend;

public class BucketBalanceNotImplementedBackend
        implements CreditByValueBackend, CreditByVoucherBackend, CreditByTransferBackend,
        CreditByRetailerTransferBackend, DebitRetailerByValueBackend {

    @Override
    public void creditByVoucher(String id, String msisdn, String voucherNumber)
            throws TechnicalException, BadRequestException {
        throw new NotImplementedException("creditByVoucher");
    }

    @Override
    public void creditByValue(String id, String msisdn, Float value, String unit, String comment,
            List<Characteristic> characteristics) throws TechnicalException, BadRequestException {
        throw new NotImplementedException("creditByValue");
    }

    @Override
    public void creditByTransfer(String originMsisdn, String targetMsisdn, Float value, String unit, String comment,
            List<Characteristic> characteristic) throws TechnicalException, BadRequestException {
        throw new NotImplementedException("creditByTransfer");
    }

    @Override
    public void creditBucketBalanceByRetailerTransfert(String id, String retailerMsisdn, String targetMsisdn,
            Float amount, String unit, String pin) throws ApiException {
        throw new NotImplementedException("creditBucketBalanceByRetailerTransfert");
    }

    @Override
    public void debitRetailerByValue(String id, String msisdn, Float value, String unit, String pin)
            throws NotImplementedException {
        throw new NotImplementedException("debitRetailerByValue");
    }

}
