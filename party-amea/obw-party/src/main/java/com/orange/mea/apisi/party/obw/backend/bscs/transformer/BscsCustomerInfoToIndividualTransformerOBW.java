package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Characteristic;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsCustomerInfoToIndividualTransformer;
import com.orange.mea.apisi.party.obw.constants.PartyConstantsOBW;
import com.orange.mea.apisi.party.obw.exception.UnparseableDateOBW;

/**
 * Transform a BscsCustomerInfo object to an Individual object
 * 
 * 
 * @author jwck2987
 * 
 */
@Service
@Primary
public class BscsCustomerInfoToIndividualTransformerOBW extends BscsCustomerInfoToIndividualTransformer {

	@Override
	public void doTransform(final BscsCustomerInfo info, final Individual individual) 
			throws UnparseableDateOBW {

		DateTimeFormatter formatter = DateTimeFormat.forPattern(PartyConstantsOBW.DATE_FORMAT);

		String registrationStatus = info.getText(PartyConstantsOBW.REGISTRATION_FIELD);
		if (StringUtils.isNotBlank(registrationStatus)) {
			Characteristic registration = new Characteristic();
			registration.setName(PartyConstantsOBW.REGISTRATION_LABEL);
			registration.setValue(registrationStatus);
			individual.addCharacteristicItem(registration);
		}

		List<IndividualIdentification> idList = individual.getIndividualIdentification();
		if (!idList.isEmpty()) {
			String issuingDate = info.getText(PartyConstantsOBW.ISSUING_DATE_FIELD);
			if (StringUtils.isNotBlank(issuingDate)) {
				try {
					idList.get(0).setIssuingDate(LocalDate.parse(issuingDate, formatter));
                } catch (final Exception e) {
					throw new UnparseableDateOBW("issuingDate", issuingDate);
				}
			}
			
			String expirationDate = info.getText(PartyConstantsOBW.EXPIRATION_DATE_FIELD);
			if (StringUtils.isNotBlank(expirationDate)) {
				try {
					idList.get(0).setExpirationDate(LocalDate.parse(expirationDate, formatter));
                } catch (final Exception e) {
					throw new UnparseableDateOBW("expirationDate", expirationDate);
				}
			}
			
			String issuingPlace = info.getText(PartyConstantsOBW.ISSUING_PLACE_FIELD);
			if (StringUtils.isNotBlank(issuingPlace)) {
				idList.get(0).setIssuingPlace(issuingPlace);
			}
		}
	}

}
