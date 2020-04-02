package com.orange.mea.apisi.productinventory.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.BscsContract;

public interface FindMainOfferByContractBackend {

    /**
     * Find main offer and add it to products if needed
     * 
     * @param products
     * @param specId
     * @param contractDetail
     * @return
     * @throws ApiException
     */
    Product getMainOffer(List<Product> products, String specId, BscsContract contractDetail) throws ApiException;

}
