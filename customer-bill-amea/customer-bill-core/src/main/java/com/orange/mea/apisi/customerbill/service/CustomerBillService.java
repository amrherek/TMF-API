package com.orange.mea.apisi.customerbill.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.MissingParameterException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.mea.apisi.customerbill.backend.DownloadCustomerBillBackend;
import com.orange.mea.apisi.customerbill.backend.GetCustomerBillBackend;
import com.orange.mea.apisi.customerbill.backend.ListCustomerBillsByMsisdnBackend;
import com.orange.mea.apisi.customerbill.backend.ListCustomerBillsFromPartyIdBackend;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;
import com.orange.mea.apisi.customerbill.rest.model.BillDocument;

/**
 * Service layer for customer bill api
 *
 * @author Denis Borscia (deyb6792)
 */
@Service
public class CustomerBillService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DownloadCustomerBillBackend downloadCustomerBillBackend;

    @Autowired
    private GetCustomerBillBackend getCustomerBillBackend;

    @Autowired
    private ListCustomerBillsByMsisdnBackend listCustomerBillsByMsisdnBackend;

    @Autowired
    private ListCustomerBillsFromPartyIdBackend listCustomerBillsFromPartyIdBackend;

    @Value("${customerBill.searchLimit:50}")
    private Integer searchLimit;

    /**
     * @param msisdn
     *            the msisdn
     * @param startDate
     *            the start date of bills (optional)
     * @param endDate
     *            the end date of bills (optional)
     * @param limit
     *            to limit the number of bills (optional)
     * @param url
     * @return the list of customerBill
     *
     * @throws ApiException
     */
    public PartialResult<CustomerBill> listCustomerBillsByMsisdn(String msisdn, Date startDate, Date endDate,
            Integer limit, String url) throws ApiException {

        CustomerBillCriteria criteria = new CustomerBillCriteria(startDate, endDate, limit);
        criteria.validate();
        criteria.transformLimit(searchLimit);

        if (msisdn.length() == 0) {
            throw new BadParameterValueException("publicKey", null);
        }

        PartialResult<CustomerBill> res = listCustomerBillsByMsisdnBackend.listCustomerBillsByMsisdn(msisdn, criteria);
        addBillDocument(res.getPartialResultList(), url);
        return res;
    }

    /**
     * Find customer bills based on related party id
     *
     * @param relatedPartyId
     * @param withHierarchy
     * @param startDate
     * @param endDate
     * @param limit
     * @param url
     * @return
     * @throws ApiException
     */
    public PartialResult<CustomerBill> listCustomerBillsByPartyId(String relatedPartyId, boolean withHierarchy,
            Date startDate, Date endDate, Integer limit, String url) throws ApiException {

        CustomerBillCriteria criteria = new CustomerBillCriteria(startDate, endDate, limit);
        criteria.validate();
        criteria.transformLimit(searchLimit);

        if (relatedPartyId.isEmpty()) {
            throw new MissingParameterException("relatedParty.id");
        }
        PartialResult<CustomerBill> res = listCustomerBillsFromPartyIdBackend
                .listCustomerBillsFromPartyId(relatedPartyId, withHierarchy, criteria);
        addBillDocument(res.getPartialResultList(), url);
        return res;
    }

    /**
     * @param billId
     * @param url
     * @return
     * @throws ApiException
     */
    public CustomerBill getCustomerBill(String billId, String url) throws ApiException {
        if (!StringUtils.isNumeric(billId)) {
            throw new BadParameterFormatException("customerBill id", billId, "a numeric value");
        }
        CustomerBill billInfo = getCustomerBillBackend.getCustomerBill(Long.parseLong(billId));
        addBillDocument(billInfo, url);
        return billInfo;
    }

    private void addBillDocument(CustomerBill billInfo, String url) throws ApiException {
        if (billInfo.getBillDocument() == null || billInfo.getBillDocument().isEmpty()) {
            // no bill image generated
            return;
        }
        try {
            BillDocument billDoc = downloadCustomerBillBackend.downloadCustomerBill(billInfo.getId());
            if (billDoc != null) {
                billDoc.getDocument().setUrl(url + "/file");
                billInfo.setBillDocument(null);
                billInfo.addBillDocumentItem(billDoc.getDocument());
            }
        }
        catch (ApiException e) {
            billInfo.setBillDocument(null);
            logger.error("Error while getting info for bill document", e);
        }
    }

    private void addBillDocument(List<CustomerBill> customerBills, String url) throws ApiException {
        for (CustomerBill cb : customerBills) {
            addBillDocument(cb, url + "/" + cb.getId());
        }
    }

    /**
     * @param billId
     * @return
     * @throws ApiException
     */
    public BillDocument downloadCustomerBill(String billId) throws ApiException {
        return downloadCustomerBillBackend.downloadCustomerBill(billId);
    }

}
