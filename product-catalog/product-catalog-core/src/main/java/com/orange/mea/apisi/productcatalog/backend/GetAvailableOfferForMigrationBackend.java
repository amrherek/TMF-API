package com.orange.mea.apisi.productcatalog.backend;

import java.util.List;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;

public interface GetAvailableOfferForMigrationBackend {

    /**
     * UC2: get offers for migration
     * 
     * @param offerProductOfferingId
     * @return
     * @throws ApiException
     */
    List<ProductOffering> getAvailableOfferForMigration(String offerProductOfferingId) throws ApiException;

}
