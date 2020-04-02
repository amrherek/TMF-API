package com.orange.bscs.service.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BscsFieldExceptionEnum {

    CUSTOMER_ID("CS_ID", "CS_ID_PUB"), //
    CONTRACT_ID("CO_ID", "CO_ID_PUB"), //
    TITLE("TTL_ID"), //
    FINANCIAL_DOCUMENT_ID("DOCUMENT_ID"), //
    BUSINESS_TRANSACTION_ID("BT_OHXACT"), //
    FINANCIAL_TRANSACTION_ID("TRANSACTION_ID"), //
    BILLING_ACCOUNT_ID("BILLING_ACCOUNT_ID", "BILLING_ACCOUNT_CODE"),//
    IDENTIFICATION_TYPE("IDTYPE_CODE"), //
    EMAIL("emailAddress", "ADR_EMAIL"), //
    CUSTOMER_GROUP_CODE("PRG_CODE"),//
    SERVICE_ID("SN_CODE"),//
    SERVICE_PACKAGE_ID("SP_CODE"), //
    PARAMETER_ID("PRM_ID"),//
    PARAMETER_VALUE("VALUE"),//
    MSISDN("DIRNUM"),//
    STORAGE_MEDIUM("SM_NUM"),//
    REASON("REASON"),//
    STATUS("CO_STATUS"),//
    RESOURCE_NUM("OLD_RES_NUM", "RES_ID"),//
    RATE_PLAN_ID("RPCODE"), //
    FREE_UNIT_PACKAGE_ID("FUP_ID"),//
    POSTAL_CODE("ADR_ZIP"),//
    DOCUMENT_REF("DOCUMENT_REF_NUM"),//
    USE_CASE_CODE("USE_CASE_CODE"),//
    AMOUNT("ASS_AMOUNT_CASH_PAY"),//
    CURRENCY("CURRENCY"),//
    ASSIGNEMENT_LEVEL("FUP_ASS_LEVEL"),//
    ;

    private List<String> fieldNames;
    private static Map<String, BscsFieldExceptionEnum> enumMap = new HashMap<>();

    static {
        for (BscsFieldExceptionEnum enumField : values()) {
            for (String field : enumField.fieldNames) {
                enumMap.put(field, enumField);
            }
        }
    }

    private BscsFieldExceptionEnum(String... fieldName) {
        fieldNames = Arrays.asList(fieldName);
    }

    public static BscsFieldExceptionEnum buildBscsFieldExceptionEnum(String field) {
        if (field == null) {
            return null;
        }
        return enumMap.get(field);
    }

}
