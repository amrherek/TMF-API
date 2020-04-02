package com.orange.mea.apisi.productcatalog.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;

public interface FindConfigurationBackend {

    /**
     * UC1: get configurations for a msisdn and a specific categoy
     * 
     * @param offerProductOfferingId
     * @param productSpecId
     * @return
     * @throws ApiException
     */
    List<ProductOffering> findConfiguration(String offerProductOfferingId, String productSpecId) throws ApiException;

}
