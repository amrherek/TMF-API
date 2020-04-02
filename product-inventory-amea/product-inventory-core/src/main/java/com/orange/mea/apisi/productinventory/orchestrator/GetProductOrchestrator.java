package com.orange.mea.apisi.productinventory.orchestrator;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;

public interface GetProductOrchestrator {

    /**
     * Get product by id
     * 
     * @param productId
     * @param withParameters
     * @return
     * @throws BadRequestException
     * @throws TechnicalException
     */
    Product getProduct(String productId, boolean withParameters) throws TechnicalException, BadRequestException;

}
