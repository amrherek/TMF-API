package com.orange.apibss.cucumber.party;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.party.IndividualProperties;
import com.orange.apibss.party.model.Individual;

import cucumber.api.java.en.Given;

/**
 * @author Thibault Duperron
 *
 */
public class IndividualGivenStepDefs extends StepDefs {
    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private IndividualSharedData individualSharedData;
    @Autowired
    private IndividualWhenStepDefs individualWhenStepDefs;


    @Given("^Have a new individual$")
    public void have_a_new_individual() throws Throwable {
        individualSharedData.setTestIndividual(new Individual());
    }

    @Given("^Have an existing individual$")
    public void have_an_existing_individual() throws Throwable {
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        // Set the ID for searching the customer
        individualWhenStepDefs.use_id("updatable");
        // Search the customer
        individualWhenStepDefs.refer_to_individual();
        // Assert the given went well
        Individual individual = individualSharedData.getIndividual();
        checkNoException();
        assertThat(individual).isNotNull();
        assertThat(individual.getId()).isEqualTo(properties.getUpdatableId());
        individualSharedData.setTestIndividual(individual);
    }

    @Given("^Have an existing tutor")
    public void have_an_existing_tutor() throws Throwable {
        // Set the ID for searching the customer's tutor
        individualWhenStepDefs.use_id("updatable");
        individualWhenStepDefs.use_a_tutor_id("updatable");
        // Search the customer
        individualWhenStepDefs.refer_to_tutor();
        // Assert the given went well
        Individual individual = individualSharedData.getIndividual();
        checkNoException();
        assertThat(individual).isNotNull();
        individualSharedData.setTestIndividual(individual);
    }
}
