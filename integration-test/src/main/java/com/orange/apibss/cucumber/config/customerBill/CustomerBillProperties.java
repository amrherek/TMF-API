package com.orange.apibss.cucumber.config.customerBill;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.orange.apibss.cucumber.config.Id;
import com.orange.apibss.cucumber.config.Msisdn;
import com.orange.apibss.customerbill.model.CustomerBill;

import lombok.Data;


/**
 * Date : 09/02/2017.
 *
 * @author Denis Borscia (deyb6792)
 */
@Data
public class CustomerBillProperties {

    private Msisdn msisdn;

    private User user;
    
    private Id id;
    
    private Id partyId;
    
    private String date;
    
    private List<CustomerBill> bills;
    
    private String billImageFormat;
    private Long billImageSize;

}
