package com.orange.apibss.cucumber.party;

import com.orange.apibss.party.model.Individual;
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
public class IndividualSharedData {

    /**
     * Individual return by the API calls
     */
    private Individual individual;
    /**
     * Individuals return by the API calls
     */
    private List<Individual> individuals;
    /**
     * Individual use for calling the API (create, update)
     */
    private Individual testIndividual;
}
