package com.orange.apibss.cucumber.config;

import lombok.Data;

@Data
public class Msisdn {

    private String valid;

    private String invalid;
    
    private String prepaid;
    
    private String postpaid;
    
    public String badFormat;

}
