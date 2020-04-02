package com.orange.apibss.cucumber.party;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.party.IndividualProperties;
import com.orange.apibss.party.model.Individual;

import cucumber.api.java.en.Then;

/**
 * @author Thibault Duperron
 *
 */
public class IndividuaThenStepDefs extends StepDefs {
    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private IndividualSharedData individualSharedData;

    @Then("^Get (valid|minor|tutor|with id) individual$")
    public void get_valid_individual(String type) throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividual()).isNotNull();
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        final Individual result;
        switch(type){
            case "valid":
                result = properties.getValid();
                break;
            case "minor":
                result = properties.getMinor();
                break;
            case "tutor":
                result = properties.getTutor();
                break;
            case "with id":
                result = properties.getWithId();
                break;
            default:
                throw new IllegalArgumentException("Can't use " + type + "for get (.*) individual");
        }
        assertThat(individualSharedData.getIndividual()).isEqualTo(result);
    }

    @Then("^Get individual$")
    public void get_individual() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividual()).isNotNull();
    }
    @Then("^Contain individual$")
    public void contain_individual() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividuals()).isNotNull();
    }
    @Then("^Contain valid individual$")
    public void contain_valid_individual() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividuals()).isNotNull();
        Individual result = apibssProperties.getParty().getIndividual().getValid();
        assertThat(individualSharedData.getIndividuals()).contains(result);
    }

    @Then("^The API return updated user$")
    public void the_api_return_updated_user() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividual()).isNotNull();
        assertThat(individualSharedData.getIndividual()).isEqualTo(individualSharedData.getTestIndividual());
    }

    @Then("^The API return updated tutor")
    public void the_api_return_updated_tutor() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividual()).isNotNull();
        assertThat(individualSharedData.getIndividual()).isEqualTo(individualSharedData.getTestIndividual());
    }

    @Then("^The API return new user$")
    public void the_api_return_new_user() throws Throwable {
        assertThat(sharedData.getException()).isNull();
        assertThat(individualSharedData.getIndividual()).isNotNull();
        assertThat(individualSharedData.getIndividual()).isEqualTo(individualSharedData.getTestIndividual());
    }

	@Then("^Data inconsistent error$")
	public void data_inconsistent_error() throws Throwable {
		checkError(HttpStatus.BAD_REQUEST, 4100, "Data inconsistent", "");
	}

}
