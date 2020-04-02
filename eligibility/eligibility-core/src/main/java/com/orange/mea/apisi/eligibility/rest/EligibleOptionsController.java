package com.orange.mea.apisi.eligibility.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.eligibility.model.EligibleOption;
import com.orange.mea.apisi.eligibility.service.EligibilityService;

/**
 * Rest Controller for Eligibility to get EligibleOption
 *
 * @author Thibault Duperron
 *
 */
@RestController
@RequestMapping("/eligibleOptions")
public class EligibleOptionsController {

    @Autowired
    private EligibilityService eligibilityService;

    @Autowired
    private AuditContext auditContext;


    /**
     * Query the list of EligibleOptions related to a contract identified by its
     * public key
     *
     * @param msisdn
     * @param productOfferingId
     * @param productSpecId
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_ELIGIBILITY")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EligibleOption> findEligibleOptions(
            @RequestParam(required = true, name = "publicKey") final String msisdn,
            @RequestParam(required = false, name = "productOfferingId") final String productOfferingId,
            @RequestParam(required = false, name = "productSpecification.id") final String productSpecId)
            throws ApiException {
        auditContext.open("findEligibleOptions", msisdn);
        if (msisdn.isEmpty()) {
            throw new MissingParameterException("publicKey");
        }
        return eligibilityService.findEligibleOptions(msisdn, productOfferingId, productSpecId);
    }

}
