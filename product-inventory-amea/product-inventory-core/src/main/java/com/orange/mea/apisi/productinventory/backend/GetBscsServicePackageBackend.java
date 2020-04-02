package com.orange.mea.apisi.productinventory.backend;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public interface GetBscsServicePackageBackend {

    /**
     * Get a service by id. Return null if the id does not match a service id,
     * throws a NotFoundException if the id match a service package id but no
     * service package found with that id
     * 
     * @param contractId
     * @param serviceId
     * @return
     * @throws TechnicalException
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    Product getBscsServicePackage(String contractId, String serviceId)
            throws TechnicalException, BscsInvalidIdException, BscsInvalidFieldException;

}
