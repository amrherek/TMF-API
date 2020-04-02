package com.orange.mea.apisi.productinventory.backend;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

public interface GetBscsServiceBackend {

    /**
     * Get a service by id. Return null if the id does not match a service id,
     * throws a NotFoundException if the id match a service id but no service
     * found with that id
     * 
     * @param contractId
     * @param serviceId
     * @param withParameters
     * @return
     * @throws TechnicalException
     * @throws BscsInvalidIdException
     * @throws BscsInvalidFieldException
     */
    Product getBscsService(String contractId, String serviceId, boolean withParameters)
            throws TechnicalException, BscsInvalidIdException, BscsInvalidFieldException;

}
