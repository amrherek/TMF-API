package com.orange.bscs.api.utils.config;


public final class Constants {

    /** Hide utility class constructor */
    private Constants(){}


    /*
     * ----------------------------------------------------------------
     * 			Common
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_ADDRESS_READ = "ADDRESS.READ";
    public static final String CMD_ADDRESSES_READ = "ADDRESSES.READ";
    public static final String CMD_CONTRACT_READ = "CONTRACT.READ";
    public static final String CMD_COUNTRIES_READ = "COUNTRIES.READ";
    public static final String CMD_CUSTCATCODE_READ = "CUSTCATCODE.READ";
    public static final String CMD_SESSION_CHANGE = "SESSION.CHANGE";
    public static final String CMD_TITLES_READ = "TITLES.READ";
    public static final String CMD_RESOURCE_STATE_WRITE = "RESOURCE_STATE.WRITE";
    public static final String CMD_BALANCES_READ = "BALANCES.READ";
    public static final String CMD_PAYMENT_WRITE  = "PAYMENT.WRITE";
    
    // SVLObject parametersCMD
    public static final String P_ADR_STREETNO = "ADR_STREETNO";
    public static final String P_ADR_ZIP = "ADR_ZIP";
    public static final String P_ADR_STREET = "ADR_STREET";
    public static final String P_ADR_CITY = "ADR_CITY";
    public static final String P_BILLING_ACCOUNT_ID = "BILLING_ACCOUNT_ID";
    public static final String P_BU_ID = "BU_ID";
    public static final String P_CNTR_DES = "CNTR_DES";
    public static final String P_CO_ID = "CO_ID";
    public static final String P_CO_ID_PUB = "CO_ID_PUB";
    public static final String P_CO_PENDING_STATUS = "CO_PENDING_STATUS";
    public static final String P_CO_STATUS = "CO_STATUS";
    public static final String P_COUNTRIES = "countries";
    public static final String P_COUNTRY_ID = "COUNTRY_ID";
    public static final String P_CS_ID = "CS_ID";
    public static final String P_ADMIN_CS_ID = "ADMIN_CUSTOMER_ID";
    public static final String P_PAYMENT_CS_ID = "PAYMENT_CUSTOMER_ID";

    public static final String P_CS_ID_PUB = "CS_ID_PUB";
    public static final String P_ADMIN_CS_ID_PUB = "ADMIN_CUSTOMER_ID_PUB";
    public static final String P_PAYMENT_CS_ID_PUB = "PAYMENT_CUSTOMER_ID_PUB";
    public static final String P_CUSTCAT_DES = "CUSTCAT_DES";
    public static final String P_KEY = "KEY";
    public static final String P_LIST_OF_CUSTOMER_CATEGORIES = "LIST_OF_CUSTOMER_CATEGORIES";
    public static final String P_LIST_OF_TITLES = "LIST_OF_TITLES";
    public static final String P_PROFILE_ID = "PROFILE_ID";
    public static final String P_PIHTAB_ID = "PIHTAB_ID";
    public static final String P_RT_CATYPE = "RT_CATYPE";
    public static final String P_RT_CAREASONCODE = "RT_CAREASONCODE";
    public static final String P_RT_CACHKNUM ="RT_CACHKNUM";
    public static final String P_RT_STARTDATE ="RT_STARTDATE";
    public static final String P_RT_ENDDATE ="RT_ENDDATE";
    public static final String P_RT_CAXACT ="RT_CAXACT";
    public static final String P_RT_CAENTDATE ="RT_CAENTDATE";
    public static final String P_RT_CACHKAMT_PAY ="RT_CACHKAMT_PAY";
    public static final String P_RT_FCCODE ="RT_FCCODE";
    public static final String P_RT_CAREM ="RT_CAREM";
    public static final String P_RT_CACURAMT_PAY ="RT_CACURAMT_PAY";
    public static final String P_RT_CACHKDATE ="RT_CACHKDATE";
    public static final String P_RT_CARECDATE ="RT_CARECDATE";
    

       
    // CONTRACT.CANCEL RETENTIONS
    public static final String P_CO_DN_RETENTION = "CO_DN_RETENTION";
    public static final String P_CO_DEV_RETENTION = "CO_DEV_RETENTION";
    public static final String P_CO_PORT_RETENTION = "CO_PORT_RETENTION";

    public static final String P_RPCODE = "RPCODE";
    public static final String P_RPCODE_PUB = "RPCODE_PUB";

    public static final String P_SCCODE = "SCCODE";
    public static final String P_SCCODE_PUB = "SCCODE_PUB";

    public static final String P_SNCODE_PUB = "SNCODE_PUB";

    public static final String P_SPCODE = "SPCODE";
    public static final String P_SPCODE_PUB = "SPCODE_PUB";

    public static final String P_TTL_DES = "TTL_DES";
    public static final String P_USER_REASON = "USER_REASON";
    public static final String P_VALUE = "VALUE";
    public static final String P_VALUE_DES = "VALUE_DES";
    public static final String P_PRG_DES = "PRG_DES";
    public static final String P_VALID_FROM = "VALID_FROM";
    public static final String P_VALID_TO = "VALID_TO";
    public static final String P_VALID_AT = "VALID_AT";

    public static final String P_CUG_ID = "CUG_ID";
    public static final String P_CUG_SEQNO = "CUG_SEQNO";
    public static final String P_CUG_ACTION = "CUG_ACTION";
    public static final String P_CUG_INTERLOCKCODE = "CUG_INTERLOCKCODE";
    public static final String P_CUG_NAME = "CUG_NAME";
    public static final String P_CUG_STATUS = "CUG_STATUS";
    public static final String P_CUG_TYPE = "CUG_TYPE";
    public static final String P_RETENTION = "RETENTION";

    public static final String P_CHARGES = "CHARGES";

    //Internal BL constants:
    public static final String BL_MAX_WISHED_RESULTS = "BL_MAX_WISHED_RESULTS";

    // MAX_NUMBER_OF_FETCHED_RECORDS
    public static final String P_MAX_NUMBER_OF_FETCHED_RECORDS = "MAX_NUMBER_OF_FETCHED_RECORDS";

    // Constants
    public static final int CST_STATUS_OK = 1;
    public static final int CST_STATUS_ERROR = 0;
    public static final int CST_THREE = 3;
    public static final String CST_ATOMIC_A = "'A'";
    public static final String CST_PIPE_REQUEST = "\\|";
    public static final String CST_PIPE = "|";
    public static final String CST_UNKNOWN = " ";
    public static final String CST_EMPTY = "";
    public static final String CST_AND = " and ";
    public static final String CST_OR = " or ";
    public static final String CST_QUOTE = "'";
    public static final String CST_SPACE_COMMA_SPACE = " ; ";
    public static final String CST_STAR = "*";
    public static final String CST_NATIONAL_ORGANISATION_IDENTIFIER = "NationalOrganisationIdentifier";
    public static final String CST_SIRET = "SIRET";
    public static final String CST_SIREN = "SIREN";
    public static final String CST_SECURITY_SOCIAL_CARD = "SecuritySocialCard";
    public static final String CST_DRIVING_LICENCE = "DrivingLicence";
    public static final String CST_DATE_BY_DEFAULT = "2099/12/31";
    public static final String CST_DATE_BY_DEFAULT_FORMAT ="yyyy/MM/dd";
    public static final String CST_THIRTY_ONE_DAYS = "31";
    public static final String CST_THIRTY_DAYS = "30";
    public static final String CST_TWENTY_EIGHT_DAYS = "28";
    public static final String CST_TWENTY_NINE_DAYS = "29";
    public static final String CST_CHECK_INFOFIELD_REGEX = "(CHECK)(0[1-9]|1[0-9]|20)";
    public static final String CST_TEXT_COMBO_INFOFIELD_REGEX = "((COMBO)(0[1-9]|1[0-9]|20))|((TEXT)(0[1-9]|1[0-9]|2[0-9]|30))";
    public static final String CST_ALL_INFOFIELD = "ALL";
    public static final String CST_TRUE_UPPER_TEXT = "TRUE";
    public static final String CST_FALSE_UPPER_TEXT = "FALSE";
    public static final String CST_STRING_TEXT = "String";
    public static final String CST_BOOLEAN_TEXT = "Boolean";

    public static final String CST_CUSTOMER 		= "Customer";
    public static final String CST_PAYER 			= "Payer";
    public static final String CST_BILLDETAIL		= "BillDetail";
    public static final String CST_BILLRECEIVER 	= "BillReceiver";
    public static final String CST_HOLDER 			= "Holder";
    public static final String CST_MAGAZINE 		= "Magazine";
    public static final String CST_SHIPMENT			= "Shipment";
    public static final String CST_USER				= "User";

    public static final String CST_NO_FILTER	    = "XXXXXXXXX";

    public static final String CST_CONTRACT_NOT_FOUND_SOI_CODE	= "FUNC_FRMWK_SRV.id0468";
    public static final long CST_MINUS_ONE_LONG = -1L;
    public static final long CST_MINUS_TWO_LONG = -2L;
    public static final long CST_ONE_LONG = 1L;
    public static final int CST_TWO_INT = 2;
    public static final long CST_UMCODE_HOURS = 9L;
    public static final long CST_UMCODE_MINUTES = 8L;
    public static final String CST_NEGATIVE_FU_ACCOUNT_KEY = "negative installedProductID";

    // Cached SOI Commands' outputs
    public static final String CACHED_REASONS_READ = "CACHED_REASONS_READ";
    public static final String CACHED_SERVICES_READ = "CACHED_SERVICES_READ";
    public static final String CACHED_SERVICE_PACKAGES_READ = "CACHED_SERVICE_PACKAGES_READ";
    public static final String CACHED_USAGE_TYPE_READ = "CACHED_USAGE_TYPE_READ";
    public static final String CACHED_UNIT_OF_MEASUREMENTS = "CACHED_UNIT_OF_MEASUREMENTS";
    public static final String CACHED_UDC_LOGICAL_QUANTITY = "CACHED_UDC_LOGICAL_QUANTITY";
    public static final String CACHED_COUNTRIES_READ = "CACHED_COUNTRIES_READ";
    public static final String CACHED_PAYMENT_METHODS_READ = "CACHED_PAYMENT_METHODS_READ";
    public static final String CACHED_CUSTCATCODE_READ = "CACHED_CUSTCATCODE_READ";
    public static final String CACHED_PAYMENT_TERMS_READ = "CACHED_PAYMENT_TERMS_READ";
    public static final String CACHED_CURRENCIES_READ = "CACHED_CURRENCIES_READ";
    public static final String CACHED_IDENTIFICATION_DOCUMENT_TYPE_READ = "CACHED_IDENTIFICATION_DOCUMENT_TYPE_READ";
    public static final String CACHED_TITLES_READ = "CACHED_TITLES_READ";
    public static final String CACHED_LANGUAGES_READ = "CACHED_LANGUAGES_READ";
    public static final String CACHED_SERVICE_PARAMETERS_READ = "CACHED_SERVICE_PARAMETERS_READ";






    /*
     * ----------------------------------------------------------------
     * ManageProductInstBaseResourceAssigment
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_PHONE_NUMBER_EXPORT_SEARCH = "PHONE_NUMBER_EXPORT.SEARCH";
    public static final String CMD_PHONE_NUMBER_IMPORT = "PHONE_NUMBER.IMPORT";
    public static final String CMD_PHONE_NUMBER_STATUS_WRITE = "PHONE_NUMBER_STATUS.WRITE";

    // SVLObject parameters
    public static final String P_DEST_PLCODE = "DEST_PLCODE";
    public static final String P_DIRNUM = "DIRNUM";
    public static final String P_DN_STATUS = "DN_STATUS";
    public static final String P_HMCODE = "HMCODE";
    public static final String P_LOCAL_PREFIX = "LOCAL_PREFIX";
    public static final String P_NPCODE = "NPCODE";
    public static final String P_PORTING_DATE = "PORTING_DATE";
    public static final String P_SRC_PLCODE = "SRC_PLCODE";
    public static final String P_SUFFIX = "SUFFIX";
    public static final String P_ACTIV_DATE = "ACTIV_DATE";


    // Constants
    public static final String CST_DEACTIVATED_STATUS = "d";
    public static final String CST_FUNCTION_SPECIFICATION_LABEL = "functionSpecificationLabel";
    public static final String CST_FUNCTION_SPECIFICATION_CODE = "functionSpecificationCode";
    public static final String CST_INCOMING = "Incoming";
    public static final String CST_MSISDN = "MSISDN";
    public static final String CST_SIM = "SIM";
    public static final String CST_PORTABILITY = "portability";
    public static final String CST_QUANTITY = "quantity";
    public static final String CST_PORTED_IN_STATUS = "i";
    public static final String CST_PORTED_OUT_STATUS = "o";
    public static final String CST_FAILED_STATUS = "l";
    public static final String CST_FREE_STATUS = "f";
    public static final String CST_RESERVED_STATUS = "r";
    public static final String CST_SNAPPED_BACK_OUT_STATUS = "s";
    public static final String CST_SNAPPED_BACK_OUT_RESERVED_STATUS = "t";


    // Mapping constants
    public static final String MAP_REQ_PHONE_NUMER = "MAP_REQ_PHONE_NUMER";
    public static final String MAP_RESP_PHONE_NUMBER = "MAP_RESP_PHONE_NUMBER";

    /*
     * ----------------------------------------------------------------
     * ManageCustomerLoyaltyLoyaltyManagement
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_CUSTOMER_BONUSPOINT_READ = "CUSTOMER_BONUSPOINT.READ";
    public static final String CMD_CUSTOMER_BONUSPOINT_EARN = "CUSTOMER_BONUSPOINT.EARN";
    public static final String CMD_CUSTOMER_BONUSPOINT_BURN = "CUSTOMER_BONUSPOINT.BURN";
    public static final String CMD_CUSTOMER_BONUSPOINT_SEARCH = "CUSTOMER_BONUSPOINT.SEARCH";

    // SVLObject parameters
    public static final String P_BONUSPOINTS = "BONUSPOINTS";
    public static final String P_EXPIRATION_DATE = "EXPIRATION_DATE";
    public static final String P_SUM_BONUSPOINTS = "SUM_BONUSPOINTS";
    public static final String P_BONUSPOINT_LIST = "BONUSPOINT_LIST";
    public static final String P_BONUS_POINT = "BONUS_POINT";
    public static final String P_VERSION = "VERSION";
    public static final String P_ENTRY_SEQ = "ENTRY_SEQ";
    public static final String P_REC_VERSION = "REC_VERSION";
    public static final String P_BONUS_POINTS = "BONUS_POINTS";



    // Constants
    public static final String CST_LOYALTY_ACCOUNT_TYPE = "Non-Federated";
    public static final String CST_LOYALTY_ACCOUNT_CATEGORY = "Billing Account";
    public static final String CST_LOYALTY_BALANCE_UNIT_CODE = "points";
    public static final String CST_LOYALTY_BALANCE_UNIT_LABEL = "bonus points";
    public static final String CST_LOYALTY_TRANSACTION_ID = "0";
    public static final String CST_PAYMENT_ID = "0";
    public static final String CST_LOYALTY_ACCOUNT_ID = "Loyalty account ID";
    public static final String CST_PARTY = "Party";
    public static final String CST_PARTY_ID = "Party ID";
    public static final Long CST_LOYALTY_EARN_VERSION = 0L;
    public static final String CST_BILLSRV_ERROR = "BILLSRV.common_map_pub_key_0";
    public static final String CST_LOYALTY_BALANCE = "CST_LOYALTY_BALANCE";
    public static final String CST_BALANCE_BY_VALIDITY_PERIOD = "CST_BALANCE_BY_VALIDITY_PERIOD";
    public static final String CST_LOYALTY_EARN = "CST_LOYALTY_EARN";
    public static final String CST_LOYALTY_BURN = "CST_LOYALTY_BURN";
    public static final String CST_ACTION = "ACTION";
    public static final String CST_EARN = "E";
    public static final String CST_BURN = "B";



    // Mapping constants
    public static final String MAP_REQ_LAFAG = "MAP_REQ_LAFAG";
    public static final String MAP_RESP_LAFAG = "MAP_RESP_LAFAG";
    public static final String MAP_REQ_ELE = "MAP_REQ_ELE";
    public static final String MAP_RESP_ELE = "MAP_RESP_ELE";
    public static final String MAP_REQ_ELP = "MAP_REQ_ELP";
    public static final String MAP_RESP_ELP = "MAP_RESP_ELP";
    public static final String MAP_REQ_FILTER_CRITERIA_LAFAG = "MAP_REQ_FILTER_CRITERIA_LAFAG";
    public static final String MAP_RES_FILTER_CRITERIA_EARN_AND_BURN_LAFAG = "MAP_RES_FILTER_CRITERIA_EARN_AND_BURN_LAFAG";



    /* ----------------------------------------------------------------
     * Billing Account
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_BILLING_ACCOUNT_READ = "BILLING_ACCOUNT.READ";
    public static final String CMD_BILLING_ACCOUNT_SEARCH = "BILLING_ACCOUNT.SEARCH";
    public static final String CMD_BILLING_ACCOUNT_WRITE = "BILLING_ACCOUNT.WRITE";
    public static final String CMD_BOOKING_REQUEST_WRITE = "BOOKING_REQUEST.WRITE";
    public static final String CMD_BOOKING_REQUESTS_SEARCH = "BOOKING_REQUESTS.SEARCH";
    public static final String CMD_PAYMENT_ARRANGEMENTS_READ = "PAYMENT_ARRANGEMENTS.READ";
    public static final String CMD_PAYMENT_METHODS_READ = "PAYMENT_METHODS.READ";
    public static final String CMD_PAYMENT_METHOD_READ = "PAYMENT_METHOD.READ";
    public static final String CMD_CONTRACTS_SEARCH = "CONTRACTS.SEARCH";
    public static final String CMD_CUSTOMERS_SEARCH = "CUSTOMERS.SEARCH";
    public static final String CMD_PAYMENT_ARRANGEMENT_WRITE = "PAYMENT_ARRANGEMENT.WRITE";
    public static final String CMD_CUSTOMER_READ = "CUSTOMER.READ";
    public static final String CMD_CUSTOMER_NEW = "CUSTOMER.NEW";
    public static final String CMD_BILL_CYCLES_READ = "BILL_CYCLES.READ";
    public static final String CMD_BILL_MEDIUM_READ = "BILL_MEDIUM.READ";
    public static final String CMD_LANGUAGES_READ = "LANGUAGES.READ";
    public static final String CMD_CURRENCIES_READ = "CURRENCIES.READ";
    public static final String CMD_PAYMENT_TERMS_READ = "PAYMENT_TERMS.READ";
    public static final String CMD_IDENTIFICATION_DOCUMENT_TYPE_READ = "IDENTIFICATION_DOCUMENT_TYPE.READ";
    public static final String CMD_FINANCIAL_DOCUMENT_SEARCH = "FINANCIAL_DOCUMENT.SEARCH";
    public static final String CMD_FINANCIAL_DOCUMENT_READ = "FINANCIAL_DOCUMENT.READ";
    public static final String CMD_FINANCIAL_DOCUMENT_DETAIL_READ = "FINANCIAL_DOCUMENT_DETAIL.READ";



    // SVLObject parameters
    public static final String P_MODE = "MODE";
    public static final String P_CSP_PMNT_ID = "CSP_PMNT_ID";
    public static final String P_CSP_BANKNAME = "CSP_BANKNAME";
    public static final String P_CSP_ACCNO = "CSP_ACCNO";
    public static final String P_CSP_CCVALIDDT = "CSP_CCVALIDDT";
    public static final String P_CSP_ACCOWNER = "CSP_ACCOWNER";
    public static final String P_CRD_ID = "CRD_ID";
    public static final String P_FLAG_CURRENT = "FLAG_CURRENT";
    public static final String P_IV_ID= "IV_ID";
    public static final String P_PAYMENT_SEQNO = "PAYMENT_SEQNO";
    public static final String P_CSP_SEQNO = "CSP_SEQNO";
    public static final String P_CSP_PMNT_ID_PUB = "CSP_PMNT_ID_PUB";
    public static final String P_PAYMETH_ID = "PAYMETH_ID";
    public static final String P_PAYMETH_ID_PUB = "PAYMETH_ID_PUB";
    public static final String P_PAYMETH_DESC = "PAYMETH_DESC";
    public static final String P_STATUS = "STATUS";
    public static final String P_CSP_SWIFTCODE = "CSP_SWIFTCODE";
    public static final String P_LINKED_PUBLIC_DIRNUM = "LINKED_PUBLIC_DIRNUM";
    public static final String P_CS_CODE = "CS_CODE";
    public static final String P_ADR_COMPNO = "ADR_COMPNO";
    public static final String P_ADR_SOCIALSENO = "ADR_SOCIALSENO";
    public static final String P_ADR_DRIVELICENCE = "ADR_DRIVELICENCE";
    public static final String P_ADR_NAME = "ADR_NAME";
    public static final String P_ADR_FNAME = "ADR_FNAME";
    public static final String P_ADR_LNAME = "ADR_LNAME";
    public static final String P_ADR_SMSNO = "ADR_SMSNO";
    public static final String P_ADR_NATIONALITY = "ADR_NATIONALITY";
    public static final String P_ADR_NATIONALITY_PUB = "ADR_NATIONALITY_PUB";
    public static final String P_COUNTRY_ID_PUB = "COUNTRY_ID_PUB";
    public static final String P_MAS_CODE_PUB = "MAS_CODE_PUB";
    public static final String P_ADR_EMPLOYEE = "ADR_EMPLOYEE";
    public static final String P_ADR_FORWARD = "ADR_FORWARD";
    public static final String P_ADR_URGENT = "ADR_URGENT";
    public static final String P_ADR_TEMPBILL_OVERRIDDEN = "ADR_TEMPBILL_OVERRIDDEN";
    public static final String P_ADR_JUR_TAX_OVERRIDDEN = "ADR_JUR_TAX_OVERRIDDEN";
    public static final String P_ADR_PASSPORTNO = "ADR_PASSPORTNO";
    public static final String P_ADR_IDNO = "ADR_IDNO";
    public static final String P_ADR_TAXNO = "ADR_TAXNO";
    public static final String P_INVOICING_IND = "INVOICING_IND";

    public static final String P_LIST_OF_BILLING_ACCOUNT_VERSIONS = "LIST_OF_BILLING_ACCOUNT_VERSIONS";
    public static final String P_LIST_OF_BOOKING_REQUESTS ="bookingrequests";
    public static final String P_LIST_OF_USAGE_DATA_RECORDS ="OUTPUT";
    public static final String P_PAYMENTS_METHODS = "paymentmethods";
    public static final String P_PAYMENT_METHOD = "paymentmethod";
    public static final String P_CONTRACTS = "contracts";
    public static final String P_LIST_OF_PAYMENT_ARRANGEMENTS = "LIST_OF_PAYMENT_ARRANGEMENTS";
    public static final String P_FLAG_CASE = "FLAG_CASE";
    public static final String P_FLAG_MATCHCODE = "FLAG_MATCHCODE";
    public static final String P_LIST_OF_ALL_ADDRESSES = "LIST_OF_ALL_ADDRESSES";
    public static final String P_ADR_ROLES = "ADR_ROLES";
    public static final String P_CONTACT_SEQNO = "CONTACT_SEQNO";
    public static final String P_CONTACT_SEQNO_TEMP = "CONTACT_SEQNO_TEMP";
    public static final String P_ADR_SEQ = "ADR_SEQ";
    public static final String P_BILLING_ACCOUNT_NAME = "BILLING_ACCOUNT_NAME";
    public static final String P_GLCODE = "GLCODE";
    public static final String P_BILLING_ACCOUNT_VERSION = "BILLING_ACCOUNT_VERSION";
    public static final String P_CS_DUNNING = "CS_DUNNING";
    public static final String P_CS_BILLCYCLE = "CS_BILLCYCLE";
    public static final String P_LAST_BILLED_DATE = "LAST_BILLED_DATE";
    public static final String P_EVENT_CODE = "EVENT_CODE";
    public static final String P_REMARK = "REMARK";
    public static final String P_AMOUNT_REL = "AMOUNT_REL";
    public static final String P_PAYMETH_CODE = "PAYMETH_CODE";
    public static final String P_BILL_CYCLES = "bill cycles";
    public static final String P_BILLCYCLE = "BILLCYCLE";
    public static final String P_INTERVAL = "INTERVAL";
    public static final String P_INTERVAL_TYPE = "INTERVAL_TYPE";
    public static final String P_LENGTH = "LENGTH";
    public static final String P_BM_ID_PUB = "BM_ID_PUB";
    public static final String P_BM_ID = "BM_ID";
    public static final String P_BILL_MEDIA = "bill media";
    public static final String P_BM_DES = "BM_DES";
    public static final String P_ADR_TYPE = "ADR_TYPE";
    public static final String P_LNG_CODE = "LNG_CODE";
    public static final String P_LNG_DES = "LNG_DES";
    public static final String P_EXPECT_PAY_CURRENCY_ID_PUB = "EXPECT_PAY_CURRENCY_ID_PUB";
    public static final String P_FC_CODE = "FC_CODE";
    public static final String P_CURRENCIES = "currencies";
    public static final String P_TERMCODE = "TERMCODE";
    public static final String P_TERM_CODE = "TERM_CODE";
    public static final String P_PAYMENT_TERMS = "paymentterms";
    public static final String P_CHARGE_AMOUNT = "CHARGE_AMOUNT";

    public static final String P_TERM_NAME = "TERM_NAME";
    public static final String P_TERM_NET = "TERM_NET";
    public static final String P_CUSTCAT_CODE = "CUSTCAT_CODE";
    public static final String P_CS_FC_ID_PUB = "CS_FC_ID_PUB";
    public static final String P_CS_FC_ID = "CS_FC_ID";
    public static final String P_SEARCH_RESULT = "SEARCH_RESULT";
    public static final String P_SEQNO = "SEQNO";
    public static final String P_ADR_CUSTTYPE = "ADR_CUSTTYPE";
    public static final String P_CS_ID_HIGH_PUB = "CS_ID_HIGH_PUB";
    public static final String P_CS_ID_HIGH = "CS_ID_HIGH";
    public static final String P_PRG_CODE = "PRG_CODE";
    public static final String P_IDTYPE_CODE = "IDTYPE_CODE";
    public static final String P_PARTY_ROLE_TYPE = "PARTY_ROLE_TYPE";
    public static final String P_ADR_PHN1 = "ADR_PHN1";
    public static final String P_ADR_PHN2 = "ADR_PHN2";
    public static final String P_ADR_FAX = "ADR_FAX";
    public static final String P_ADR_PHN1_AREA = "ADR_PHN1_AREA";
    public static final String P_ADR_PHN2_AREA = "ADR_PHN2_AREA";
    public static final String P_ADR_FAX_AREA = "ADR_FAX_AREA";
    public static final String P_LIST_OF_IDENTIFICATION_DOCUMENT_TYPE = "LIST_OF_IDENTIFICATION_DOCUMENT_TYPE";
    public static final String P_IDTYPE_NAME = "IDTYPE_NAME";
    public static final String P_CSP_CCAG_CODE_PUB = "CSP_CCAG_CODE_PUB";
    public static final String P_CREATE = "CREATE";
    public static final String P_CSP_ACT_USED = "CSP_ACT_USED";
    public static final String P_ORDR_VALID_FROM = "ORDR_VALID_FROM";
    public static final String P_FEE_CLASS = "FEE_CLASS";
    public static final String P_RECORD_ID ="RECORD_ID";
    public static final String P_RECORD_SUB_ID = "RECORD_SUB_ID" ;
    public static final String P_BASE_PART_ID ="BASE_PART_ID";
    public static final String P_CHARGE_PART_ID = "CHARGE_PART_ID";
    public static final String P_RERATE_SEQNO = "RERATE_SEQNO";
    public static final String P_CALL_DATE ="CALL_DATE";
    public static final String P_ACTION_CODE ="ACTION_CODE" ;
    public static final String P_FEE_TYPE ="FEE_TYPE";
    public static final String P_START_VALID_FROM = "START_VALID_FROM";
    public static final String P_END_VALID_FROM="END_VALID_FROM";
    public static final String P_VAS_CODE="VAS_CODE";


    // constants
    public static final String CST_CASH_CARD = "CashCardDebitPMSpec";
    public static final String CST_CARD_NUMBER = "cardNumber";
    public static final String CST_CARD_TYPE_PREPAID = "Prepaid";
    public static final String CST_CASH_CARD_CODE = "R";
    public static final String CST_DIRECT_DEBIT = "BankAccountDebitPMSpec";
    public static final String CST_DIRECT_DEBIT_CODE = "D";
    public static final String CST_IBAN = "internationalBankAccountNumber";
    public static final int CST_IBAN_MAX_LENGTH = 25;
    public static final int CST_IBAN_MIN_LENGTH = 12;

    public static final Character CST_INVOICE_INVOICING = 'I';
    public static final Character CST_INVOICE_RECONCILIATION = 'R';
    public static final Character CST_INVOICE_CREDIT_MEMO = 'C';

    public static final String CST_BANK_TRANSFER = "BankTransferPMSpec";
    public static final String CST_BANK_TRANSFER_CODE = "T";
    public static final String CST_CASH_PM = "CashPMSpec";
    public static final String CST_CASH_PM_CODE = "P";
    public static final String CST_MSISDN_MIN = "msisdn";
    public static final String CST_ICC_NUMBER = "icc number";

    public static final String CST_CHEQUE_PM = "ChequePMSpec";
    public static final String CST_OTHER_PM = "OtherPMSpec";
    public static final String CST_UNKNOWN_PM_CODE = "UNKNOWN";

    public static final String CST_NATIONAL_ID = "nationalId";
    public static final String CST_NATIONAL_TYPE = "nationalType";
    public static final String CST_BANK_ACCOUNT = "bank account";
    public static final String CST_NOT_APPLICABLE = "not applicable";
    public static final String CST_PAYMENT_MEANS_SPEC_TYPE = "payment means spec type";

    public static final char CST_BILL_ADRESS_ROLE 			= 'B';
    public static final char CST_PREVIOUS_BILL_ADRESS_ROLE 	= 'R';
    public static final char CST_TEMPORARY_BILL_ADRESS_ROLE = 'E';
    public static final char CST_DETAILED_BILL_ADRESS_ROLE 	= 'I';
    public static final char CST_CONTRACT_ADRESS_ROLE 		= 'C';
    public static final char CST_MAGAZINE_ADRESS_ROLE 		= 'P';
    public static final char CST_DIRECTORY_ADRESS_ROLE 		= 'D';
    public static final char CST_SHIPMENT_ADRESS_ROLE 		= 'S';
    public static final int CST_SWIFT_CODE_BEGINNING = 2;
    public static final int CST_SWIFT_CODE_END = 12;
    public static final char CST_USER_ADRESS_ROLE 			= 'U';

    public static final char CST_ONLY_LATEST_VERSION_MODE	= 'L';
    public static final char CST_ALL_VERSION_MODE			= 'A';

    public static final char CST_ONLY_ACTIVE_BA_MODE		= 'U';
    public static final char CST_ALL_BA_MODE				= 'O';

    public static final char CST_ACTIVE_STATUS				= 'A';
    public static final char CST_INACTIVE_STATUS			= 'I';
    public static final char CST_ACTIVE_STATUS_MIN			= 'a';
    public static final char CST_INACTIVE_STATUS_MIN		= 'i';
    public static final char CST_DEACTIVE_STATUS_MIN		= 'd';
    public static final char CST_CLOSED_STATUS				= 'C';
    public static final String CST_GDM_ACTIVE_STATUS		= "active";
    public static final String CST_GDM_INACTIVE_STATUS		= "inactive";
    public static final String CST_GDM_CLOSED_STATUS		= "closed";

    public static final char CST_CUSTOMER_TYPE_CONSUMER 	= 'C';
    public static final char CST_CUSTOMER_TYPE_BUSINESS 	= 'B';

    public static final String CST_BILLING_ADR_ROLE_RECEIVER= "BillReceiver";
    public static final String CST_BILLING_ADR_ROLE_PAYER	= "Payer";

    public static final String CST_PERSON = "person";
    public static final String CST_ORGANISATION = "organisation";
    public static final String CST_SOCIAL_SECURITY_NUMBER = "SecuritySocialCard";
    public static final String CST_DRIVING_LICENSE_NUMBER = "DrivingLicence";
    public static final String CST_COMPANY_NUMBER = "NationalOrganisationIdentifier";
    public static final String CST_MAINNUMBER = "MainNumber";
    public static final String CST_SECONDARYNUMBER = "SecondaryNumber";
    public static final String CST_FAX = "Fax";
    public static final String P_ADJUSTMENT_AMOUNT = "AdjustmentAmount";
    public static final String P_ADJUSTMENT_PERCENTAGE = "AdjustmentPercentage";
    public static final String CST_APPLIED_BILL_ADJUSTMENT = "AppliedBillAdjustment";
    public static final String CST_APPLIED_CHARGE_ADJUSTMENT = "AppliedChargeAdjustment";
    public static final String P_APPLIED_CHARGE_ADJUSTMENT_DATE = "AppliedChargeAdjustmentDate";
    public static final String CST_APPLIED_PRODUCT_RATED_CHARGE = "AppliedProuctRatedCharge";
    public static final String CST_TAX_INCLUDED_BILL_AJUSTMENT =" taxIncludedBillAdjAmount";
    public static final String CST_DUTY_FREE_BILL_ADJUSTMENT_AMOUNT="dutyFreeBillAdjustmentAmount";
    public static final String CST_BILL_ADJUSTMENT_PERCENTAGE="billAdjustmentPercentage";
    public static final String P_BILL_ADJUSTMENT_DATE = "BillAdjustmentDate";
    public static final String P_LOCAL_CRD_ID = "local_cdrID";
    public static final String P_REASON_CODE = "reasonCode";
    public static final String P_REASON_LABEL =" reasonLabel";
    public static final String P_INVOICE_ID = "INVOICE_ID";
    public static final String P_CREDIT_TYPE = "CREDIT_TYPE";

    // param for bl internal process
    public static final String BL_PAYMENT_MEAN_VIEW = "BL_PAYMENT_MEAN_VIEW";
    public static final String BL_PAYMENT_TERM_VIEW = "BL_PAYMENT_TERM_VIEW";
    public static final String BL_BILL_DATE_VIEW = "BL_BILL_DATE_VIEW";
    public static final String BL_CURRENCY_VIEW = "BL_CURRENCY_VIEW";
    public static final String BL_INVOICE_FORMAT_SPEC_VIEW = "BL_P_INVOICE_FORMAT_SPEC_VIEW";
    public static final String BL_RETURNED_RECORDS_NUMBER = "BL_RETURNED_RECORDS_NUMBER";
    public static final String BL_IS_LAST_RECORD_RETURNED = "BL_IS_LAST_RECORD_RETURNED";
    public static final String BL_TOTALS_RECORDS = "BL_TOTALS_RECORDS";
    public static final String BL_NATIONAL_ID = "BL_NATIONAL_ID";
    public static final String BL_NATIONAL_ID_TYPE = "BL_NATIONAL_ID_TYPE";
    public static final String BL_STATUS_FILTER = "BL_STATUS_FILTER";
    public static final String BL_USER_CO_ID_PUB_LIST = "BL_USER_CO_ID_PUB_LIST";
    public static final String BL_USER_CO_ID_PUB = "BL_USER_CO_ID_PUB";
    public static final String BL_HOLDER_CO_ID_PUB_LIST = "BL_HOLDER_CO_ID_PUB_LIST";
    public static final String BL_HOLDER_CO_ID_PUB = "BL_HOLDER_CO_ID_PUB";

    // mapping constants
    public static final String MAP_REQ_APPLIED_CHARGE_ADJUSTMENT = "MAP_REQ_APPLIED_CHARGE_ADJUSTMENT";
    public static final String MAP_REQ_APPLIED_BILL_ADJUSTMENT = "MAP_REQ_APPLIED_BILL_ADJUSTMENT" ;
    public static final String MAP_REQ_BILLING_ACCOUNT_TO_READ = "MAP_REQ_BILLING_ACCOUNT_TO_READ";
    public static final String MAP_REQ_CASH_CARDS = "MAP_REQ_CASH_CARDS";
    public static final String MAP_REQ_DIRECT_DEBIT = "MAP_REQ_DIRECT_DEBIT";
    public static final String MAP_REQ_BANK_TRANSFERT = "MAP_REQ_BANK_TRANSFERT";
    public static final String MAP_REQ_CASH_PM = "MAP_REQ_CASH_PM";
    public static final String MAP_RESP_UPDATE = "MAP_RESP_UPDATE";
    public static final String MAP_REQ_FAG_BY_BILLING_ACCOUNT_ID = "MAP_REQ_FAG_BY_BILLING_ACCOUNT_ID";
    public static final String MAP_REQ_FAG_BY_BILLING_ACCOUNT_CODE = "MAP_REQ_FAG_BY_BILLING_ACCOUNT_CODE";
    public static final String MAP_REQ_FAG_BY_CONTRACT = "MAP_REQ_FAG_BY_CONTRACT";
    public static final String MAP_REQ_FAG_BY_CUSTOMER = "MAP_REQ_FAG_BY_CUSTOMER";
    public static final String MAP_REQ_FAG_BY_PARTY = "MAP_REQ_FAG_BY_PARTY";
    public static final String MAP_REQ_FAG_STATUS_FILTER = "MAP_REQ_FAG_STATUS_FILTER";
    public static final String MAP_REQ_FAG_RETURN_VIEW = "MAP_REQ_FAG_RETURN_VIEW";

    public static final String MAP_RESP_CREDIT_RESULTS_VIEW ="MAP_RESP_CREDIT_RESULTS_VIEW";
    public static final String MAP_RESP_FAG_VIEW = "MAP_RESP_FAG_VIEW";
    public static final String MAP_RESP_FAG_RESULTS_VIEW = "MAP_RESP_FAG_RESULTS_VIEW";
    public static final String MAP_RESP_FAG_BILLINGACCOUNT_VIEW = "MAP_RESP_FAG_BILLINGACCOUNT_VIEW";
    public static final String MAP_RESP_FAG_BASIC_VIEW = "MAP_RESP_FAG_BASIC_VIEW";
    public static final String MAP_RESP_FAG_STATUS_VIEW = "MAP_RESP_FAG_STATUS_VIEW";
    public static final String MAP_RESP_FAG_CUSTOMER_VIEW = "MAP_RESP_FAG_CUSTOMER_VIEW";
    public static final String MAP_RESP_FAG_TAX_PROFILE_VIEW = "MAP_RESP_FAG_TAX_PROFILE_VIEW";
    public static final String MAP_RESP_FAG_PARTY_VIEW = "MAP_RESP_FAG_PARTY_VIEW";
    public static final String MAP_RESP_FAG_PARTY_ROLE_VIEW = "MAP_RESP_FAG_PARTY_ROLE_VIEW";
    public static final String MAP_RESP_FAG_PAYMENT_MEAN_VIEW = "MAP_RESP_FAG_PAYMENT_MEAN_VIEW";
    public static final String MAP_RESP_FAG_PAYMENT_TERM_VIEW = "MAP_RESP_FAG_PAYMENT_TERM_VIEW";
    public static final String MAP_RESP_FAG_BILL_DATE_VIEW = "MAP_RESP_FAG_BILL_DATE_VIEW";
    public static final String MAP_RESP_FAG_CURRENCY_VIEW = "MAP_RESP_FAG_CURRENCY_VIEW";
    public static final String MAP_RESP_FAG_INVOICE_FORMAT_SPEC_VIEW = "MAP_RESP_FAG_INVOICE_FORMAT_SPEC_VIEW";
    public static final String MAP_RESP_BILLING_CYCLE_SPECIFICATION = "MAP_RESP_BILLING_CYCLE_SPECIFICATION";
    public static final String MAP_RESP_PARTY_IDENTIFICATION_VIEW = "MAP_RESP_PARTY_IDENTIFICATION_VIEW";



    /*
     * ----------------------------------------------------------------
     * ManageCommercialInstalledBaseInstalledOfferProductManagement
     * ----------------------------------------------------------------
     */

