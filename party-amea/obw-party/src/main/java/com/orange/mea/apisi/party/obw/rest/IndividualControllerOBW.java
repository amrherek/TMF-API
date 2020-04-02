package com.orange.mea.apisi.party.obw.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;
import com.orange.mea.apisi.party.service.IndividualService;

@RestController
@RequestMapping("/individual")
public class IndividualControllerOBW {

    @Autowired
    private IndividualService individualService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Search and find all individuals with trading name
     * 
     * @param tradingName
     * @return a list of individual with the given trading name
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PARTY")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "tradingName")
    public List<Individual> findIndividualsByTradingName(final String tradingName) throws ApiException {
        auditContext.open("findIndividualsByTradingName", null);
        IndividualFilteringCriteria criteria = new IndividualFilteringCriteria();
        criteria.setTradingName(tradingName);
        return individualService.findIndividualsByCriteria(criteria);
    }

}
