package com.orange.mea.apisi.party.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.ContactMediumType;
import com.orange.apibss.party.model.Medium;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCountry;
import com.orange.mea.apisi.party.backend.bscs.BscsConstantsService;
import com.orange.mea.apisi.party.constants.PartyConstants;
import com.orange.mea.apisi.party.exceptions.CountryNotFoundException;

/**
 * <p>
 * It takes a BscsAddress object in input and return an ContactMedium object in
 * output.
 * </p>
 * 
 * @author mfrh5781
 */
@Service
public class BscsAddressToContactMediumTransformer {

	@Autowired
    private BscsConstantsService constantsService;

    public List<ContactMedium> doTransform(BscsAddress bscsAdress) throws CountryNotFoundException {
		List<ContactMedium> contactMediumList = new ArrayList<ContactMedium>();

		// manage email contact type
        if (!Tools.isNullOrEmpty(bscsAdress.getEmail())) {
			ContactMedium emailContactMedium = new ContactMedium();
			Medium email = new Medium();
            email.setEmailAddress(bscsAdress.getEmail());
            emailContactMedium.setType(ContactMediumType.EMAILADDRESS);
			emailContactMedium.setMedium(email);
			contactMediumList.add(emailContactMedium);
		}
		// manage phone contact type
        if (!Tools.isNullOrEmpty(bscsAdress.getTelephone1())) {
			ContactMedium mobileContactMedium = new ContactMedium();
			Medium mobile = new Medium();
            mobile.setNumber(bscsAdress.getTelephone1());
			mobile.setType(PartyConstants.CONTACT);
            mobileContactMedium.setType(ContactMediumType.PHONENUMBER);
			mobileContactMedium.setMedium(mobile);
			contactMediumList.add(mobileContactMedium);
		}
		// manage postal contact type
        transformPostalContact(bscsAdress, contactMediumList);

		return contactMediumList;
	}

    protected void transformPostalContact(BscsAddress bscsAdress, List<ContactMedium> contactMediumList)
            throws CountryNotFoundException {
        if (StringUtils.isNotBlank(bscsAdress.getStreet()) || StringUtils.isNotBlank(bscsAdress.getCity())
                || bscsAdress.getCountryId() != null) {
            ContactMedium postalContactMedium = new ContactMedium();
            Medium postal = new Medium();
            if (StringUtils.isNotBlank(bscsAdress.getStreetNumber())) {
                postal.setNumber(bscsAdress.getStreetNumber());
            }
            if (StringUtils.isNotBlank(bscsAdress.getStreet())) {
                postal.setStreetOne(bscsAdress.getStreet());
            }
            if (StringUtils.isNotBlank(bscsAdress.getPostalCode())) {
                postal.setPostcode(bscsAdress.getPostalCode());
            }
            if (StringUtils.isNotBlank(bscsAdress.getCity())) {
                postal.setCity(bscsAdress.getCity());
            }
            Long countryId = bscsAdress.getCountryId();
            if (countryId != null) {
                BscsCountry country = constantsService.getCountryById(countryId);

                postal.setCountry(country.getIso2());
            }
            if (StringUtils.isNotBlank(bscsAdress.getState())) {
                postal.setStateOrProvince(bscsAdress.getState());
            }
            postalContactMedium.setType(ContactMediumType.POSTALADDRESS);
            postalContactMedium.setMedium(postal);
            contactMediumList.add(postalContactMedium);
        }
    }

}