    //*** SOI commands

    // (CMS/BILLSRV)
    public static final String CMD_BILLING_ACCOUNT_ASSIGNMENT_READ = "BILLING_ACCOUNT_ASSIGNMENT.READ";
    public static final String CMD_CONTRACT_DEVICE_ADD = "CONTRACT_DEVICE.ADD";
    public static final String CMD_CONTRACT_DEVICES_READ = "CONTRACT_DEVICES.READ";
    public static final String CMD_CONTRACT_EXPIRATION_DATE_WRITE = "CONTRACT_EXPIRATION_DATE.WRITE";
    public static final String CMD_CONTRACT_INFO_READ = "CONTRACT_INFO.READ";
    public static final String CMD_CONTRACT_INFO_WRITE = "CONTRACT_INFO.WRITE";
    public static final String CMD_CONTRACT_HISTORY_READ = "CONTRACT_HISTORY.READ";
    public static final String CMD_CONTRACT_RESOURCES_REPLACE = "CONTRACT_RESOURCES.REPLACE";
    public static final String CMD_CONTRACT_SERVICES_ADD = "CONTRACT_SERVICES.ADD";
    public static final String CMD_CONTRACT_SERVICES_READ = "CONTRACT_SERVICES.READ";
    public static final String CMD_CONTRACT_SERVICES_WRITE = "CONTRACT_SERVICES.WRITE";
    public static final String CMD_CONTRACT_SERVICE_PARAMETERS_READ = "CONTRACT_SERVICE_PARAMETERS.READ";
    public static final String CMD_CONTRACT_SERVICE_PARAMETERS__HISTORY_READ = "CONTRACT_SERVICE_PARAMETERS_HISTORY.READ";
    public static final String CMD_CONTRACT_SERVICE_PARAMETERS_WRITE = "CONTRACT_SERVICE_PARAMETERS.WRITE";
    public static final String CMD_CONTRACT_SERVICE_RESOURCES_WRITE = "CONTRACT_SERVICE_RESOURCES.WRITE";
    public static final String CMD_CONTRACT_NEW = "CONTRACT.NEW";
    public static final String CMD_CUG_AGREEMENT_WRITE = "CUG_AGREEMENT.WRITE";
    public static final String CMD_CONTRACT_WRITE = "CONTRACT.WRITE";
    public static final String CMD_CONTRACT_CANCEL = "CONTRACT.CANCEL";
    public static final String CMD_FREE_UNIT_PACKAGES_READ = "FREE_UNIT_PACKAGES.READ";
    public static final String CMD_FREE_UNIT_PACKAGE_ELEMENTS_READ = "FREE_UNIT_PACKAGE_ELEMENTS.READ";
    public static final String CMD_GENERIC_DIRECTORY_NUMBER_READ = "GENERIC_DIRECTORY_NUMBER.READ";
    public static final String CMD_GENERIC_DIRECTORY_NUMBER_SEARCH = "GENERIC_DIRECTORY_NUMBER.SEARCH";
    public static final String CMD_INSTALLATION_ADDRESS_READ = "INSTALLATION_ADDRESS.READ";
    public static final String CMD_PARAMETER_READ = "PARAMETER.READ";
    public static final String CMD_PORT_HISTORY_READ = "PORT_HISTORY.READ";

