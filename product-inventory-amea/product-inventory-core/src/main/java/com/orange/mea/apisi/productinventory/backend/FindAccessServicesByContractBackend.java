package com.orange.mea.apisi.productinventory.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.BscsContract;

public interface FindAccessServicesByContractBackend {

    /**
     * Find access services and add them to products
     * 
     * @param products
     * @param specId
     * @param contractDetail
     * @param msisdn
     * @throws ApiException
     */
    void findAccessServicesByContract(List<Product> products, String specId, BscsContract contractDetail,
            String msisdn) throws ApiException;

}
