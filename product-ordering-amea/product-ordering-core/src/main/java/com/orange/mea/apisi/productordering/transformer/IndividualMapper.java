package com.orange.mea.apisi.productordering.transformer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.RelatedIndividual;

/**
 * Mapper for transforming RelatedIndividual to Individual
 * 
 * @author xbbs3851
 *
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface IndividualMapper {

	///////////////////////////////////////////////////////////////////
	// RelatedIndividual (product ordering) => Individual (party)
	///////////////////////////////////////////////////////////////////	
	
	@Mappings({
		@Mapping(target = "externalReference", ignore = true),
		@Mapping(target = "relatedParty", ignore=true),
   })
	Individual asIndividual(RelatedIndividual indiv);

	com.orange.apibss.party.model.Characteristic mapCharacteristic(com.orange.apibss.productOrdering.model.Characteristic characteristic);
	
	@Mappings({
		@Mapping(target = "preferred", ignore=true),
		@Mapping(target = "validFor", ignore=true),
   })
	com.orange.apibss.party.model.ContactMedium mapContactMedium(com.orange.apibss.productOrdering.model.ContactMedium contactMedium);
	
	@Mappings({
		@Mapping(target = "countryCode", ignore=true),
   })
	com.orange.apibss.party.model.Medium map(com.orange.apibss.productOrdering.model.Medium value);
	
	@Mappings({
		@Mapping(source = "MALE", target = "MALE"),
		@Mapping(source = "FEMALE", target = "FEMALE"),
	})
    com.orange.apibss.party.model.Gender map(com.orange.apibss.productOrdering.model.Gender value);
	
	@Mappings({
		@Mapping(source = "SINGLE", target = "SINGLE"),
		@Mapping(source = "MARRIED", target = "MARRIED"),
		@Mapping(source = "DIVORCED", target = "DIVORCED"),
		@Mapping(source = "WIDOWED", target = "WIDOWED"), })
    com.orange.apibss.party.model.MaritalStatus map(com.orange.apibss.productOrdering.model.MaritalStatus value);

	@Mappings({
		@Mapping(target = "issuingAuthority", ignore=true),
   })
	com.orange.apibss.party.model.IndividualIdentification map(com.orange.apibss.productOrdering.model.Identification value);
	
    com.orange.apibss.party.model.ContactMediumType map(
            com.orange.apibss.productOrdering.model.ContactMediumType value);

	///////////////////////////////////////////////////////////////////
	// Individual (party) => RelatedIndividual (product ordering)
	///////////////////////////////////////////////////////////////////
	
	@Mappings({
		@Mapping(target = "role", ignore=true),
   })
	RelatedIndividual asRelatedIndividual(Individual indiv);
	
	com.orange.apibss.productOrdering.model.Characteristic mapCharacteristic(com.orange.apibss.party.model.Characteristic value);
	
	com.orange.apibss.productOrdering.model.ContactMedium mapContactMedium(com.orange.apibss.party.model.ContactMedium value);
	
	com.orange.apibss.productOrdering.model.Medium mapMedium(com.orange.apibss.party.model.Medium value);
	
	@Mappings({
		@Mapping(source = "MALE", target = "MALE"),
		@Mapping(source = "FEMALE", target = "FEMALE"),
	})
    com.orange.apibss.productOrdering.model.Gender mapGender(com.orange.apibss.party.model.Gender value);
	
	@Mappings({ @Mapping(source = "SINGLE", target = "SINGLE"),
			@Mapping(source = "MARRIED", target = "MARRIED"),
			@Mapping(source = "DIVORCED", target = "DIVORCED"),
			@Mapping(source = "WIDOWED", target = "WIDOWED"), })
    com.orange.apibss.productOrdering.model.MaritalStatus map(com.orange.apibss.party.model.MaritalStatus value);

	com.orange.apibss.productOrdering.model.Identification mapIdentification(com.orange.apibss.party.model.IndividualIdentification value);

    com.orange.apibss.productOrdering.model.ContactMediumType map(
            com.orange.apibss.party.model.ContactMediumType value);

}
