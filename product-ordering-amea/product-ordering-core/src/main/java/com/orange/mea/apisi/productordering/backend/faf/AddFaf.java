package com.orange.mea.apisi.productordering.backend.faf;

import com.orange.apibss.common.exceptions.ApiException;

public interface AddFaf {

    void addFaf(String msisdn, String friendAndFamilyNumber) throws ApiException;

}
