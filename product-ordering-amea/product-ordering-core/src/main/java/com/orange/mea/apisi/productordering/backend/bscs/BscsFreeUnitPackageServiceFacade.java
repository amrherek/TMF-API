package com.orange.mea.apisi.productordering.backend.bscs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsFreeUnitPackage;
import com.orange.bscs.model.BscsService;
import com.orange.bscs.model.contract.EnumServiceSubType;
import com.orange.bscs.service.BscsProductServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@Service
public class BscsFreeUnitPackageServiceFacade {

    @Autowired
    private BscsProductServiceAdapter productServiceAdapter;

    public BscsFreeUnitPackage getFreeUnitPackage(Long fuPackageId) throws BscsInvalidIdException {
        return productServiceAdapter.getFreeUnitPackage(fuPackageId);
    }

    public boolean isFreeUnit(String snCodePub) throws BscsInvalidIdException {
        BscsService bscsService = productServiceAdapter.getService(snCodePub);
        EnumServiceSubType srvSubType = bscsService.getSubType();
        boolean isFreeUnit = false;
        if (srvSubType != null) {
            switch (srvSubType) {
            case COFU:
            case POFU:
            case POFUL:
                isFreeUnit = true;
                break;
            default:
                // other subTypes are not in enum
                isFreeUnit = false;
                break;
            }
        }
        return isFreeUnit;
    }

}
