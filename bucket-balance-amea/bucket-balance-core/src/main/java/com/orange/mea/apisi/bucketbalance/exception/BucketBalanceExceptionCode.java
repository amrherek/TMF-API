package com.orange.mea.apisi.bucketbalance.exception;

public class BucketBalanceExceptionCode {

    // functionnal
    public static final Integer FC_CONTRACT_NOT_FOUND = 4100;
    public static final Integer FC_BAD_CREDIT_AMOUNT = 4101;

    private BucketBalanceExceptionCode() {
        throw new IllegalStateException("Utility class");
    }

}
