package com.orange.apibss.cucumber.party;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.party.IndividualProperties;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.ContactMediumType;
import com.orange.apibss.party.model.Gender;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.apibss.party.model.Medium;

import cucumber.api.java.en.When;

/**
 * @author Thibault Duperron
 *
 */
public class IndividualWhenStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private IndividualSharedData individualSharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^Change the given name$")
    public void change_the_given_name() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        individualSharedData.getTestIndividual().setGivenName(uuid);
    }

    @When("^Change the family name$")
    public void change_the_family_name() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        individualSharedData.getTestIndividual().setFamilyName(uuid);
    }
    @When("^Change the email with (valid|invalid)$")
    public void change_the_email(String type) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String email;
        switch(type){
            case "valid":
                email = uuid.substring(0,5) + "@pony.com";
                break;
            case "invalid":
                email = uuid.substring(0,5) + "pony.com";
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Change the email with (.*)\" step");
        }
        boolean updated = false;
        Individual testIndividual = individualSharedData.getTestIndividual();
        for (ContactMedium cm : testIndividual.getContactMedium()){
            if (ContactMediumType.EMAILADDRESS.equals(cm.getType())) {
                cm.getMedium().setEmailAddress(email);
                updated = true;
                break;
            }
        }
        // TODO extraire une methode add_new_email qui permetrait de mettre 2 emails de contacts
        if(!updated){
            ContactMedium cm = new ContactMedium();
            cm.setType(ContactMediumType.EMAILADDRESS);
            Medium m = new Medium();
            m.setEmailAddress(email);
            testIndividual.addContactMediumItem(cm);
        }
        testIndividual.setFamilyName(uuid);
    }

	@When("^Change the gender with (valid|incompatible)$")
	public void change_the_gender(String type) {
        Gender gender;
		switch (type) {
		case "valid":
            gender = Gender.MALE;
			break;
		case "incompatible":
            gender = Gender.FEMALE;
			break;
		default:
			throw new IllegalArgumentException("Can't use key " + type + " on \"Change the gender with (.*)\" step");
		}
		individualSharedData.getTestIndividual().setGender(gender);
	}

	@When("^Change the title with (valid|invalid|incompatible)$")
	public void change_the_title(String type) {
		String title;
		switch (type) {
		case "valid":
			title = "2";
			break;
		case "invalid":
			title = "998";
			break;
		case "incompatible":
			title = "1";
			break;
		default:
			throw new IllegalArgumentException("Can't use key " + type + " on \"Change the title with (.*)\" step");
		}
		individualSharedData.getTestIndividual().setTitle(title);
		;
	}

    @When("^Change the birth date$")
    public void change_the_birth_date() {
        individualSharedData.getTestIndividual().setBirthDate(Tools.randomDate(21,99));
    }

    @When("^Change the identification")
    public void change_the_identification() {
		IndividualIdentification identification = individualSharedData.getTestIndividual().getIndividualIdentification()
				.get(0);
        identification.setType(Tools.randomString());
        identification.setExpirationDate(Tools.randomDate(20));
        individualSharedData.getTestIndividual().setBirthDate(Tools.randomDate(21,99));
    }

    @When("^Change the registration status")
    public void change_the_registration_status() {
        List<Characteristic> characteristics = individualSharedData.getTestIndividual().getCharacteristic();
        if (characteristics == null) {
            characteristics = new ArrayList<>();
        }
        Characteristic registrationStatus = null;
        for(Characteristic characteristic : characteristics) {
            if (characteristic.getName().equals("registrationStatus")) {
                registrationStatus = characteristic;
            }
        }
        if (registrationStatus == null) {
            registrationStatus = new Characteristic();
            registrationStatus.setName("registrationStatus");
            characteristics.add(registrationStatus);
        }
        registrationStatus.setValue(Tools.randomString());
    }

    @When("^Create the customer$")
    public void create_the_customer() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getPartyApiUrl() + "/individual/");
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST,
                    new HttpEntity<>(individualSharedData.getTestIndividual(), headers), Individual.class);
            individualSharedData.setIndividual(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }
    @When("^Update the customer$")
    public void update_the_customer() {
        try {
            Individual testIndividual = individualSharedData.getTestIndividual();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getPartyApiUrl() + "/individual/" + testIndividual.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT, new HttpEntity<>(testIndividual, headers), Individual.class);
            individualSharedData.setIndividual(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Use a customer id (valid|invalid|unknown|updatable|minor|with id)")
    public void use_id(String type) {
        final Arguments arg = Arguments.individual;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                arguments.put(arg, properties.getValid().getId());
                break;
            case "invalid":
                arguments.put(arg, properties.getInvalidId());
                break;
            case "unknown":
                arguments.put(arg, properties.getUnknownId());
                break;
            case "updatable":
                arguments.put(arg, properties.getUpdatableId());
                break;
            case "minor":
                arguments.put(arg, properties.getMinor().getId());
                break;
        case "with id":
            arguments.put(arg, properties.getWithId().getId());
            break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer id (.*)\" step");
        }
    }

    @When("^Use a customer family name (valid)")
    public void use_family_name(String type) {
        final Arguments arg = Arguments.familyName;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                arguments.put(arg, properties.getValid().getFamilyName());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer family name (.*)\" step");
        }
    }

    @When("^Use a customer given name (valid)")
    public void use_given_name(String type) {
        final Arguments arg = Arguments.givenName;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                arguments.put(arg, properties.getValid().getGivenName());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer given name (.*)\" step");
        }
    }

    @When("^Use a customer email (valid)")
    public void use_email(String type) {
        final Arguments arg = Arguments.email;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                arguments.put(arg, Tools.extractEmail(properties.getValid().getContactMedium()));
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer email (.*)\" step");
        }
    }

    @When("^Use a customer identification type (valid)")
    public void use_identification_type(String type) {
        final Arguments arg = Arguments.identificationType;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                // TODO faire mieux que ça
            arguments.put(arg, properties.getValid().getIndividualIdentification() == null ? "1"
					: properties.getValid().getIndividualIdentification().get(0).getType());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer identification type (.*)\" step");
        }
    }

    @When("^Use a customer identification id (valid)")
    public void use_identification_id(String type) {
        final Arguments arg = Arguments.identificationId;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                // TODO faire mieux que ça
            arguments.put(arg, properties.getValid().getIndividualIdentification() == null ? "1234"
					: properties.getValid().getIndividualIdentification().get(0).getIdentificationId());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer identification id (.*)\" step");
        }
    }


    @When("^Use a tutor id (valid|invalid|unknown|blank)")
    public void use_a_tutor_id(String type) {
        final Arguments arg = Arguments.tutorId;
        IndividualProperties properties = apibssProperties.getParty().getIndividual();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
            case "valid":
                arguments.put(arg, properties.getValidTutorId());
                break;
            case "invalid":
                arguments.put(arg, properties.getInvalidTutorId());
                break;
            case "unknown":
                arguments.put(arg, properties.getUnknownTutorId());
                break;
            case "updatable":
                arguments.put(arg, properties.getUpdatableTutorId());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a customer identification id (.*)\" step");
        }
    }

    @When("^Refer to individual$")
    public void refer_to_individual() throws Throwable {
        try {
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual> response = restTemplate.exchange(buildUriIndividual(), HttpMethod.GET,
                    new HttpEntity<>(headers), Individual.class);
            individualSharedData.setIndividual(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Refer to the tutor$")
    public void refer_to_tutor() throws Throwable {
        // refer to tutor need an individual ID
        Map<Arguments, String> arguments = sharedData.getArguments();
        final String individualId = arguments.get(Arguments.individual);
        final String tutorId = arguments.get(Arguments.tutorId);
        assertThat(individualId).isNotNull();
        assertThat(tutorId).isNotNull();
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getPartyApiUrl());
            builder.pathSegment("individual", individualId);
            builder.pathSegment("tutor");
            builder.queryParam("contractId", tutorId);
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual> response = this.restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Individual.class);
            individualSharedData.setIndividual(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }
    @When("^Refer to individuals$")
    public void refer_to_individuals() throws Throwable {
        try {
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual[]> response = restTemplate.exchange(buildUriIndividual(), HttpMethod.GET,
                    new HttpEntity<>(headers), Individual[].class);

            individualSharedData.setIndividuals(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Update the tutor")
    public void update_the_tutor() throws Throwable{
        try {
            IndividualProperties properties = apibssProperties.getParty().getIndividual();
            Individual testIndividual = individualSharedData.getTestIndividual();
            // TODO revoir la manière de passer les ID pour la requête
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getPartyApiUrl() + "/individual/" + properties.getMinor().getId() + "/tutor?contractId=" + properties.getUpdatableTutorId());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Individual> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.PUT, new HttpEntity<>(testIndividual, headers), Individual.class);
            individualSharedData.setIndividual(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    /**
     * Uri for Individuals
     * @return the URI
     */
    private URI buildUriIndividual(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getPartyApiUrl() + "/individual");
        Map<Arguments, String> arguments = sharedData.getArguments();
        for (Arguments key : arguments.keySet()) {
            String value = arguments.get(key);
            switch (key) {
                case individual:
                    builder.pathSegment(value);
                    break;
			case identificationType:
				builder.queryParam("individualIdentification.type", value);
				break;
			case identificationId:
				builder.queryParam("individualIdentification.identificationId", value);
				break;
                default:
                    builder.queryParam(key.name(), value);
                    break;
            }
        }
        return builder.build().toUri();
    }
}
