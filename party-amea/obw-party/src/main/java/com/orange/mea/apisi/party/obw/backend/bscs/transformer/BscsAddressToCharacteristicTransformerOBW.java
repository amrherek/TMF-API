package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.apibss.party.model.Characteristic;
import com.orange.bscs.api.utils.tools.Tools;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToCharacteristicTransformer;
import com.orange.mea.apisi.party.exceptions.CustomerGroupNotFoundException;
import com.orange.mea.apisi.party.obw.constants.PartyConstantsOBW;

@Component
@Primary
public class BscsAddressToCharacteristicTransformerOBW extends BscsAddressToCharacteristicTransformer {

    @Override
    public List<Characteristic> doTransform(BscsAddress address, BscsCustomer customer)
            throws CustomerGroupNotFoundException {
        List<Characteristic> characteristicList = super.doTransform(address, customer);
        if (!Tools.isNullOrEmpty(address.getCompanyName())) {
            Characteristic companyName = new Characteristic();
            companyName.setName(PartyConstantsOBW.TRADING_NAME);
            companyName.setValue(address.getCompanyName());
            characteristicList.add(companyName);
        }
        return characteristicList;
    }

}
