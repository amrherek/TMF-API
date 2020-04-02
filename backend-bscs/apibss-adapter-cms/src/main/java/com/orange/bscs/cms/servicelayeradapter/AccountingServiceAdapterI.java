package com.orange.bscs.cms.servicelayeradapter;

import java.util.Date;
import java.util.List;

import com.orange.bscs.api.model.BSCSModel;
import com.orange.bscs.model.accounting.BSCSBookingRequest;
import com.orange.bscs.model.accounting.BSCSPaymentMethod;
import com.orange.bscs.model.accounting.BSCSPaymentTerm;
import com.orange.bscs.model.billing.BSCSCreditCardCompagnie;
import com.orange.bscs.model.billing.BSCSDocuments;
import com.orange.bscs.model.billing.BusinessTransaction;

/**
 * Serve BOOKING_REQUEST.SEARCH, BOOKING_REQUEST.WRITE
 *
 * <pre>
 * {@code
 * BANK.READ
 * BANKS.READ
 * BANKS.SEARCH
 * CREDITCARD_COMPANY.READ
 * CREDITCARD_COMPANIES.READ
 * PAYMENT_METHOD.READ
 * PAYMENT_TERMS.READ
 * BOOKING_REQUESTS.SEARCH
 * BOOKING_REQUEST.WRITE
 * PAYMENT_METHODS.READ
 * GLACCOUNTS.READ
 * GLACCOUNT_PACKAGE.READ
 * JOBCOSTS.READ
 * }
 * @author IT&Labs
 * @version $Id: $
 */
public interface AccountingServiceAdapterI {

    /**
     * This command searches for booking requests according to criteria.
     *
     * <pre>{@code
     * BOOKING_REQUESTS.SEARCH {
     *   BASE_PART_ID         =  : java.lang.Integer
     *   CHARGE_PART_ID       =  : java.lang.Integer
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   END_VALID_FROM       =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     *   IV_ID                =  : java.lang.Long
     *   ORDR_VALID_FROM      =  : java.lang.Character
     *   RECORD_ID            =  : java.lang.Long
     *   RECORD_SUB_ID        =  : java.lang.Integer
     *   SRCH_COUNT           =  : java.lang.Integer
     *   START_VALID_FROM     =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * => {
     *   SEARCH_IS_COMPLETE   =  : java.lang.Boolean
     *   bookingrequests      = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * 		BSCSBookingRequest
     * }
     * }</pre>
     *
     * @see BSCSBookingRequest
     * @param criterias a {@link com.orange.bscs.model.accounting.BSCSBookingRequest} object.
     * @return a {@link java.util.List} object.
     */
    List<BSCSBookingRequest> bookingRequestsSearch(BSCSBookingRequest criterias);

    /**
     * This command is used to write booking requests (formerly known as OCC). .
     * <p>
     * The category of the booking request to be written is defined in the
     * FEE_CLASS parameter.
     * <p>
     * The mode of action to be done should be given in the ACTION_CODE
     * parameter.
     * <p>
     * The actions insert and delete of a booking request are supported
     * ACTION_CODE='I' or 'D'
     *
     * <pre>{@code
     * BOOKING_REQUEST.WRITE {
     * * ACTION_CODE          =  : java.lang.Character
     *   AMOUNT               =  : com.lhs.ccb.common.soiimpl.SVLMoneyImpl
     *   AMOUNT_REL           =  : java.lang.Float
     *   BASE_PART_ID         =  : java.lang.Integer
     *   BILLING_ACCOUNT_CODE =  : java.lang.String
     *   BILLING_ACCOUNT_ID   =  : java.lang.Long
     *   BILL_FORMAT          =  : java.lang.String
     *   CALL_DATE            =  : com.lhs.ccb.common.soiimpl.SVLDateTimeImpl
     *   CHARGE_PART_ID       =  : java.lang.Integer
     *   CO_ID                =  : java.lang.Long
     *   CO_ID_PUB            =  : java.lang.String
     *   CS_ID                =  : java.lang.Long
     *   CS_ID_PUB            =  : java.lang.String
     *   DEVICENUM            =  : java.lang.String
     *   DIRNUM               =  : java.lang.String
     *   EVENT_CODE           =  : java.lang.Long
     * * FEE_CLASS            =  : java.lang.Integer
     * * FEE_TYPE             =  : java.lang.String
     *   FU_NUM               =  : java.lang.Float
     *   FU_PACK_ID           =  : java.lang.Integer
     *   FU_PACK_ID_PUB       =  : java.lang.String
     *   FU_PKELSQ            =  : java.lang.Integer
     *   FU_PKVER             =  : java.lang.Integer
     *   FU_VER               =  : java.lang.Integer
     *   GLCODE               =  : java.lang.String
     *   GLCODEDISC           =  : java.lang.String
     *   GLCODEMIN            =  : java.lang.String
     *   IV_ID                =  : java.lang.Long
     *   JOBCOST              =  : java.lang.Long
     *   JOBCOSTDISC          =  : java.lang.Long
     *   JOBCOSTDISC_PUB      =  : java.lang.String
     *   JOBCOSTMIN           =  : java.lang.Long
     *   JOBCOSTMIN_PUB       =  : java.lang.String
     *   JOBCOST_PUB          =  : java.lang.String
     *   NUM_PERIODS          =  : java.lang.Integer
     *   RECORD_ID            =  : java.lang.Long
     *   RECORD_SUB_ID        =  : java.lang.Integer
     *   REMARK               =  : java.lang.String
     *   RPCODE               =  : java.lang.Long
     *   RPCODE_PUB           =  : java.lang.String
     *   RP_VSCODE            =  : java.lang.Long
     *   SEQNO                =  : java.lang.Long
     *   SERVCAT_CODE         =  : java.lang.String
     *   SERV_CODE            =  : java.lang.String
     *   SERV_TYPE            =  : java.lang.String
     *   SNCODE               =  : java.lang.Long
     *   SNCODE_PUB           =  : java.lang.String
     *   SPCODE               =  : java.lang.Long
     *   SPCODE_PUB           =  : java.lang.String
     *   VALID_FROM           =  : com.lhs.ccb.common.soiimpl.SVLDateImpl
     * }
     * => {
     * }
     * }</pre>
     *
     * @see BSCSBookingRequest
     * @param bookingRequestsModelInput a {@link com.orange.bscs.model.accounting.BSCSBookingRequest} object.
     */
    void bookingRequestWrite(BSCSBookingRequest bookingRequestsModelInput);

