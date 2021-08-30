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
import com.orange.apibss.party.model.obw.IndividualCreateEvent;
import com.orange.apibss.party.model.obw.IndividualCreateEventPayload;
import com.orange.apibss.party.model.obw.Organization;
import com.orange.apibss.party.model.obw.OrganizationCreateEvent;
import com.orange.apibss.party.model.obw.OrganizationCreateEventPayload;
import com.orange.mea.apisi.party.service.IndividualService;
import com.orange.mea.apisi.party.service.OrganizationService;

@RestController
@RequestMapping({"/organizationCreateEvent"})
public class OrganizationCreateEventApiController {

    private static final Logger log = LoggerFactory.getLogger(OrganizationCreateEventApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private OrganizationService organizationService;
    
    @Autowired
    private AuditContext auditContext;

    @Autowired
    public OrganizationCreateEventApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    @RolesAllowed({"ROLE_PARTY"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
    public OrganizationCreateEvent organizationCreateEventCreate(@ApiParam(value = "" ,required=true )  @Valid @RequestBody OrganizationCreateEvent organizationCreateEvent) throws ApiException {   	
    	 log.debug("post Party Organization  in : " + organizationCreateEvent.toString());
    	    Long requestId = null;
    	    
    	    OrganizationCreateEvent newOrganizationCreateEvent = new OrganizationCreateEvent();
    	    newOrganizationCreateEvent= organizationCreateEvent;
    	    Organization newOrganization = new Organization();

    	      this.auditContext.open("organizationCreateEventCreate", null);
    	      newOrganization = this.organizationService.postOrganization(organizationCreateEvent.getEvent().getOrganization());
    	      OrganizationCreateEventPayload event = organizationCreateEvent.getEvent();
    	      event.setOrganization(newOrganization);
    	      newOrganizationCreateEvent.setEvent(event);

    	    log.debug("post Party Organization out Organization:" + newOrganizationCreateEvent);
    	  return newOrganizationCreateEvent;
    	   }
    }

