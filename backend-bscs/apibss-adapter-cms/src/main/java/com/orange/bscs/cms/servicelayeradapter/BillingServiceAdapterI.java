package com.orange.bscs.cms.servicelayeradapter;

import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
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
 *And others commands
 *<pre>
 *{@code
 * BillCycleAssignmentHistoryRead
 * BillCycleChangeReasonsRead
 * BillingAccountAssignmentFinish
 * BillingAccountAssignmentHistoryRead
 * BillingAccountAssignmentRead
 * BillingAccountAssignmentWrite
 * BillingAccountRead
 * BillingAccountSearch
 * BillingAccountWrite
 *}</pre>
 *
 *
 * @author IT&Labs
 *
 */
public interface BillingServiceAdapterI {


    /**
     * Reads the data of all bill cycles.
     * <p>
     * party type identifier:
     * <ul>
     * <li>C: customer,
     * <li>B: business partner
     * </ul>
     *
     * <pre>{@code
     * BILL_CYCLES.READ {
     *   PARTY_TYPE           =  : java.lang.String
     * }
     * => {
     *   bill cycles          = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]BILLCYCLE            =  : java.lang.String
     * *-[0]BILL_INFO_AVAILABLE  =  : java.lang.Boolean
     * *-[0]DESCRIPTION          =  : java.lang.String
     * *-[0]DUMMY_BILLCYCLE      =  : java.lang.Boolean
     * *-[0]INTERVAL_TYPE        =  : java.lang.Character
     * *-[0]LENGTH               =  : java.lang.Integer
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSBillCycle> billCyclesRead();

    /**
     * Reads the data of all bill media.
     * <p>
     * <pre>{@code
     * BILL_MEDIUM.READ {
     * }
     * => {
     *   BM_DEF               =  : java.lang.Long
     *   BM_DEF_PUB           =  : java.lang.String
     *   bill media           = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]BM_DES               =  : java.lang.String
     *  -[0]BM_ID                =  : java.lang.Long
     *  -[0]BM_ID_PUB            =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSModel> billMediumRead();

    /**
     * Reads info for a bill document
     * 
     * @param documentRef
     * @return
     */
    BSCSModel billDocumentRead(String documentRef);

    /**
     * This command retrieves assignments that are either related to a billing
     * account OR a contract. <br/>
     * The client must only specify exactly one of both billing account or
     * contract otherwise an error condition is indicated. <br/>
     * The retrieval is based on the current date and time. <br/>
     * (*) If a billing account is specified, all explicit assignments for that
     * billing account currently valid are returned. If the specified BA is
     * primary, all implicitly assigned contracts are also returned. These are
     * all contracts for which charges exist that are currently not covered by
     * an exact explicit assignment and thus are paid - by default - by the
     * owner of the primary billing account specified. <br/>
     * (*) If a contract is specified also the profile, service and charge type
     * may be specified. In this case the explicit billing account assignment is
     * returned that possibly may cover charges arising from the specified
     * level, or if no matching explicit assignment exists the implicitly
     * assigned primary billing account belonging to either the contract owner
     * or any other customer above in the hierarchy. If an explicit assignment
     * exists on contract level, the implicitly assigned billing account is NOT
     * returned. The search for an explicit assignment is a best match search,
     * i.e. if no exact match is found for the specified level, but an explicit
     * assignment exists for a higher level then this best match is returned as
     * explicit assignment.<br />
     * (*) An assignment criterion belonging to a certain level (i.e. contract,
     * profile, service or charge type) can only be specified if an assignment
     * criterion for the level above has also been specified. If a billing
     * account is specified any input data for profile, service or charge type
     * is ignored. (*) The contract-based search can be extended such, that all
     * explicit assignments for that contract are returned or if none exists the
     * implicitly assigned Billing Account. For this purpose a boolean flag
     * exists. If set all search criteria on lower levels (profile etc.) are
     * ignored. If an explicit assignment exists on contract level, the
     * implicitly assigned billing account is NOT returned. <br/>
     * (*)Information returned consists of assignment type indicator (explicit /
     * implicit), assignment sequence number, contract, profile, service and
     * charge type identifiers. For an implicit assignment the latter three are
     * not set.
     * 
     * <pre>
     * {@code
     * BILLING_ACCOUNT_ASSIGNMENT.READ {
     *   ALL                  =  : java.lang.Boolean
     *   BILLING_ACCOUNT_CODE =  : java.lang.String
     *   BILLING_ACCOUNT_ID   =  : java.lang.Long
     *   CHARGE_TYPE_ID       =  : java.lang.Long
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   PROFILE_ID           =  : java.lang.Long
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     * }
     * => {
     *   RESULT_LIST          = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * }
     * }
     * </pre>
     * 
     * 
     * @param input
     *            criterias
     * @return a {@link java.util.List} list of BillingAssignment (link between
     *         Contract and Billing Account).
     */
    List<BSCSBillingAssignment> billingAccountAssignmentRead(BSCSBillingAssignment input);