    public static final String CMD_PRODUCT_CHANGE = "PRODUCT.CHANGE";
    public static final String CMD_PRODUCT_CHANGE_SERVICES_READ = "PRODUCT_CHANGE_SERVICES.READ";

    public static final String CMD_RATEPLANS_READ = "RATEPLANS.READ";
    public static final String CMD_REASONS_READ = "REASONS.READ";
    public static final String CMD_SERVICE_PACKAGES_READ = "SERVICE_PACKAGES.READ";
    public static final String CMD_SERVICE_PARAMETERS_READ = "SERVICE_PARAMETERS.READ";
    public static final String CMD_ALLOWED_SERVICES_READ = "ALLOWED_SERVICES.READ";
    public static final String CMD_ALLOWED_RATEPLANS_READ = "ALLOWED_RATEPLANS.READ";

    public static final String CMD_SERVICES_READ = "SERVICES.READ";
    public static final String CMD_STORAGE_MEDIUM_SEARCH = "STORAGE_MEDIUM.SEARCH";
    public static final String CMD_SUB_MARKETS_READ = "SUB_MARKETS.READ";

    public static final String CMD_USAGE_TYPE_READ = "USAGE_TYPE.READ";
    public static final String CMD_CONTRACT_RATEPLAN_HISTORY_READ = "CONTRACT_RATEPLAN_HISTORY.READ";

    //*** MYSOI commands
    public static final String CMD_CONTRACT_FREE_UNITS_READ = "CONTRACT_FREE_UNITS.READ";
    public static final String CMD_SERVICE_CREATION_DATE_READ = "SERVICE_CREATION_DATE.READ";

    // SOI parameters
    public static final String P_ALL_STORAGE_MEDIUMS= "ALL STORAGEMEDIUMs";

    public static final String P_AUTO_ASSIGN_CORE_SERV = "AUTO_ASSIGN_CORE_SERV";
    public static final String P_CO_ADD_MONTHS = "CO_ADD_MONTHS";
    public static final String P_CO_ARCHIVE  = "CO_ARCHIVE";
    public static final String P_ADVANCE_ACCESS_OVW = "ADVANCE_ACCESS_OVW";
    public static final String P_ADVANCE_ACCESS_OVW_PRD = "ADVANCE_ACCESS_OVW_PRD";
    public static final String P_ADVANCE_ACCESS_OVW_TYPE = "ADVANCE_ACCESS_OVW_TYPE";
    public static final String P_ALL = "ALL";
    public static final String P_ASSIGNMENT_SEQNO = "ASSIGNMENT_SEQNO";
    public static final String P_ASSIGNMENT_TYPE = "ASSIGNMENT_TYPE";
    public static final String P_BILLING_ACCOUNT_CODE = "BILLING_ACCOUNT_CODE";
    public static final String P_BILL_NUMBER = "BILL_NUMBER";
    public static final String P_CALC_ACC = "CALC_ACC";
    public static final String P_CALC_SUB = "CALC_SUB";
    public static final String P_CHARGE_TYPE_ID = "CHARGE_TYPE_ID";
    public static final String P_CHARGE_AMOUNT_OVW = "CHARGE_AMOUNT_OVW";
    public static final String P_CO_ACTIVATED = "CO_ACTIVATED";
    public static final String P_CO_ENTDATE = "CO_ENTDATE";
    public static final String P_CO_EXPIR_DATE = "CO_EXPIR_DATE";
    public static final String P_CO_LAST_STATUS_CHANGE_DATE = "CO_LAST_STATUS_CHANGE_DATE";
    public static final String P_CO_MODDATE = "CO_MODDATE";
    public static final String P_CO_PENDING_DATE = "CO_PENDING_DATE";
    public static final String P_CO_SIGNED_DATE = "CO_SIGNED_DATE";
    public static final String P_CO_STMEDNO = "CO_STMEDNO";
    public static final String P_COMPLEX_LEVEL = "COMPLEX_LEVEL";
    public static final String P_COMPLEX_NO = "COMPLEX_NO";
    public static final String P_COS_ACTION = "COS_ACTION";
    public static final String P_COS_ACTIVATION_DATE = "COS_ACTIVATION_DATE";
    public static final String P_COS_LAST_ACT_DATE = "COS_LAST_ACT_DATE";
    public static final String P_COS_PENDING_STATUS = "COS_PENDING_STATUS";
    public static final String P_COS_PENDING_STATUS_DATE = "COS_PENDING_STATUS_DATE";
    public static final String P_COS_STATUS = "COS_STATUS";
    public static final String P_COS_STATUS_DATE = "COS_STATUS_DATE";
    // Service status on Contract commands (CONTRACT.READ/SEARCH)
    public static final String P_CO_SN_PENDING_STATUS = "CO_SN_PENDING_STATUS";

