package com.orange.apibss.cucumber.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.orange.apibss.cucumber.config.productOrdering.ActivateOffer;
import com.orange.apibss.cucumber.config.productOrdering.ProductOrderCommonProperties;
import com.orange.apibss.cucumber.config.productOrdering.PublicKey;
import com.orange.apibss.cucumber.config.productOrdering.Service;
import com.orange.apibss.cucumber.config.productOrdering.SimSwap;

import lombok.Data;

/**
 * Properties for product ordering tests
 *
 * @author Thibault Duperron
 */
@Data
@ConfigurationProperties(prefix = "productOrdering", ignoreUnknownFields = false)
public class ProductOrderingProperties {

    private ActivateOffer activateOffer;

    private SimSwap simSwap;

    private ProductOrderCommonProperties friendAndFamily;

    /**
     * Update tarif plan (um13)
     */
    private ProductOrderCommonProperties um13;

    /**
     * Purchase of punctual  with immediate payment  or recurring volume of usage
     - volume of usage = bundle (voice or sms/mms or data) (um7)
     */
    private ProductOrderCommonProperties um7;
    
    private ProductOrderCommonProperties emergencyCredit;
    
    private ProductOrderCommonProperties dataBundle;
    
    private PublicKey publicKey;
    
    private Service service;
    
}
