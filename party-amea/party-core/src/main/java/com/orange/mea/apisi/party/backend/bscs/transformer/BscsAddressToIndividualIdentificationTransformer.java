package com.orange.mea.apisi.party.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.IndividualIdentification;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.model.BscsAddress;

/**
 * <p>
 * It takes a BscsAddress object in input and return an IndividualIdentification
 * object in output.
 * </p>
 * 
 * @author mfrh5781
 */
@Service
public class BscsAddressToIndividualIdentificationTransformer {

    public List<IndividualIdentification> doTransform(BscsAddress address) {

		List<IndividualIdentification> individualIdentificationList = new ArrayList<IndividualIdentification>();
        if (!Tools.isNullOrEmpty(address.getNationalCard())) {
				IndividualIdentification individualIdentification = new IndividualIdentification();
            individualIdentification.setIdentificationId(address.getNationalCard());
				individualIdentificationList.add(individualIdentification);
            Long code = address.getNationalIdTypeCode();
            if (code != null) {
                individualIdentification.setType(code.toString());
            }
		}


		return individualIdentificationList;
	}

}
