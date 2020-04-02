/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orange.mea.apisi.billingaccount.service;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.orange.apibss.common.exceptions.badrequest.BadRequestException;
import com.orange.apibss.common.exceptions.technical.TechnicalException;
import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.BscsBillingAccountV9;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.BscsBillingAssignmentV9;
import com.orange.bscs.model.BscsContractSearchV9;
import com.orange.bscs.model.BscsCustomerV9;
import com.orange.bscs.model.BscsPaymentArrangementV9;
import com.orange.bscs.model.billing.BSCSBillingAccount;
import com.orange.bscs.model.businesspartner.BSCSBillingAssignment;
import com.orange.bscs.model.businesspartner.BSCSCustomer;
import com.orange.bscs.model.businesspartner.BSCSPaymentArrangement;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;
import com.orange.mea.apisi.billingaccount.backend.bscs.BillingAccountBscsBackend;
import com.orange.mea.apisi.billingaccount.backend.bscs.BscsToBillingAccountTransformer;
import com.orange.mea.apisi.billingaccount.backend.bscs.service.BscsBillingAccountService;

/**
 *
 * @author ECUS6396
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "file")

public class BillingAccountBscsBackendTest {

    @InjectMocks
    public BillingAccountBscsBackend billingAccountBscsBackend;

    @Mock
    private BscsBillingAccountService bscsBillingAccountService;

    @Mock
    private BscsConnectionService bscsConnectionService;

    @Mock
    private BscsToBillingAccountTransformer bscsToBillingAccountTransformer;

    @Before
    public void init() {
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
    }

    @Test
    public void findBillingAccountsByCriteriaTest()
            throws BadRequestException, TechnicalException, BscsInvalidFieldException, BscsInvalidIdException {

        // data used between BSCS calls
        final String contractId = "contractId";
        final String customerId = "customerId";
        final String billingCode = "billingCode";

        // data and bscsBillingAccountService configuration
        final BSCSContract contract = new BSCSContract();
        contract.setContractIdPub(contractId);
        when(bscsBillingAccountService.getContract(anyString())).thenReturn(new BscsContractSearchV9(contract));

        final List<BscsBillingAssignment> billingAssignments = new ArrayList<>();
        final BSCSBillingAssignment billingAssignment = new BSCSBillingAssignment();
        billingAssignment.setBillingAccountCode(billingCode);
        billingAssignments.add(new BscsBillingAssignmentV9(billingAssignment));
        when(bscsBillingAccountService.getBillingAssignments(contractId)).thenReturn(billingAssignments);

        final BSCSBillingAccount billingAccount = new BSCSBillingAccount();
        billingAccount.setCustomerIdPub(customerId);
        when(bscsBillingAccountService.getBillingAccountRead(billingCode))
                .thenReturn(new BscsBillingAccountV9(billingAccount));

        final BSCSPaymentArrangement paymentArrangement = new BSCSPaymentArrangement();
        when(bscsBillingAccountService.getPaymentArrangement(customerId))
                .thenReturn(new BscsPaymentArrangementV9(paymentArrangement));

        final BSCSCustomer customer = new BSCSCustomer();
        when(bscsBillingAccountService.getCustomer(customerId))
                .thenReturn(new BscsCustomerV9(customer));

        // call service
        billingAccountBscsBackend.findBillingAccountsByMsisdn("pony");

        // verify bscsBillingAccountService calls
        verify(bscsBillingAccountService).getContract("pony");
        verify(bscsBillingAccountService).getCustomer(customerId);
        verify(bscsBillingAccountService).getBillingAssignments(contractId);
        verify(bscsBillingAccountService).getBillingAccountRead(billingCode);
        verify(bscsBillingAccountService).getPaymentArrangement(customerId);
    }

}
