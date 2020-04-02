package com.orange.mea.apisi.bucketbalance.enums;

/**
 * Enumeration pour type recharge
 * 
 * @author 
 *
 */
public enum RechargeTypeEnum {
	EXPRESS("R"),
	PRIVATE("PR"),
    ;

    private String value;

    private RechargeTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}