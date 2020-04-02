package com.orange.mea.apisi.party.backend.bscs.transformer;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.Gender;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.apibss.party.model.MaritalStatus;
import com.orange.apibss.party.model.RelatedParty;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.BscsCustomer;
import com.orange.bscs.model.businesspartner.EnumMaritalStatus;
import com.orange.mea.apisi.party.backend.bscs.BscsConstantsService;
import com.orange.mea.apisi.party.exceptions.CountryNotFoundException;
import com.orange.mea.apisi.party.exceptions.CustomerGroupNotFoundException;
import com.orange.mea.apisi.party.exceptions.MaritalStatusNotFoundException;
import com.orange.mea.apisi.party.exceptions.MaritalStatusNotKnownException;

/**
 * 
 * Transformer from BscsAddress to Individual
 * 
 * 
 * @author xbbs3851
 * 
 */
@Service
public class BscsAddressToIndividualTransformer {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private BscsConstantsService constantsService;

	@Autowired
	private BscsAddressToRelatedPartyTransformer partyTransformer;

	@Autowired
	private BscsAddressToCharacteristicTransformer characteristicTransformer;

	@Autowired
	private BscsAddressToIndividualIdentificationTransformer individualIdentificationTransformer;

	@Autowired
	private BscsAddressToContactMediumTransformer contactMediumTransformer;

	@Autowired
	private BscsMaritalStatusToIndividualMaritalStatusTransformer maritalStatusTransformer;

	/**
     * Transforms a BscsAddress to a TMF Individual object
     * 
     * @param address
     * @param customer
     * @return
     * @throws ApiException
     */
    public Individual doTransform(BscsAddress address, BscsCustomer customer) throws ApiException {

        // build a full individual tmf representation from BscsAddress
		Individual individual = new Individual();

        individual.setId(address.getCustomerId());
        individual.setGivenName(address.getFirstName());
        individual.setMaritalStatus(transformMaritalStatus(address));
        individual.setGender(transformGender(address));
        individual.setFamilyName(address.getLastName());
        if (address.getBirthDate() != null) {
            individual.setBirthDate(new LocalDate(address.getBirthDate(), DateTimeZone.UTC));
        }
        individual.setNationality(transformNationality(address));
        individual.setTitle(ObjectUtils.toString(address.getTitleId(), null));

        individual.setCharacteristic(buildCharacteristic(address, customer));
        individual.setIndividualIdentification(buildIndividualIdentification(address));

        individual.setContactMedium(buildContactMedium(address));

		return individual;
	}

	/**
     * Extract country from BscsAddress using its code
     * 
     * @param address
     * @return
     * @throws CountryNotFoundException
     */
    private String transformNationality(BscsAddress address) throws CountryNotFoundException {

        Long countryId = address.getNationalityId();
		if (countryId != null) {
            BscsCountry country = constantsService.getCountryById(countryId);
			return country.getIso2();
		}
		return null;
	}

	/**
     * Extract Marital status from BscsAddress
     * 
     * @param address
     * @return
     * @throws ApiException
     */
    private MaritalStatus transformMaritalStatus(BscsAddress address) throws ApiException {
        Long maritalStatusCode = address.getMaritalStatusId();
		if (maritalStatusCode != null) {
			EnumMaritalStatus status = EnumMaritalStatus.parseLong(maritalStatusCode);
			if (status == null) {
				logger.error("Martial status code not found in EnumMaritalStatus : [{}]", maritalStatusCode);
				throw new MaritalStatusNotFoundException(
						"Martial status code not found in EnumMaritalStatus : " + maritalStatusCode);
			}
            MaritalStatus enumStatus = maritalStatusTransformer.doTransform(status);
			if (enumStatus == null) {
				logger.error("Martial status not known in IndividualMaritalStatus : [{}]", status.getDescription());
				throw new MaritalStatusNotKnownException(
						"Martial status not known in IndividualMaritalStatus : " + status.getDescription());
			}
			return enumStatus;
		}
		return null;
	}

	/**
     * Extract gender from BscsAddress
     * 
     * @param address
     * @return
     */
    private Gender transformGender(BscsAddress address) {

		// Gender
        if (address.getSex() != null) {
            Character gender = address.getSex();

			if (gender.equals('F')) {
                return Gender.FEMALE;
			} else if (gender.equals('M')) {
                return Gender.MALE;
			}
		}
		return null;

	}

	/**
	 * 
	 * @param bscsAdress
	 * @return party list
	 */
	protected List<RelatedParty> buildRelatedParty(BscsAddress bscsAdress) {
		return partyTransformer.doTransform(bscsAdress);
	}

	/**
	 * 
	 * @param bscsAdress
	 * @return party list
	 * @throws CountryNotFoundException
	 */
	protected List<ContactMedium> buildContactMedium(BscsAddress bscsAdress) throws CountryNotFoundException {
		return contactMediumTransformer.doTransform(bscsAdress);
	}

	/**
     * 
     * @param address
     * @param customer
     * @return characteristic list
     * @throws CustomerGroupNotFoundException
     */
    protected List<Characteristic> buildCharacteristic(BscsAddress address, BscsCustomer customer) throws CustomerGroupNotFoundException {
        return characteristicTransformer.doTransform(address, customer);
	}

	/**
     * 
     * @param address
     * @return identification list
     */
    protected List<IndividualIdentification> buildIndividualIdentification(BscsAddress address) {
        return individualIdentificationTransformer.doTransform(address);
	}

}
