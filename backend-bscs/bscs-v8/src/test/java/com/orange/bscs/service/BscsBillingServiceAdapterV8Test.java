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
import com.orange.bscs.model.BscsBillCycle;
import com.orange.bscs.model.billing.EnumBillingAccountReadMod;
import com.orange.bscs.model.billing.EnumBillingAccountSearchMod;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsBillingServiceAdapterV8Test {
    
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
    public void getBillingAssignmentByContract_notAvailable() {
        try {
            adapter.getBillingAssignmentByContract("1001");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getBillCycles_ok() {
        List<BscsBillCycle> billCycles = adapter.getBillCycles();
        assertThat(billCycles).isNotEmpty();
    }

    @Test
    public void findBillingAccountsByCustomer__notAvailable() {
        try {
            adapter.findBillingAccountsByCustomer("1001", EnumBillingAccountSearchMod.ALL);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

    @Test
    public void getBillingAccount__notAvailable() throws BscsInvalidIdException {
        try {
            adapter.getBillingAccount("56",
                EnumBillingAccountReadMod.LATEST_VERSION);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).containsIgnoringCase("Not available");
            return;
        }
        fail("No exception");
    }

}
