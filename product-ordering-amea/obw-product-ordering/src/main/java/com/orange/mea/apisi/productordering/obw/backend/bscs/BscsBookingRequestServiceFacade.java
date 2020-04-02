package com.orange.mea.apisi.productordering.obw.backend.bscs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.bscs.model.BscsBookingRequest;
import com.orange.bscs.model.accounting.EnumBookingActionCode;
import com.orange.bscs.model.accounting.EnumBookingFeeType;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.BscsAccountingServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.productordering.exception.OptionAddDeleteException;

@Service
public class BscsBookingRequestServiceFacade {

    @Autowired
    private BscsAccountingServiceAdapter accountingServiceAdapter;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    public void bookingRequest(String coIdPub, String csIdPub, String snCodePub, String spCodePub)
            throws OptionAddDeleteException {
        BscsBookingRequest bscsBookingRequest = bscsObjectFactory.createBscsBookingRequest();
        bscsBookingRequest.setCustomerId(csIdPub);
        bscsBookingRequest.setContractId(coIdPub);
        bscsBookingRequest.setServiceCode(snCodePub);
        bscsBookingRequest.setServicePackageCode(spCodePub);
        bscsBookingRequest.setFeeType(EnumBookingFeeType.NON_RECURRING);
        // OCC fee class
        bscsBookingRequest.setFeeClass(3);
        bscsBookingRequest.setActionCode(EnumBookingActionCode.INSERT);
        try {
            accountingServiceAdapter.writeBookingRequest(bscsBookingRequest, true);
        } catch (BscsInvalidIdException e) {
            if (e.getName() == null) {
                // invalid public key, but we do not known which one
                throw new OptionAddDeleteException(snCodePub, spCodePub, coIdPub,
                        "Invalid id for contract, service package or service");
            }
            throw new OptionAddDeleteException(snCodePub, spCodePub, coIdPub, e.getMessage());
        } catch (BscsInvalidFieldException e) {
            // incoherent service
            throw new OptionAddDeleteException(snCodePub, spCodePub, coIdPub,
                    "Service not found in service package or contract");
        }
    }

}
