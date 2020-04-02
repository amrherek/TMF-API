package com.orange.mea.apisi.customerbill.backend.bscs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.notfound.NotFoundIdException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsFinancialDocument;
import com.orange.bscs.model.BscsFinancialDocumentDetail;
import com.orange.bscs.model.BscsFinancialDocumentSearch;
import com.orange.bscs.model.criteria.BscsFinancialDocumentSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;
import com.orange.mea.apisi.customerbill.transformer.FinancialDocumentTransformer;

/**
 * Implementation of BSCS calls for financial documents
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillBscsBackendFinancialDocument extends CustomerBillBscsBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Autowired
    protected BscsBusinessPartnerServiceAdapter businessPartnerAdapter;

    @Autowired
    private FinancialDocumentTransformer financialDocumentTransformer;

    @Override
    protected CustomerBill getBill(Long billId) throws ApiException {
        // Call CMS command
        BscsFinancialDocument financialDocument = getFinancialDocument(billId);
        List<BscsFinancialDocumentDetail> financialDocumentDetails = getFinancialDocumentDetails(billId);
        // transform result
        return financialDocumentTransformer.transform(financialDocument, financialDocumentDetails);
    }

    protected BscsFinancialDocument getFinancialDocument(Long billId) throws NotFoundIdException {
        try {
            // Call CMS command
            BscsFinancialDocument financialDocument = accountingServiceAdapter.getFinancialDocument(billId);
            if (!"IN".equals(financialDocument.getType())) {
                logger.debug("Bill is not an invoice, its type is:" + financialDocument.getType());
                throw new NotFoundIdException("bill", billId.toString());
            }
            return financialDocument;
        } catch (BscsInvalidIdException e) {
            logger.debug("Unkown billId", e);
            throw new NotFoundIdException("bill", billId.toString());
        }
    }

    protected List<BscsFinancialDocumentDetail> getFinancialDocumentDetails(Long billId) throws TechnicalException {
        try {
            // Call CMS command
            return accountingServiceAdapter.getFinancialDocumentDetails(billId);
        } catch (BscsInvalidIdException e) {
            logger.error("Unkown billId when reading details", e);
            throw new TechnicalException("Unkown billId when reading details", e);
        }
    }

    @Override
    protected PartialResult<CustomerBill> getCustomerBills(String partyId, CustomerBillCriteria criteria)
            throws TechnicalException {

        // Fill BSCS search criteria
        BscsFinancialDocumentSearchCriteria bscsCriteria = fillCriteria(criteria);
        bscsCriteria.setCustomerId(partyId);

        // Call CMS command: do not use limit to get the total number of items
        List<BscsFinancialDocumentSearch> financialDocuments = accountingServiceAdapter.findInvoices(bscsCriteria);
        List<CustomerBill> res = new ArrayList<>();
        for (BscsFinancialDocumentSearch doc : financialDocuments) {
            List<BscsFinancialDocumentDetail> financialDocumentDetails = getFinancialDocumentDetails(doc.getId());
            // transform result
            res.add(financialDocumentTransformer.transform(doc, financialDocumentDetails));
            if (criteria.getWishedResults() != null && res.size() == criteria.getWishedResults()) {
                break;
            }
        }

        return new PartialResult<>(res, financialDocuments.size());
    }

    private BscsFinancialDocumentSearchCriteria fillCriteria(CustomerBillCriteria criteria) {
        BscsFinancialDocumentSearchCriteria bscsCriteria = bscsObjectFactory
                .createBscsFinancialDocumentSearchCriteria();
        bscsCriteria.setStartDate(criteria.getStartDate());
        bscsCriteria.setEndDate(criteria.getEndDate());
        return bscsCriteria;
    }

    @Override
    protected PartialResult<CustomerBill> getCustomerBillsWithHierarchy(String customerId,
            CustomerBillCriteria criteria) throws TechnicalException {
        try {
            BscsCustomer customer = businessPartnerAdapter.getCustomer(customerId);
            // add * to customer code to get bills with hierarchy
            String customerCode = customer.getCode() + "*";

            // Fill BSCS search criteria
            BscsFinancialDocumentSearchCriteria bscsCriteria = fillCriteria(criteria);
            bscsCriteria.setCustomerCode(customerCode);

            // Call CMS command: do not use limit to get the total number of
            // items
            List<BscsFinancialDocumentSearch> financialDocuments = accountingServiceAdapter.findInvoices(bscsCriteria);
            List<CustomerBill> res = new ArrayList<>();
            for (BscsFinancialDocumentSearch doc : financialDocuments) {
                List<BscsFinancialDocumentDetail> financialDocumentDetails = getFinancialDocumentDetails(doc.getId());
                // transform result
                res.add(financialDocumentTransformer.transform(doc, financialDocumentDetails));
                if (criteria.getWishedResults() != null && res.size() == criteria.getWishedResults()) {
                    break;
                }
            }

            return new PartialResult<>(res, financialDocuments.size());
        } catch (BscsInvalidIdException e) {
            logger.debug("No customer exists with id: " + customerId, e);
            return new PartialResult<>();
        }
    }

    @Override
    protected String getDocumentReference(String billId) throws ApiException {
        if (!StringUtils.isNumeric(billId)) {
            throw new BadParameterFormatException("customerBill id", billId, "a numeric value");
        }
        BscsFinancialDocument bill = getFinancialDocument(Long.parseLong(billId));
        return bill.getCode();
    }
}
