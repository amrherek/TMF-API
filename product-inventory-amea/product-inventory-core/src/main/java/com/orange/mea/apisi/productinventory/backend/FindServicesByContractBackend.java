package com.orange.mea.apisi.productinventory.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.BscsContract;

public interface FindServicesByContractBackend {

    /**
     * Find all services and add them to products
     * 
     * @param products
     * @param specId
     * @param contractDetail
     * @param withParameters
     * @throws ApiException
     */
    void getServices(List<Product> products, final String specId, final BscsContract contractDetail,
            final boolean withParameters) throws ApiException;

}
