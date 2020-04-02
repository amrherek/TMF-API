package com.orange.apibss.cucumber.productOrdering;

import org.springframework.http.HttpStatus;

import com.orange.apibss.cucumber.StepDefs;

import cucumber.api.java.en.Then;

/**
 * @author Thibault Duperron
 *
 */
public class ProductOrderingThenStepDefs extends StepDefs {

	@Then("^Contract state change error$")
    public void contract_state_change_error() throws Throwable {
		checkError(HttpStatus.BAD_REQUEST, 4104, "Contract state change impossible", "");
	}

    @Then("^Invalid contract error$")
    public void invalid_contract_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4100, "Contract not found", "");
    }

    @Then("^Service state change error$")
    public void service_state_change_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4102, "Service state change impossible", "");
    }

    @Then("^Service add error$")
    public void service_add_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4105, "Service add impossible", "");
    }

    @Then("^Sim swap error$")
    public void sim_swap_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4103, "Sim swap impossible", "");
    }

    @Then("^Option add error$")
    public void option_add_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4106, "Option add / delete impossible", "");
    }

    @Then("^Parameter modify error$")
    public void parameter_modify_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4107, "Service parameters change impossible", "");
    }

    @Then("^Migration error$")
    public void migration_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4110, "Migration impossible", "");
    }

}
