package com.orange.mea.apisi.productcatalog.backend;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;

public interface GetConfigurationBackend {

    /**
     * UC1: get configuration for a specific product offering id
     * 
     * @param productOfferingId
     * @return
     * @throws ApiException
     */
    ProductOffering getConfiguration(String productOfferingId) throws ApiException;

}
