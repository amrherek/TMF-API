package com.orange.bscs.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.cms.servicelayeradapter.BillingServiceAdapterI;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillDocument;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.billing.BSCSBillCycle;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;
import com.orange.bscs.model.criteria.BscsBillingAssignmentSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public abstract class BscsBillingServiceAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BillingServiceAdapterI billingServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    /**
     * execute BILLING_ACCOUNT_ASSIGNMENT.READ command
     * 
     * @param contractId
     * @return
     */
    public List<BscsBillingAssignment> getBillingAssignmentByContract(String contractId) {
        BscsBillingAssignmentSearchCriteria criteria = bscsObjectFactory.createBscsBillingAssignmentSearchCriteria();
        criteria.setContractId(contractId);
        return getBscsBillingAssignments(criteria);
    }

    protected List<BscsBillingAssignment> getBscsBillingAssignments(BscsBillingAssignmentSearchCriteria criteria) {
        try {
            List<BSCSBillingAssignment> billingAccountAssignment = billingServiceAdapter
                    .billingAccountAssignmentRead(criteria.getBscsModel());
            List<BscsBillingAssignment> res = new ArrayList<>();
            for (BSCSBillingAssignment assignment : billingAccountAssignment) {
                res.add(bscsObjectFactory.createBscsBillingAssignment(assignment));
            }
            return res;
        } catch (SOIException ex) {
            logger.debug("BSCS BILLING_ACCOUNT_ASSIGNMENT.READ error with code: " + ex.getCode());
            if (ex.getCode() != null && ex.getCode().contains("FUNC_FRMWK_SRV.id0468")) {
                // bad public key given
                return new ArrayList<>();
            }
            throw ex;
        }
    }

    /**
     * execute BILL_CYCLES.READ command
     * 
     * @return
     */
    public List<BscsBillCycle> getBillCycles() {
        List<BSCSBillCycle> billCycleList = billingServiceAdapter.billCyclesRead();
        List<BscsBillCycle> res = new ArrayList<>();
        for (BSCSBillCycle billCycle : billCycleList) {
            res.add(bscsObjectFactory.createBscsBillCycle(billCycle));
        }
        return res;
    }

    /**
     * execute BILLING_ACCOUNT.SEARCH command
     * 
     * @param customerId
     *            customer id
     * @param mode
     *            search mode
     * @return
     */
    public abstract List<BscsBillingAccountSearch> findBillingAccountsByCustomer(String customerId,
            EnumBillingAccountSearchMod mode);

    /**
     * execute BILLING_ACCOUNT.READ command
     * 
     * @param billingAccountCode
     * @param mode
     *            read mode
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsBillingAccount getBillingAccount(String billingAccountCode, EnumBillingAccountReadMod mode)
            throws BscsInvalidIdException;

    /**
     * execute BILLDOCUMENT.READ
     * 
     * @param docRef
     * @return
     * @throws BscsInvalidIdException
     */
    public abstract BscsBillDocument getBillDocument(String docRef) throws BscsInvalidIdException;


}
