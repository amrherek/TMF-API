package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.api.utils.config.Constants;
import com.orange.bscs.commands.BaseDAO;
import com.orange.bscs.model.Caches;
import com.orange.bscs.model.Flags;
import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.BSCSBillingAccount;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.model.billing.EnumInvoicingIndicator;
import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;

/**
 * /**
 * <pre>
 * {@code
 * BILL_CYCLES.READ
 * BILL_MEDIUM.READ
 * PROPOSED_BILL_CYCLE.READ
 * BILLING_ACCOUNT_TAXEXEMPTION.READ
 * BILLING_ACCOUNT_TAXEXEMPTION.WRITE
 *}</pre>
 *
 * @author IT&Labs
 *
 */
@Repository
public class BillingServiceAdapter extends BaseDAO implements BillingServiceAdapterI {
    
    private static final Logger LOG = LoggerFactory.getLogger(BillingServiceAdapter.class);
    
    
    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_BILL_CYCLES_READ + QUOTE)
    @Override
    public List<BSCSBillCycle> billCyclesRead() {
        BSCSModel out = execute(Constants.CMD_BILL_CYCLES_READ);
        return out.getListOfBSCSModelValue(Constants.P_BILL_CYCLES, BSCSBillCycle.class);
    }

    /** {@inheritDoc} */
    @Cacheable(value = Caches.REFERENTIAL, key = QUOTE + Constants.CMD_BILL_MEDIUM_READ + QUOTE)
    @Override
    public List<BSCSModel> billMediumRead() {
        return execute(Constants.CMD_BILL_MEDIUM_READ).getListOfBSCSModelValue(Constants.P_BILL_MEDIA);
    }

    /** {@inheritDoc} */
    @Override
    public List<BSCSBillingAssignment> billingAccountAssignmentRead(BSCSBillingAssignment input) {
    	List<BSCSBillingAssignment> result = null;
    	BSCSModel output = ConnectionHolder.getCurrentConnection().execute(
    			Constants.CMD_BILLING_ACCOUNT_ASSIGNMENT_READ, input);
    	if (null != output) {
    		result = output.getListOfBSCSModelValue(Constants.P_RESULT_LIST, BSCSBillingAssignment.class);
    	}
    	return result;
    }

    /** {@inheritDoc} */
    @Override
    public BSCSBillingAccount billingAccountRead(Long billingAccountId, String billingAccountCode,
    		EnumBillingAccountReadMod mode) {
    
    	BSCSBillingAccount input = new BSCSBillingAccount();
    	if (null != billingAccountCode) {
    		input.setBillingAccountCode(billingAccountCode);
    	} else {
    		input.setBillingAccountId(billingAccountId);
    	}
    	input.setMode(mode);
    
    	return ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BILLING_ACCOUNT_READ, input,
    			BSCSBillingAccount.class);
    }

    /**
     * {@inheritDoc}
     * 
     * in IX R3, InvoiceInd change from Character to String
     */
    @Override
    @Deprecated
    public List<BSCSBillingAccount> billingAccountSearch(Long csId, String csIdPub, EnumBillingAccountSearchMod mode,
    		Flags<EnumInvoicingIndicator> withInvoicing, Integer searchCount) {
    
    	BSCSBillingAccount criteria = new BSCSBillingAccount();
    
    	if (null != csIdPub) {
    		criteria.setCustomerIdPub(csIdPub);
    	} else {
    		criteria.setCustomerId(csId);
    	}
    	criteria.setMode(mode);
    	if (null != withInvoicing && !withInvoicing.isEmpty()) {
    		StringBuilder sb = new StringBuilder();
    		for (EnumInvoicingIndicator v : withInvoicing.values()) {
    			sb.append(v.toCharacter());
    		}
    		// BSCS IX R2 only one, replace by sbtoString() with IX R3
    		criteria.setInvoicingIndicator(withInvoicing.values().iterator().next());
    	}
    	criteria.setSearchCount(searchCount);
    
    	BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BILLING_ACCOUNT_SEARCH,
    			criteria);
    
    	List<BSCSBillingAccount> result = null;
    	if (null != output) {
    		result = output.getListOfBSCSModelValue(Constants.P_RESULT_LIST, BSCSBillingAccount.class);
    	}
    	return result;
    }

    @Override
    	public BSCSBillingAccount billingAccountWrite(BSCSBillingAccount billingAccountToCreate) {
    
    		BSCSBillingAccount billingAccountCreated = ConnectionHolder.getCurrentConnection().execute(
    				Constants.CMD_BILLING_ACCOUNT_WRITE, billingAccountToCreate, BSCSBillingAccount.class);
    
    		LOG.debug("billingAccountCreated: {}", billingAccountCreated);
    
    		return billingAccountCreated;
    
    	}
    
    @Override
    public List<BSCSBillingAccount> getBillingAccount(Long csId, String csIdPub,EnumBillingAccountSearchMod mode) {
    
    	BSCSBillingAccount criteria = new BSCSBillingAccount();
    
    	if (null != csIdPub) {
    		criteria.setCustomerIdPub(csIdPub);
    	} else {
    		criteria.setCustomerId(csId);
    	}
    
        if (null != mode) {
            criteria.setMode(mode);
        }
    
    	BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BILLING_ACCOUNT_SEARCH,
    			criteria);
    
    	List<BSCSBillingAccount> result = null;
    	if (null != output) {
    		result = output.getListOfBSCSModelValue(Constants.P_RESULT_LIST, BSCSBillingAccount.class);
    	}
    	return result;
    }

    @Override
    public BSCSModel billDocumentRead(String documentRef) {
        BSCSModel input = new BSCSModel();
        input.setStringValue(Constants.P_DOCUMENT_REF_NUM, documentRef);
        BSCSModel output = ConnectionHolder.getCurrentConnection().execute(Constants.CMD_BILLDOCUMENT_READ, input);
        List<BSCSModel> result = output.getListOfBSCSModelValue(Constants.P_RESULT_LIST, BSCSModel.class);
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

}
