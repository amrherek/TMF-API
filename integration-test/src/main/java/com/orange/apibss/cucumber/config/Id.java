package com.orange.apibss.cucumber.config;

import lombok.Data;

@Data
public class Id {

    private String valid;

    private String invalid;
    
    public String badFormat;
    
    public String largeAccount;

}
