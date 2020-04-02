package com.orange.mea.apisi.party.obw.backend.bscs;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.orange.mea.apisi.party.backend.bscs.IndividualBscsBackend;


@Service
@Primary
public class IndividualBscsBackendOBW extends IndividualBscsBackend {

    public IndividualBscsBackendOBW() {
        super();
        customerInfoNeeded = true;
    }

}
