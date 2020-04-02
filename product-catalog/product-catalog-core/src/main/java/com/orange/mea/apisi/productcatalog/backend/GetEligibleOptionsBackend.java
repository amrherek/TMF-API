package com.orange.mea.apisi.productcatalog.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;

public interface GetEligibleOptionsBackend {

    /**
     * UC2: get eligible options
     * 
     * @param offerProductOfferingId
     * @param productSpecId
     * @return
     * @throws ApiException
     */
    List<ProductOffering> getEligibleOptions(String offerProductOfferingId, String productSpecId) throws ApiException;

}
