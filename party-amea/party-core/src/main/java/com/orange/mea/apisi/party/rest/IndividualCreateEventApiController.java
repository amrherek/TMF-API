package com.orange.mea.apisi.party.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.obw.Individual;
import com.orange.apibss.party.model.obw.IndividualCreateEvent;
import com.orange.apibss.party.model.obw.IndividualCreateEventPayload;
import com.orange.bscs.api.model.exception.CMSException;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.party.service.IndividualService;

import io.swagger.annotations.ApiParam;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/individualCreateEvent"})
public class IndividualCreateEventApiController
{
  private static final Logger log = LoggerFactory.getLogger(IndividualCreateEventApiController.class);
  private final ObjectMapper objectMapper;
  private final HttpServletRequest request;
  @Autowired
  private IndividualService individualService;
  @Autowired
  private AuditContext auditContext;
  
  @Autowired
  public IndividualCreateEventApiController(ObjectMapper objectMapper, HttpServletRequest request)
  {
    this.objectMapper = objectMapper;
    this.request = request;
  }
  
  @RolesAllowed({"ROLE_PARTY"})
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  public IndividualCreateEvent individualCreateEventCreate(@ApiParam(value="", required=true) @Valid @RequestBody IndividualCreateEvent individualCreateEvent) throws ApiException, BscsInvalidIdException, BscsInvalidFieldException, CMSException
  {
    log.debug("POST Party Individual  in : " + individualCreateEvent.toString());
    Long requestId = null;
    
    IndividualCreateEvent newIndividualCreateEvent = new IndividualCreateEvent();
    newIndividualCreateEvent=individualCreateEvent;
    
      Individual newIndividual = new Individual();
      this.auditContext.open("individualCreateEventCreate", null);
      newIndividual = this.individualService.postIndividual(individualCreateEvent.getEvent().getIndividual());
      IndividualCreateEventPayload event = individualCreateEvent.getEvent();
      event.setIndividual(newIndividual);
      newIndividualCreateEvent.setEvent(event);
    
    log.debug("POST Party Individual out individual:" + newIndividualCreateEvent);
  return newIndividualCreateEvent;
   }
}

