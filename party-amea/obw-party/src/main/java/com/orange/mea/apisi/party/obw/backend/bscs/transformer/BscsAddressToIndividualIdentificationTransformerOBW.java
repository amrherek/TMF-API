package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.model.BscsAddress;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualIdentificationTransformer;

/**
 * <p>
 * It takes a BscsAddress object in input and return an IndividualIdentification
 * object in output.
 * </p>
 * 
 * @author mfrh5781
 */
@Service
@Primary
public class BscsAddressToIndividualIdentificationTransformerOBW
		extends BscsAddressToIndividualIdentificationTransformer {

	@Override
	public List<IndividualIdentification> doTransform(BscsAddress address) {
		Long code = address.getNationalIdTypeCode();
		if (null != code && code == 3) {
			List<IndividualIdentification> individualIdentificationList = new ArrayList<>();
			String drivingLicense = address.getBscsModel().getDrivingLicence();
			if (!Tools.isNullOrEmpty(drivingLicense)) {
				IndividualIdentification individualIdentification = new IndividualIdentification();
				individualIdentification.setIdentificationId(drivingLicense);
				individualIdentification.setType(code.toString());
				individualIdentificationList.add(individualIdentification);
			}
			return individualIdentificationList;
		}
		return super.doTransform(address);
	}

}
