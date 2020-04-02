package com.orange.apibss.cucumber.payment;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.orange.apibss.payment.model.Payment;

import lombok.Data;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class PaymentSharedData {

    private List<Payment> payments;
    
    private Payment payment;

}
