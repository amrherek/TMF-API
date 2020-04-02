package com.orange.apibss.cucumber.config;

import java.util.List;

import com.orange.apibss.billingAccount.model.BillingAccount;
import com.orange.apibss.cucumber.config.billingAccount.BillingAccountId;
import com.orange.apibss.cucumber.config.billingAccount.PartyId;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for billing account tests
 * 
 * @author Stéphanie Gerbaud
 *
 */
@Data
@ConfigurationProperties(prefix = "billingAccount", ignoreUnknownFields = false)
public class BillingAccountProperties {
    
    private BillingAccountId billingAccountId;
    
    private PartyId partyId;
    
    private Msisdn msisdn;

    private List<BillingAccount> valid;
    
    private List<BillingAccount> several;
	
}
