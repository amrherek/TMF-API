package com.orange.mea.apisi.productordering.rest;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.mea.apisi.productordering.service.ProductOrderService;

/**
 * Rest Controller for product ordering
 *
 * @author xbbs3851
 *
 */
@RestController
@RequestMapping("/productOrder")
public class ProductOrderingController {

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private AuditContext auditContext;

    @RolesAllowed("ROLE_PRODUCT_ORDERING")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ProductOrder createProductOrder(@RequestBody final ProductOrder productOrder) throws ApiException {
        auditContext.open("createProductOrder",
                (productOrder.getRelatedPublicKey() != null && !productOrder.getRelatedPublicKey().isEmpty()
                        && productOrder.getRelatedPublicKey().get(0) != null)
                        ? productOrder.getRelatedPublicKey().get(0).getId() : null);
        return this.productOrderService.create(productOrder);
    }

}
