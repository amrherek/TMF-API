package com.orange.mea.apisi.productordering.exception;

public final class ProductOrderingApiExceptionCode {

    private ProductOrderingApiExceptionCode() {
    }

	// technical
    public static final Integer TC_PARTY = 5200;

	// functionnal
    public static final Integer FC_CONTRACT_PARTY_UPDATE = 4101;
    public static final Integer FC_SERVICE_STATE_CHANGE = 4102;
    public static final Integer FC_SIM_SWAP = 4103;
    public static final Integer FC_CONTRACT_STATE_CHANGE = 4104;
    public static final Integer FC_SERVICE_ADD = 4105;
    public static final Integer FC_OPTION_ADD = 4106;
    public static final Integer FC_SERVICE_PARAMETERS_CHANGE = 4107;
    public static final Integer FC_INVALID_MSISDN = 4108;
    public static final Integer FC_NO_FAF = 4109;
    public static final Integer FC_MIGRATION = 4110;

}
