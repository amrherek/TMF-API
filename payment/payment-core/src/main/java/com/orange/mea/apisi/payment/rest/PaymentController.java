package com.orange.mea.apisi.payment.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.payment.model.Payment;
import com.orange.mea.apisi.payment.service.PaymentService;


/**
 * Rest Controller for payment
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Get a payment based on its id
     *
     * @param paymentId
     * @return payment
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PAYMENT")
    @RequestMapping(method = RequestMethod.GET, value = "/{paymentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment getPayment(@PathVariable final String paymentId) throws ApiException {
        auditContext.open("getPayment", null);
        return paymentService.getPayment(paymentId);
    }

    /**
     * Find all payments based on party
     * 
     * @param payerId
     * @param startDate
     * @param endDate
     * @param limit
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PAYMENT")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "payer.id")
    public PartialResult<Payment> findPaymentsByParty(
            @RequestParam(required = true, name = "payer.id") final String payerId,
            @RequestParam(required = true, name = "paymentDate.gte") final Date startDate,
            @RequestParam(required = false, name = "paymentDate.lte") final Date endDate,
            @RequestParam(required = false, name = "limit") final Integer limit)
            throws ApiException {
        auditContext.open("findPaymentsByParty", null);
        if (payerId.isEmpty()) {
            throw new MissingParameterException("payer.id");
        }
        return paymentService.findPaymentsByParty(payerId, startDate, endDate, limit);
    }

    /**
     * Create a new payment
     * 
     * @param payment
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_PAYMENT")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@RequestBody final Payment payment) throws ApiException {
        auditContext.open("createPayment", null);
        return paymentService.create(payment);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // register date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true, 10));
    }

}
