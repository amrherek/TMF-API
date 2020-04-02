package com.orange.mea.apisi.party.backend.bscs.transformer;

import org.apache.cxf.common.util.StringUtils;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.technical.EnumTechnicalException;
import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.ContactMediumType;
import com.orange.apibss.party.model.Gender;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCountry;
import com.orange.bscs.model.businesspartner.EnumMaritalStatus;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.mea.apisi.party.backend.bscs.BscsConstantsService;
import com.orange.mea.apisi.party.constants.PartyConstants;
import com.orange.mea.apisi.party.exceptions.CountryNotFoundException;

/**
 * 
 * @author xbbs3851
 *
 */
@Service
public class IndividualToBscsAddressTransformer {

	@Autowired
    private BscsConstantsService constantsService;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

	@Autowired
	private IndividualMaritalStatusToBscsMaritalStatusTransformer maritalStatusTransformer;

	/**
     * Transform a TMF individual to a BscsAddress
     * 
     * @param individual
     * @return
     * @throws ApiException
     */
    public BscsAddress doTransform(Individual individual) throws ApiException {

        BscsAddress bscsAddress = bscsObjectFactory.createBscsAddress();

		// Build the sequenceNumer
		// TODO: change?
		bscsAddress.setSequenceNumber(1l);

        if (individual.getBirthDate() != null) {
            bscsAddress.setBirthDate(individual.getBirthDate().toDateTimeAtStartOfDay(DateTimeZone.UTC).toDate());
        }

		bscsAddress.setSex(buildGender(individual));

		// build person name
		bscsAddress.setFirstName(individual.getGivenName());
		bscsAddress.setLastName(individual.getFamilyName());

		// build title
		bscsAddress.setTitleId(buildTitle(individual));

		// Build Contact info
		buildContactInformation(individual, bscsAddress);

		bscsAddress.setJobDescription(buildOccupation(individual));
		bscsAddress.setMaritalStatusId(buildMaritalStatus(individual));
		// Build Identification Method
		buildIdentification(individual, bscsAddress);
		// Build Nationality
		bscsAddress.setNationalityId(buildNationality(individual));

        bscsAddress.setCustomerId(individual.getId());
        return bscsAddress;
	}

	private Long buildTitle(Individual individual) throws BadParameterFormatException {
		if (individual == null || StringUtils.isEmpty(individual.getTitle())) {
			return null;
		}
		Long title = null;
		try {
			title = Long.valueOf(individual.getTitle());
		} catch (NumberFormatException e) {
            throw new BadParameterFormatException("individual.title", individual.getTitle(), "a numeric value", e);
		}
		return title;

	}

	private Long buildNationality(Individual individual) throws BadParameterValueException {
		if (individual == null || individual.getNationality() == null
				|| StringUtils.isEmpty(individual.getNationality())) {
			return null;
		}
		try {
            BscsCountry country = constantsService.getCountryByIso2(individual.getNationality());
            return country.getNumericId();
		} catch (CountryNotFoundException e) {
			throw new BadParameterValueException("individual.nationality", individual.getNationality(), e);
		}
	}

	private String buildOccupation(Individual individual) {
		if (individual != null && individual.getCharacteristic() != null) {
			for (Characteristic characteristic : individual.getCharacteristic()) {
				if (!StringUtils.isEmpty(characteristic.getName())
						&& characteristic.getName().equalsIgnoreCase(PartyConstants.OCCUPATION)) {
					return characteristic.getValue();
				}
			}
		}

		return null;
	}

	private Long buildMaritalStatus(Individual individual) throws EnumTechnicalException {
		if (individual != null && individual.getMaritalStatus() != null) {
			EnumMaritalStatus status = maritalStatusTransformer.doTransform(individual.getMaritalStatus());
			if (status == null) {
				throw new EnumTechnicalException(
                        "Marital status not found for code " + individual.getMaritalStatus().getValue());
			}
			return status.getCode();
		}

		return null;
	}

	private Character buildGender(Individual individual) {
		if (individual.getGender() != null) {
            if (individual.getGender().equals(Gender.FEMALE)) {
				return 'F';
            } else if (individual.getGender().equals(Gender.MALE)) {
				return 'M';
			}
		}

		return null;
	}

    protected void buildIdentification(Individual individual, BscsAddress bscsAddress)
			throws BadParameterFormatException {
        if (individual.getIndividualIdentification() == null) {
            return;
        }
		for (IndividualIdentification id : individual.getIndividualIdentification()) {
            String type = id.getType();
            if (type != null) {
                try {
                    Long idType = Long.valueOf(type);
                    bscsAddress.setNationalIdTypeCode(idType);
                } catch (NumberFormatException e) {
                    throw new BadParameterFormatException("individual.identification.type", type, "a numeric value");
                }
            }
            if (id.getIdentificationId() != null) {
                bscsAddress.setNationalCard(id.getIdentificationId());
            }
		}
	}

    protected void buildContactInformation(Individual individual, BscsAddress bscsAddress)
			throws BadParameterValueException {
        if (individual.getContactMedium() == null) {
            return;
        }
		for (ContactMedium contact : individual.getContactMedium()) {
            if (null != contact.getMedium()) {

                if (contact.getType().equals(ContactMediumType.EMAILADDRESS)
                        && contact.getMedium().getEmailAddress() != null) {
                    bscsAddress.setEmail(contact.getMedium().getEmailAddress());
                }

                if (contact.getType().equals(ContactMediumType.PHONENUMBER)
                        && contact.getMedium().getNumber() != null) {
                    bscsAddress.setTelephone1(contact.getMedium().getNumber());
                }

                if (contact.getType().equals(ContactMediumType.POSTALADDRESS)) {
                    buildPostalAddress(contact, bscsAddress);
                }
            }
        }
    }

    protected void buildPostalAddress(ContactMedium contact, BscsAddress bscsAddress)
            throws BadParameterValueException {
        if (contact.getMedium().getCity() != null) {
            bscsAddress.setCity(contact.getMedium().getCity());
        }
        if (contact.getMedium().getNumber() != null) {
            bscsAddress.setStreetNumber(contact.getMedium().getNumber());
        }
        if (contact.getMedium().getStreetOne() != null) {
            bscsAddress.setStreet(contact.getMedium().getStreetOne());
        }
        if (contact.getMedium().getPostcode() != null) {
            bscsAddress.setPostalCode(contact.getMedium().getPostcode());
        }
        if (contact.getMedium().getStateOrProvince() != null) {
            bscsAddress.setState(contact.getMedium().getStateOrProvince());
        }
        if (contact.getMedium().getCountry() != null) {
            try {
                BscsCountry country = constantsService.getCountryByIso2(contact.getMedium().getCountry());
                bscsAddress.setCountryId(country.getNumericId());
            } catch (CountryNotFoundException e) {
                throw new BadParameterValueException("individual.contactMedium.medium.country",
                        contact.getMedium().getCountry(), e);
            }
        }
    }
}
