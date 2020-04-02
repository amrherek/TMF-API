package com.orange.mea.apisi.party.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Characteristic;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.mea.apisi.party.backend.bscs.BscsConstantsService;
import com.orange.mea.apisi.party.constants.PartyConstants;
import com.orange.mea.apisi.party.exceptions.CustomerGroupNotFoundException;

/**
 * <p>
 * It takes a BscsAddress object in input and return an Characteristic object in
 * output.
 * </p>
 * 
 * @author mfrh5781
 */
@Service
public class BscsAddressToCharacteristicTransformer {

	@Autowired
    private BscsConstantsService constantsService;

    public List<Characteristic> doTransform(BscsAddress address, BscsCustomer customer) throws CustomerGroupNotFoundException {
		List<Characteristic> characteristicList = new ArrayList<Characteristic>();

		if (!Tools.isNullOrEmpty(address.getJobDescription())) {
			Characteristic occupation = new Characteristic();
			occupation.setName(PartyConstants.OCCUPATION);
            occupation.setValue(address.getJobDescription());
			characteristicList.add(occupation);
		}

		if (!Tools.isNullOrEmpty(customer.getPriceGroupCode())) {
        	Characteristic prgCode = new Characteristic();
        	prgCode.setName(PartyConstants.CATEGORY_CODE);
        	prgCode.setValue(customer.getPriceGroupCode());
        	characteristicList.add(prgCode);

			Characteristic prgDescription = new Characteristic();
			prgDescription.setName(PartyConstants.CATEGORY_LABEL);
			prgDescription.setValue(constantsService.getCustomerGroupByCode(customer.getPriceGroupCode()).getDescription());
			characteristicList.add(prgDescription);
		}


		return characteristicList;
	}
}
