package com.orange.mea.apisi.productinventory.obw.backend.zteIn;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.ProductOffering;
import com.orange.apibss.productInventory.model.ProductSpecification;
import com.orange.apibss.productInventory.model.State;
import com.orange.mea.apisi.obw.webservice.TPricePlanDto;
import com.orange.mea.apisi.obw.webservice.TQueryIndividualPricePlanResponse;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.ProductTransformerUtil;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Component
public class InServiceTransformer {

    @Autowired
    private ProductTransformerUtil productTransformerUtil;

    /**
     * Transform bundle of data/voice/SMS to products
     * 
     * @param response
     * @param mainOffer
     * @return
     */
    public List<Product> transform(TQueryIndividualPricePlanResponse response, Product mainOffer) {
        List<Product> res = new ArrayList<>();
        if (response != null && response.getPricePlanDtoList() != null
                && response.getPricePlanDtoList().getPricePlanDto() != null) {
            for (TPricePlanDto pricePlan : response.getPricePlanDtoList().getPricePlanDto()) {
                // TODO: filter results to only keep bundle of data/voice/SMS
                Product product = new Product();

                product.setName(pricePlan.getPricePlanName());
                if (pricePlan.getCreatedDate() != null) {
                    product.setStartDate(pricePlan.getCreatedDate().toDateTimeAtStartOfDay(DateTimeZone.UTC));
                }
                product.setOrderDate(pricePlan.getEffDate());
                product.setTerminationDate(pricePlan.getExpDate());
                product.setStatus(State.ACTIVE);

                ProductOffering offering = new ProductOffering();
                offering.setId(pricePlan.getPricePlanCode());
                offering.setName(pricePlan.getPricePlanName());
                // TODO: only options are returned?
                offering.setCategory(ProductInventoryConstants.OPTION);
                product.setProductOffering(offering);

                ProductSpecification productSpecification = new ProductSpecification();
                // TODO: fill productSpecId
                productSpecification.setId(ProductInventoryConstants.DATA_BUNDLE);
                product.setProductSpecification(productSpecification);

                // add relationship with main offer
                product.addProductRelationshipItem(
                        productTransformerUtil.buildContainedInRelationship(mainOffer.getId()));

                res.add(product);
            }
        }
        return res;
    }

}
