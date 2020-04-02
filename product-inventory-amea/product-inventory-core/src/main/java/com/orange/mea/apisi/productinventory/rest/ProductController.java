package com.orange.mea.apisi.productinventory.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.utils.EnumCaseInsensitiveConverter;
import com.orange.apibss.productInventory.model.Product;
import com.orange.apibss.productInventory.model.State;
import com.orange.mea.apisi.productinventory.service.ProductService;

/**
 * Rest controller for products
 *
 * @author Thibault Duperron
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    protected ProductService productService;

    @Autowired
    private AuditContext auditContext;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "publicKey")
    @RolesAllowed("ROLE_PRODUCT_INVENTORY")
    public List<Product> findProductsByMsisdn(@RequestParam(value = "publicKey", required = true) final String msisdn,
            @RequestParam(value = "productOffering.category", required = false) final String category,
            @RequestParam(value = "productSpecification.id", required = false) final String specId,
            @RequestParam(value = "status", required = false) final State productStatus,
            @RequestParam(value = "productOffering.id", required = false) final String productOfferingId,
            @RequestParam(value = "withParameters", required = false, defaultValue = "false") final boolean withParameters)
            throws ApiException {
        auditContext.open("findProductsByMsisdn", msisdn);
        if (msisdn.isEmpty()) {
            throw new MissingParameterException("publicKey");
        }
        return productService.getProductsByMsisdn(msisdn, category, specId, productStatus, productOfferingId,
                withParameters);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "iccId")
    @RolesAllowed("ROLE_PRODUCT_INVENTORY")
    public List<Product> findProductsByIccid(
            @RequestParam(value = "iccId", required = true) final String iccId,
            @RequestParam(value = "productOffering.category", required = false) final String category,
            @RequestParam(value = "productSpecification.id", required = false) final String specId,
            @RequestParam(value = "status", required = false) final State productStatus,
            @RequestParam(value = "productOffering.id", required = false) final String productOfferingId,
            @RequestParam(value = "withParameters", required = false, defaultValue = "false") final boolean withParameters)
            throws ApiException {
        auditContext.open("findProductsByIccid", null);
        if (iccId.isEmpty()) {
            throw new MissingParameterException("iccId");
        }
        return productService.getProductsByIccId(iccId, category, specId, productStatus, productOfferingId,
                withParameters);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed("ROLE_PRODUCT_INVENTORY")
    public Product getProduct(@PathVariable final String productId,
            @RequestParam(value = "withParameters", required = false, defaultValue = "false") final boolean withParameters)
                    throws TechnicalException, BadRequestException {
        auditContext.open("getProduct", null);
        return productService.getProduct(productId, withParameters);
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(State.class, new EnumCaseInsensitiveConverter<State>(State.class));
    }

}