    public static final String P_CONTRACT_HISTORY = "contract_history";
    public static final String P_CONTRACT_REASON = "CONTRACT_REASON";
    public static final String P_CONTRACT_VALID_FROM = "CONTRACT_VALID_FROM";
    public static final String P_CS_CONTRESP = "CS_CONTRESP";


    public static final String P_DATA_TYPE = "DATA_TYPE";
    public static final String P_DEALER_ID_PUB = "DEALER_ID_PUB";
    public static final String P_DIRECTORY_NUMBERS = "directory numbers";
    public static final String P_DIRECTORYNUMBERS = "DirectoryNumbers";
    public static final String P_ENTRY_DATE = "ENTRY_DATE";
    public static final String P_GET_VALUES = "GET_VALUES";
    public static final String P_FREE_UNITS = "free_units";
    public static final String P_FU_ACC_KEY = "FU_ACC_KEY";
    public static final String P_FU_ELM_SEQ = "FU_ELM_SEQ";
    public static final String P_FU_ELM_VER = "FU_ELM_VER";
    public static final String P_FU_PACK_CODE = "FU_PACK_CODE";
    public static final String P_FU_PACK_VER = "FU_PACK_VER";
    public static final String P_FU_PKELSQ = "FU_PKELSQ";
    public static final String P_FU_PKVER = "FU_PKVER";
    public static final String P_FU_NUM = "P_FU_NUM";
    public static final String P_FU_TYPE = "FU_TYPE";
    public static final String P_FU_VER = "FU_VER";
    public static final String P_FUP_SEQ = "FUP_SEQ";
    public static final String P_FUP_UOM_CODE = "FUP_UOM_CODE";
    public static final String P_FUP_VOLUME = "FUP_VOLUME";
    public static final String P_HLCODE = "HLCODE";
    public static final String P_HLCODE_PUB = "HLCODE_PUB";
    public static final String P_INSTALLATION_ADDRESSES = "installation addresses";
    public static final String P_LIST_1 = "LIST_1";
    public static final String P_LIST_2 = "LIST_2";
    public static final String P_LIST_SP_CODE = "LIST_SP_CODE";
    public static final String P_LIST_SN_CODE = "LIST_SN_CODE";
    public static final String P_LIST_OF_USAGE_TYPES = "LIST_OF_USAGE_TYPES";
    public static final String P_MARKETS = "markets";
    public static final String P_MULT_VALUE_IND = "MULT_VALUE_IND";
    public static final String P_MULT_VALUES = "MULT_VALUES";
    public static final String P_NEW_RPCODE = "NEW_RPCODE";
    public static final String P_NEW_RPCODE_PUB = "NEW_RPCODE_PUB";
    public static final String P_N_VALUES = "N_VALUES";
    public static final String P_NUM_PARAM = "NUM_PARAM";
    public static final String P_NUM_PARAMS = "NUM_PARAMS";
    public static final String P_NUM_PERIODS = "NUM_PERIODS";
    public static final String P_NUM_RP = "NUM_RP";
    public static final String P_NUM_SERV = "NUM_SERV";
    public static final String P_NUM_SP = "NUM_SP";
    public static final String P_NUM_SV = "NUM_SV";
    public static final String P_OTHER_CHARGE_AND_CREDIT = "OTHER_CHARGE_AND_CREDIT";
    public static final String P_PARAM_VALUES = "PARAM_VALUES";
    public static final String P_PARENT_NO = "PARENT_NO";
    public static final String P_PIN = "PIN";
    public static final String P_PIN2 = "PIN_2";
    public static final String P_PLCODE = "PLCODE";
    public static final String P_PLCODE_PUB = "PLCODE_PUB";
    public static final String P_NTWK_CODE = "NTWK_CODE";
    public static final String P_PORT_NUM = "PORT_NUM";
    public static final String PORT_NP_PUB = "PORT_NP_PUB";
    public static final String P_PORTNUM = "PORTNUM";
    public static final String P_PORT_STATUS = "PORT_STATUS";
    public static final String P_PORTS = "ports";
    public static final String P_PRIMARY_FLAG = "PRIMARY_FLAG";
    public static final String P_PRM_DES = "PRM_DES";
    public static final String P_PRM_ID = "PRM_ID";
    public static final String P_PRM_ID_PUB = "PRM_ID_PUB";
    public static final String P_PRM_NO = "PRM_NO";
    public static final String P_PUK = "PUK";
    public static final String P_PUK2 = "PUK_2";
    public static final String P_REASON = "REASON";
    public static final String P_REASONS = "reasons";
    public static final String P_RESULT_LIST = "RESULT_LIST";
    public static final String P_RETURNED_PORTS = "Returned PORTs";
    public static final String P_RPCODE_NEW_PUB = "RPCODE_NEW_PUB";
    public static final String P_RP_VSCODE = "RP_VSCODE";
    public static final String P_RS_CODE = "RS_CODE";
    public static final String P_RS_DES = "RS_DES";
    public static final String P_SEARCHER = "SEARCHER";
    public static final String P_SERVICES = "services";
    public static final String P_SIBLING_NO = "SIBLING_NO";
    public static final String P_SM_MODDATE = "SM_MODDATE";
    public static final String P_SPCODE_NEW_PUB = "SPCODE_NEW_PUB";
    public static final String P_SPCODE_OLD_PUB = "SPCODE_OLD_PUB";
    public static final String P_SRCH_COUNT = "SRCH_COUNT";
    public static final String P_SM_ID = "SM_ID";
    public static final String P_SMC_ID = "SMC_ID";
    public static final String P_SM_NUM = "SM_NUM";
    public static final String P_SM_STATUS = "SM_STATUS";
    public static final String P_SUBM_ID = "SUBM_ID";
    public static final String P_SUBM_ID_PUB = "SUBM_ID_PUB";
    public static final String P_MAIN_DIRNUM = "MAIN_DIRNUM";
    public static final String P_SUBM_DIRNUM_NPCODE  = "SUBM_DIRNUM_NPCODE";
    public static final String P_SP_DES = "SP_DES";
    public static final String P_SP_SHDES = "SP_SHDES";
    public static final String P_SRV_SUBTYPE = "SRV_SUBTYPE";
    public static final String P_SV_TYPE = "SV_TYPE";
    public static final String P_TARGET_PARAM_VALUES = "TARGET_PARAM_VALUES";
    public static final String P_TARGET_SIBLING_NO = "TARGET_SIBLING_NO";
    public static final String P_USAGE_TYPE_DES = "USAGE_TYPE_DES";
    public static final String P_USAGE_TYPE_SHNAME = "USAGE_TYPE_SHNAME";
    public static final String P_USAGE_TYPE_ID = "USAGE_TYPE_ID";
    public static final String P_VALUE_PUB = "VALUE_PUB";
    public static final String P_VALUE_SEQNO = "VALUE_SEQNO";
    public static final String P_CONTRACTED_COMPOSITE_PRODUCT_HISTORY = "contracted composite product history";
    public static final String P_SEQUENCE_OF_SERVICES_AND_SERVICE_PACKAGES = "Sequence of services and service packages";
    public static final String P_RP_VALID_FROM = "RP_VALID_FROM";

    // API parameters
    public static final String P_AMOUNT = "AMOUNT";
    public static final String P_AMOUNT_DISCOUNT = "AMOUNT_DISCOUNT";
    public static final String P_APPLIED_CHARGE_TYPE = "APPLIED_CHARGE_TYPE";
    public static final String P_ATOMIC_INSTALLED_OFFER_TYPE = "ATOMIC_INSTALLED_OFFER_TYPE";
    public static final String P_BILL_CONTRACT_PRIVATE_ID = "P_BILL_CONTRACT_PRIVATE_ID";
    public static final String P_BILL_SERVICE_ID = "BILL_SERVICE_ID";
    public static final String P_BILLING_ACCOUNT_ASSIGNMENT_LIST = "BILLING_ACCOUNT_ASSIGNMENT_LIST";
    public static final String P_BU_UNIT_ID = "BU_UNIT_ID";
    public static final String P_COMMERCIAL_OPERATION_TYPE = "COMMERCIAL_OPERATION_TYPE";
    public static final String P_CONTRACT_FREE_UNITS_LIST = "CONTRACT_FREE_UNITS_LIST";
    public static final String P_CONTRACT_STATUS = "CONTRACT_STATUS";
    public static final String P_CUSTOMER_ID = "CUSTOMER_ID";
    public static final String P_DN_ID = "DN_ID";
    public static final String P_DURATION_CHOSEN = "DURATION_CHOSEN";
    public static final String P_DURATION_UNIT = "DURATION_UNIT";
    public static final String P_FREE_UNIT_LABEL = "FREE_UNIT_LABEL";
    public static final String P_FUNCTION_FREE_UNIT_LIST = "FUNCTION_FREE_UNIT_LIST";
    public static final String P_FUNCTIONAL_OPERATION_TYPE = "FUNCTIONAL_OPERATION_TYPE";
    public static final String P_FUNCTION_PARAM_MSISDN_LIST = "FUNCTION_PARAM_MSISDN_LIST";
    public static final String P_FUNCTION_SPEC_ACTION = "FUNCTION_SPECIFICATION_ACTION";
    public static final String P_FUNCTION_SPEC_CODE = "FUNCTION_SPECIFICATION_CODE";
    public static final String P_FUNCTION_SPEC_LIST = "FUNCTION_SPECIFICATION_LIST";
    public static final String P_FUNCTION_SPEC_LABEL = "FUNCTION_SPEC_LABEL";
    public static final String P_FUNCTION_VALUE = "FUNCTION_VALUE";
    public static final String P_FUNCTION_SIM_LIST= "FUNCTION_SIM_LIST";
    public static final String P_ICCID = "ICCID";
    public static final String P_INSTALLED_DISCOUNT_ID = "INSTALLED_DISCOUNT_ID";
    public static final String P_INSTALLED_OFFER_ENTRY_DATE = "INSTALLED_OFFER_ENTRY_DATE";
    public static final String P_INSTALLED_OFFER_ID = "INSTALLED_OFFER_ID";
    public static final String P_LOCAL_INSTALLED_OFFER_ID = "LOCAL_INSTALLED_OFFER_ID";
    public static final String P_INSTALLED_OFFER_REASON_STATUS_CHANGE = "INSTALLED_OFFER_REASON_STATUS_CHANGE";
    public static final String P_INSTALLED_OFFER_REASON_LABEL = "INSTALLED_OFFER_REASON_LABEL_STATUS_CHANGE";
    public static final String P_INSTALLED_OFFER_STATUS = "INSTALLED_OFFER_STATUS";
    public static final String P_INSTALLED_OFFER_STATUS_DATE = "INSTALLED_OFFER_STATUS_DATE";
    public static final String P_INSTALLED_OFFER_STATUS_DATE_TIME = "INSTALLED_OFFER_STATUS_DATE_TIME";
    public static final String P_INSTALLED_OFFER_TEMPORARY_ID = "INSTALLED_OFFER_TEMPORARY_ID";
    public static final String P_INSTALLED_OFFER_TYPE = "INSTALLED_OFFER_TYPE";

    public static final String P_INSTALLED_PRODUCT_ID = "INSTALLED_PRODUCT_ID";
    public static final String P_INSTALLED_PRODUCT_LIST = "INSTALLED_PRODUCT_LIST";
    public static final String P_INSTALLED_TARIFF_ID = "INSTALLED_TARIFF_ID";
    public static final String P_IS_COMPLETE_CUSTOMER = "IS_COMPLETE_CUSTOMER";
    public static final String P_IS_INSTALLED_PUBLIC_KEY = "IS_INSTALLED_PUBLIC_KEY";
    public static final String P_IS_NEW_INSTALLED_OFFER = "IS_NEW_INSTALLED_OFFER";
    public static final String P_IS_PRODUCT_VIEW = "IS_PRODUCT_VIEW";
    public static final String P_IS_STATUS_HISTORY_VIEW = "IS_STATUS_HISTORY_VIEW";
    public static final String P_LOCAL_CONTRACT_INFO_FIELD_VIEW = "P_LOCAL_CONTRACT_INFO_FIELD_VIEW";
    public static final String P_LOCAL_REMAINDER_TIME = "LOCAL_REMAINDER_TIME";
    public static final String P_LOCAL_REMAINDER_TIME_UNIT = "LOCAL_REMAINDER_TIME_UNIT";
    public static final String P_MARKET_ID = "MARKET_ID";
    public static final String P_OLD_DIRNUM = "OLD_DIRNUM";
    public static final String P_OLD_PORT_NUM = "OLD_PORT_NUM";
    public static final String P_OFFER_LEVEL = "OFFER_LEVEL";
    public static final String P_OPERATOR_PARTY_ROLE_ID = "OPERATOR_PARTY_ROLE_ID";
    public static final String P_OPERATOR_PARTY_ID = "OPERATOR_PARTY_ID";
    public static final String P_PARENT_INSTALLED_OFFER_ID = "PARENT_INSTALLED_OFFER_ID";
    public static final String P_PARENT_INSTALLED_OFFER_TEMPORARY_ID = "PARENT_INSTALLED_OFFER_TEMPORARY_ID";
    public static final String BL_PARTY_ID = "PARTY_ID";
    public static final String P_PARTY_LIST = "PARTY_LIST";
    public static final String P_PARTY_ROLE_ID = "PARTY_ROLE_ID";
    public static final String P_PART_ROLE_SHNAME = "PART_ROLE_SHNAME";
    public static final String P_PORT_ID = "PORT_ID";
    public static final String P_OFFER_SPECIFICATION_CODE = "OFFER_SPECIFICATION_CODE";
    public static final String P_OFFER_SPECIFICATION_TYPE = "OFFER_SPECIFICATION_TYPE";
    public static final String P_OFFER_SPECIFICATION_CATEGORY = "OFFER_SPECIFICATION_CATEGORY";
    public static final String P_OP_ASSISTED_ACTION = "OP_ASSISTED_ACTION";
    public static final String P_PRODUCT_EXPL_FRRE_UNIT_LIST = "PRODUCT_EXPL_FRRE_UNIT_LIST";
    public static final String P_PRODUCT_IMPL_FRRE_UNIT_LIST = "PRODUCT_IMPL_FRRE_UNIT_LIST";
    public static final String P_PRODUCT_PARAM_MSISDN = "PRODUCT_PARAM_MSISDN";
    public static final String P_PRODUCT_SPEC_CODE = "PRODUCT_SPEC_CODE";
    public static final String P_PRODUCT_SPEC_LABEL = "PRODUCT_SPEC_LABEL";
    public static final String P_PRODUCT_SPEC_TYPE = "PRODUCT_SPEC_TYPE";
    public static final String P_RATEPLAN_ID = "RATEPLAN_ID";
    public static final String P_RES_ID = "RES_ID";
    public static final String P_RES_TYPE = "RES_TYPE";
    public static final String P_OLD_RES_NUM = "OLD_RES_NUM";
    public static final String P_SRCH_MAX = "SRCH_MAX";
    public static final String P_STMEDNO = "STMEDNO";
    public static final String P_SUB_MARKETS = "sub_markets";
    public static final String P_RESERVATION = "RESERVATION";
    public static final String P_RES_NUM = "RES_NUM";


    public static final String P_TAILOR_MADE_AMOUNT = "TAILOR_MADE_AMOUNT";
    public static final String P_TAILOR_MADE_PERCENTAGE= "TAILOR_MADE_PERCENTAGE";
    public static final String P_TARIFF_SPEC_TYPE = "TARIFF_SPEC_TYPE";
    public static final String P_IS_LOCAL_OFFER_HISTORY_VIEW = "IS_LOCAL_OFFER_HISTORY_VIEW";


    //*** constants

    // numbers
    public static final String CST_0 = "0";
    public static final String CST_FEE_CLASS_FREE_UNIT = "5";
    public static final String CST_FEE_CLASS_CONTRACT_AND_CUSTOMER = "3";
    public static final String CST_LESS_1 = "-1";
    public static final String CST_MORE_1 = "1";
    public static final String CST_MORE_2 = "2";
    public static final String CST_MORE_3 = "3";
    public static final String CST_COS_PENDING_STATUS_ACTIVATION = "2";
    public static final String CST_COS_PENDING_STATUS_DEACTIVATION = "4";
    public static final String CST_PORTED_OUT_REASON = "14";

