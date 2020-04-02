package com.orange.mea.apisi.productordering.backend.faf;

import com.orange.apibss.common.exceptions.ApiException;

public interface UpdateFaf {

    void updateFaf(String msisdn, String currentNumber, String newNumber) throws ApiException;
	
}
