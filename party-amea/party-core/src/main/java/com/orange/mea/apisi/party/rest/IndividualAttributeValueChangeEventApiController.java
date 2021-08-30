package com.orange.mea.apisi.party.rest;

import io.swagger.annotations.ApiParam;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.obw.Individual;
import com.orange.apibss.party.model.obw.IndividualAttributeValueChangeEvent;
import com.orange.apibss.party.model.obw.IndividualAttributeValueChangeEventPayload;
import com.orange.apibss.party.model.obw.Organization;
import com.orange.apibss.party.model.obw.OrganizationAttributeValueChangeEvent;
import com.orange.apibss.party.model.obw.OrganizationAttributeValueChangeEventPayload;
import com.orange.mea.apisi.party.service.IndividualService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-26T19:44:07.086Z")

@RestController
@RequestMapping("/individualAttributeValueChangeEvent")
public class IndividualAttributeValueChangeEventApiController {

    private static final Logger log = LoggerFactory.getLogger(IndividualAttributeValueChangeEventApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private IndividualService individualService;
    @Autowired
    private AuditContext auditContext;

    @Autowired
    public IndividualAttributeValueChangeEventApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @RolesAllowed({"ROLE_PARTY"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.PATCH}, produces={"application/json"})
    public IndividualAttributeValueChangeEvent individualAttributeValueChangeEventCreate(@ApiParam(value = "" ,required=true )  @Valid @RequestBody IndividualAttributeValueChangeEvent individualAttributeValueChangeEvent) throws ApiException {
   	 log.debug("PATCH Party Individual  in : " + individualAttributeValueChangeEvent.toString());

	   IndividualAttributeValueChangeEvent individualChangeEvent = new IndividualAttributeValueChangeEvent();
	   individualChangeEvent = individualAttributeValueChangeEvent;
	    Individual individualUp = new Individual();

	      this.auditContext.open("organizationAttributeValueChangeEvent", null);
	      individualUp = this.individualService.patchIndividual(individualAttributeValueChangeEvent.getEvent().getIndividual());
	      IndividualAttributeValueChangeEventPayload event = individualAttributeValueChangeEvent.getEvent();
	      event.setIndividual(individualUp);
	      individualChangeEvent.setEvent(event);
	    log.debug("PATCH Party Individual out ==>  Individual:" + individualChangeEvent);
	  return individualChangeEvent;
    }
    
}
