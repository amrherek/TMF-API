package com.orange.apibss.cucumber.config;

import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Properties for eligibility tests
 *
 * @author Thibault Duperron
 */
@Data
@ConfigurationProperties(prefix = "eligibility", ignoreUnknownFields = false)
public class EligibilityProperties {

    private String msisdnValid;
    private String msisdnInvalid;
    private String msisdnUnknown;
    private String productOfferingId;

    private List<EligibleOption> eligibleOptions;
    private List<EligibleOption> eligibleOptionsFiltered;
    private List<EligibleOption> transferDataEligibleOptions;
    private List<EligibleOption> emergencyVoiceEligibleOptions;
    private List<EligibleOption> dataBundleEligibleOptions;
    private List<EligibleOption> emergencyCreditEligibleOptions;
    
    private EligibleContract eligibleContract;
}