    /**
     * <pre>{@code
     * CREDITCARD_COMPANIES.READ {
     * }
     * => {
     *   creditcardcompanies  = sub element : com.lhs.ccb.common.soiimpl.NamedValueContainerList
     *  -[0]BANK_CITY            =  : java.lang.String
     *  -[0]BANK_COUNTRY         =  : java.lang.Long
     *  -[0]BANK_COUNTRY_PUB     =  : java.lang.String
     *  -[0]BANK_COUNTY          =  : java.lang.String
     *  -[0]BANK_DEF             =  : java.lang.Character
     *  -[0]BANK_ID              =  : java.lang.Long
     *  -[0]BANK_ID_PUB          =  : java.lang.String
     *  -[0]BANK_NAME            =  : java.lang.String
     *  -[0]BANK_STATE           =  : java.lang.String
     *  -[0]BANK_STREET          =  : java.lang.String
     *  -[0]BANK_STREET_NUM      =  : java.lang.String
     *  -[0]BANK_ZIP             =  : java.lang.String
     * }
     * }</pre>
     * 
     * @return liste of CreditCardBanks (VISA, MasterCard...)
     */
    List<BSCSCreditCardCompagnie> creditCardCompaniesRead();

    /**
     * Read all the payment methods data.
     * <p>
     * <pre>{@code
     * PAYMENT_METHODS.READ {
     * }
     * => {
     *   paymentmethods       = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     *  -[0]PAYMETH_ACCTOWNER    =  : java.lang.Boolean
     *  -[0]PAYMETH_ACCT_VALIDATION =  : java.lang.Character
     *  -[0]PAYMETH_BANKACCT_NUM =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKCITY     =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKCOUNTRY  =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKNAME     =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKSTATE    =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKSTREET   =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKSTREET_NUM =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKSUBACCT_NUM =  : java.lang.Boolean
     *  -[0]PAYMETH_BANKZIP      =  : java.lang.Boolean
     * *-[0]PAYMETH_CODE         =  : java.lang.String
     *  -[0]PAYMETH_CREDITCARDCOMPANY =  : java.lang.Boolean
     *  -[0]PAYMETH_DESC         =  : java.lang.String
     *  -[0]PAYMETH_ID           =  : java.lang.Long
     *  -[0]PAYMETH_ID_PUB       =  : java.lang.String
     *  -[0]PAYMETH_PAYMENT_AUTHORIZATION =  : java.lang.Character
     *  -[0]PAYMETH_PAYMENT_DEFAULT =  : java.lang.Character
     *  -[0]PAYMETH_PREPAY_DEF   =  : java.lang.Character
     *  -[0]PAYMETH_SWIFTCODE    =  : java.lang.Boolean
     *  -[0]PAYMETH_VALIDTHROUGHDATE =  : java.lang.Boolean
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSPaymentMethod> paymentMethodsRead();

    /**
     * Read all payment terms and related items.
     * <p>
     * <pre>{@code
     * PAYMENT_TERMS.READ {
     * }
     * => {
     *   paymentterms         = sub element : com.lhs.ccb.common.soiimpl.NamedValueCont
     * ainerList
     * *-[0]TERM_CODE            =  : java.lang.Long
     * *-[0]TERM_DEFAULT         =  : java.lang.Boolean
     *  -[0]TERM_DISDAYS         =  : java.lang.Integer
     *  -[0]TERM_DISRATE         =  : java.lang.Float
     * *-[0]TERM_NAME            =  : java.lang.String
     *  -[0]TERM_NET             =  : java.lang.Integer
     * *-[0]TERM_TYPE            =  : java.lang.String
     * }
     * }</pre>
     *
     * @return a {@link java.util.List} object.
     */
    List<BSCSPaymentTerm> paymentTermsRead();
    /**
     * This command searches financial documents according to the input parameter (ORDERHEADER)
     * <p>
     * <pre> {@code
     * FINANCIAL_DOCUMENT.SEARCH{
     * }
     * =>{
     * DOCUMENTS  sub element :
     * }
     * }
     * @return a {@link java.util.List} object
     */
    List<BSCSDocuments> financialDocumentSearch(String csIdPub, Date startDat, Date endDate,
			Integer wishedResults);
    
