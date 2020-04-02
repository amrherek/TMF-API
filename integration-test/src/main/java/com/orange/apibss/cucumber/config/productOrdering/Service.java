package com.orange.apibss.cucumber.config.productOrdering;

import lombok.Data;

@Data
public class Service {
    
    private String validProductId;
    private String invalidProductId;
    private String terminatedProductId;
    
    private String invalidServiceId;
    private String badServiceId;
    private String activatedServiceId;
    private String invalidServicePackageId;
    private String badServicePackageId;
    
    private String validServiceIdAdd;
    private String validServiceIdActivate;   
    private String validServicePackageIdAdd;    
    
    private String validServiceIdWithparametersModify;
    private String validServiceIdWithparametersAdd;
    private String validServicePackageIdWithparameters;
    private String validParameterId;
    private String invalidParameterId;
    private String validParameterValue;
    private String invalidParameterValue;
}
