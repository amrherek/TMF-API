package com.orange.apibss.cucumber.customerBill;

import com.orange.apibss.cucumber.config.customerBill.User;
import com.orange.apibss.customerbill.model.CustomerBill;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Date : 02/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class CustomerBillSharedData {
    private List<CustomerBill> bills;
    private String msisdn;
    private String partyId;

    private User user;
    private String limit;
    private String startDate;
    private String endDate;

    private String customerBillId;
    
    private Boolean withHierarchy;
    
    private HttpHeaders headers;

    public void setBill(CustomerBill bill) {
        bills = new ArrayList<>();
        bills.add(bill);
    }
}
