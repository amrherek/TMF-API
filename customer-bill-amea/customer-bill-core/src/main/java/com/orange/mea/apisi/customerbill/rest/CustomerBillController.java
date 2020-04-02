/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.mea.apisi.customerbill.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.mea.apisi.customerbill.constants.CustomerBillConstants;
import com.orange.mea.apisi.customerbill.rest.model.BillDocument;
import com.orange.mea.apisi.customerbill.service.CustomerBillService;

@RestController
@RequestMapping("/customerBill")
public class CustomerBillController {

    @Autowired
    private CustomerBillService customerBillService;

    @Autowired
    private AuditContext auditContext;

    /**
     * Search the customer bills from the msisdn UC30 : list customer bills
     *
     * @param msisdn
     *            the msisdn
     * @param limit
     *            to limit the number of bills (optional)
     * @param startDate
     * @param endDate
     * @param request
     * @return the list of bills for this publicKey (msisdn)
     * @throws ApiException
     */
    @RolesAllowed("ROLE_CUSTOMER_BILL")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "publicKey")
    public PartialResult<CustomerBill> listCustomerBillsByMsisdn(@RequestParam(name = "publicKey") String msisdn,
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = true, name = "billDate.gte") Date startDate,
            @RequestParam(required = false, name = "billDate.lte") Date endDate, HttpServletRequest request)
            throws ApiException {
        auditContext.open(CustomerBillConstants.CUSTOMER_BILL_FIND_BY_MSISDN_METHOD_NAME, msisdn);
        String url = request.getRequestURL().toString();
        return customerBillService.listCustomerBillsByMsisdn(msisdn, startDate, endDate, limit, url);
    }

    /**
     * Search the customer bills from the relatedParty id UC30 : list customer
     * bills
     *
     *
     * @param relatedPartyId
     * @param limit
     * @param startDate
     * @param endDate
     * @param withHierarchy
     * @param request
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_CUSTOMER_BILL")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, params = "relatedParty.id")
    public PartialResult<CustomerBill> listCustomerBillsByPartyId(
            @RequestParam(name = "relatedParty.id") String relatedPartyId,
            @RequestParam(required = false, name = "limit") Integer limit,
            @RequestParam(required = true, name = "billDate.gte") Date startDate,
            @RequestParam(required = false, name = "billDate.lte") Date endDate,
            @RequestParam(required = false, name = "withHierarchy", defaultValue = "false") boolean withHierarchy,
            HttpServletRequest request)
            throws ApiException {
        auditContext.open(CustomerBillConstants.CUSTOMER_BILL_FIND_BY_PARTY_ID_METHOD_NAME, null);
        String url = request.getRequestURL().toString();
        return customerBillService.listCustomerBillsByPartyId(relatedPartyId, withHierarchy, startDate, endDate, limit,
                url);
    }

    /**
     * UC31 : retrieve the open bill amount for a bill
     * 
     * @param billId
     * @param request
     * @return the customer bill information
     * @throws ApiException
     */
    @RolesAllowed("ROLE_CUSTOMER_BILL")
    @GetMapping(value = "/{billId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerBill getCustomerBill(@PathVariable String billId, HttpServletRequest request) throws ApiException {
        auditContext.open(CustomerBillConstants.CUSTOMER_BILL_GET_METHOD_NAME, null);
        String url = request.getRequestURL().toString();
        return customerBillService.getCustomerBill(billId, url);
    }

    /**
     * UC32: retrieve the pdf of a customer bill
     * 
     * @param billId
     * @return
     * @throws ApiException
     */
    @RolesAllowed("ROLE_CUSTOMER_BILL")
    @RequestMapping(value = "/{billId}/file", method = RequestMethod.GET)
    public HttpEntity<byte[]> getFile(@PathVariable String billId) throws ApiException {
        auditContext.open(CustomerBillConstants.CUSTOMER_BILL_DOWNLOAD_METHOD_NAME, null);
        BillDocument customerBillDoc = customerBillService.downloadCustomerBill(billId);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(customerBillDoc.getDocument().getMimeType()));
        header.setContentLength(customerBillDoc.getData().length);

        return new HttpEntity<byte[]>(customerBillDoc.getData(), header);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // register date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
