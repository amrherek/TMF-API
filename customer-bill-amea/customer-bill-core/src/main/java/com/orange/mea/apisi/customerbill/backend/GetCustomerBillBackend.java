package com.orange.mea.apisi.customerbill.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.customerbill.model.CustomerBill;

public interface GetCustomerBillBackend {

    /**
     * Retrieve customer bill by id
     * 
     * @param billId
     * @return
     * @throws ApiException
     */
    CustomerBill getCustomerBill(Long billId) throws ApiException;

}
