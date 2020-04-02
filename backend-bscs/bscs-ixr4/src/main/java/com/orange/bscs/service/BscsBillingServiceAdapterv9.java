package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.billing.BSCSBillingAccount;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsBillingServiceAdapterv9 extends BscsBillingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<BscsBillingAccountSearch> findBillingAccountsByCustomer(String customerId,
            EnumBillingAccountSearchMod mode) {
        try {
            List<BSCSBillingAccount> billingAccounts = billingServiceAdapter.getBillingAccount(null, customerId, mode);
            List<BscsBillingAccountSearch> res = new ArrayList<>();
            for (BSCSBillingAccount billingAccount : billingAccounts) {
                res.add(bscsObjectFactory.createBscsBillingAccountSearch(billingAccount));
            }
            return res;
        } catch (final SOIException exception) {
            logger.debug("BSCS BILLING_ACCOUNT.SEARCH error with code: " + exception.getCode());
            if (exception.getCode() != null && (exception.getCode().contains("FUNC_FRMWK_SRV.id0468")
                    || exception.getCode().contains("RC6701"))) {
                // unknown customer Id
                return new ArrayList<>();
            }
            throw exception;
        }
    }

    @Override
    public BscsBillingAccount getBillingAccount(String billingAccountCode, EnumBillingAccountReadMod mode)
            throws BscsInvalidIdException {
        try {
            BSCSBillingAccount billingAccountRead = billingServiceAdapter.billingAccountRead(null, billingAccountCode,
                    EnumBillingAccountReadMod.LATEST_VERSION);
            return bscsObjectFactory.createBscsBillingAccount(billingAccountRead);
        } catch (final SOIException exception) {
            logger.debug("BSCS BILLING_ACCOUNT.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && exception.getCode().contains("NotFound")) {
                // unknown id
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.BILLING_ACCOUNT_ID, billingAccountCode,
                        exception.getMessage());
            }
            throw exception;
        }
    }

    @Override
    public BscsBillDocument getBillDocument(String docRef) throws BscsInvalidIdException {
        try {
            BSCSModel billDocumentRead = billingServiceAdapter.billDocumentRead(docRef);
            return bscsObjectFactory.createBscsBillDocument(billDocumentRead);
        } catch (final SOIException exception) {
            logger.debug("BSCS BILLDOCUMENT.READ error with code: " + exception.getCode());
            if (exception.getCode() != null && exception.getCode().contains("BILLSRV.billproc_nobillimagefound")) {
                // unknown bill Id
                throw new BscsInvalidIdException(BscsFieldExceptionEnum.DOCUMENT_REF, docRef, exception.getMessage());
            }
            throw exception;
        }
    }

}
