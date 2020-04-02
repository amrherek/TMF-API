package com.orange.bscs.service;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.orange.bscs.config.BscsTestConfig;
import com.orange.bscs.model.BscsUsageDataRecord;
import com.orange.bscs.model.criteria.BscsUsageDataRecordSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsUsageDataServiceAdapterV9Test {

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

    @Test
    public void getUsageDataRecords_ok() {
        BscsUsageDataRecordSearchCriteria criteria = bscsObjectFactory.createBscsUsageDataRecordSearchCriteria();
        criteria.setContractId("CONT0000138421");
        criteria.setSearchLimit(10);
        List<BscsUsageDataRecord> usageDataRecords = adapter.getUsageDataRecords(criteria);
        // no data
        assertThat(usageDataRecords).isEmpty();
    }

}
