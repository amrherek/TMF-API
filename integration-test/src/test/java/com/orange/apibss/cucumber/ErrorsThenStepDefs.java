package com.orange.apibss.cucumber;

import org.springframework.http.HttpStatus;

import cucumber.api.java.en.Then;

/**
 * @author Thibault Duperron
 *
 */
public class ErrorsThenStepDefs extends StepDefs {

	@Then("^Bad request error$")
	public void bad_request_error() throws Throwable {
		checkError(HttpStatus.BAD_REQUEST, 4000, "Bad request", "");
	}
	@Then("^Empty parameter error$")
	public void empty_parameter_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4001, "Missing parameter", "Parameter (.*) is missing, null or empty");
	}

	@Then("^Bad parameter format error$")
	public void bad_parameter_format_error() throws Throwable {
		checkError(HttpStatus.BAD_REQUEST, 4002, "Bad parameter format", "Bad format for parameter (.*): (.*), (.*)");
    }

	@Then("^Bad parameter value error$")
	public void bad_parameter_value_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4003, "Bad parameter value", "");
	}

	@Then("^Too many parameter error$")
	public void Too_many_parameter_error() throws Throwable {
		checkError(HttpStatus.BAD_REQUEST, 4006, "Too many parameters", "");
	}

	@Then("^Missing parameter error$")
	public void missing_parameter_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4001, "Missing parameter", "");
	}

    @Then("^Invalid msisdn error$")
    public void invalid_msisdn_error() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4200, "Invalid Msisdn", "");
    }

	@Then("^Not found error$")
	public void not_found_error() throws Throwable {
		checkError(HttpStatus.NOT_FOUND, 4040, "Not found", "");
	}

	@Then("^Internal server error$")
	public void internal_server_error() throws Throwable {
		checkError(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "Internal error", "");
    }

	@Then("^Internal BSCS error$")
	public void internal_bscs_error() throws Throwable {
        checkError(HttpStatus.INTERNAL_SERVER_ERROR, 5100, "BSCS internal error", "");
	}

    @Then("^Internal WS error$")
    public void internal_ws_error() throws Throwable {
        checkError(HttpStatus.INTERNAL_SERVER_ERROR, 5300, "Webservice call internal error", "");
    }

    @Then("^Not implemented error$")
    public void not_implemented_error() throws Throwable {
        checkError(HttpStatus.INTERNAL_SERVER_ERROR, 5003, "Not implemented", "");
    }

}
