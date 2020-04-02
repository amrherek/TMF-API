package com.orange.apibss.cucumber.billingAccount;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orange.apibss.billingAccount.model.BillingAccount;

import lombok.Data;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class BillingAccountSharedData {

    private List<BillingAccount> billingAccounts;
    
    private BillingAccount billingAccount;

}
