package com.orange.apibss.common.utils;

import org.springframework.beans.factory.annotation.Value;

public class InternationalMsisdnTool {

    @Value("${msisdn.international.prefix}")
    private String prefix;

    @Value("${msisdn.local.length}")
    private Integer localLength;

    public String removeInternationalPrefix(String msisdn) {
        if (msisdn != null && msisdn.length() == (localLength + prefix.length()) && msisdn.startsWith(prefix)) {
            return msisdn.substring(3);
        }
        return msisdn;
    }

    public String addInternationalPrefix(String msisdn) {
        if (msisdn != null && msisdn.length() == localLength) {
            return prefix + msisdn;
        }
        return msisdn;
    }

}
