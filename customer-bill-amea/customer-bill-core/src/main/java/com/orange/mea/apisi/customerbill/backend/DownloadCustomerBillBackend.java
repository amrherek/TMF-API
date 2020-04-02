package com.orange.mea.apisi.customerbill.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.mea.apisi.customerbill.rest.model.BillDocument;

public interface DownloadCustomerBillBackend {

    /**
     * Download a customer bill
     * 
     * @param billId
     * @return
     * @throws ApiException
     */
    BillDocument downloadCustomerBill(String billId) throws ApiException;

}
