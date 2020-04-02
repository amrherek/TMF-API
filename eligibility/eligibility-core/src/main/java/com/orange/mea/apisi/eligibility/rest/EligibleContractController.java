package com.orange.mea.apisi.eligibility.rest;

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
import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.mea.apisi.eligibility.service.EligibilityService;

/**
 * Rest Controller for Eligibility to get EligibleContract
 *
 */
@RestController
@RequestMapping("/eligibleContract")
public class EligibleContractController {

    @Autowired
    private EligibilityService eligibilityService;

    @Autowired
    private AuditContext auditContext;


    /**
     * Query the list of EligibleContracts related to a contract identified by
     * its public key
     *
     * @param msisdn
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_ELIGIBILITY")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EligibleContract findEligibleContract(
            @RequestParam(required = true, name = "publicKey") final String msisdn) throws ApiException {
        auditContext.open("findEligibleContract", msisdn);
        if (msisdn.isEmpty()) {
            throw new MissingParameterException("publicKey");
        }
        return eligibilityService.findEligibleContract(msisdn);
    }

}
