package com.orange.mea.apisi.productinventory.orchestrator;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productInventory.model.Product;

public interface FindProductsByIccIdOrchestrator {

    /**
     * Find products by iccId
     * 
     * @param iccId
     * @param specId
     * @param withParameters
     * @return
     * @throws ApiException
     */
    List<Product> findProductsByIccId(String iccId, String specId, boolean withParameters) throws ApiException;

}
