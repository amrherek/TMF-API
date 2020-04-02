package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
import com.orange.bscs.model.BscsCustomerInfo;
import com.orange.bscs.model.factory.BscsObjectFactory;
import com.orange.bscs.service.exception.BscsFieldExceptionEnum;
import com.orange.bscs.service.exception.BscsInvalidFieldException;
import com.orange.bscs.service.exception.BscsInvalidIdException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsMarketSegmentServiceAdapterIXR2Test {

    @Autowired
    private BscsMarketSegmentServiceAdapter adapter;

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
    public void getCustomerInfo_ok() throws BscsInvalidIdException {
        BscsCustomerInfo customerInfo = adapter.getCustomerInfo("CUST0000000658");
        assertThat(customerInfo).isNotNull();
    }

    @Test
    public void getCustomerInfo_unknownId() {
        try {
            adapter.getCustomerInfo("CUST000");
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("CUST000");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

    @Test
    public void writeCustomerInfo_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsCustomerInfo info = bscsObjectFactory.createBscsCustomerInfo();
        info.setCustomerId("CUST0000000658");
        adapter.writeCustomerInfo(info, true);
    }

    @Test
    public void writeCustomerInfo_unknonwId() throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            BscsCustomerInfo info = bscsObjectFactory.createBscsCustomerInfo();
            info.setCustomerId("CUST000");
            adapter.writeCustomerInfo(info, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("CUST000");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

}
