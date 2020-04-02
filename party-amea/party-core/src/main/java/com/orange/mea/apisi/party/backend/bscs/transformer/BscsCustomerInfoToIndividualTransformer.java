package com.orange.mea.apisi.party.backend.bscs.transformer;

import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.party.model.Individual;
import com.orange.bscs.model.BscsCustomerInfo;

/**
 * Transformer from BscsCustomerInfo to Individual
 * 
 * @author jwck2987
 *
 */
@Service
public class BscsCustomerInfoToIndividualTransformer {

    public void doTransform(BscsCustomerInfo customerInfo, Individual individual) throws TechnicalException {
        // default behavior: nothing to do
    }

}
