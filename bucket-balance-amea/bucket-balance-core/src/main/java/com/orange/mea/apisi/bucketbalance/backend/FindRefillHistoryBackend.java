package com.orange.mea.apisi.bucketbalance.backend;

import org.joda.time.LocalDate;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.bucketBalance.model.RatingType;
import com.orange.apibss.bucketBalance.model.Type;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;

public interface FindRefillHistoryBackend {

    /**
     * UC92: find refill history
     * 
     * @param msisdn
     * @param ratingType
     * @param startDate
     * @param endDate
     * @param limit
     * @param type
     * @return
     * @throws ApiException
     */
    PartialResult<CreditBucketBalanceTransaction> findRefillHistory(String msisdn, RatingType ratingType,
            LocalDate startDate, LocalDate endDate, int limit, Type type) throws ApiException;

}