    /**
     * This command searches financial documents according to the input
     * parameter (ORDERHEADER)
     * <p>
     * 
     * <pre>
     * {@code FINANCIAL_DOCUMENT.SEARCH{ } =>{ DOCUMENTS sub element : } }
     * 
     * @return a {@link java.util.List} object
     */
    List<BSCSDocuments> financialDocumentSearch(BSCSDocuments searchCriteria);

    /**
     * Read a financial document
     * 
     * @param documentId
     * @return
     */
    BSCSDocuments financialDocumentRead(Long documentId);

    /**
     * 
     * @param csCode
     * @param startDat
     * @param endDate
     * @param wishedResults
     * @return
     */
    List<BSCSDocuments> getLastInvoice(String csCode);

    /**
     * /**
     * 
     * <pre>
     *   {@code
     *   BUSINESS_TRANSACTION_SEARCH
     * }
     * </pre>
     *
     * @author ihab.bensouda
     *
     */
    List<BusinessTransaction> businessTransactionSearch(String customerIdPub, Date startDate, Date endDate,
            Integer max);

    /**
     * /**
     * 
     * <pre>
     *   {@code
     *   BUSINESS_TRANSACTION_SEARCH
     * }
     * </pre>
     *
     * @author shainy.ajit.jain
     *
     */
    List<BusinessTransaction> businessTransactionSearchCoIdPub(String coIdPub);

    /**
     * execute BUSINESS_TRANSACTION_SEARCH
     * 
     * @param criteria
     * @return
     */
    List<BusinessTransaction> businessTransactionSearch(BusinessTransaction criteria);

    /**
     * /**
     * 
     * <pre>
     *   {@code
     *   BUSINESS_TRANSACTION_READ
     * }
     * </pre>
     *
     * @author ihab.bensouda
     *
     */
    BusinessTransaction businessTransactionRead(Long transactionId);

    /**
     * FINANCIAL_TRANSACTION.READ
     * 
     * @param transactionId
     * @return
     */
    BSCSModel financialTransactionRead(Long transactionId);

    /**
     * FINANCIAL_TRANSACTION.SEARCH
     * 
     * @param criteria
     * @return
     */
    List<BSCSModel> financialTransactionSearch(BSCSModel criteria);

    /**
     * FINANCIAL_TRANSACTION.WRITE
     * 
     * @param input
     * @return
     */
    Long financialTransactionWrite(BSCSModel input);

    /**
     * FINANCIAL_TRANSACTION_DETAIL.READ
     * 
     * @param transactionId
     * @return
     */
    List<BSCSModel> financialTransactionDetailRead(Long transactionId);

    /**
     * FINANCIAL_DOCUMENT_DETAIL.READ
     * 
     * @param documentId
     * @return
     */
    List<BSCSModel> financialDocumentDetailRead(Long documentId);

    /**
     * CREDITSBYDEBIT.READ
     * 
     * @param transactionId
     * @return
     */
    List<BSCSModel> creditsByDebitsRead(Long transactionId);
    
    /**
     * PAYMENT.WRITE
     * 
     * @param payment
     * @return
     */
    Long paymentWrite(BSCSModel payment);
    
    /**
     * REFERENCED_TRANSACTION.SEARCH
     *
     * @param criteria
     * @return
     */
    public List<BSCSModel> referencedTransactionSearch(BSCSModel criteria);
    
    /**
     * execute DEBITSBYCREDIT.READ
     *
     * @param idTransaction
     *
     * @return
     */
    public List<BSCSModel> debitsByCreditRead(Long idTransaction);

}
