package com.orange.apibss.cucumber.config;

import com.orange.apibss.cucumber.config.customerBill.CustomerBillProperties;
import lombok.Data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * Properties for integration tests
 * 
 * @author Thibault Duperron
 *
 */
@Data
@ConfigurationProperties(prefix = "apibss", ignoreUnknownFields = false)
public class ApibssProperties {
	/**
	 * Url of the product inventory API
	 */
	private String productInventoryApiUrl;
	/**
	 * Url of the party API
	 */
	private String partyApiUrl;
	/**
	 * Url of the product ordering API
	 */
	private String productOrderingUrl;
	/**
     * Url of the product catalog API
     */
    private String productCatalogUrl;
    /**
     * Url of the bucket balance API
     */
    private String bucketBalanceUrl;
	/**
	 * Url of the customer bill API
	 */
	private String customerBillUrl;
	/**
	 * Url of the billing account API
	 */
	private String billingAccountUrl;
	/**
	 * Url of the eligibility API
	 */
	private String eligibilityUrl;
	/**
     * Url of the payment API
     */
    private String paymentUrl;

	private ProductInventoryProperties productInventory;

	private PartyProperties party;

	private CustomerBillProperties customerBill;

	private List<Header> headers;
}