    /**
     * Reads a billing account's attributes
     * 
     * <pre>
     * {@code
     * BILLING_ACCOUNT.READ {
     *    BILLING_ACCOUNT_CODE = : java.lang.String
     *    BILLING_ACCOUNT_ID = : java.lang.Long
     *  * MODE = : java.lang.Character
     *  } => {
     *    BSCSBillingAccount
     *  }
     * }
     * </pre>
     * 
     * @param billingAccountId
     *            Billing account identifier
     * @param billingAccountCode
     *            Billing account code
     * @param mode
     *            A: Provide versioned billing account attributes for (A)ll
     *            versions. <br/>
     *            L: Provide versioned billing account attributes only for
     *            (L)atest version.
     * @return a {@link com.orange.bscs.model.billing.BSCSBillingAccount}
     *         object.
     */
    BSCSBillingAccount billingAccountRead(Long billingAccountId, String billingAccountCode,
    		EnumBillingAccountReadMod mode);

    /**
     * This command comes in two modes: It lists all identifiers for all billing
     * accounts that are owned by a party (mode = 'O') or that can be used for
     * the assignment of charges generated by that party (mode = 'U'). <br/>
     * . In mode 'O', all billing account states are considered. For a single
     * subscriber or business partner, all billing accounts owned by the party
     * are returned. For a large account member, all billing accounts of any
     * large account member are returned. .
     * 
     * In mode 'U' only active billing accounts, which can be used for explicit
     * billing account assignments, are returned.
     * 
     * <pre>
     * {@code
     * BILLING_ACCOUNT.SEARCH {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   INVOICING_IND        =  : java.lang.Character
     * * MODE                 =  : java.lang.Character
     *   SRCH_COUNT           =  : java.lang.Integer
     * }   => {
     * RESULT_LIST = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * }
     * }
     * </pre>
     * 
     * @param csId
     *            internal BSCS customer id
     * @param csIdPub
     *            Customers public key
     * @param mode
     *            O: Return data of all billing accounts owned. <br/>
     *            U: Return data of all potentially usable (i.e. only active)
     *            billing accounts.
     * @param withInvoicing
     *            Flags to specify whether the Billing Account is used for
     *            invoicing or for reconciliation: <br/>
     *            I: invoicing <br/>
     *            R: reconciliation <br/>
     *            C: credit memo
     *            <p>
     *            The flags can be combined, e.g. IC would search for invoicing
     *            and credit memo billing accounts
     * @param searchCount
     *            Size of result set.
     * @return List of available billing accounts
     */
    List<BSCSBillingAccount> billingAccountSearch(Long csId, String csIdPub, EnumBillingAccountSearchMod mode,
    		Flags<EnumInvoicingIndicator> withInvoicing, Integer searchCount);

    /**
     * Write a billing account's attributes
     * 
     * <pre>
     * {@code
     * BILLING_ACCOUNT.WRITE {
     *    BILLING_ACCOUNT_CODE = : java.lang.String
     *    CREATE = : java.lang.Boolean
     *    CS_ID = : java.lang.Long
     *    CS_ID_PUB = : java.lang.String
     *  } => {
     *    BSCSBillingAccount
     *  }
     * }
     * </pre>
     * 
     * to create a BA, minimum commande is
     * BILLING_ACCOUNT.WRITE CS_ID=, CREATE=TRUE,INVOICING_IND=I,CONTACT_SEQNO_TEMP=1;
     * 
     */
    BSCSBillingAccount billingAccountWrite(BSCSBillingAccount billingAccountToCreate);
    
    /**
     * This command comes in two modes: It lists all identifiers for all billing
     * accounts that are owned by a party (mode = 'O') or that can be used for
     * the assignment of charges generated by that party (mode = 'U'). <br/>
     * . In mode 'O', all billing account states are considered. For a single
     * subscriber or business partner, all billing accounts owned by the party
     * are returned. For a large account member, all billing accounts of any
     * large account member are returned. .
     * 
     * In mode 'U' only active billing accounts, which can be used for explicit
     * billing account assignments, are returned.
     * 
     * <pre>
     * {@code
     * BILLING_ACCOUNT.SEARCH {
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String   
     *	 MODE                 =  : java.lang.Character
     * }   => {
     * RESULT_LIST = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     * }
     * }
     * </pre>
     * 
     * @param csId
     *            internal BSCS customer id
     * @param csIdPub
     *            Customers public key
     * @param mode
     *            O: Return data of all billing accounts owned. <br/>
     *            U: Return data of all potentially usable (i.e. only active)
     *            billing accounts.
     * @return List of available billing accounts
     */
    List<BSCSBillingAccount> getBillingAccount(Long csId, String csIdPub, EnumBillingAccountSearchMod mode);
  
}
