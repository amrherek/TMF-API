package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.mea.apisi.party.backend.bscs.transformer.IndividualToBscsCustomerInfoTransformer;
import com.orange.mea.apisi.party.obw.constants.PartyConstantsOBW;

@Service
@Primary
public class IndividualToBscsCustomerInfoTransformerOBW extends IndividualToBscsCustomerInfoTransformer {

	@Override
	public BscsCustomerInfo doTransform(final Individual individual) {
		if (individual.getCharacteristic() == null && (individual.getIndividualIdentification() == null
				|| individual.getIndividualIdentification().isEmpty())) {
			return null;
		} else {
			final BscsCustomerInfo customerInfo = bscsObjectFactory.createBscsCustomerInfo();

			buildCharacteristic(individual, customerInfo);

			buildIdentification(individual, customerInfo);

			return customerInfo;
		}
	}

	private void buildCharacteristic(final Individual updateParty, final BscsCustomerInfo customerInfo) {
		if (updateParty.getCharacteristic() == null) {
			return;
		}
		for (Characteristic charac : updateParty.getCharacteristic()) {
			if (PartyConstantsOBW.REGISTRATION_LABEL.equals(charac.getName())) {
				customerInfo.setCustomerId(updateParty.getId());
				customerInfo.setText(PartyConstantsOBW.REGISTRATION_FIELD, charac.getValue());
			}
		}
	}

	private void buildIdentification(final Individual updateParty, final BscsCustomerInfo customerInfo) {
		if (updateParty.getIndividualIdentification() == null) {
			return;
		}
		for (IndividualIdentification identification : updateParty.getIndividualIdentification()) {
			customerInfo.setCustomerId(updateParty.getId());
			buildExpirationDate(identification, customerInfo);
			buildIssuingDate(identification, customerInfo);
			buildIssuingPlace(identification, customerInfo);
		}
	}

	private void buildExpirationDate(final IndividualIdentification updateIdent, final BscsCustomerInfo customerInfo) {
		if (updateIdent.getExpirationDate() != null) {
			customerInfo.setText(PartyConstantsOBW.EXPIRATION_DATE_FIELD,
					updateIdent.getExpirationDate().toString(PartyConstantsOBW.DATE_FORMAT));
		}
	}

	private void buildIssuingDate(final IndividualIdentification updateIdent, final BscsCustomerInfo customerInfo) {
		if (updateIdent.getIssuingDate() != null) {
			customerInfo.setText(PartyConstantsOBW.ISSUING_DATE_FIELD,
					updateIdent.getIssuingDate().toString(PartyConstantsOBW.DATE_FORMAT));
		}
	}

	private void buildIssuingPlace(final IndividualIdentification updateIdent, final BscsCustomerInfo customerInfo) {
		if (updateIdent.getIssuingPlace() != null) {
			customerInfo.setText(PartyConstantsOBW.ISSUING_PLACE_FIELD, updateIdent.getIssuingPlace());
		}
	}

}
