package com.orange.mea.apisi.productordering.transformer;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.RelatedIndividual;

/**
 * Transformer from RelatedIndividual to Individual
 * 
 * @author xbbs3851
 *
 */
@Service
public class RelatedPartyToIndividualTransformer {

	public Individual doTransform(RelatedIndividual party) {
		IndividualMapper mapper = Mappers.getMapper(IndividualMapper.class);
		return mapper.asIndividual(party);
	}

}