    // Resource type for Device / Sim card
    public static final String CST_RES_TYPE_DEVICE = "1";
    // Resource type for Directory Number
    public static final String CST_RES_TYPE = "2";

    public static final String CST_STATUS_ONHOLD = "1";
    public static final String CST_STATUS_ACTIVATION = "2";
    public static final String CST_STATUS_SUSPENSION = "3";
    public static final String CST_STATUS_DEACTIVATION = "4";
    public static final String CST_USER_REASON = "3";
    public static final String CST_ACTIVATION_REASON = "1";



    // characters
    public static final String CST_ADD_SERVICE_ACTION = "a";
    public static final String CST_ATOMIC_TYPE = "A";
    public static final String CST_ABSOLUTE_TYPE = "A";
    public static final String CST_ADDRESS_TYPE_BY_DEFAULT = "B";
    public static final String CST_COFU_TYPE = "C";
    public static final String CST_COMPOSITE_TYPE = "C";
    public static final String CST_SET_TYPE = "S";
    public static final String CST_COST_CONTROL_TYPE = "B";
    public static final String CST_DEASSIGNED_NUMBER_STATUS = "d";
    public static final String CST_DEACTIVATION_OP_ASSISTED_ACTION  = "d";
    public static final String CST_IMPLICIT_FREE_UNITS = "I";
    public static final String CST_IMPLICIT_BILLING_ACCOUNT_TYPE = "I";
    public static final String CST_EXPLICIT_BILLING_ACCOUNT_TYPE = "E";
    public static final String CST_FEE_TYPE_RECURRING_FEE = "R";
    public static final String CST_FEE_TYPE_NON_RECURRING_FEE = "N";
    public static final String CST_MODIFY_SERVICE_ACTION = "m";
    public static final String P_INSERT_OCC_CODE = "I";
    public static final String CST_PERCENTAGE_TYPE = "R";
    public static final String CST_POFU_TYPE = "P";
    public static final String CST_POFUL_TYPE = "L";
    public static final String CST_REMOVE_SERVICE_ACTION = "d";
    public static final String CST_RESOURCE_RESERVED_STATUS = "r";




    // others
    public static final String CST_ACTIVATION_OPERATION = "add";
    public static final String CST_ADD_SERVICE_OPERATION = "add";
    public static final String CST_ADD_SERVICE_PARAMETERS = "add";
    public static final String CST_ADD_SERVICE_RESOURCES = "add";
    public static final String CST_ALL_SPEC = "all";
    public static final String CST_ALL_CHARGES = "all charges";
    public static final String CST_ATOMIC_IMPL_FUP = "ATOMIC_IMPL_FUP";
    public static final String CST_ATOMIC_SERVICE = "ATOMIC_SERVICE";
    public static final String CST_ATOMIC_SIM = "ATOMIC_SIM";
    public static final String CST_ATOMIC_SPEC = "atomic offer";
    public static final String CST_BU_ID_NOT_GOOD_SOI_CODE = "CMS.CommonDomain.WrongAccess";
    public static final String CST_BILLING_ACCOUNT_NOT_FOUND_SOI_CODE = "CMS.BillingAccount.NotFound";
    public static final String CST_BILL_CONTRACT = "BILL_CONTRACT";
    public static final String CST_BUSINESS_UNIT_ID_XML = "businessUnitId";
    public static final String CST_CHANGE_COMMITMENT_DURATION = "change";
    public static final String CST_CHANGE_SERVICE_PARAMETERS = "change";
    public static final String CST_CHANGE_SERVICE_RESOURCES = "change";
    public static final String CST_CHANGE_SERVICE_PACKAGE_OPERATION = "change";
    public static final String CST_CF = "CF";
    public static final String CST_COCC = "COCC";
    public static final String CST_COFU_SPEC = "fixed price package prod spec";
    public static final String CST_COMMERCIAL_OPERATION_CHARGE = "commercial operation charge";
    public static final String CST_COMMERCIAL_OPERATION_TARIFF_SPEC = "Commercial operation tariff spec";
    public static final String CST_COMMERCIAL_OPERATION_TYPE_XML = "commercialOperationType";
    public static final String CST_COMMITMENT_XML = "Commitment";
    public static final String CST_COMPOSITE_SPEC = "composite offer";
    public static final String CST_CONTRACT_SPEC = "contract";
    public static final String CST_CREATE_CONTRACT = "add";
    public static final String CST_COST_CONTROL_SPEC = "fixed price package prod spec";
    public static final String CST_DATE_TYPE = "DT";
    public static final String CST_DEACTIVATE_OPERATION = "deactive";
    public static final String CST_DEACTIVATED = "deactivated";
    public static final String CST_DEFAULT_SPEC = "usage product specification";
    public static final String CST_RESOURCE_SPEC = "goods product specification";
    public static final String CST_DURATION_CHOSEN_XML = "durationChosen";
    public static final String CST_FALSE = "false";
    public static final String CST_FUOCC = "FUOCC";
    public static final String CST_FUNCTION_VALUE_XML = "functionValue";
    public static final String CST_GENERIC_PRODUCT_CODE_XML = "genericProductCode";
    public static final String CST_GRANTED_VOLUME = "Granted volume";
    public static final String CST_GTEL = "GTEL";
    public static final String CST_ICC_NUMBER_RESOURCE = "icc number";
    public static final String CST_IMPL_FUP_CODE = "IMPL_FUP";
    public static final String CST_IMSI_NUMBER_RESOURCE = "imsi";
    public static final String CST_IN_ORDER = "in order";
    public static final String CST_INITIALISED = "initialised";
    public static final String CST_INSTALLATION_ADDRESS_NOT_FOUND = "CMS.Contract.UserInstallationAddress.NotAllowed";
    public static final String CST_INSTALLED_OFFER_ID_XML = "installedOfferID";
    public static final String CST_INSTALLED_OFFER_TEMPORARY_ID_XML = "installedOfferTemporaryID";
    public static final String CST_INSTALLED_OFFER_MODEL_LIST = "INSTALLED_OFFER_MODEL_LIST";
    public static final String CST_INSTALLED_OFFER_VIEW_LEVEL_XML = "installedOfferViewLevel";
    public static final String CST_FUNCTION_SPECIFICATION_ACTION_XML = "functionSpecificationAction";
    public static final String CST_INSTALLED_PRODUCT_CREATION = "creation";
    public static final String CST_INSTALLED_PRODUCT_FUNCTION_VALUE_MODIFICATION  = "function value modification";
    public static final String CST_IS_INSTALLED_PUBLIC_KEY_XML = "isInstalledPublicKey";
    public static final String CST_IS_PUBLIC_KEY_XML = "isPublicKey";
    public static final String CST_LOCAL_INSTALLED_OFFER_ID_XML = "localInstalledOfferID";
    public static final String CST_MAX_VALUE = "Max Value";
    public static final String CST_MIN_VALUE= "Min Value";
    public static final String CST_MF = "MF";
    public static final String CST_MONTHS = "months";
    public static final String CST_MSISDN_RESOURCE = "msisdn";
    public static final String CST_OCC_PARAMETER_BILL_NUMBER_LABEL = "Number of bill process";
    public static final String CST_OCC_PARAMETER_GLCODE_LABEL = "General Ledger Account";
    public static final String CST_OCC_PARAMETER_REMARK_LABEL = "Remark";
    public static final String CST_OCC_PARAMETER_SEQNO_LABEL = "Customer fee sequence number";
    public static final String CST_OCC_SERVICE_LABEL = "OCC service";
    public static final String CST_OCC_SERVICE_PACKAGE_CODE = "OS";
    public static final String CST_OCC_SERVICE_PACKAGE_LABEL = "OCC service package";
    public static final String CST_OFFER_CHARGE = "offer charge";
    public static final String CST_OFFER_SPEC_TYPE = "offerSpecificationType";
    public static final String CST_OPERATOR_XML = "Operator";
    public static final String CST_ORIGIN_CODE_XML = "originCode";
    public static final String CST_OTHER_CHARGE_AND_CREDIT_FOR_CONTRACT = "contract other charge and credit";
    public static final String CST_OTHER_CHARGE_AND_CREDIT_FOR_CUSTOMER = "customer other charge and credit";
    public static final String CST_OTHER_CHARGE_AND_CREDIT_FOR_FREE_UNITS = "free units other charge and credit";
    public static final String CST_OVW_PERIOD = "OVW_PERIOD";
    public static final String CST_PARAM_DATA_FIELD = "DF";
    public static final String CST_PARAM_LIST_BOX = "LB";
    public static final String CST_PARAM_LIST_DB = "DB";
    public static final String CST_PARENT_INSTALLED_OFFER_XML = "ParentInstalledOffer";
    public static final String CST_PARENT_INSTALLED_OFFER_ID_XML = "ParentInstalledOffer@installedOfferID";
    public static final String CST_PARENT_INSTALLED_OFFER_TEMPORARY_ID_XML = "ParentInstalledOffer@installedOfferTemporaryID";
    public static final String CST_PARTY_ID_XML = "partyID";
    public static final String CST_PIN_NUMBER_RESOURCE = "pin";
    public static final String CST_PIN2_NUMBER_RESOURCE = "pin2";
    public static final String CST_POFU_POFUL_SPEC = "shared fx price package prod spec";
    public static final String CST_PUK_NUMBER_RESOURCE = "puk";
    public static final String CST_PUK2_NUMBER_RESOURCE = "puk2";
    public static final String CST_REACTIVATE_OPERATION = "reconnect";
    public static final String CST_REASON_LABEL_EXPORT = "export";
    public static final String CST_RECURRING_TARIFF_SPEC = "Recurring tariff spec";
    public static final String CST_REMOVE_SERVICE_OPERATION = "remove";
    public static final String CST_REMOVED = "removed";
    public static final String CST_SEARCH_CONTRACTS_EVEN_WITHOUT_DN = "SearchContractsEvenWithoutDN";
    public static final String CST_SERVICES_ADDED = "Services added";
    public static final String CST_SERVICES_DROPPED = "Services dropped";
    public static final String CST_SERVICE_NOT_FOUND_SOI_CODE = "CMS.RC6608-004";
    public static final String CST_SERVICE_PACKAGE_NOT_FOUND_SOI_CODE = "CMS.RC6704-003";
    public static final String CST_SERVICE_PACKAGE_AND_SERVICE_NOT_FOUND_SOI_CODE = "CMS.RC6703-002";
    public static final String CST_SIM_CARD_CODE = "SIM_CARD";
    public static final String CST_SIM_CARD_LABEL = "SIM card";
    public static final String CST_SIM_SPEC = "goods product specification";
    public static final String CST_SPEC_LABEL_VALUE = "Occ Service";
    public static final String CST_STATUS_CODE_XML = "statusCode";
    public static final String CST_SUSPENDED = "suspended";
    public static final String CST_SUSPENSION_OPERATION = "suspend";
    public static final String CST_UNCHANGE_OPERATION = "unchange";
    public static final String CST_UOM = "UoM";
    public static final String CST_UNIT_OF_MEASUREMENT = "Unit of measurement";
    public static final String CST_USAGE_CHARGE = "usage charge";
    public static final String CST_VOLUME = "Volume";
    public static final String CST_MANDATORY = "MANDATORY";
    public static final String CST_OPTIONAL = "OPTIONAL";
    public static final int CST_REASON_CODE_BEGINNING = 0;
    public static final int CST_REASON_CODE_EXPORT = 3;
    public static final long CST_REASON_CODE_ACTIVATION=1l;
    public static final long CST_REASON_CODE_INTERESTED=7l;
    public static final String[] CST_REASON_LABEL_TAB = {"activation", "suspension", "deactivation", "reactivation",
            "replacement", "onhold", "interested", "lost card", "stolen card", "defective card", "customers decision",
            "opertors decision" , "no payment",  "ported out", "taken over", "payment made", "TAKEOVER", "IX Collections activation",
            "IX Collections deactivation", "activation overdraft clearance", "deactivation overdraft clearance", "initiated", "accepted",
            "postponed", "rejected", "forwarded", "canceled", "success", "failed"};

    public static final String P_DESCRIPTION = "DESCRIPTION";



    //*** Mapping constants

    // request mapping
    public static final String MAP_REQ_ADDITIONAL_CRITERIA_LIST = "REQ_ADD_CRITERIA_LIST";
    public static final String MAP_REQ_BILLING_ACCOUNT_LIST = "REQ_BILLING_ACCOUNT_LIST";
    public static final String MAP_REQ_CONTRACT_LIST = "REQ_CONTRACT_LIST";
    public static final String MAP_REQ_CUSTOMER_LIST = "REQ_CUSTOMER_LIST";
    public static final String MAP_REQ_FILTER_CRITERIA_LIST = "REQ_FILTER_CRITERIA_LIST";
    public static final String MAP_REQ_INSTALLED_OFFER_LIST = "REQ_INSTALLED_OFFER_LIST";
    public static final String MAP_REQ_RESOURCE_LIST = "REQ_RESOURCE_LIST";
    public static final String MAP_CONTRACT_INFO_FIELD = "MAP_CONTRACT_INFO_FIELD";
    public static final String MAP_REQ_PARTY_LIST = "MAP_REQ_PARTY_LIST";
    public static final String MAP_REQ_BILL_MEDIUM_LIST = "MAP_REQ_BILL_MEDIUM_LIST";


    // response mapping
    public static final String MAP_RES_BILLING_ACCOUNT_ASSIGNMENT_READ = "BILLING_ACCOUNT_ASSIGNMENT_READ";
    public static final String MAP_RES_BILLING_ACCOUNT_ASSIGNMENT_API_LIST = "BILLING_ACCOUNT_ASSIGNMENT_API_LIST";
    public static final String MAP_RES_CONTRACT_READ = "CONTRACT_READ";
    public static final String MAP_RES_CONTRACT_DEVICES_READ = "CONTRACT_DEVICE_READ";
    public static final String MAP_RES_CONTRACT_SERVICE_READ = "CONTRACT_SERVICE_READ";
    public static final String MAP_RES_CONTRACT_INFO_FIELD = "MAP_RES_CONTRACT_INFO_FIELD";
    public static final String MAP_RES_FEES_API_LIST = "FEES_API_LIST";
    public static final String MAP_RES_INSTALLED_OFFER_LIST = "RES_INSTALLED_OFFER_LIST";
    public static final String MAP_RES_INSTALLED_PRODUCT_API = "INSTALLED_PRODUCT_API";
    public static final String MAP_RES_RATEPLAN_READ = "RATEPLAN_READ";
    public static final String MAP_RES_REASON_READ = "REASON_READ";
    public static final String MAP_RES_SERVICE_PACKAGE_READ = "SERVICE_PACKAGE_READ";
    public static final String MAP_RES_SERVICE_READ = "SERVICE_READ";
    public static final String MAP_RES_STATUS_HISTORY_READ = "MAP_RES_STATUS_HISTORY_READ";
    public static final String MAP_RES_RATEPLAN_HISTORY_READ = "MAP_RES_RATEPLAN_HISTORY_READ";
    public static final String MAP_RES_RATEPLANS_READ = "MAP_RES_RATEPLANS_READ";


