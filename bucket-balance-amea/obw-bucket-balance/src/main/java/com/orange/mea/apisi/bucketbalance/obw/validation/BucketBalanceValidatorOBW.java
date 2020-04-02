package com.orange.mea.apisi.bucketbalance.obw.validation;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.mea.apisi.bucketbalance.service.validation.BucketBalanceValidator;

@Service
@Primary
public class BucketBalanceValidatorOBW extends BucketBalanceValidator {

    @Value("${currencyCode}")
    private String currencyCode;

    @Override
    protected void validateIdForCreditBucketBalanceByValue(String id) throws MissingParameterException {
        if (StringUtils.isBlank(id)) {
            throw new MissingParameterException("id");
        }
    }

    @Override
    protected void validateUnit(String unit) throws BadParameterValueException {
        if (unit != null && !unit.equalsIgnoreCase(currencyCode)) {
            throw new BadParameterValueException("unit", unit, currencyCode);
        }
    }

}
