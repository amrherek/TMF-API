package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orange.bscs.api.connection.service.BscsConnectionService;
import com.orange.bscs.api.model.exception.SOIException;
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.BscsBillingAccount;
import com.orange.bscs.model.BscsBillingAccountSearch;
import com.orange.bscs.model.BscsBillingAssignment;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsBillingServiceAdapterIXR2Test {
    
    @Autowired
    private BscsBillingServiceAdapter adapter;

    @Autowired
    private BscsConnectionService bscsConnectionService;

    @Autowired
    protected BscsObjectFactory bscsObjectFactory;

    @Before
    public void openConnection() throws Exception {
        bscsConnectionService.openConnection();
    }

    @After
    public void closeConnection() {
        bscsConnectionService.closeConnection();
    }
    
    @Test
    public void getBillingAssignmentByContract_ok() {
        List<BscsBillingAssignment> billingAssignmentByContract = adapter
                .getBillingAssignmentByContract("CONTR0000000217");
        assertThat(billingAssignmentByContract).isNotEmpty();
    }

    @Test
    public void getBillingAssignmentByContract_unknownId() {
        List<BscsBillingAssignment> billingAssignmentByContract = adapter.getBillingAssignmentByContract("UNKNOWN");
        assertThat(billingAssignmentByContract).isEmpty();
    }

    @Test(expected = SOIException.class)
    public void getBillingAssignmentByContract_nullId() {
        adapter.getBillingAssignmentByContract(null);
    }

    @Test
    public void getBillCycles_ok() {
        List<BscsBillCycle> billCycles = adapter.getBillCycles();
        assertThat(billCycles).isNotEmpty();
    }

    @Test
    public void findBillingAccountsByCustomer_ok() {
        List<BscsBillingAccountSearch> findBillingAccountsByCustomer = adapter
                .findBillingAccountsByCustomer("CUST0000000183", EnumBillingAccountSearchMod.ALL);
        assertThat(findBillingAccountsByCustomer).isNotEmpty();
    }

    @Test
    public void findBillingAccountsByCustomer_unknownCustomerId() {
        List<BscsBillingAccountSearch> findBillingAccountsByCustomer = adapter.findBillingAccountsByCustomer("UNKNOWN",
                EnumBillingAccountSearchMod.ALL);
        assertThat(findBillingAccountsByCustomer).isEmpty();
    }

    @Test
    public void getBillingAccount_ok() throws BscsInvalidIdException {
        BscsBillingAccount billingAccount = adapter.getBillingAccount("BA0000000056",
                EnumBillingAccountReadMod.LATEST_VERSION);
        assertThat(billingAccount).isNotNull();
    }

    @Test
    public void getBillingAccount_badId() {
        try {
            adapter.getBillingAccount("UNKNOWN", EnumBillingAccountReadMod.LATEST_VERSION);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("UNKNOWN");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.BILLING_ACCOUNT_ID);
            return;
        }
        fail("No exception");
    }

}
