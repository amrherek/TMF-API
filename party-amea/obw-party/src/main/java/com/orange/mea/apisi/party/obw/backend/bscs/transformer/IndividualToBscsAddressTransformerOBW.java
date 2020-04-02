package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterFormatException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.model.BscsAddress;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsAddressTransformer;
import com.orange.mea.apisi.party.obw.constants.PartyConstantsOBW;

@Component
@Primary
public class IndividualToBscsAddressTransformerOBW extends IndividualToBscsAddressTransformer {

	@Override
	protected void buildPostalAddress(ContactMedium contact, BscsAddress bscsAddress)
			throws BadParameterValueException {
		super.buildPostalAddress(contact, bscsAddress);
		if (contact.getMedium().getStreetTwo() != null) {
			bscsAddress.setNote1(contact.getMedium().getStreetTwo());
		}
	}

	@Override
	public BscsAddress doTransform(Individual individual) throws ApiException {
		BscsAddress bscsAddress = super.doTransform(individual);
		if (individual != null && individual.getCharacteristic() != null) {
			for (Characteristic characteristic : individual.getCharacteristic()) {
				if (!StringUtils.isEmpty(characteristic.getName())
						&& characteristic.getName().equalsIgnoreCase(PartyConstantsOBW.TRADING_NAME)) {
					bscsAddress.setCompanyName(characteristic.getValue());
				}
			}
		}
		return bscsAddress;
	}

	@Override
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
					if (id.getIdentificationId() != null) {
						if (idType == 3) { // Specif OBW for DrivingLicence
							bscsAddress.getBscsModel().setDrivingLicense(id.getIdentificationId());
						} else {
							bscsAddress.setNationalCard(id.getIdentificationId());
						}
					}
				} catch (NumberFormatException e) {
					throw new BadParameterFormatException("individual.identification.type", type, "a numeric value");
				}
			}
		}
	}

}
