package com.orange.mea.apisi.bucketbalance.obw.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.apibss.bucketBalance.model.CreditBucketBalanceTransaction;
import com.orange.apibss.common.model.PartialResult;
import com.orange.mea.apisi.obw.webservice.TCDRDto;
import com.orange.mea.apisi.obw.webservice.TQueryOCSCDRResponse;

@Component
public class CreditBucketBalanceTransactionTransformerOBW {

    @Value("${currencyCode}")
    private String currencyCode;

    public PartialResult<CreditBucketBalanceTransaction> transform(TQueryOCSCDRResponse topupHistory, String msisdn,
            int limit) {
        if (topupHistory.getCDRDtoList() == null || topupHistory.getCDRDtoList().getCDRDto() == null) {
            return new PartialResult<>();
        }
        List<CreditBucketBalanceTransaction> res = new ArrayList<>();
        for (TCDRDto cdr : topupHistory.getCDRDtoList().getCDRDto()) {
            CreditBucketBalanceTransaction transaction = new CreditBucketBalanceTransaction();
            // convert, reverse sign and divide by 1000
            BigDecimal amount = new BigDecimal(cdr.getCharge1());
            transaction.setValue(amount.negate().divide(BigDecimal.valueOf(1000)).floatValue());
            transaction.setDate(cdr.getStartTime());
            transaction.setUnit(currencyCode);
            // TODO: set type?
            res.add(transaction);
            if (res.size() == limit) {
                break;
            }
        }
        return new PartialResult<>(res, topupHistory.getCDRDtoList().getCDRDto().size());
    }

}
