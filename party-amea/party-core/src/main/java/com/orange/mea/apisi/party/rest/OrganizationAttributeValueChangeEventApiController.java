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
import com.orange.apibss.party.model.obw.Organization;
import com.orange.apibss.party.model.obw.OrganizationAttributeValueChangeEvent;
import com.orange.apibss.party.model.obw.OrganizationAttributeValueChangeEventPayload;
import com.orange.apibss.party.model.obw.OrganizationCreateEvent;
import com.orange.apibss.party.model.obw.OrganizationCreateEventPayload;
import com.orange.mea.apisi.party.service.OrganizationService;

@RestController
@RequestMapping({"/organizationAttributeValueChangeEvent"})
public class OrganizationAttributeValueChangeEventApiController {

    private static final Logger log = LoggerFactory.getLogger(OrganizationAttributeValueChangeEventApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private AuditContext auditContext;
    
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    public OrganizationAttributeValueChangeEventApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    
    @RolesAllowed({"ROLE_PARTY"})
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.PATCH}, produces={"application/json"})
    public OrganizationAttributeValueChangeEvent organizationAttributeValueChangeEventCreate(@ApiParam(value = "" ,required=true )  @Valid @RequestBody OrganizationAttributeValueChangeEvent organizationAttributeValueChangeEvent) throws ApiException {
    	 log.debug("PATCH Party Organization  in : " + organizationAttributeValueChangeEvent.toString());

 	   OrganizationAttributeValueChangeEvent organizationChangeEvent = new OrganizationAttributeValueChangeEvent();
 	  organizationChangeEvent = organizationAttributeValueChangeEvent;
 	    
 	    Organization newOrganization = new Organization();
 	      this.auditContext.open("organizationAttributeValueChangeEvent", null);
 	      newOrganization = this.organizationService.patchOrganization(organizationAttributeValueChangeEvent.getEvent().getOrganization());
 	      OrganizationAttributeValueChangeEventPayload event = organizationAttributeValueChangeEvent.getEvent();
 	      event.setOrganization(newOrganization);
 	     organizationChangeEvent.setEvent(event);

 	    log.debug("PATCH Party Organization out Organization:" + organizationChangeEvent);
 	  return organizationChangeEvent;
 	
    }

}
