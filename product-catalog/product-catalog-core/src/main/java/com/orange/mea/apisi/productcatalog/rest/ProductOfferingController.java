package com.orange.mea.apisi.productcatalog.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang.StringUtils;
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
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.utils.EnumCaseInsensitiveConverter;
import com.orange.apibss.productCatalog.ProductOffering;
import com.orange.mea.apisi.productcatalog.enums.ProductOfferOperationTypeEnum;
import com.orange.mea.apisi.productcatalog.service.ProductOfferingService;

/**
 * Rest Controller for product catalog offering
 *
 * @author xbbs3851
 *
 */
@RestController
@RequestMapping("/productOffering")
public class ProductOfferingController {

    @Autowired
    private ProductOfferingService productOfferingService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Find offers available for migration or eligible optional offers
     *
     * @param offerProductOfferingId
     *            product offering id of main offer
     * @param operationType
     * @param productSpecId
     * @return
     * @throws ApiException
     *
     */
    @RolesAllowed("ROLE_PRODUCT_CATALOG")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductOffering> findAvailableOffers(
            @RequestParam(required = true, name = "offerProductOfferingId") final String offerProductOfferingId,
            @RequestParam(required = true, name = "operationType") final ProductOfferOperationTypeEnum operationType,
            @RequestParam(required = false, name = "productSpecification.id") final String productSpecId)
            throws ApiException {
        auditContext.open("findProductOfferings", null);

        if (StringUtils.isBlank(offerProductOfferingId)) {
            throw new MissingParameterException("offerProductOfferingId");
        }
        return productOfferingService.getOffers(offerProductOfferingId, operationType, productSpecId);
    }

    /**
     * Get a product offering from its id
     * 
     * @param productOfferingId
     * @param operationType
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PRODUCT_CATALOG")
    @RequestMapping(method = RequestMethod.GET, value = "/{productOfferingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductOffering getProductOffering(@PathVariable final String productOfferingId,
            @RequestParam(required = true, name = "operationType") final ProductOfferOperationTypeEnum operationType)
            throws ApiException {
        auditContext.open("getProductOffering", null);
        return productOfferingService.getProductOffering(productOfferingId, operationType);
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(ProductOfferOperationTypeEnum.class,
                new EnumCaseInsensitiveConverter<ProductOfferOperationTypeEnum>(ProductOfferOperationTypeEnum.class));
    }

}
