package com.orange.apibss.cucumber.eligibility;

import com.orange.apibss.eligibility.model.EligibleContract;
import com.orange.apibss.eligibility.model.EligibleOption;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Thibault Duperron
 */
@Component
// New bean for each scenario
@Scope("cucumber-glue")
@Data
public class EligibilitySharedData {
    private List<EligibleOption> eligibleOptions;
    private EligibleContract eligibleContract;
}
