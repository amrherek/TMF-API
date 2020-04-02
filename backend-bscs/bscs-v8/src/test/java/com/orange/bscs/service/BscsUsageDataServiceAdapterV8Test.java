package com.orange.bscs.service;

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
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsUsageDataServiceAdapterV8Test {

    @Autowired
    private BscsUsageDataServiceAdapter adapter;

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

    @Test(expected = SOIException.class)
    public void getUsageDataRecords_ok() {
        BscsUsageDataRecordSearchCriteria criteria = bscsObjectFactory.createBscsUsageDataRecordSearchCriteria();
        criteria.setContractId("4836067");
        criteria.setSearchLimit(10);
        // technical exception
        adapter.getUsageDataRecords(criteria);
    }

}
