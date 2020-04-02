package com.orange.apibss.cucumber.config.billingAccount;

import lombok.Data;

@Data
public class PartyId {

    private String valid;

    private String invalid;
    
    private String withSeveralBillingAccounts;
    
    public String badFormat;

}
