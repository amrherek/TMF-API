package com.orange.mea.apisi.productinventory.obw.backend.bscs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.utils.InternationalMsisdnTool;
import com.orange.apibss.productInventory.model.Product;
import com.orange.bscs.model.businesspartner.EnumContractStatus;
import com.orange.mea.apisi.productinventory.backend.bscs.transformer.AccessServiceTransformer;

/**
 * Product transformer specialisation for OBW
 * 
 * @author Thibault Duperron
 *
 */
@Service
@Primary
public class AccessServiceTransformerOBW extends AccessServiceTransformer {

    @Autowired
    private InternationalMsisdnTool internationalMsisdnTool;

    @Override
    public Product mapToMsisdnProduct(String msisdn, String parentProductOfferingId,
            EnumContractStatus contractStatus) {
        // remove international prefix
        msisdn = internationalMsisdnTool.removeInternationalPrefix(msisdn);
        return super.mapToMsisdnProduct(msisdn, parentProductOfferingId, contractStatus);
    }
}
