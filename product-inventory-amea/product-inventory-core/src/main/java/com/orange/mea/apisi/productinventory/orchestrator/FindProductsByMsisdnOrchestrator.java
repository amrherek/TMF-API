package com.orange.mea.apisi.productinventory.orchestrator;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productInventory.model.Product;

public interface FindProductsByMsisdnOrchestrator {

    /**
     * Find products by msisdn
     * 
     * @param msisdn
     * @param specId
     * @param withParameters
     * @return
     * @throws ApiException
     */
    List<Product> findProductsByMsisdn(String msisdn, String specId, boolean withParameters) throws ApiException;

}
