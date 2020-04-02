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
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.bscs.model.BscsBusinessTransaction;
import com.orange.bscs.model.BscsBusinessTransactionSearch;
import com.orange.bscs.model.BscsReferencedTransactionSearch;
import com.orange.bscs.model.criteria.BscsBusinessTransactionSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;
import com.orange.mea.apisi.customerbill.transformer.BusinessTransactionTransformer;

/**
 * Implementation of BSCS calls for busines transactions
 *
 * @author Denis Borscia (deyb6792)
 */
public class CustomerBillBscsBackendBusinessTransaction extends CustomerBillBscsBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Autowired
    private BusinessTransactionTransformer businessTransactionTransformer;

    @Override
    protected CustomerBill getBill(Long billId) throws NotFoundIdException {
        // Call CMS command
        BscsBusinessTransaction businessTransaction = getBusinessTransaction(billId);
        List<BscsReferencedTransactionSearch> referencedTransactions = accountingServiceAdapter
                .getCreditsByDebit(billId);
        // transform result
        return businessTransactionTransformer.transform(businessTransaction, referencedTransactions);
    }

    private BscsBusinessTransaction getBusinessTransaction(Long billId) throws NotFoundIdException {
        try {
            // Call CMS command
            BscsBusinessTransaction businessTransaction = accountingServiceAdapter.getBusinessTransaction(billId);
            if (!"IN".equals(businessTransaction.getStatus())) {
                logger.debug("Bill is not an invoice, its type is:" + businessTransaction.getStatus());
                throw new NotFoundIdException("bill", billId.toString());
            }
            return businessTransaction;
        } catch (BscsInvalidIdException e) {
            logger.debug("Unkown billId", e);
            throw new NotFoundIdException("bill", billId.toString());
        }
    }

    @Override
    protected PartialResult<CustomerBill> getCustomerBills(String partyId, CustomerBillCriteria criteria) {
        // Fill BSCS search criteria
        BscsBusinessTransactionSearchCriteria bscsCriteria = bscsObjectFactory
                .createBscsBusinessTransactionSearchCriteria();
        bscsCriteria.setCustomerId(partyId);
        bscsCriteria.setStartDate(criteria.getStartDate());
        bscsCriteria.setEndDate(criteria.getEndDate());

        // Call CMS command: do not use limit to get the total number of items
        List<BscsBusinessTransactionSearch> businessTransactions = accountingServiceAdapter
                .findBusinessTransactions(bscsCriteria);
        List<CustomerBill> res = new ArrayList<>();
        for (BscsBusinessTransactionSearch businessTransaction : businessTransactions) {
            List<BscsReferencedTransactionSearch> referencedTransactions = accountingServiceAdapter
                    .getCreditsByDebit(businessTransaction.getId());
            // transform result
            res.add(businessTransactionTransformer.transform(businessTransaction, referencedTransactions));
            if (criteria.getWishedResults() != null && res.size() == criteria.getWishedResults()) {
                break;
            }
        }

        return new PartialResult<>(res, businessTransactions.size());
    }

    @Override
    protected PartialResult<CustomerBill> getCustomerBillsWithHierarchy(String customerId,
            CustomerBillCriteria criteria) throws TechnicalException {
        throw new NotImplementedException("getCustomerBillsWithHierarchy");
    }

    @Override
    protected String getDocumentReference(String billId) throws ApiException {
        if (!StringUtils.isNumeric(billId)) {
            throw new BadParameterFormatException("customerBill id", billId, "a numeric value");
        }
        BscsBusinessTransaction bill = getBusinessTransaction(Long.parseLong(billId));
        return bill.getRef();
    }
}
