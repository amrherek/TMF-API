package com.orange.mea.apisi.payment.backend.bscs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.common.model.PartialResult;
import com.orange.apibss.payment.model.Payment;
import com.orange.bscs.api.aop.TransactionalBscs;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.BscsFinancialTransaction;
import com.orange.bscs.model.BscsFinancialTransactionDetail;
import com.orange.bscs.model.BscsFinancialTransactionSearch;
import com.orange.bscs.model.criteria.BscsFinancialTransactionSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsAccountingServiceAdapter;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.payment.backend.CreatePaymentBackend;
import com.orange.mea.apisi.payment.backend.FindPaymentsByPartyBackend;
import com.orange.mea.apisi.payment.backend.GetPaymentBackend;

@Component
public class PaymentBscsBackend implements GetPaymentBackend, FindPaymentsByPartyBackend, CreatePaymentBackend {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BscsAccountingServiceAdapter accountingService;

    @Autowired
    private BscsFinancialTransactionTransformer bscsFinancialTransactionToPaymentTransformer;

    @Autowired
    protected BscsBusinessPartnerServiceAdapter businessPartnerAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Override
    @TransactionalBscs
    public PartialResult<Payment> findPaymentsByParty(String partyId, Date startDate, Date endDate, Integer limit)
            throws ApiException {
        try {
            BscsCustomer customer = businessPartnerAdapter.getCustomer(partyId);
            BscsFinancialTransactionSearchCriteria criteria = bscsObjectFactory
                    .createBscsFinancialTransactionSearchCriteria();
            criteria.setCustomerNumericId(customer.getNumericId());
            criteria.setStartDate(startDate);
            criteria.setEndDate(endDate);
            criteria.setMinAmount(0.0);
            criteria.setTransactionCodes(Arrays.asList(PaymentConstants.ADVANCE_PAYMENT_CODE,
                    PaymentConstants.ASSIGN_CASH_ENTRY, PaymentConstants.DEPOSIT));

            // do not use limit to get the total number of items
            List<BscsFinancialTransactionSearch> transactions = accountingService.findFinancialTransactions(criteria);

            List<Payment> res = new ArrayList<Payment>();
            for (BscsFinancialTransactionSearch transaction : transactions) {
                BscsFinancialTransaction financialTransaction = accountingService
                        .getFinancialTransaction(transaction.getId());
                List<BscsFinancialTransactionDetail> financialTransactionDetails = accountingService
                        .getFinancialTransactionDetails(financialTransaction.getId());
                res.add(bscsFinancialTransactionToPaymentTransformer.doTransform(financialTransaction,
                            financialTransactionDetails));
                if (limit != null && res.size() == limit) {
                    break;
                }
            }
            return new PartialResult<>(res, transactions.size());
        } catch (BscsInvalidIdException e) {
            logger.debug("No customer exists with id: " + partyId, e);
            return new PartialResult<>();
        }
    }

    @Override
    @TransactionalBscs
    public Payment getPayment(String paymentId) throws ApiException {
        if (!StringUtils.isNumeric(paymentId)) {
            throw new BadParameterFormatException("payment id", paymentId, "a numeric value");
        }
        try {
            BscsFinancialTransaction financialTransaction = accountingService
                    .getFinancialTransaction(Long.parseLong(paymentId));
            List<BscsFinancialTransactionDetail> financialTransactionDetails = accountingService
                    .getFinancialTransactionDetails(financialTransaction.getId());
            return bscsFinancialTransactionToPaymentTransformer.doTransform(financialTransaction,
                    financialTransactionDetails);
        } catch (BscsInvalidIdException e) {
            logger.debug("No payment found with this id", e);
            throw new NotFoundException("No payment found with id: " + paymentId);
        } catch (EnumTechnicalException e) {
            logger.error("The financial transaction with id: " + paymentId + " is not a TMF payment", e);
            throw new NotFoundException("No payment found with id: " + paymentId);
        }
    }

    @Override
    @TransactionalBscs
    public String createPayment(Payment payment) throws ApiException {
        BscsFinancialTransaction transaction = bscsFinancialTransactionToPaymentTransformer.doTransform(payment);
        try {
            return accountingService.writeFinancialTransaction(transaction, true);
        } catch (BscsInvalidIdException e) {
            if (BscsFieldExceptionEnum.CUSTOMER_ID == e.getName()) {
                throw new BadParameterValueException("payer.id", e.getId(), e);
            } else if (BscsFieldExceptionEnum.FINANCIAL_DOCUMENT_ID == e.getName()) {
                throw new BadParameterValueException("paymentItem.item.id", e.getId(), e);
            }
            throw new TechnicalException(e.getMessage(), e);
        } catch (BscsInvalidFieldException e) {
            if (BscsFieldExceptionEnum.USE_CASE_CODE == e.getName()) {
                throw new TechnicalException("Bad conversion for transactionType", e);
            } else if (BscsFieldExceptionEnum.USE_CASE_CODE == e.getName()) {
                throw new BadParameterValueException("units", e.getValue(), e);
            }
            throw new TechnicalException(e.getMessage(), e);
        }
    }

}
