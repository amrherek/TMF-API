package com.orange.mea.apisi.productcatalog.obw.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.orange.apibss.productCatalog.CategoryRef;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.apibss.productCatalog.ProductSpecification;
import com.orange.mea.apisi.obw.webservice.TPricePlanDto2;
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;
import com.orange.mea.apisi.productcatalog.constants.ProductOfferingConstants;

@Component
public class ProductOfferingZteInTransformerOBW {

    public List<ProductOffering> transform(TQueryAllPricePlanResponse response) {
        List<ProductOffering> result = new ArrayList<>();
        if (response != null && response.getPricePlanDtoList() != null
                && response.getPricePlanDtoList().getPricePlanDto() != null) {
            // TODO: filter results to only keep bundle of data/voice/SMS
            for (TPricePlanDto2 pricePlan : response.getPricePlanDtoList().getPricePlanDto()) {
                ProductOffering po = new ProductOffering();
                po.setId(pricePlan.getPricePlanIndex());
                po.setName(pricePlan.getPricePlanName());

                final CategoryRef category = new CategoryRef();
                category.setName(ProductOfferingConstants.OPTION);
                po.addCategoryItem(category);

                // TODO: fill productSpecId, depends on
                // pricePlan.getPricePlanType() ?
                ProductSpecification productSpecification = new ProductSpecification();
                productSpecification.setId(ProductOfferingConstants.DATA_BUNDLE);
                po.setProductSpecification(productSpecification);

                result.add(po);
            }
        }
        return result;
    }

}
