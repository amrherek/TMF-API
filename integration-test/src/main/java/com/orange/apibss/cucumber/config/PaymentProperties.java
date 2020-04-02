package com.orange.apibss.cucumber.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.orange.apibss.payment.model.Payment;

import lombok.Data;

/**
 * Properties for payment tests
 * 
 * @author Stéphanie Gerbaud
 *
 */
@Data
@ConfigurationProperties(prefix = "payment", ignoreUnknownFields = false)
public class PaymentProperties {

    private Id paymentId;
    
    private Id partyId;
    
    private List<Payment> payments;
    
}
