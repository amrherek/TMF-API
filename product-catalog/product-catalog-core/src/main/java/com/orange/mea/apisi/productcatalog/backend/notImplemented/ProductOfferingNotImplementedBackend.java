package com.orange.mea.apisi.productcatalog.backend.notImplemented;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.mea.apisi.productcatalog.backend.FindConfigurationBackend;

@Service
public class ProductOfferingNotImplementedBackend implements FindConfigurationBackend {

    @Override
    public List<ProductOffering> findConfiguration(String offerProductOfferingId, String productSpecId)
            throws ApiException {
        throw new NotImplementedException("findConfiguration");
    }

}
