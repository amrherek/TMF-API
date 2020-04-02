package com.orange.mea.apisi.bucketbalance.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface CreditByVoucherBackend {

    /**
     * UM22: credit by voucher
     * 
     * @param id
     * @param msisdn
     * @param voucherNumber
     * @throws ApiException
     */
    void creditByVoucher(String id, String msisdn, String voucherNumber) throws ApiException;

}
