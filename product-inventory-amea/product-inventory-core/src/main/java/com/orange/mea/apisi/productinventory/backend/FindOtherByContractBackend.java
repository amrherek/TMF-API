package com.orange.mea.apisi.productinventory.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;

public interface FindOtherByContractBackend {

    /**
     * Get products other than bscs products and add them to main offer
     * relationships
     * 
     * @param products
     * @param specId
     * @param msisdn
     * @param mainOffer
     * @throws TechnicalException
     */
    void findOtherProducts(List<Product> products, String specId, String msisdn, Product mainOffer)
            throws TechnicalException;

    /**
     * Get a list of products based on msisdn and add them to contract
     * relationship
     * 
     * @param msisdn
     * @param mainOffer
     * @return
     * @throws TechnicalException
     */
    List<Product> getProducts(String msisdn, Product mainOffer) throws TechnicalException;

}
