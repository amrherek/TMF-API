package com.orange.mea.apisi.party.backend.bscs.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.apibss.party.model.Individual;
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.factory.BscsObjectFactory;

@Service
public class IndividualToBscsCustomerInfoTransformer {

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    public BscsCustomerInfo doTransform(Individual individual) {
        // default behavior: nothing to do
        return null;
    }

}