    /*
     * ----------------------------------------------------------------
     * ManageAppliedCustBillingRateConsFollowUp
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_USAGE_DATA_RECORDS_READ = "USAGE_DATA_RECORDS.READ";
    public static final String CMD_UNIT_OF_MEASUREMENTS_READ = "UNIT_OF_MEASUREMENTS.READ";
    public static final String CMD_UDC_LOGICAL_QUANTITY_READ = "UDC_LOGICAL_QUANTITY.READ";

    // SVLObject parameters
    public static final String P_CALLEDPARTY = "CALLEDPARTY";
    public static final String P_LOGICAL_QUANTITY_CODE = "LOGICAL_QUANTITY_CODE";
    public static final String P_SV_DES = "SV_DES";
    public static final String P_SEARCH_LIMIT = "SEARCH_LIMIT";
    public static final String P_RESULT_LIMIT = "RESULT_LIMIT";
    public static final String P_START_FROM_DATE = "START_FROM_DATE";
    public static final String P_START_TO_DATE = "START_TO_DATE";
    public static final String P_SNCODE = "SNCODE";
    public static final String P_SVCODE = "SV_CODE";
    public static final String P_ENTRY_FROM_DATE = "ENTRY_FROM_DATE";
    public static final String P_ORIG_START = "ORIG_START";
    public static final String P_BILLED_IND = "BILLED_IND";
    public static final String P_INVOICENO = "INVOICENO";
    public static final String P_CDR_ID = "CDR_ID";
    public static final String P_CDR_SUB_ID = "CDR_SUB_ID";
    public static final String P_UDR_BASEPART_ID = "UDR_BASEPART_ID";
    public static final String P_UDR_CHARGEPART_ID = "UDR_CHARGEPART_ID";
    public static final String P_RATED_VOLUME_UOM = "RATED_VOLUME_UOM";
    public static final String P_UOM_DES = "DES";
    public static final String P_RATED_FLAT_AMOUNT = "RATED_FLAT_AMOUNT";
    public static final String P_RATED_TAX_AMOUNT = "RATED_TAX_AMOUNT";
    public static final String P_RATED_VOLUME = "RATED_VOLUME";
    public static final String P_TAXRATE = "TAXRATE";
    public static final String P_CCH_DISCOUNTS_LIST = "CCH_DISCOUNTS_LIST";
    public static final String P_CCIP_FREE_AM = "CCIP_FREE_AM";
    public static final String P_XFILE_NET_RATE = "XFILE_NET_RATE";
    public static final String P_XFILE_EXTERNAL_CHARGE = "XFILE_EXTERNAL_CHARGE";
    public static final String P_FREE_UNITS_LIST = "FREE_UNITS_LIST";
    public static final String P_UMCODE_PUB = "UMCODE_PUB";
    public static final String P_FUP_RATE_VOL = "FUP_RATE_VOL";
    public static final String P_FUP_FREE_AM = "FUP_FREE_AM";
    public static final String P_FUP_ACC_KEY = "FUP_ACC_KEY";
    public static final String P_FUP_ACC_HIST = "FUP_ACC_HIST";
    public static final String P_FUP_PAC_EL = "FUP_PAC_EL";
    public static final String P_FU_PACK_ID_PUB = "FU_PACK_ID_PUB";
    public static final String P_CALL_TYPE = "CALL_TYPE";
    public static final String P_TYPE = "TYPE";
    public static final String P_DESTINATION = "DESTINATION";
    public static final String P_TZNAME = "TZNAME";
    public static final String P_TTNAME = "TTNAME";
    public static final String P_HOME_PL_CODE = "HOME_PL_CODE";
    public static final String P_HOME_TERMINATED_IND = "HOME_TERMINATED_IND";
    public static final String P_CELL_ADDR = "CELL_ADDR";
    public static final String P_CURRENCY_ID_PUB = "CURRENCY_ID_PUB";
    public static final String P_CURRENCY_ID = "CURRENCY_ID";
    public static final String P_DEVICE_NUM = "DEVICE_NUM";
    public static final String P_DEVICENUM = "DEVICENUM";
    public static final String P_CALLINGPARTY = "CALLINGPARTY";
    public static final String P_RP_DES = "RP_DES";
    public static final String P_RP_SHDES = "RP_SHDES";
    public static final String P_PAYMENT_METHOD_IND = "PAYMENT_METHOD_IND";
    public static final String P_OUTPUT = "OUTPUT";
    public static final String P_DURATION = "DURATION";
    public static final String P_UMCODE = "UMCODE";
    public static final String P_UNIT_OF_MEASUREMENTS = "UnitOfMeasurements";
    public static final String P_UDC_LOGICAL_QUANTITY = "UDCLogicalQuantitiy";
    public static final String P_LOGICAL_QUANTITY_SHDES = "LOGICAL_QUANTITY_SHDES";
    public static final String P_MESSAGE_VOLUME = "MESSAGE_VOLUME";
    public static final String P_EVENT_VOLUME = "EVENT_VOLUME";
    public static final String P_DATA_VOLUME = "DATA_VOLUME";
    public static final String P_START_DATE = "START_DATE";
    public static final String DURATION = "DURATION";

    // BSCSModel parameters
    public static final String P_USAGE_RATING_TAG_LIST = "USAGE_RATING_TAG_LIST";
    public static final String P_END_DATE = "END_DATE";
    public static final String P_UNIT_VALUE = "UNIT_VALUE";



    // constants
    public static final String CST_FUNCTION_SPECIFICATION_CODE_MANDATORY = "Function Specification Code";
    public static final String CST_USAGE_SPECIFICATION_CHARACTERISTIC_LABEL = "Usage specification characteristic label";
    public static final String CST_USAGE = "usage";
    public static final String CST_INCLUDED_USAGE = "included usage";
    public static final String CST_NON_INCLUDED_USAGE = "non included usage";
    public static final char   CST_BILLING_ACCOUNT_FOR_LATEST_VERSION = 'L';
    public static final String CST_CHRONO_ORDER_SET = "CHRONO_ORDER_SET";
    public static final String CST_TOTAL_COST = "Total";
    public static final String CST_SERVICE_COST = "Service cost";
    public static final String CST_CALL_TYPE = "call_type";
    public static final String CST_CALL_DESTINATION = "call_destination";
    public static final String CST_TZNAME = "tzname";
    public static final String CST_TTNAME = "ttname";
    public static final String CST_HOME = "Home";
    public static final String CST_VISITED = "Visited";
    public static final String CST_USAGE_RATING_TAG = "USAGE_RATING_TAG";
    public static final String CST_EXCLUDED_USAGE_RATING_TAG = "EXCLUDED_USAGE_RATING_TAG";
    public static final String CST_CHAR_VAL = "CHAR_VAL";
    public static final String CST_EXCLUDED_CHAR_VAL = "EXCLUDED_CHAR_VAL";
    public static final String CST_EXCLUDED_DESTINATION = "EXCLUDED_DEST";
    public static final String CST_SECONDS = "Seconds";
    public static final String CST_MES = "MES";
    public static final String CST_EVENT = "EVENT";
    public static final String CST_DATAV = "DATAV";
    public static final String CST_INSTALLED_PUBLIC_KEY = "Installed public key : MSISDN/ICC";
    public static final String CST_CMS_RC_NO_CUSTOMER_WITH_THIS_ID = "CMS.RC6701";
    public static final String CST_OFFER_SPECIFICATION = "OfferSpecification";
    public static final String CST_OFFER_SPEC_CODE = "offerSpecificationCode";
    public static final String CST_OFFER_SPEC_LABEL = "offerSpecificationLabel";
    public static final String CST_CONTRACT_ASSOCIATED = "contract associated to search criteria";







    // Mapping constants

    public static final String MAP_REQ_FAG_APRC = "MAP_REQ_FAG_APRC";
    public static final String MAP_REQ_FAG_APRC_CONTRACTS_SEARCH = "MAP_REQ_FAG_APRC_CONTRACTS_SEARCH";
    public static final String MAP_REQ_FAG_APRC_BILLING_ACCOUNT_READ = "MAP_REQ_FAG_APRC_BILLING_ACCOUNT_READ";
    public static final String MAP_REQ_FAG_APRC_USAGE_RATING_TAGS = "MAP_REQ_FAG_APRC_USAGE_RATING_TAGS";
    public static final String MAP_REQ_FAG_APRC_EXCLUDED_USAGE_RATING_TAGS = "MAP_REQ_FAG_APRC_EXCLUDED_USAGE_RATING_TAGS";
    public static final String MAP_REQ_FAG_APRC_DESTINATIONS = "MAP_REQ_FAG_APRC_DESTINATIONS";
    public static final String MAP_REQ_FAG_APRC_CHAR_VAL = "MAP_REQ_FAG_APRC_CHAR_VAL";
    public static final String MAP_REQ_FAG_APRC_EXCLUDED_CHAR_VAL = "MAP_REQ_FAG_APRC_EXCLUDED_CHAR_VAL";
    public static final String MAP_REQ_FAG_APRC_EXCLUDED_DESTINATIONS = "MAP_REQ_FAG_APRC_EXCLUDED_DESTINATIONS";

    public static final String MAP_RES_FAG_APRC = "MAP_RES_FAG_APRC";
    public static final String MAP_RES_FAG_APRC_CONTRACTS_SEARCH = "MAP_RES_FAG_APRC_CONTRACTS_SEARCH";
    public static final String MAP_RES_FAG_APRC_BILLING_ACCOUNT_READ = "MAP_RES_FAG_APRC_CONTRACTS_SEARCH";
    public static final String MAP_RES_FAG_APRC_USAGE_RATING_TAGS = "MAP_RES_FAG_APRC_USAGE_RATING_TAGS";
    public static final String MAP_RES_FAG_APRC_EXCLUDED_USAGE_RATING_TAGS = "MAP_RES_FAG_APRC_EXCLUDED_USAGE_RATING_TAGS";
    public static final String MAP_RES_FAG_APRC_DESTINATIONS = "MAP_RES_FAG_APRC_DESTINATIONS";
    public static final String MAP_RES_FAG_APRC_CHAR_VAL = "MAP_RES_FAG_APRC_CHAR_VAL";
    public static final String MAP_RES_FAG_APRC_EXCLUDED_CHAR_VAL = "MAP_RES_FAG_APRC_EXCLUDED_CHAR_VAL";
    public static final String MAP_RES_FAG_APRC_EXCLUDED_DESTINATIONS = "MAP_RES_FAG_APRC_EXCLUDED_DESTINATIONS";
    public static final String MAP_REQ_FAG_APRC_CALL_TYPE_CHAR_VAL = "MAP_REQ_FAG_APRC_CALL_TYPE_CHAR_VAL";
    public static final String MAP_REQ_FAG_APRC_CALL_DESTINATION_CHAR_VAL = "MAP_REQ_FAG_APRC_CALL_DESTINATION_CHAR_VAL";
    public static final String MAP_REQ_FAG_APRC_EXCLUDED_CALL_TYPE_CHAR_VAL = "MAP_REQ_FAG_APRC_EXCLUDED_CALL_TYPE_CHAR_VAL";
    public static final String MAP_REQ_FAG_APRC_EXCLUDED_CALL_DESTINATION_CHAR_VAL = "MAP_REQ_FAG_APRC_EXCLUDED_CALL_DESTINATION_CHAR_VAL";


    /*
     * ----------------------------------------------------------------
     * PARTY
     * ----------------------------------------------------------------
     */
    // SOI COMMANDS
    public static final String CMD_ADDRESS_WRITE = "ADDRESS.WRITE";
    public static final String CMD_CUSTOMER_WRITE = "CUSTOMER.WRITE";
    public static final String CMD_CUSTOMER_HIERARCHY_WRITE = "CUSTOMER_HIERARCHY.WRITE";
    public static final String CMD_CUSTOMER_INFO_READ = "CUSTOMER_INFO.READ";
    public static final String CMD_CUSTOMER_INFO_WRITE = "CUSTOMER_INFO.WRITE";
    public static final String CMD_CUSTOMER_FLAT_WRITE = "CUSTOMER_FLAT.WRITE";
    public static final String CMD_MARITAL_STATUS_READ = "MARITAL_STATUS.READ";
    public static final String CMD_INSTALLATION_ADDRESS_WRITE = "INSTALLATION_ADDRESS.WRITE";
    public static final String CMD_CUSTOMER_GROUPS_READ = "CUSTOMER_GROUPS.READ";
    public static final String CMD_INSTALLATION_ADDRESSES_READ = "INSTALLATION_ADDRESSES.READ";


    // Constants
    public static final String CST_EMAIL_ADDRESS = "EmailAddress";
    public static final String CST_CITY_NAME = "cityName";
    public static final String CST_DISTRICT_NAME = "districtName";
    public static final String CST_STATE_NAME = "stateName";
    public static final String CST_PHONE_NUMBER = "phone";
    public static final String CST_FIRSTNAME = "firstName";
    public static final String CST_LASTNAME = "lastName";
    public static final String CST_TRADINGNAME = "tradingName";
    public static final String CST_NAME = "name";
    public static final String CST_VALUE = "value";
    public static final String CST_DISTRIBUTOR = "distributor";



    //SVL Constant
    public static final String P_ADR_LOCATION_1 = "ADR_LOCATION_1";
    public static final String P_ADR_LOCATION_2 = "ADR_LOCATION_2";
    public static final String P_ADR_LOCATION_3 = "ADR_LOCATION_3";

    public static final String P_ADR_NOTE1 = "ADR_NOTE1";
    public static final String P_ADR_NOTE2 = "ADR_NOTE2";
    public static final String P_ADR_NOTE3 = "ADR_NOTE3";
    public static final String P_ADR_EMAIL = "ADR_EMAIL";
    public static final String P_ADR_STATE = "ADR_STATE";
    public static final String P_ISO2_3166 = "ISO2_3166";
    public static final String P_ADR_COUNTY = "ADR_COUNTY";
    public static final String P_TEMP_INPUT_PARTI_ID = "TEMP_INPUT_PARTI_ID";
    public static final String P_EXTERNAL_CUSTOMER_ID = "EXTERNAL_CUSTOMER_ID";
    public static final String P_EXTERNAL_CUSTOMER_SET_ID = "EXTERNAL_CUSTOMER_SET_ID";
    public static final String P_CS_DEALERID_PUB = "CS_DEALERID_PUB";
    public static final String P_CS_LEVEL_CODE = "CS_LEVEL_CODE";
    public static final String P_CS_CREATION_DATE = "CS_CREATION_DATE";
    public static final String P_CS_STATUS = "CS_STATUS";
    public static final String P_CS_PASSWORD = "CS_PASSWORD";
    public static final String P_CS_STATUS_DATE = "CS_STATUS_DATE";
    public static final String P_ADR_SEX = "ADR_SEX";
    public static final String P_ADR_BIRTHDT = "ADR_BIRTHDT";
    public static final String P_MAS_DES = "MAS_DES";
    public static final String P_MAS_CODE = "MAS_CODE";
    public static final String P_LIST_OF_MARITAL_STATUS = "LIST_OF_MARITAL_STATUS";
    public static final String P_ADR_JBDES = "ADR_JBDES";
    public static final String P_TTL_ID = "TTL_ID";
    public static final String P_TTL_ID_PUB = "TTL_ID_PUB";
    public static final String P_ADR_MNAME = "ADR_MNAME";
    public static final String P_LNG_CODE_PUB = "LNG_CODE_PUB";
    public static final String P_LIST_OF_LANGUAGES = "LIST_OF_LANGUAGES";
    public static final String P_LIST_OF_CUSTOMER_GROUPS = "LIST_OF_CUSTOMER_GROUPS";
    public static final String P_USE_INSTALLATION_ADDRESS = "USE_INSTALLATION_ADDRESS";
    public static final String P_LIST_OF_ENTITYWITHRIGHTS = "LIST_OF_ENTITYWITHRIGHTS";
    public static final String P_HIERARCHY_LEVEL_EXPRESSION = "HIERARCHY_LEVEL_EXPRESSION";

    public static final String P_SUM_UNBILLED_AMOUNT = "SUM_UNBILLED_AMOUNT";
    public static final String P_CS_RESELLER = "CS_RESELLER";

