package com.orange.mea.apisi.customerbill.backend.bscs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.customerbill.model.CustomerBill;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsContractSearch;
import com.orange.bscs.service.BscsAccountingServiceAdapter;
import com.orange.bscs.service.BscsBillingServiceAdapter;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.customerbill.backend.DownloadCustomerBillBackend;
import com.orange.mea.apisi.customerbill.backend.GetCustomerBillBackend;
import com.orange.mea.apisi.customerbill.backend.ListCustomerBillsByMsisdnBackend;
import com.orange.mea.apisi.customerbill.backend.ListCustomerBillsFromPartyIdBackend;
import com.orange.mea.apisi.customerbill.beans.CustomerBillCriteria;
import com.orange.mea.apisi.customerbill.rest.model.BillDocument;
import com.orange.mea.apisi.customerbill.transformer.BillDocumentTransformer;

/**
 * Impl to execute command on BSCS Backend
 *
 * @author Denis Borscia (deyb6792)
 */
public abstract class CustomerBillBscsBackend implements GetCustomerBillBackend, ListCustomerBillsByMsisdnBackend,
        ListCustomerBillsFromPartyIdBackend, DownloadCustomerBillBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BscsContractServiceAdapter contractServiceAdapter;

    @Autowired
    protected BscsAccountingServiceAdapter accountingServiceAdapter;

    @Autowired
    private BscsBillingServiceAdapter billingServiceAdapter;

    @Autowired
    private BillDocumentTransformer billDocumentTransformer;

    /**
     * List customer bills from msisdn
     * @param msisdn
     * @param criteria
     * @return
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public PartialResult<CustomerBill> listCustomerBillsByMsisdn(String msisdn, CustomerBillCriteria criteria)
            throws ApiException {

        String customerId;
        try {
            customerId = getCustomerId(msisdn);
        } catch (BscsInvalidFieldException e) {
            logger.debug("Unkown msisdn", e);
            return new PartialResult<>();
        }

        return getCustomerBills(customerId, criteria);
    }

    /**
     * List customer bills from partyId
     * 
     * @param partyId
     * @param withHierarchy
     * @param criteria
     * @return
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public PartialResult<CustomerBill> listCustomerBillsFromPartyId(String partyId, boolean withHierarchy,
            CustomerBillCriteria criteria) throws ApiException {
        if (withHierarchy) {
            return getCustomerBillsWithHierarchy(partyId, criteria);
        } else {
            return getCustomerBills(partyId, criteria);
        }
    }

    /**
     * Retrieve customer bill by id
     * @param billId
     * @return
     * @throws ApiException
     */
    @Override
    @TransactionalBscs
    public CustomerBill getCustomerBill(Long billId) throws ApiException {
        return getBill(billId);
    }

    protected abstract CustomerBill getBill(Long billId) throws ApiException;


    /**
     * Retrieve the customer id from the msisdn. Depending on country and BSCS
     * configuration it may differ.
     *
     * @param msisdn
     * @return
     * @throws BscsInvalidFieldException
     */
    protected String getCustomerId(String msisdn) throws BscsInvalidFieldException {

        // Call CMS ContractSearch (without history)
        BscsContractSearch bscsContractSearched = contractServiceAdapter.findContractByMsisdn(msisdn);
        return bscsContractSearched.getCustomerId();
    }

    protected abstract PartialResult<CustomerBill> getCustomerBills(String customerId, CustomerBillCriteria context)
            throws TechnicalException;

    protected abstract PartialResult<CustomerBill> getCustomerBillsWithHierarchy(String customerId,
            CustomerBillCriteria criteria) throws TechnicalException;

    @Override
    @TransactionalBscs
    public BillDocument downloadCustomerBill(String billId) throws ApiException {
        String docRef = getDocumentReference(billId);
        BscsBillDocument billDocument;
        try {
            billDocument = billingServiceAdapter.getBillDocument(docRef);
        } catch (BscsInvalidIdException e) {
            throw new TechnicalException("The document reference for this bill id is unknown", e);
        }
        return billDocumentTransformer.transform(billDocument);
    }

    /**
     * Get document reference from bill id
     * 
     * @param billId
     * @return
     * @throws ApiException
     */
    protected abstract String getDocumentReference(String billId) throws ApiException;

}
