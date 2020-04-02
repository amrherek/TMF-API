package com.orange.mea.apisi.customerbill.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;

public interface ListCustomerBillsByMsisdnBackend {

    /**
     * List customer bills from msisdn
     * 
     * @param msisdn
     * @param criteria
     * @return
     * @throws ApiException
     */
    PartialResult<CustomerBill> listCustomerBillsByMsisdn(String msisdn, CustomerBillCriteria criteria)
            throws ApiException;

}
