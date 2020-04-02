package com.orange.mea.apisi.customerbill.constants;

/**
 * The constants related to Customer bill API
 */
public final class CustomerBillConstants {

    private CustomerBillConstants() {
    }

    /**
     * getCustomerBill
     */
    public final static String CUSTOMER_BILL_GET_METHOD_NAME = "getCustomerBill";

    /**
     * downloadCustomerBill
     */
    public final static String CUSTOMER_BILL_DOWNLOAD_METHOD_NAME = "downloadCustomerBill";

    /**
     * findCustomerBill
     */
    public final static String CUSTOMER_BILL_FIND_BY_MSISDN_METHOD_NAME = "listCustomerBillsByMsisdn";

    /**
     * findCustomerBill
     */
    public final static String CUSTOMER_BILL_FIND_BY_PARTY_ID_METHOD_NAME = "listCustomerBillsByPartyId";

}
