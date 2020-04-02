package com.orange.apibss.common.generator;

import java.util.UUID;

public class TransactionGeneratorUUIID implements IdentificationGenerator<String> {

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}

}
