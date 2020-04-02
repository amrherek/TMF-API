package com.orange.mea.apisi.billingaccount.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.BscsPaymentArrangement;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;
import com.orange.bscs.service.BscsBillingServiceAdapter;
import com.orange.bscs.service.BscsBusinessPartnerServiceAdapter;
import com.orange.bscs.service.BscsContractServiceAdapter;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.billingaccount.backend.bscs.service.BscsBillingAccountService;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "file")

public class BscsBillingAccountServiceTest {

    @Before
    public void init() {
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
    }

    @Mock
    private BscsContractServiceAdapter contractService;

    @Mock
    private BscsBusinessPartnerServiceAdapter businessPartnerServiceAdapter;

    @Mock
    private BscsBillingServiceAdapter billingServiceAdapter;

    @InjectMocks
    public BscsBillingAccountService bscsBillingAccountServiceImpl;

    @Test(expected = BscsInvalidFieldException.class)
    public void shouldThrowContractNotFoundDoNotExist()
            throws TechnicalException, BscsInvalidFieldException {
        // mock contracts.search
        when(contractService.findContractByMsisdn("0123456789"))
                .thenThrow(new BscsInvalidFieldException(null, null, null));
        bscsBillingAccountServiceImpl.getContract("0123456789");
    }

    @Test(expected = BscsInvalidIdException.class)
    public void shouldThrowCustomerNotFoundDoNotExist() throws BscsInvalidIdException {
        // execute customer.read
        final BscsInvalidIdException soiExp = new BscsInvalidIdException(null, null, null);
        when(businessPartnerServiceAdapter.getCustomer("111")).thenThrow(soiExp);
        bscsBillingAccountServiceImpl.getCustomer("111");
    }


    public void shouldThrowPaymentArrangementNotFound()
            throws BscsInvalidIdException, TechnicalException {
        when(businessPartnerServiceAdapter.getCurrentPaymentArrangement("111")).thenReturn(null);
        final BscsPaymentArrangement res = bscsBillingAccountServiceImpl.getPaymentArrangement("111");
        Assert.assertNull(res);
    }


}
