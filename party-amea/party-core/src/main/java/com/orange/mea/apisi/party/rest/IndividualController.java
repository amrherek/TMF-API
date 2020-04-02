package com.orange.mea.apisi.party.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.party.model.Individual;
import com.orange.mea.apisi.party.beans.IndividualFilteringCriteria;
import com.orange.mea.apisi.party.service.IndividualService;

/**
 * Rest Controller for individuals
 *
 * @author xbbs3851
 *
 */
@RestController
@RequestMapping("/individual")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Search and find all individuals matching the provided filters
     * 
     * @param criteria
     * @return a list of individual matching the criteria
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PARTY")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Individual> findIndividuals(final IndividualFilteringCriteria criteria)
            throws ApiException {
        auditContext.open("findIndividuals", null);
        if (criteria.getGivenName() == null && criteria.getFamilyName() == null && criteria.getEmail() == null) {
            throw new BadRequestException("Missing search criteria: givenName, familyName or email");
        }
        return individualService.findIndividualsByCriteria(criteria);
    }

    /**
     * Search individuals by identification documents (id and type)
     * 
     * @param identificationType
     * @param identificationId
     * @param criteria
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PARTY")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "individualIdentification.identificationId")
    public List<Individual> findIndividualsByIdentification(
            @RequestParam(name = "individualIdentification.type") Long identificationType,
            @RequestParam(name = "individualIdentification.identificationId") String identificationId,
            final IndividualFilteringCriteria criteria)
            throws ApiException {
        auditContext.open("findIndividualsByIdentification", null);
        // request like ?individualIdentification.type=&individualIdentification.identificationId=123
        if (identificationType == null) {
            throw new MissingParameterException("individualIdentification.type");
        }
        if (identificationId.isEmpty()) {
            throw new MissingParameterException("individualIdentification.identificationId");
        }
        if (criteria.getGivenName() != null || criteria.getFamilyName() != null || criteria.getEmail() != null) {
            throw new BadRequestException(
                    "Search criteria: givenName, familyName and email are not compatible with individualIdentification.identificationId and individualIdentification.type");
        }
        return individualService.findIndividualsByIdentification(identificationType, identificationId);
    }

    /**
     * Gets an individual using its id
     *
     * @param individualId
     * @return the individual matching the provided id
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PARTY")
    @RequestMapping(method = RequestMethod.GET, value = "/{individualId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Individual getIndividual(@PathVariable final String individualId) throws ApiException {
        auditContext.open("getIndividual", null);
        return individualService.getIndividual(individualId);
    }

    /**
     * Updates an existing individual
     * 
     * @param individualId
     * @param individual
     * @return the updated individual
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PARTY")
    @RequestMapping(method = RequestMethod.PUT, value = "/{individualId}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public Individual updateIndividual(@PathVariable(required = true) final String individualId,
            @RequestBody(required = true) final Individual individual)
                    throws ApiException {
        auditContext.open("updateIndividual", null);
        individual.setId(individualId);
        return individualService.updateIndividual(individual);

    }

}
