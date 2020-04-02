package com.orange.mea.apisi.productinventory.obw.backend.zteIn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.productInventory.model.Product;
import com.orange.mea.apisi.obw.webservice.TArrayOfFellowISDNDto;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.FafServiceNotConfiguredException;
import com.orange.mea.apisi.obw.webservice.exception.InvalidMsisdnException;
import com.orange.mea.apisi.productinventory.backend.EnhancedBscsServiceBackend;
import com.orange.mea.apisi.productinventory.constants.ProductInventoryConstants;

@Service
public class FafZteInBackendOBW implements EnhancedBscsServiceBackend {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("#{T(java.util.Arrays).asList('${faf.snCodePubs}')}")
    private List<String> fafSnCodes;

    @Value("${faf.activateZte}")
    private boolean activated;

    @Autowired
    private ZteWebService webService;

    @Autowired
    private FafTransformerOBW fafTransformer;

    @Override
    public void enhanceService(String msisdn, Product fafProduct) throws TechnicalException {
        if (!activated || fafProduct == null) {
            return;
        }
        try {
            TArrayOfFellowISDNDto fafNumbers = webService.listFriendsAndFamily(msisdn);
            fafTransformer.transform(fafProduct, fafNumbers);
        } catch (FafServiceNotConfiguredException e) {
            // should not happen since BSCS FaF product has already been found
            logger.error("no Faf configured for contract with msisdn [" + msisdn + "]");
            throw new TechnicalException(e.getMessage(), e);
        } catch (InvalidMsisdnException e) {
            // should not happen since BSCS FaF product has already been found
            logger.error("Invalid msisdn [" + msisdn + "] for FaF WS");
            throw new TechnicalException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getServiceCodes(String msisdn) {
        if (activated) {
            return fafSnCodes;
        }
        return new ArrayList<>();
    }

    @Override
    public String getSpecificationId() {
        return ProductInventoryConstants.FAF;
    }

    @Override
    public void dealWithNoProductFound(String msisdn, String snCode) throws TechnicalException {
        // SN_CODE_PUB not associated to contract
        logger.debug("The service code configured for Friends and Family service [" + snCode
                + "] is not available for contract with msisdn [" + msisdn + "]");
    }

}
