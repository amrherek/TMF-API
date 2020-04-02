package com.orange.mea.apisi.productordering.backend;

import com.orange.apibss.common.exceptions.ApiException;

public interface BundleBackend {

    void addBundle(String msisdn, String productOfferingId) throws ApiException;

}
