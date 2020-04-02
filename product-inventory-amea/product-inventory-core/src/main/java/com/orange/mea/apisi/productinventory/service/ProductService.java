package com.orange.mea.apisi.productinventory.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.State;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;
import com.orange.mea.apisi.productinventory.orchestrator.FindProductsByIccIdOrchestrator;
import com.orange.mea.apisi.productinventory.orchestrator.FindProductsByMsisdnOrchestrator;
import com.orange.mea.apisi.productinventory.orchestrator.GetProductOrchestrator;

/**
 * Service for products
 *
 * @author Thibault Duperron
 *
 */
@Service
public class ProductService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetProductOrchestrator getProductOrchestrator;
    
    @Autowired
    private FindProductsByIccIdOrchestrator findProductsByIccIdOrchestrator;

    @Autowired
    private FindProductsByMsisdnOrchestrator findProductsByMsisdnOrchestrator;

    /**
     * Search a product by Msisdn
     *
     * @param msisdn
     *            the msisdn
     * @param category
     *            category filter criteria
     * @param specId
     *            productSpecification.id
     * @param productStatus
     *            product status filter criteria
     * @param productOfferingId
     *            productOffering id filter criteria
     * @param withParameters
     * @return a list with the product and all subproducts
     * @throws ApiException
     */
    public List<Product> getProductsByMsisdn(final String msisdn, final String category,
            String specId, final State productStatus, final String productOfferingId, final boolean withParameters)
            throws ApiException {
        // for backward compatibility, transform "offer" category in "offer" specId
        if (ProductInventoryConstants.OFFER.equals(category) && specId == null) {
            specId = ProductInventoryConstants.OFFER;
        }
        List<Product> products = findProductsByMsisdnOrchestrator.findProductsByMsisdn(msisdn, specId, withParameters);
        if (StringUtils.isNotBlank(category)) {
            products = filterProductsWithCategory(products, category);
        }
        if (StringUtils.isNotBlank(productOfferingId)) {
            products = filterProductsWithProductOfferingId(products, productOfferingId);
        }
        if (productStatus != null) {
            products = filterProductsWithProductStatus(products, productStatus);
        }
        return products;
    }

    /**
     * Search a product by IccId
     *
     * @param iccId
     *            the iccid
     * @param category
     * @param specId
     *            productSpecification.id
     * @param productStatus
     *            product status filter criteria
     * @param productOfferingId
     *            productOffering id filter criteria
     * @param withParameters
     * @return a list with the product and all subproducts
     * @throws ApiException
     */
    public List<Product> getProductsByIccId(final String iccId, final String category,
            String specId, final State productStatus, final String productOfferingId, final boolean withParameters)
            throws ApiException {
        // for backward compatibility, transform "offer" category in "offer"
        // specId
        if (ProductInventoryConstants.OFFER.equals(category) && specId == null) {
            specId = ProductInventoryConstants.OFFER;
        }
        List<Product> products = findProductsByIccIdOrchestrator.findProductsByIccId(iccId, specId, withParameters);
        if (StringUtils.isNotBlank(category)) {
            products = filterProductsWithCategory(products, category);
        }
        if (StringUtils.isNotBlank(productOfferingId)) {
            products = filterProductsWithProductOfferingId(products, productOfferingId);
        }
        if (productStatus != null) {
            products = filterProductsWithProductStatus(products, productStatus);
        }
        return products;
    }

    /**
     * Get a Product based on its id
     * 
     * @param productId
     * @param withParameters
     * @return
     * @throws TechnicalException
     * @throws BadRequestException
     */
    public Product getProduct(String productId, boolean withParameters) throws TechnicalException, BadRequestException {
        return getProductOrchestrator.getProduct(productId, withParameters);
    }

    /**
     * Filter products based on the productOffering.id
     *
     * @param products
     * @param productOfferingId
     * @return
     * @throws TechnicalException
     * @throws BadRequestException
     */
    protected List<Product> filterProductsWithProductOfferingId(final List<Product> products, final String productOfferingId)
            throws TechnicalException, BadRequestException {
        final List<Product> res = new ArrayList<>();
        for (final Product product : products) {
            if (product.getProductOffering() != null
                    && productOfferingId.equals(product.getProductOffering().getId())) {
                res.add(product);
            }
        }
        return res;
    }

    /**
     * Filter product with status
     * 
     * @param products
     * @param productStatus
     * @return
     */
    protected List<Product> filterProductsWithProductStatus(List<Product> products, State productStatus) {
        final List<Product> res = new ArrayList<>();
        for (final Product product : products) {
            if (product.getStatus() != null && product.getStatus().equals(productStatus)) {
                res.add(product);
            }
        }
        return res;
    }

    /**
     * Filter products with category
     * 
     * @param products
     * @param category
     * @return
     */
    protected List<Product> filterProductsWithCategory(List<Product> products, String category) {
        final List<Product> res = new ArrayList<>();
        for (final Product product : products) {
            if (product.getProductOffering() != null && category.equals(product.getProductOffering().getCategory())) {
                res.add(product);
            }
        }
        return res;
    }

}
