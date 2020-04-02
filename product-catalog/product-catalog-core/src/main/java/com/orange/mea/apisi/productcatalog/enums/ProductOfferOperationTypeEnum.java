package com.orange.mea.apisi.productcatalog.enums;

/**
 * Enumeration for ProductOffer Rest query parameters LinkType
 * 
 * @author xbbs3851
 *
 */
public enum ProductOfferOperationTypeEnum {
    MIGRATIONFROM("migrationFrom"), //
    COMPATIBILITYWITH("compatibilityWith"), //
    PARAMETERCONFIGURATION("parameterConfiguration");

	private String value;

	private ProductOfferOperationTypeEnum(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
