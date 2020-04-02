package com.orange.mea.apisi.productinventory.obw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.State;
import com.orange.mea.apisi.productinventory.service.ProductService;

@Service
@Primary
public class ProductServiceOBW extends ProductService {

    @Autowired
    private InternationalMsisdnTool internationalMsisdnTool;

    @Override
    public List<Product> getProductsByMsisdn(final String msisdn, final String category, String specId,
            final State productStatus, final String productOfferingId, final boolean withParameters)
            throws ApiException {
        // add international prefix
        String internationalMsisdn = internationalMsisdnTool.addInternationalPrefix(msisdn);
        return super.getProductsByMsisdn(internationalMsisdn, category, specId, productStatus, productOfferingId,
                withParameters);
    }

}
