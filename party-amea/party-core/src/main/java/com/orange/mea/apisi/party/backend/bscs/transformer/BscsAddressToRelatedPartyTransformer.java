package com.orange.mea.apisi.party.backend.bscs.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.RelatedParty;
import com.orange.bscs.model.BscsAddress;

/**
 * <p>
 * It takes a BscsAddress object in input and return an RelatedParty object in
 * output.
 * </p>
 * 
 * @author mfrh5781
 */
@Service
public class BscsAddressToRelatedPartyTransformer {


    public List<RelatedParty> doTransform(BscsAddress bscsAdress) {
		List<RelatedParty> relatedPartyList = new ArrayList<RelatedParty>();

		if (bscsAdress.getCustomerId() != null) {
            RelatedParty party = new RelatedParty();
            party.setId(bscsAdress.getCustomerId());
            party.setName(bscsAdress.getLastName());
			relatedPartyList.add(party);
		}

		return relatedPartyList;
	}

}
