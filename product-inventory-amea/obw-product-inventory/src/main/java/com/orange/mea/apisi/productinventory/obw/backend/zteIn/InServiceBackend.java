package com.orange.mea.apisi.productinventory.obw.backend.zteIn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.mea.apisi.obw.webservice.TQueryIndividualPricePlanResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;
import com.orange.mea.apisi.productinventory.backend.FindOtherByContractBackend;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.ProductTransformerUtil;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Component
public class InServiceBackend implements FindOtherByContractBackend {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ZteWebService webService;

    @Autowired
    private InServiceTransformer inServiceTransformer;

    @Autowired
    protected ProductTransformerUtil productTransformerUtil;

    @Override
    public void findOtherProducts(List<Product> products, String specId, String msisdn, Product mainOffer)
            throws TechnicalException {
        // bundle of data/voice/SMS
        if (specId == null || ProductInventoryConstants.DATA_BUNDLE.equalsIgnoreCase(specId)
                || ProductInventoryConstants.VOICE_BUNDLE.equalsIgnoreCase(specId)
                || ProductInventoryConstants.OFFER.equalsIgnoreCase(specId)) {
            List<Product> bundleProducts = getProducts(msisdn, mainOffer);
            if (!ProductInventoryConstants.OFFER.equalsIgnoreCase(specId)) {
                products.addAll(bundleProducts);
            }
        }
    }

    @Override
    public List<Product> getProducts(String msisdn, Product mainOffer)
            throws TechnicalException {
        try {
            TQueryIndividualPricePlanResponse pricePlans = webService.getPricePlans(msisdn);
            List<Product> res = inServiceTransformer.transform(pricePlans, mainOffer);
            // add contains relationships to main offer
            for (Product bundle : res) {
                mainOffer.addProductRelationshipItem(productTransformerUtil
                        .buildContainsRelationshipWithProductOffering(bundle.getProductOffering().getId()));
            }
            return res;
        } catch (InvalidMsisdnException e) {
            logger.error("Invalid msisdn [" + msisdn + "] for ZTE IN WS");
            throw new TechnicalException(e.getMessage(), e);
        }

    }

}
