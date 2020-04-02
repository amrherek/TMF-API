package com.orange.mea.apisi.productordering.constants;

import java.util.Arrays;
import java.util.List;

public class ProductOrderingConstants {

    private ProductOrderingConstants() {
    }

    public static final String OFFER = "offer";
    public static final String OPTION = "option";
    public static final String SERVICE_BSCS = "serviceBSCS";
    public static final String ACCESS_SERVICE = "accessService";
    public static final String FAF = "faf";
    public static final String EMERGENCY_VOICE = "emergencyVoice";
    public static final String EMERGENCY_DATA = "emergencyData";
    public static final String EMERGENCY_CREDIT = "emergencyCredit";
    public static final List<String> EMERGENCY_SERVICES = Arrays.asList(EMERGENCY_DATA, EMERGENCY_VOICE,
            EMERGENCY_CREDIT);
    public static final String TRANSFER_DATA = "transferData";

    public static final String TRANSFER_CREDIT = "transferCredit";
    public static final String TRANSFER_CREDIT_POSTPAID = "transferCreditPostpaid";

    public static final List<String> BUNDLE_SERVICES = Arrays.asList("bundleData", "bundleVoice");

}
