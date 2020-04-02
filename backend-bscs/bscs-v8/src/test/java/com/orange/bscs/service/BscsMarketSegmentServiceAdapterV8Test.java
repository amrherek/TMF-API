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
public class BscsMarketSegmentServiceAdapterV8Test {

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
        BscsCustomerInfo customerInfo = adapter.getCustomerInfo("1001");
        assertThat(customerInfo).isNotNull();
    }

    @Test
    public void getCustomerInfo_unknownId() throws BscsInvalidIdException {
        // no exception if customer id does not exist
        adapter.getCustomerInfo("10");
    }

    @Test
    public void writeCustomerInfo_ok() throws BscsInvalidIdException, BscsInvalidFieldException {
        BscsCustomerInfo info = bscsObjectFactory.createBscsCustomerInfo();
        info.setCustomerId("8181196");
        adapter.writeCustomerInfo(info, true);
    }

    @Test
    public void writeCustomerInfo_unknonwId() throws BscsInvalidIdException, BscsInvalidFieldException {
        try {
            BscsCustomerInfo info = bscsObjectFactory.createBscsCustomerInfo();
            info.setCustomerId("10");
            adapter.writeCustomerInfo(info, true);
        } catch (BscsInvalidIdException e) {
            assertThat(e.getId()).isEqualTo("10");
            assertThat(e.getName()).isEqualTo(BscsFieldExceptionEnum.CUSTOMER_ID);
            return;
        }
        fail("No exception");
    }

}
