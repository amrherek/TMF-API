package com.orange.mea.apisi.party.service.individual;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.party.model.Individual;
import com.orange.bscs.cms.servicelayeradapter.BusinessPartnerServiceAdapter;
import com.orange.bscs.model.BscsAddress;
import com.orange.bscs.model.BscsCustomer;
import com.orange.mea.apisi.party.backend.bscs.BscsIndividualService;
import com.orange.mea.apisi.party.backend.bscs.IndividualBscsBackend;
import com.orange.mea.apisi.party.backend.bscs.transformer.BscsAddressToIndividualTransformer;

/**
 * @author ECUS6396
 */

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "file")

public class IndividualBscsBackendTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@InjectMocks
    protected IndividualBscsBackend individualService;

	@Mock
	protected BscsIndividualService bscsIndividualService;

	@Mock
	protected BscsAddressToIndividualTransformer bscsAddressToIndividualTransformer;

	@Mock
	protected BusinessPartnerServiceAdapter businessPartnerServiceAdapter;

	@Test
	public void getIndividual() throws ApiException {
		String id = "CUST00TEST001";
		logger.debug("Testing Getting individual [{}]", id);

        BscsAddress address = mock(BscsAddress.class);
        when(bscsIndividualService.getBscsAddress(id)).thenReturn(address);

        BscsCustomer customer = mock(BscsCustomer.class);
		when(bscsIndividualService.getBscsCustomer(id)).thenReturn(customer);

		Individual individualMock = new Individual();
		individualMock.setGivenName("Rafik");
		when(bscsAddressToIndividualTransformer.doTransform(address, customer)).thenReturn(individualMock);

		// instanciation du service
		Individual individual = individualService.getIndividual(id);

		logger.debug("Testing individual givenName [{}]", individual.getGivenName());
		/**
		 * test : verify bscsIndividualService appel bien la methode
		 * getBscsAddress avec l'id en parametre
		 */
		// vérification de l'adresse
        verify(bscsIndividualService).getBscsAddress(id);

	}
	
	


}
