package com.orange.apibss.cucumber.config.billingAccount;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Properties for BillingAccountId
 * 
 * @author Thibault Duperron
 *
 */
@ToString
public class BillingAccountId {
    /**
     * BillingAccountId valid
     */
    @Getter
    @Setter
    public String valid;

	/**
	 * BillingAccountId that does not exist
	 */
	@Getter
	@Setter
	public String invalid;

	/**
     * BillingAccountId not well formated
     */
    @Getter
    @Setter
    public String badFormat;
}
