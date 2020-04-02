package com.orange.mea.apisi.customerbill.obw.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.mea.apisi.customerbill.service.CustomerBillService;

@Service
@Primary
public class CustomerBillServiceOBW extends CustomerBillService {

    @Autowired
    private InternationalMsisdnTool internationalMsisdnTool;

    @Override
    public PartialResult<CustomerBill> listCustomerBillsByMsisdn(String msisdn, Date startDate, Date endDate,
            Integer limit, String url) throws ApiException {
        // add international prefix
        String internationalMsisdn = internationalMsisdnTool.addInternationalPrefix(msisdn);
        return super.listCustomerBillsByMsisdn(internationalMsisdn, startDate, endDate, limit, url);
    }

}
