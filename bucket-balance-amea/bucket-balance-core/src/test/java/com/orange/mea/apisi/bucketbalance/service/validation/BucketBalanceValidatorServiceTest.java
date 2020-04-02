package com.orange.mea.apisi.bucketbalance.service.validation;

import java.text.ParseException;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;

@RunWith(JUnit4.class)
public class BucketBalanceValidatorServiceTest {

    // -------------------------
    // validate date and limit
    // -------------------------

    @Test
    public void validate_noLimit_noDate_ok() throws BadParameterValueException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(null, null, null);
    }

    @Test
    public void validate_startDate_endDate_ok() throws BadParameterValueException, ParseException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(new LocalDate("2016-01-01"), new LocalDate("2017-01-01"), null);
    }

    @Test(expected = BadParameterValueException.class)
    public void validate_startDate_after_endDate_error() throws BadParameterValueException, ParseException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(new LocalDate("2017-01-01"), new LocalDate("2016-01-01"), null);
    }

    @Test(expected = BadParameterValueException.class)
    public void validate_negativeLimit_error() throws BadParameterValueException, ParseException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(null, null, -10);
    }

    @Test
    public void validate_limit_ok() throws BadParameterValueException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(null, null, 10);
    }

    @Test
    public void validate_limit_noLimit_ok() throws BadParameterValueException {
        BucketBalanceValidator validator = new BucketBalanceValidator();
        validator.validate(null, null, -1);
    }

}
