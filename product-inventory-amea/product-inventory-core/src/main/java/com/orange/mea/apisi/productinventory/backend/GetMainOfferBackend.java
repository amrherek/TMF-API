package com.orange.mea.apisi.productinventory.backend;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;

public interface GetMainOfferBackend {

    /**
     * Get a main offer by its id. Return null if no offer found with that id
     * 
     * @param productId
     * @return
     * @throws TechnicalException
     */
    Product getMainOffer(String productId) throws TechnicalException;

}
