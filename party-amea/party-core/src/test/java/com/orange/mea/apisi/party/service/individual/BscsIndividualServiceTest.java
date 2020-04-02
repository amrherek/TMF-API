package com.orange.mea.apisi.party.service.individual;


import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.badrequest.BadParameterValueException;
import com.orange.apibss.common.exceptions.badrequest.notfound.NotFoundIdException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.businesspartner.EnumAddressRole;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.party.backend.bscs.BscsIndividualService;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "file")

public class BscsIndividualServiceTest {

    @Before
    public void init() {
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
    }

    @Mock
    private BscsBusinessPartnerServiceAdapter businessPartnerServiceAdapter;

    @InjectMocks
    private BscsIndividualService bscsIndividualService;

    @Test(expected = NotFoundIdException.class)
    public void shouldThrowNotFoundIdException()
            throws ApiException {
        final String customerId = null;
        bscsIndividualService.getBscsAddress(customerId);
    }

    @Test(expected = NotFoundIdException.class)
    public void getBscsAddressShouldThrowNotFoundIdException()
            throws BscsInvalidIdException, ApiException {
        final String customerId = "CUST001";
        final BscsInvalidIdException except = new BscsInvalidIdException(null, null, null);
        when(businessPartnerServiceAdapter.getAddress(customerId, EnumAddressRole.BILL)).thenThrow(except);
        bscsIndividualService.getBscsAddress(customerId);
    }

    @Test(expected = NotFoundIdException.class)
    public void updateIndividualShouldThrowNotFoundIdException()
            throws BscsInvalidIdException, BscsInvalidFieldException, ApiException {
        final BscsInvalidIdException except = new BscsInvalidIdException(null, null, null);
        final BscsAddress address = mock(BscsAddress.class);
        doThrow(except).when(businessPartnerServiceAdapter).writeAddress(address, true);
        bscsIndividualService.updateIndividual(address);
    }

    @Test(expected = BadParameterValueException.class)
    public void updateIndividualShouldThrowBadParameterValueException()
            throws BscsInvalidIdException, BscsInvalidFieldException, ApiException {
        final BscsInvalidFieldException except = new BscsInvalidFieldException(BscsFieldExceptionEnum.TITLE, null,
                null);
        final BscsAddress address = mock(BscsAddress.class);
        doThrow(except).when(businessPartnerServiceAdapter).writeAddress(address, true);
        bscsIndividualService.updateIndividual(address);
    }

    @Test(expected = TechnicalException.class)
    public void updateIndividualShouldThrowTechnicalException()
            throws BscsInvalidIdException, BscsInvalidFieldException, ApiException {
        final BscsInvalidFieldException except = new BscsInvalidFieldException(null, null, null);
        final BscsAddress address = mock(BscsAddress.class);
        doThrow(except).when(businessPartnerServiceAdapter).writeAddress(address, true);
        bscsIndividualService.updateIndividual(address);
    }

}
