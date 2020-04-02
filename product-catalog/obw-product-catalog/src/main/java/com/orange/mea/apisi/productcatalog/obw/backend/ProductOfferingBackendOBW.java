package com.orange.mea.apisi.productcatalog.obw.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.mea.apisi.productcatalog.backend.GetEligibleOptionsBackend;
import com.orange.mea.apisi.productcatalog.backend.bscs.ProductOfferingBscsService;

@Component
@Primary
public class ProductOfferingBackendOBW implements GetEligibleOptionsBackend {

    @Autowired
    private ProductOfferingBscsService bscsBackend;

    @Autowired
    private ProductOfferingZteInBackendOBW zteInBackend;

    @Override
    public List<ProductOffering> getEligibleOptions(String offerProductOfferingId, String productSpecId)
            throws ApiException {
        List<ProductOffering> eligibleOptions = bscsBackend.getEligibleOptions(offerProductOfferingId, productSpecId);
        if (productSpecId == null && eligibleOptions.isEmpty()) {
            // bad offerProductOfferingId => do not call IN
            return new ArrayList<>();
        }
        eligibleOptions.addAll(zteInBackend.getEligibleOptions(offerProductOfferingId, productSpecId));
        return eligibleOptions;
    }

}