    public static final String P_COST_CODE_PUB = "COST_CODE_PUB";
    public static final String P_COST_ID = "COST_ID";
    public static final String P_CREATED_BY_USER = "CREATED_BY_USER";
    public static final String P_CS_ACTIVATION_DATE = "CS_ACTIVATION_DATE";
    public static final String P_CS_BILL_INFORMATION = "CS_BILL_INFORMATION";
    public static final String P_CS_CLIMIT = "CS_CLIMIT";
    public static final String P_CS_CONVRATETYPE_PAYMENT = "CS_CONVRATETYPE_PAYMENT";
    public static final String P_CS_CONVRATETYPE_PAYMENT_PUB = "CS_CONVRATETYPE_PAYMENT_PUB";
    public static final String P_CS_CONVRATETYPE_REFUND = "CS_CONVRATETYPE_REFUND";
    public static final String P_CS_CONVRATETYPE_REFUND_PUB = "CS_CONVRATETYPE_REFUND_PUB";
    public static final String P_CS_CRCHECK_AGREED = "CS_CRCHECK_AGREED";
    public static final String P_CS_DEPOSIT = "CS_DEPOSIT";
    public static final String P_CS_INCORPORATED_IND = "CS_INCORPORATED_IND";
    public static final String P_CS_INIT_PREPAID_CONTR_OWNER = "CS_INIT_PREPAID_CONTR_OWNER";
    public static final String P_CS_LIMIT_TR1 = "CS_LIMIT_TR1";
    public static final String P_CS_LIMIT_TR1_PUB = "CS_LIMIT_TR1_PUB";
    public static final String P_CS_LIMIT_TR2 = "CS_LIMIT_TR2";
    public static final String P_CS_LIMIT_TR2_PUB = "CS_LIMIT_TR2_PUB";
    public static final String P_CS_LIMIT_TR3 = "CS_LIMIT_TR3";
    public static final String P_CS_LIMIT_TR3_PUB = "CS_LIMIT_TR3_PUB";
    public static final String P_CS_PAYMNT_RESP_CODE = "CS_PAYMNT_RESP_CODE";
    public static final String P_CS_PREPAYMENT = "CS_PREPAYMENT";
    public static final String P_CS_PREVBALANCE = "CS_PREVBALANCE";
    public static final String P_CS_UNBILLED_AMOUNT = "CS_UNBILLED_AMOUNT";
    public static final String P_CUSTCAT_CODE_PUB = "CUSTCAT_CODE_PUB";
    public static final String P_EXPECT_PAY_CURRENCY_ID = "EXPECT_PAY_CURRENCY_ID";
    public static final String P_FAMILY_ID = "FAMILY_ID";
    public static final String P_IS_INDIVIDUAL_OVERDISC_DISABLED = "IS_INDIVIDUAL_OVERDISC_DISABLED";
    public static final String P_LAST_MODIFICATION_USER = "LAST_MODIFICATION_USER";
    public static final String P_PARTY_TYPE = "PARTY_TYPE";
    public static final String P_PAYMENT_RESP = "PAYMENT_RESP";
    public static final String P_PAYMENT_RESP_ID = "PAYMENT_RESP_ID";
    public static final String P_PAYMENT_RESP_ID_PUB = "PAYMENT_RESP_ID_PUB";

    public static final String P_REFUND_CURRENCY_ID = "REFUND_CURRENCY_ID";
    public static final String P_REFUND_CURRENCY_ID_PUB = "REFUND_CURRENCY_ID_PUB";
    public static final String P_TRADE_CODE = "TRADE_CODE";
    public static final String P_WP_CODE = "WP_CODE";


    public static final String P_AREA_CODE = "AREA_CODE";
    public static final String P_CS_CSCREDIT_REMARK="CS_CSCREDIT_REMARK";
    public static final String P_CS_DEALERID = "CS_DEALERID";
    public static final String P_CS_COLLECTOR = "CS_COLLECTOR";
    public static final String P_CS_JURISDICTION_CODE = "CS_JURISDICTION_CODE";
    public static final String P_CS_JURISDICTION_ID = "CS_JURISDICTION_ID";
    public static final String P_CS_REMARK1 = "CS_REMARK1";
    public static final String P_CS_REMARK2 = "CS_REMARK2";
    public static final String P_PARTY_ROLE_ASSIGNMENTS = "PARTY_ROLE_ASSIGNMENTS";
    public static final String P_SR_CODE = "SR_CODE";
    public static final String P_TB_AMOUNT = "TB_AMOUNT";
    public static final String P_TB_CURRENCY_ID = "TB_CURRENCY_ID";
    public static final String P_TB_CURRENCY_ID_PUB = "TB_CURRENCY_ID_PUB";
    public static final String P_TB_MODE = "TB_MODE";
    public static final String P_TB_PURPOSE = "TB_PURPOSE";
    public static final String P_BC_COMMENT = "BC_COMMENT";
    public static final String P_LA_MEMBER = "LA_MEMBER";
    public static final String P_CS_CONTR_RESP = "CS_CONTR_RESP";



    // Format
    public static final String CST_PARTY_ID_FORMAT = "CS_ID_PUB|ADR_SEQ";
    public static final String CST_10 = "10";
    public static final String CST_20 = "20";
    public static final String CST_30 = "30";
    public static final String CST_40 = "40";
    public static final String CST_GDM_LEVEL_10 = "large account";
    public static final String CST_GDM_LEVEL_20 = "division";
    public static final String CST_GDM_LEVEL_30 = "cost center";
    public static final String CST_GDM_LEVEL_40 = "subscriber";
    public static final String CST_VALIDATED = "Validated";
    public static final String CST_INITIALIZED = "Initialized";
    public static final String CST_DEACTIVATED_MAJ = "Deactivated";
    public static final Character CST_M = 'M';
    public static final Character CST_F = 'F';
    public static final String CST_MALE = "Male";
    public static final String CST_FEMALE = "Female";
    public static final String CST_PHONENUMBER = "PhoneNumber";
    public static final String CST_POSTALCONTACT = "PostalContact";
    public static final String CST_BILLING_ADRESS_ROLE = "BillingAddressRole";
    public static final String CST_INSTALLEDOFFER = "InstalledOffer";
    public static final String CST_BILLINGACCOUNT = "BillingAccount";
    public static final String CST_INSTALLEDPRODUCT = "InstalledProduct";
    public static final String CST_HIERARCHYLEVEL_REGEX = "(10|20|30|40|50|60|70|75|80)";
    public static final String CST_HIERARCHYLEVELVIEW_REGEX = "^(!?)(10|20|30|40|50|60|70|75|80)$";

    // Mapping constants
    public static final String MAP_REQ_PARTYFAG_ADDRESS = "MAP_REQ_PARTYFAG_ADDRESS";
    public static final String MAP_REQ_PARTYFAG_COUNTRY = "MAP_REQ_PARTYFAG_COUNTRY";
    public static final String MAP_REQ_PARTYFAG_CUSTOMERINFOFIELD = "MAP_REQ_PARTYFAG_CUSTOMERINFOFIELD";
    public static final String MAP_REQ_PARTYFAG_FILTER = "MAP_REQ_PARTYFAG_FILTER";
    public static final String MAP_REQ_PARTYFAG_HIERARCHYLEVEL = "MAP_REQ_PARTYFAG_HIERARCHYLEVEL";
    public static final String MAP_REQ_PARTYFAG_ORGANISATIONANDPERSON = "MAP_REQ_PARTYFAG_ORGANISATIONANDPERSON";
    public static final String MAP_REQ_PARTYFAG_RATEPLANMARKETANDPASSWORD = "MAP_REQ_PARTYFAG_RATEPLANMARKETANDPASSWORD";
    public static final String MAP_REQ_PARTYFAG_RESPONSEPARTYID = "MAP_REQ_PARTYFAG_RESPONSEPARTYID";
    public static final String MAP_REQ_PARTYFAG_REQLIST = "MAP_REQ_PARTYFAG_REQLIST";
    public static final String MAP_REQ_PARTYFAG_BYPARTYID = "MAP_REQ_PARTYFAG_BYPARTYID";
    public static final String MAP_REQ_PARTYFAG_BYCUSTOMER = "MAP_REQ_PARTYFAG_BYCUSTOMER";
    public static final String MAP_REQ_PARTYFAG_BYNAME = "MAP_REQ_PARTYFAG_BYNAME";
    public static final String MAP_REQ_PARTYFAG_BYIDENTIFICATION = "MAP_REQ_PARTYFAG_BYIDENTIFICATION";
    public static final String MAP_REQ_PARTYFAG_BYCONTACTMETHOD = "MAP_REQ_PARTYFAG_BYCONTACTMETHOD";
    public static final String MAP_RES_PARTYFAG = "MAP_RES_PARTYFAG";
    public static final String MAP_REQ_PARTYFAG_BYPARTYROLE = "MAP_REQ_PARTYFAG_BYPARTYROLE";
    public static final String MAP_RES_PARTYFAG_PARTYVIEW = "MAP_RES_PARTYFAG_PARTYVIEW";
    public static final String MAP_RES_PARTYFAG_ENTITYVIEW = "MAP_RES_PARTYFAG_ENTITYVIEW";
    public static final String MAP_RES_PARTYFAG_PARTYIDENTIFICATIONVIEW = "MAP_RES_PARTYFAG_PARTYIDENTIFICATIONVIEW";
    public static final String MAP_RES_PARTYFAG_MAYUSECONTACTVIEW = "MAP_RES_PARTYFAG_CONTACTVIEW";
    public static final String MAP_RES_PARTYFAG_ROLEVIEW = "MAP_RES_PARTYFAG_ROLEVIEW";
    public static final String MAP_RES_PARTYFAG_CUSTOMERINFOFIELDVIEW = "MAP_RES_PARTYFAG_CUSTOMERINFOFIELDVIEW";
    public static final String MAP_RES_PARTYFAG_RESULTSVIEW = "MAP_RES_PARTYFAG_RESULTSVIEW";




    //internal BL constants:
    public static final String BL_PARTY_ROLE_VIEW = "BL_PARTY_ROLE_VIEW";
    public static final String BL_PARTY_ROLE_USER_VIEW = "BL_PARTY_ROLE_USER_VIEW";
    public static final String BL_CONTACT_METHOD_ROLE = "BL_CONTACT_METHOD_ROLE";
    public static final String BL_CONTACT_METHOD_VIEW = "BL_CONTACT_METHOD_VIEW";
    public static final String BL_CUSTOMER_INFO_FIELD_VIEW = "BL_CUSTOMER_INFO_FIELD_VIEW";
    public static final String BL_HIERARCHY_LEVEL_VIEW = "BL_HIERARCHY_LEVEL_VIEW";
    public static final String BL_ADRESSES = "BL_ADRESSES";
    public static final String BL_INSTALLATION_ADDRESS = "BL_INSTALLATION_ADDRESS";
    public static final String BL_PARTY_ROLE_ID = "BL_PARTY_ROLE_ID";
    public static final String BL_PARTY_ROLE_TYPE = "BL_PARTY_ROLE_TYPE";
    public static final String BL_ENTITY_WITH_RIGHTS_ID = "BL_ENTITY_WITH_RIGHTS_ID";
    public static final String BL_ENTITY_WITH_RIGHTS_TYPE = "BL_ENTITY_WITH_RIGHTS_TYPE";
    public static final String BL_ENTITY_WITH_RIGHTS_LIST = "BL_ENTITY_WITH_RIGHTS_LIST";
    public static final String BL_OTHER_PARTY_IS_USER = "BL_OTHER_PARTY_IS_USER";
    public static final String BL_OTHER_PARTY_IS_HOLDER = "BL_OTHER_PARTY_IS_HOLDER";
    public static final String BL_OTHER_PARTY_IS_PAYER = "BL_OTHER_PARTY_IS_PAYER";
    public static final String BL_OTHER_PARTY_IS_RECEIVER = "BL_OTHER_PARTY_IS_RECEIVER";



    /*
     * ----------------------------------------------------------------
     * IUPC
     * ----------------------------------------------------------------
     */

    // SOI COMMANDS
    public static final String CMD_CONTRACT_FUA_READ = "CONTRACT_FUA.READ";
    public static final String CMD_CONSUMPTION_READ = "CONSUMPTION.READ";

    // Constants
    public static final char   CST_FREE_UNIT_CUSTOMER_LEVEL = 'L';
    public static final char   CST_FREE_UNIT_LA_LEVEL = 'P';
    public static final char   CST_IMPLICIT_FREE_UNIT_ASSIGNMENT = 'I';
    public static final String CST_TOTAL = "total";
    public static final String CST_INSTALLED_PRODUCT_ID = "installedProductID";
    public static final String CST_INTERIM = "I";
    public static final String CST_BILLING_ACCOUNT_ID = "billingAccountID";
    public static final String CST_CONTRACT = "Contract";
    public static final long   CST_SEC = 60L;
    public static final double CST_HALF_MINUTE = 30d;
    public static final double CST_SIXTY = 60d;
    public static final long CST_LONG_SIXTY = 60L;
    public static final String CST_COULD_NOT_FIND_FREE_UNIT_ACCOUNT = "COULD_NOT_FIND_FREE_UNIT_ACCOUNT";
    public static final String CST_COULD_NOT_FIND_CS_CODE = "COULD_NOT_FIND_CS_CODE";
    public static final String CST_COULD_NOT_FIND_CO_ID = "COULD_NOT_FIND_CO_ID";
    public static final String CST_COULD_NOT_FIND_CO_ID_PUB = "COULD_NOT_FIND_CO_ID_PUB";
    public static final String CST_COULD_NOT_FIND_CS_ID = "COULD_NOT_FIND_CS_ID";
    public static final String CST_COULD_NOT_FIND_CS_ID_PUB = "COULD_NOT_FIND_CS_ID_PUB";
    public static final String CST_NO_CUSTOMER_OR_CONTRACT_IDENTIFIER = "NO_CUSTOMER_OR_CONTRACT_IDENTIFIER";
    public static final String CST_CUST_CODE = "custCode";
    public static final String CST_CUSTOMER_ID_PUB = "customerIDPub";
    public static final String CST_INSTALLED_CONTRACT_ID = "installedContractID";
    public static final String CST_CUSTOMER_ID = "customerID";
    public static final String CST_VOICE = "VOICE";


    // SVL parameters
    public static final String P_FU_ACCOUNT_KEY = "FU_ACCOUNT_KEY";
    public static final String P_CS_LAST_BC_DATE = "CS_LAST_BC_DATE";
    public static final String P_START_DATE_BILLCYCLE = "START_DATE_BILLCYCLE";
    public static final String P_END_DATE_BILLCYCLE = "END_DATE_BILLCYCLE";
    public static final String P_LAST_CALL_DATE = "LAST_CALL_DATE";
    public static final String P_FUP_ASS_LEVEL = "FUP_ASS_LEVEL";
    public static final String P_FUA_DATA = "fua_data";
    public static final String P_FUP_LIST = "FUP_LIST";
    public static final String P_FU_SEQ = "FU_SEQ";
    public static final String P_FU_UMCODE = "FU_UMCODE";
    public static final String P_OUT_OF_BUNDLE_FU_UMCODE = "OUT_OF_BUNDLE_FU_UMCODE";
    public static final String P_IN_BUNDLE_FU_UMCODE = "IN_BUNDLE_FU_UMCODE";
    public static final String P_IN_BUNDLE_DES = "IN_BUNDLE_DES";
    public static final String P_OUT_OF_BUNDLE_DES = "OUT_OF_BUNDLE_DES";
    public static final String P_OUT_OF_BUNDLE_USAGE_TYPE = "OUT_OF_BUNDLE_USAGE_TYPE";
    public static final String P_IN_BUNDLE_USAGE_TYPE = "IN_BUNDLE_USAGE_TYPE";
    public static final String P_FU_GRANT_INT = "FU_GRANT_INT";
    public static final String P_FU_PACK_NAME = "FU_PACK_NAME";
    public static final String P_FU_CARRYOVER = "FU_CARRYOVER";
    public static final String P_PERIOD_FROM = "PERIOD_FROM";
    public static final String P_TRAFFIC_TYPE = "TRAFFIC_TYPE";
    public static final String P_MAX_FREEUNITS = "MAX_FREEUNITS";
    public static final String P_CO_VOLUME_APPLIED = "CO_VOLUME_APPLIED";
    public static final String P_INFO_CO_VOLUME_APPLIED = "INFO_CO_VOLUME_APPLIED";
    public static final String P_DES = "DES";
    public static final String P_TAX_RATE = "TAX_RATE";
    public static final String P_CONSO = "CONSO";
    public static final String P_FU_PACK_ID = "FU_PACK_ID";
    public static final String P_FUP_DES = "FUP_DES";
    public static final String P_PERIOD_TO = "PERIOD_TO";
    public static final String P_EXP_DATE_CARRY_OVER = "EXP_DATE_CARRY_OVER";
    public static final String P_ROLLOVER = "ROLLOVER";
    public static final String P_BALANCE_TYPE = "BALANCE_TYPE";
    public static final String P_UOM = "UOM";
    public static final String P_FU_UOM = "FU_UOM";
    public static final String P_UOM_CODE = "UOM_CODE";

