package com.orange.mea.apisi.billingaccount.obw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.billingaccount.model.BillingAccount;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.mea.apisi.billingaccount.service.BillingAccountService;

@Service
@Primary
public class BillingAccountServiceOBW extends BillingAccountService {

    @Autowired
    private InternationalMsisdnTool internationalMsisdnTool;

    @Override
    public List<BillingAccount> findBillingAccountsByMsisdn(final String msisdn) throws ApiException {
        // add international prefix
        String internationalMsisdn = internationalMsisdnTool.addInternationalPrefix(msisdn);
        return super.findBillingAccountsByMsisdn(internationalMsisdn);
    }


}
