package com.orange.mea.apisi.productordering.transformer;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.RelatedIndividual;
import com.orange.apibss.productOrdering.model.Role;

/**
 * Last transformer in prepaid activation process. Modify the input
 * productOrder updating its dates and status
 * 
 * @author shainy.ajit.jain reviewed by xbbs3851 on 2016/12/09
 * 
 */
@Service
public class ActivatePrepaidProductOrderResponseTransformer {

	public void doTransform(ProductOrder productOrderRequest, Individual individual, ProductOrder response) {

		if (individual != null) {
			// Setting updated Individual
			RelatedIndividual relatedIndividual = transformParty(individual);
            relatedIndividual.setRole(Role.CUSTOMER);
            response.addRelatedIndividualItem(relatedIndividual);
		}

	}

	public RelatedIndividual transformParty(Individual ind) {
		IndividualMapper mapper = Mappers.getMapper(IndividualMapper.class);
		return mapper.asRelatedIndividual(ind);
	}
}
