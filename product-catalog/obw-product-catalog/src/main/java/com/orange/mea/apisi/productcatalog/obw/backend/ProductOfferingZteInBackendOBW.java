package com.orange.mea.apisi.productcatalog.obw.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.productcatalog.backend.GetEligibleOptionsBackend;
import com.orange.mea.apisi.productcatalog.constants.ProductOfferingConstants;

@Component
public class ProductOfferingZteInBackendOBW implements GetEligibleOptionsBackend {

    @Value("${queryAllPricePlan.productCode}")
    private String productCode;

    @Autowired
    private ZteWebService webService;

    @Autowired
    private ProductOfferingZteInTransformerOBW transformer;

    @Override
    public List<ProductOffering> getEligibleOptions(String offerProductOfferingId, String productSpecId)
            throws ApiException {
        // note: offerProductOfferingId is ignored
        if (productSpecId == null || ProductOfferingConstants.DATA_BUNDLE.equalsIgnoreCase(productSpecId)) {
            TQueryAllPricePlanResponse pricePlans = webService.queryAllPricePlan(productCode);
            return transformer.transform(pricePlans);
        }
        return new ArrayList<>();
    }

}
