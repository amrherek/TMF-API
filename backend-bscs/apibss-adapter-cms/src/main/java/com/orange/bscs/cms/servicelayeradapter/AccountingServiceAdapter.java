package com.orange.bscs.cms.servicelayeradapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.accounting.BSCSBookingRequest;
import com.orange.bscs.model.accounting.BSCSPaymentMethod;
import com.orange.bscs.model.accounting.BSCSPaymentTerm;
import com.orange.bscs.model.billing.BSCSCreditCardCompagnie;
import com.orange.bscs.model.billing.BSCSDocuments;
import com.orange.bscs.model.billing.BusinessTransaction;

/**
 * <pre>
 *  {@code
 *  BILL_CYCLES.READ
 *  BILL_MEDIUM.READ
 *  PROPOSED_BILL_CYCLE.READ
 *  PROPOSED_BILL_CYCLE.READ
 *  BILLING_ACCOUNT_TAXEXEMPTION.READ
 *  BILLING_ACCOUNT_TAXEXEMPTION.WRITE
 * }
 * </pre>
 * 
 * @author IT&Labs
 * @version $Id: $
 */
@Repository
public class AccountingServiceAdapter extends BaseDAO implements
		AccountingServiceAdapterI {

	/** {@inheritDoc} */
	@Override
	public List<BSCSBookingRequest> bookingRequestsSearch(
			BSCSBookingRequest criterias) {
		BSCSModel res = ConnectionHolder.getCurrentConnection().execute(
				Constants.CMD_BOOKING_REQUESTS_SEARCH, criterias);
		return res.getListOfBSCSModelValue(
				Constants.P_LIST_OF_BOOKING_REQUESTS, BSCSBookingRequest.class);
	}

	/** {@inheritDoc} */
	@Override
	public void bookingRequestWrite(BSCSBookingRequest bookingRequestsModelInput) {
		ConnectionHolder.getCurrentConnection().execute(
				Constants.CMD_BOOKING_REQUEST_WRITE, bookingRequestsModelInput);
	}

	@Override
	@Cacheable(value = Caches.REFERENTIAL, key = QUOTE
			+ Constants.CMD_CREDITCARD_COMPANIES_READ + QUOTE)
	public List<BSCSCreditCardCompagnie> creditCardCompaniesRead() {
		BSCSModel res = execute(Constants.CMD_CREDITCARD_COMPANIES_READ);
		if (null != res) {
			return res.getListOfBSCSModelValue(Constants.P_CREDITCARDCOMPANIES,
					BSCSCreditCardCompagnie.class);
		}
		return new ArrayList<BSCSCreditCardCompagnie>();
	}

	/** {@inheritDoc} */
	@Cacheable(value = Caches.REFERENTIAL, key = QUOTE
			+ Constants.CMD_PAYMENT_METHODS_READ + QUOTE)
	@Override
	public List<BSCSPaymentMethod> paymentMethodsRead() {
		BSCSModel out = execute(Constants.CMD_PAYMENT_METHODS_READ);
		return out.getListOfBSCSModelValue(Constants.P_PAYMENTS_METHODS,
				BSCSPaymentMethod.class);
	}

	/** {@inheritDoc} */
	@Cacheable(value = Caches.REFERENTIAL, key = QUOTE
			+ Constants.CMD_PAYMENT_TERMS_READ + QUOTE)
	@Override
	public List<BSCSPaymentTerm> paymentTermsRead() {
		BSCSModel out = execute(Constants.CMD_PAYMENT_TERMS_READ);
		return out.getListOfBSCSModelValue(Constants.P_PAYMENT_TERMS,
				BSCSPaymentTerm.class);
	}

	@Override
    public List<BSCSDocuments> financialDocumentSearch(String csIdPub,
			Date startDat, Date endDate, Integer wishedResults) {
		BSCSDocuments modelDoc = new BSCSDocuments();
        if (null != csIdPub) {
            modelDoc.setCsIdPub(csIdPub);
		}

		if (null != startDat) {
			modelDoc.setStartOhRefDate(startDat);
		}
		if (null != endDate) {
			modelDoc.setEndOhRefDate(endDate);
		}
		if (null != wishedResults) {
			modelDoc.setWishedRecords(wishedResults);
		}
		
		modelDoc.setIgnoreBunisnessUnit(Boolean.TRUE);
        // modelDoc.setDocStatus('P');
        // modelDoc.setMinimumAmt(0.01);
        // descending search: newest entries first
        modelDoc.setCharacterValue(Constants.P_ORDR_REF_DATE, 'd');
		
		List<String> invoiceTypes = new ArrayList<String>();
        // only invoices
        invoiceTypes.add("IN");
		modelDoc.setDocumentTypes(invoiceTypes);
	
		BSCSDocuments out = execute(Constants.CMD_FINANCIAL_DOCUMENT_SEARCH,
				modelDoc, BSCSDocuments.class);
		
		return out.getFinancialTransactionSearch();
	}

	@Override
	public List<BSCSDocuments> getLastInvoice(String csCode) {
		BSCSDocuments modelDoc = new BSCSDocuments();
		if (null != csCode) {
			modelDoc.setCustCode(csCode);
		}
		modelDoc.setIgnoreBunisnessUnit(Boolean.TRUE);

		List<String> invoiceTypes = new ArrayList<String>();
		invoiceTypes.add("IN");
		
		modelDoc.setDocumentTypes(invoiceTypes);
		
		modelDoc.setCharacterValue(Constants.P_ORDR_REF_DATE, 'd');

		modelDoc.setIntegerValue(Constants.P_RESULT_LIMIT, 1);
		BSCSDocuments out = execute(Constants.CMD_FINANCIAL_DOCUMENT_SEARCH,
				modelDoc, BSCSDocuments.class);
		return out.getFinancialTransactionSearch();

	}

    @Override
    public BSCSDocuments financialDocumentRead(Long documentId) {
        BSCSDocuments modelDoc = new BSCSDocuments();
        modelDoc.setInvoiceId(documentId);
        return execute(Constants.CMD_FINANCIAL_DOCUMENT_READ, modelDoc, BSCSDocuments.class);
    }

    @Override
    public List<BSCSDocuments> financialDocumentSearch(BSCSDocuments searchCriteria) {
        BSCSDocuments out = execute(Constants.CMD_FINANCIAL_DOCUMENT_SEARCH, searchCriteria, BSCSDocuments.class);

        return out.getFinancialTransactionSearch();
    }

    @Override
    public BusinessTransaction businessTransactionRead(Long transactionId) {

        // Initialize the object BusinessTransaction
        BusinessTransaction businessTransaction = new BusinessTransaction();
        if (null != transactionId) {
            businessTransaction.setTransactionId(transactionId);
        }

        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BUSINESS_TRANSACTION_READ,
                businessTransaction);
        if (null != output) {
            List<BusinessTransaction> list = output.getListOfBSCSModelValue(Constants.P_REFERENCEDBTRANS,
                    BusinessTransaction.class);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public List<BusinessTransaction> businessTransactionSearch(String customerIdPub, Date startDat, Date endDate,
            Integer wishedResults) {

        // Initialize the BusinessTransaction
        BusinessTransaction businessTransaction = new BusinessTransaction();

        if (null != customerIdPub) {
            businessTransaction.setCsIdPub(customerIdPub);
        }

        if (null != startDat) {
            businessTransaction.setStartBtOhRefDate(startDat);
        }
        if (null != endDate) {
            businessTransaction.setEndBtOhRefDate(endDate);
        }
        if (null != wishedResults) {
            businessTransaction.setMaxNumOfFetchRecords(wishedResults.longValue());
        }
        // only invoices
        businessTransaction.setBtOhStatut("IN");

        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BUSINESS_TRANSACTIONS_SEARCH,
                businessTransaction);
        if (null != output) {
            return output.getListOfBSCSIvoiceModelValue(Constants.P_REFERENCEDTRANSACTIONS, BusinessTransaction.class,
                    wishedResults);
        }
        return null;

    }

    @Override
    public List<BusinessTransaction> businessTransactionSearchCoIdPub(String coIdPub) {

        // Initialize the BusinessTransaction
        BusinessTransaction businessTransaction = new BusinessTransaction();

        if (null != coIdPub) {
            businessTransaction.setContractIdPub(coIdPub);
        }

        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BUSINESS_TRANSACTIONS_SEARCH,
                businessTransaction);
        if (null != output) {
            return output.getListOfBSCSModelValue(Constants.P_REFERENCEDTRANSACTIONS, BusinessTransaction.class);
        }

        return null;
    }

    @Override
    public List<BusinessTransaction> businessTransactionSearch(BusinessTransaction criteria) {
        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BUSINESS_TRANSACTIONS_SEARCH,
                criteria);
        return output.getListOfBSCSModelValue(Constants.P_REFERENCEDTRANSACTIONS, BusinessTransaction.class);
    }

	@Override
	public Long paymentWrite(BSCSModel payment) {
		BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_PAYMENT_WRITE, payment);
		return output.getLongValue(Constants.P_PIHTAB_ID);
	}

    @Override
    public List<BSCSModel> referencedTransactionSearch(BSCSModel criteria) {
        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_REFERENCED_TRANSACTIONS_SEARCH,
                criteria);
        return output.getListOfBSCSModelValue(Constants.P_REFERENCEDTRANSACTIONS, BSCSModel.class);
    }

    @Override
    public List<BSCSModel> debitsByCreditRead(Long idTransaction) {
        BSCSModel bscsModel = new BSCSModel();
        bscsModel.setLongValue(Constants.P_BT_CAXACT, idTransaction);
        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_DEBITSBYCREDIT_READ,
                bscsModel);
        return output.getListOfBSCSModelValue(Constants.P_REFERENCEDTRANSACTIONS, BSCSModel.class);
    }
       
   

    @Override
    public BSCSModel financialTransactionRead(Long transactionId) {

        BSCSModel input = new BSCSModel();
        input.setLongValue("TRANSACTION_ID", transactionId);

        return ConnectionHolder.getCurrentConnection().execute(Constants.CMD_FINANCIAL_TRANSACTION_READ, input);
    }

    @Override
    public List<BSCSModel> financialTransactionDetailRead(Long transactionId) {

        BSCSModel input = new BSCSModel();
        input.setLongValue("TRANSACTION_ID", transactionId);

        BSCSModel output = ConnectionHolder.getCurrentConnection()
                .execute(Constants.CMD_FINANCIAL_TRANSACTION_DETAIL_READ, input);
        return output.getListOfBSCSModelValue("ITEMS");
    }

    @Override
    public List<BSCSModel> financialTransactionSearch(BSCSModel criteria) {
        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_FINANCIAL_TRANSACTION_SEARCH,
                criteria);
        return output.getListOfBSCSModelValue("TRANSACTIONS", BSCSModel.class);
    }

    @Override
    public Long financialTransactionWrite(BSCSModel input) {
        List<BSCSModel> output = ConnectionHolder.getCurrentConnection()
                .execute(Constants.CMD_FINANCIAL_TRANSACTION_WRITE, input)
                .getListOfBSCSModelValue(Constants.P_RESULT_LIST, BSCSModel.class);
        if (output.isEmpty()) {
            // no transaction created
            return null;
        }
        return output.get(0).getLongValue("TRANSACTION_ID");
    }

    @Override
    public List<BSCSModel> financialDocumentDetailRead(Long documentId) {
        BSCSModel input = new BSCSModel();
        input.setLongValue("DOCUMENT_ID", documentId);

        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_FINANCIAL_DOCUMENT_DETAIL_READ,
                input);
        return output.getListOfBSCSModelValue("ITEMS");
    }

    @Override
    public List<BSCSModel> creditsByDebitsRead(Long transactionId) {
        BSCSModel input = new BSCSModel();
        input.setLongValue("RT_OHXACT", transactionId);

        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_CREDITSBYDEBIT_READ, input);
        return output.getListOfBSCSModelValue(Constants.P_REFERENCEDTRANSACTIONS);
    }

}
