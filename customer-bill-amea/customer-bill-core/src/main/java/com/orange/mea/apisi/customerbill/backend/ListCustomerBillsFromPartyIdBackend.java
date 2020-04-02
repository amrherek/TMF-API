package com.orange.mea.apisi.customerbill.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;

public interface ListCustomerBillsFromPartyIdBackend {

    /**
     * List customer bills from partyId
     * 
     * @param partyId
     * @param withHierarchy
     * @param criteria
     * @return
     * @throws ApiException
     */
    PartialResult<CustomerBill> listCustomerBillsFromPartyId(String partyId, boolean withHierarchy,
            CustomerBillCriteria criteria) throws ApiException;


}
