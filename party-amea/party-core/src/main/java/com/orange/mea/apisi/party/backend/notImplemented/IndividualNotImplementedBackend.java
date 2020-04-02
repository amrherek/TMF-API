package com.orange.mea.apisi.party.backend.notImplemented;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.NotImplementedException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.apibss.party.model.Individual;
import com.orange.mea.apisi.party.backend.FindIndividualsByIdentificationBackend;

@Service
public class IndividualNotImplementedBackend implements FindIndividualsByIdentificationBackend {

    @Override
    public List<Individual> findIndividualsByIdentification(Long identificationType, String identificationId)
            throws TechnicalException, BadRequestException {
        throw new NotImplementedException("findIndividualsByIdentification");
    }

}