    // BSCSModel parameters
    public static final String P_REQUEST_EXTENDED_TO_BA = "requestExtendedToBA";
    public static final String P_REQUEST_EXTENDED_TO_CONTRACT = "requestExtendedToContract";
    public static final String P_ROUNDED_DURATION = "roundedDuration";
    public static final String P_DECIMAL_NUMBER = "decimalNumber";
    public static final String P_INSTALLED_USAGE_PRODUCT = "INSTALLED_USAGE_PRODUCT";
    public static final String P_INSTALLED_FX_PRICE_PACKAGES = "INSTALLED_FX_PRICE_PACKAGES";
    public static final String P_LOCAL_PRODUCT_SPECIFICATIONS = "LOCAL_PRODUCT_SPECIFICATIONS";
    public static final String P_INST_FX_PRICE_PACKAGE_BALANCES = "INST_FX_PRICE_PACKAGE_BALANCES";
    public static final String P_USAGE_TYPES = "USAGE_TYPES";
    public static final String P_USAGE_TYPE = "USAGE_TYPE";
    public static final String P_REMAINING_FU_CARRYOVER = "REMAINING_FU_CARRYOVER";
    public static final String P_INST_USAGE_PRODUCT_CONSUMPTION = "INST_USAGE_PRODUCT_CONSUMPTION";
    public static final String P_FLAT_AMOUNT = "FLAT_AMOUNT";
    public static final String P_FLAT_AMOUNT_TAX = "FLAT_AMOUNT_TAX";
    public static final String P_INST_USAGE_PRODUCT_CONS_DETAILS_LIST = "INST_USAGE_PRODUCT_CONS_DETAILS_LIST";
    public static final String P_ELEM_LIST = "ELEM_LIST";
    public static final String P_ELEMDEF_LIST = "ELEMDEF_LIST";
    public static final String P_ELEM_SEQNUM = "ELEM_SEQNUM";

    public static final String P_FU_APPL_INT = "FU_APPL_INT";
    public static final String P_FU_UOM_PUB = "FU_UOM_PUB";

    // Format

    // Mapping constants
    public static final String MAP_REQ_FAG_IUPC = "MAP_REQ_FAG_IUPC";
    public static final String MAP_RES_FAG_IUPC = "MAP_RES_FAG_IUPC";


    /*
     * ----------------------------------------------------------------
     * ManageInvoiceInvoicing
     * ----------------------------------------------------------------
     */

    // SOI commands
    public static final String CMD_BILLDOCUMENT_READ = "BILLDOCUMENT.READ";
    public static final String CMD_BILLDOCUMENT_REFERENCE_SEARCH = "BILLDOCUMENT_REFERENCE.SEARCH";
    public static final String CMD_BUSINESS_TRANSACTION_READ = "BUSINESS_TRANSACTION.READ";
    public static final String CMD_BUSINESS_TRANSACTIONS_SEARCH = "BUSINESS_TRANSACTIONS.SEARCH";
    public static final String CMD_FINANCIAL_TRANSACTION_READ = "FINANCIAL_TRANSACTION.READ";
    public static final String CMD_FINANCIAL_TRANSACTION_SEARCH = "FINANCIAL_TRANSACTION.SEARCH";
    public static final String CMD_FINANCIAL_TRANSACTION_WRITE = "FINANCIAL_TRANSACTION.WRITE";
    public static final String CMD_FINANCIAL_TRANSACTION_DETAIL_READ = "FINANCIAL_TRANSACTION_DETAIL.READ";
    public static final String CMD_CREDITSBYDEBIT_READ = "CREDITSBYDEBIT.READ";
    public static final String CMD_REFERENCED_TRANSACTIONS_SEARCH = "REFERENCED_TRANSACTION.SEARCH";
    public static final String CMD_DEBITSBYCREDIT_READ  = "DEBITSBYCREDIT.READ";


    //SOI parameters
    public static final String P_BT_OHXACT = "BT_OHXACT";
    public static final String P_BT_CAXACT = "BT_CAXACT";
    public static final String P_BT_OHSTATUS = "BT_OHSTATUS";
    public static final String P_DOCUMENT_REF_NUM = "DOCUMENT_REF_NUM";
    public static final String P_BT_OHREFNUM = "BT_OHREFNUM";
    public static final String P_END_BT_OHREFDATE = "END_BT_OHREFDATE";
    public static final String P_START_BT_OHREFDATE = "START_BT_OHREFDATE";
    public static final String P_REFERENCEDBTRANS = "referencedbtrans";
    public static final String P_REFERENCEDTRANSACTIONS = "referencedtransactions";
    public static final String P_BT_OHREFDATE = "BT_OHREFDATE";
    public static final String P_BT_OHINVAMT_DOC = "BT_OHINVAMT_DOC";
    public static final String P_BT_OHDUEDATE = "BT_OHDUEDATE";
    public static final String P_BT_OHENTDATE = "BT_OHENTDATE";
    public static final String P_BT_OHOPNAMT_DOC = "BT_OHOPNAMT_DOC";
    public static final String P_DOCUMENT_FORMAT = "DOCUMENT_FORMAT";
    public static final String P_DOCUMENT = "DOCUMENT";
    public static final String P_DOCUMENT_FILE_NAME = "DOCUMENT_FILE_NAME";
    public static final String P_DOCUMENT_DIR = "DOCUMENT_DIR ";
    public static final String P_CS_CSCURBALANCE = "CS_CSCURBALANCE";
    public static final String P_INV_STATUS = "INV_STATUS";
    public static final String P_BT_OHTAXAMT_GL = "BT_OHTAXAMT_GL";
    public static final String P_DOC_ID = "DOCUMENT_ID";
    public static final String P_DOC_REFDATE = "REF_DATE";
    public static final String P_DOC_DUEDATE = "DUE_DATE";
    public static final String P_DOC_INVOICESTATUS = "DOCUMENT_PROCESSING_STATUS";
    public static final String P_DOC_INVOICEOPENAMOUNT = "OPEN_AMOUNT_DOC";
    public static final String P_DOC_INVOICEAMOUNT = "DOCUMENT_AMOUNT_DOC";
    public static final String P_DOC_INVOICECURRENCY = "GL_CURRENCY";
    public static final String P_END_DOC_OHREFDATE = "REF_DATE_UNTIL";
    public static final String P_START_DOC_OHREFDATE = "REF_DATE_FROM";
    public static final String P_DOCUMENTS = "DOCUMENTS";
    public static final String P_IGNORE_BU_IND = "IGNORE_BU_IND";
    public static final String P_DOCUMENT_STATUS = "DOCUMENT_STATUS";
    public static final String P_AMOUNT_DOC_MIN = "DOCUMENT_AMOUNT_DOC_MIN";
    public static final String P_ORDR_REF_DATE = "ORDR_REF_DATE";
    public static final String P_BT_INVTYPE = "BT_INVTYPE";




    //internal BL constants
    public static final String BL_INVOICE_IMAGE_VIEW = "BL_INVOICE_IMAGE_VIEW";
    public static final String BL_LOCAL_LAST_INVOICE = "BL_LOCAL_LAST_INVOICE";
    public static final String BL_FIND_PERIOD_START_DATE = "BL_FIND_PERIOD_START_DATE";
    public static final String BL_FIND_PERIOD_END_DATE = "BL_FIND_PERIOD_END_DATE";
    public static final String BL_CURRENCY_BALANCE = "BL_CURRENCY_BALANCE";

    //Mapping constants
    public static final String MAP_REQ_INVOICEFAG_INVOICE = "MAP_REQ_INVOICEFAG_INVOICE";
    public static final String MAP_RESP_INVOICEFAG_INVOICE = "MAP_RESP_INVOICEFAG_INVOICE";
    public static final String MAP_REQ_INVOICEFAG_DOCUMENT = "MAP_REQ_INVOICEFAG_DOCUMENT";
    public static final String MAP_RESP_INVOICEFAG_DOCUMENT = "MAP_RESP_INVOICEFAG_DOCUMENT";
    public static final String MAP_REQ_INVOICEFAG_FILTER = "MAP_REQ_INVOICEFAG_FILTER";
    public static final String MAP_RESP_INVOICEFAG_FILTER = "MAP_RESP_INVOICEFAG_FILTER";

    //Constants
    public static final String CST_MODE_OWNED = "O";
    public static final String CST_MODE_ALL = "A";

    // NEW IN CUCIB v2.3
    public static final String P_LINK_OFFERID = "LINK_OFFERID";
    // NEW IN CUCIB v2.3
    public static final String P_OFFER_SPECIFICATION_INTERNAL_CODE = "OFFER_SPECIFICATION_INTERNAL_CODE";
    // NEW IN CUCIB v2.3
    public static final String P_COMMERCIAL_OPERATION_EFFECTIVE_DATE = "COMMERCIAL_OPERATION_EFFECTIVE_DATE";
    // NEW IN CUCIB v2.3
    public static final String P_COMMERCIAL_OPERATION_NEXTBILLCYBLE = "COMMERCIAL_OPERATION_NEXTBILLCYBLE";
    // NEW IN CUCIB v2.3
    public static final String P_FEE_CODE = "FEE_CODE";


    public static final String CST_PRODUCT_MOBILE = "MOBILE";
    public static final String CST_PRODUCT_INTERNET = "INTERNET";
    public static final String CST_PRODUCT_WIMAX = "WIMAX";
    public static final String CST_PRODUCT_FIXE = "FIXE";
    public static final Object CST_ITEL = "ITEL";

    public static final String CST_BSCS = "BSCS";

    // Don't really exist, used in FAGCIB to known if it's a reel status are not
    public static final String P_INSTALLED_OFFER_ISPENDING_STATUS = "INSTALLED_OFFER_ISPENDING_STATUS";
    public static final String P_CO_SN_STATUS = "CO_SN_STATUS";


    // Declare with PaymentArrangementAssignment
    public static final String CMD_PAYMENT_ARRANGEMENT_ASSIGNMENT_READ = "PAYMENT_ARRANGEMENT_ASSIGNMENT.READ";
    public static final String P_PAYMENT_ARRANGEMENT_LIST = "PAYMENT_ARRANGEMENT_LIST";

    public static final String P_LEVEL = "LEVEL";
    public static final String P_DOCUMENT_TYPE = "DOCUMENT_TYPE";
    public static final String P_PAY_ARR_ID = "PAY_ARR_ID";
    public static final String P_PAYMENT_ID = "PAYMENT_ID";
    public static final String P_ACCNO = "ACCNO";
    public static final String P_ACCOWNER = "ACCOWNER";
    public static final String P_CURRENT = "CURRENT";
    public static final String P_PAYMENT_MODE = "PAYMENT_MODE";
    public static final String P_VALID_UNTIL = "VALID_UNTIL";

    // Declare with PaymentArrangementAssignment
    public static final String P_AUTH_CREDIT = "AUTH_CREDIT";
    public static final String P_AUTH_DATE = "AUTH_DATE";
    public static final String P_AUTH_NO = "AUTH_NO";
    public static final String P_AUTH_OK = "AUTH_OK";
    public static final String P_AUTH_REMARK = "P_AUTH_REMARK";
    public static final String P_AUTH_TN = "AUTH_TN";
    public static final String P_CSP_BANKCITY = "CSP_BANKCITY";
    public static final String P_CSP_BANKCODE = "CSP_BANKCODE";
    public static final String P_CSP_BANKCOUNTRY = "CSP_BANKCOUNTRY";
    public static final String P_CSP_BANKCOUNTRY_PUB = "CSP_BANKCOUNTRY_PUB";
    public static final String P_CSP_BANKCOUNTY = "CSP_BANKCOUNTY";
    public static final String P_CSP_BANKSTATE = "CSP_BANKSTATE";
    public static final String P_CSP_BANKSTREET = "CSP_BANKSTREET";
    public static final String P_CSP_BANKSTREETNO = "CSP_BANKSTREETNO";
    public static final String P_CSP_BANKZIP = "CSP_BANKZIP";
    public static final String P_CSP_CCAG_CODE = "CSP_CCAG_CODE";
    public static final String P_CSP_CEILING = "P_CSP_CEILING";
    public static final String P_CSP_ID = "CSP_ID"; //"P_CSP_ID";
    public static final String P_CSP_ORDERNUMBER = "CSP_ORDERNUMBER";
    public static final String P_CSP_VALID_FROM = "CSP_VALID_FROM";

    // Create BillingAccount/CardTypeCode = BankName of CREDITCARD_COMPANIES.READ, need IDPUB for PaymentMean
    public static final String CST_CCBANKNAME="CCBANKNAME";


    // CREDITCARD_COMPANIES.READ
    public static final String CMD_CREDITCARD_COMPANIES_READ = "CREDITCARD_COMPANIES.READ";
    public static final String P_CREDITCARDCOMPANIES = "creditcardcompanies";
    public static final String P_BANK_ID = "BANK_ID";
    public static final String P_BANK_ID_PUB = "BANK_ID_PUB";
    public static final String P_BANK_NAME = "BANK_NAME";
    public static final String P_BANK_DEF="BANK_DEF";
    public static final String P_BANK_STREET = "BANK_STREET";
    public static final String P_BANK_STREET_NUM = "BANK_STREET_NUM";
    public static final String P_BANK_ZIP = "BANK_ZIP";
    public static final String P_BANK_COUNTY = "BANK_COUNTY";
    public static final String P_BANK_STATE = "BANK_STATE";
    public static final String P_BANK_COUNTRY = "BANK_COUNTRY";
    public static final String P_BANK_COUNTRY_PUB = "BANK_COUNTRY_PUB";

    // BALANCES.READ
    public static final String P_ACTUAL_VALUE = "ACTUAL_VALUE";
    public static final String P_REAL_CREDIT = "REAL_CREDIT";
    public static final String P_CREDIT_VALUE = "CREDIT_VALUE";
    public static final String P_OVERDRAFT_VALUE = "OVERDRAFT_VALUE";
    public static final String P_BALANCE_STATE = "STATE";
    public static final String P_BALANCE_COLOR = "COLOR";
    public static final String P_CREDITABLE_FLAG = "CREDITABLE_FLAG";
    public static final String P_NEXT_RESET_DATE = "NEXT_RESET_DATE";
    public static final String P_BALANCE_SNAPSHOT_DATE = "BALANCE_SNAPSHOT_DATE";
    public static final String P_BP_ID = "BP_ID";
    public static final String P_LIST_OF_BALANCES = "BALANCES";

    // Create new Large Account, or convert a flat customer to LA
    public static final String CMD_LA_MEMBER_NEW = "LA_MEMBER.NEW";

    // Last status change on CONTRACT.READ
    public static final String P_CO_LAST_REASON = "CO_LAST_REASON";
    public static final String CMD_PAYMENT_ARRANGEMENT_ASSIGNMENT_WRITE = "PAYMENT_ARRANGEMENT_ASSIGNMENT.WRITE";

    public static final String CST_INFOFIELD_MASTERPLAY = "TEXT15";
    public static final String CST_INFOFIELD_CONTRACTCASTOR = "TEXT14";

    // Customer.Read parameter
    public static final String P_SYNC_WITH_DB = "SYNC_WITH_DB";


    // Key in castor catalog containing BSCS code
    public static final String CST_BSCS_REFERENTIAL_ID = "BSCS_CODE";

    // default platform encoding
    public static final String CST_DEFAULT_CHARSET = "UTF-8";

    public static final String P_SEARCH_IS_COMPLETE = "SEARCH_IS_COMPLETE";
    public static final String P_START_INDEX = "START_INDEX";
    public static final String P_SEARCH_BLOCK = "SEARCH_BLOCK";
    public static final String P_MIN_BLK_SIZE = "MIN_BLK_SIZE";
    public static final String P_MAX_BLK_SIZE = "MAX_BLK_SIZE";


}
