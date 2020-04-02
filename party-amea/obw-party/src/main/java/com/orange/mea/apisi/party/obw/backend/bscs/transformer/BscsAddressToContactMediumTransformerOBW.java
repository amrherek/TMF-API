package com.orange.mea.apisi.party.obw.backend.bscs.transformer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.orange.apibss.party.model.ContactMedium;
import com.orange.apibss.party.model.ContactMediumType;
import com.orange.bscs.model.BscsAddress;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToContactMediumTransformer;
import com.orange.mea.apisi.party.exceptions.CountryNotFoundException;

@Component
@Primary
public class BscsAddressToContactMediumTransformerOBW extends BscsAddressToContactMediumTransformer {

    @Override
    protected void transformPostalContact(BscsAddress bscsAdress, List<ContactMedium> contactMediumList)
            throws CountryNotFoundException {
        super.transformPostalContact(bscsAdress, contactMediumList);
        if (contactMediumList.isEmpty()) {
            return;
        }
        // get last contactMedium added
        ContactMedium postalContactMedium = contactMediumList.get(contactMediumList.size() - 1);
        if (postalContactMedium.getType() == ContactMediumType.POSTALADDRESS) {
            if (StringUtils.isNotBlank(bscsAdress.getNote1())) {
                postalContactMedium.getMedium().setStreetTwo(bscsAdress.getNote1());
            }
        }
    }

}
