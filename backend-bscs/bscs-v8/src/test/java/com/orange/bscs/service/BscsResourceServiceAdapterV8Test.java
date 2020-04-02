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
import com.orange.bscs.model.BscsStorageMedium;
import com.orange.bscs.model.criteria.BscsStorageMediumSearchCriteria;
import com.orange.bscs.model.factory.BscsObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BscsTestConfig.class })
@ActiveProfiles("corba")
@Category(CorbaBscsTest.class)
public class BscsResourceServiceAdapterV8Test {

    @Autowired
    private BscsResourceServiceAdapter adapter;

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
    public void findStorageMedium_ok() {
        final BscsStorageMediumSearchCriteria criteria = bscsObjectFactory.createBscsStorageMediumSearchCriteria();
        criteria.setSubmarketId(1L);
        criteria.setNetworkId(69L);
        criteria.setSerialNumber("89237021109100575980");
        criteria.setType(1L);
        List<BscsStorageMedium> sm = adapter.findStorageMedium(criteria);
        assertThat(sm).hasSize(1);
    }

    @Test
    public void findStorageMedium_okWithStar() {
        final BscsStorageMediumSearchCriteria criteria = bscsObjectFactory.createBscsStorageMediumSearchCriteria();
        criteria.setSubmarketId(1L);
        criteria.setNetworkId(69L);
        criteria.setSerialNumber("8923702110910057598*");
        criteria.setType(1L);
        List<BscsStorageMedium> sm = adapter.findStorageMedium(criteria);
        assertThat(sm).hasSize(1);
    }

    @Test
    public void findStorageMedium_koWithStar() {
        final BscsStorageMediumSearchCriteria criteria = bscsObjectFactory.createBscsStorageMediumSearchCriteria();
        criteria.setSubmarketId(1L);
        criteria.setNetworkId(69L);
        criteria.setSerialNumber("89237020906101205231");
        criteria.setType(1L);
        List<BscsStorageMedium> sm = adapter.findStorageMedium(criteria);
        assertThat(sm).hasSize(1);
        // star do not find attributed msisdn
        criteria.setSerialNumber("8923702090610120523*");
        sm = adapter.findStorageMedium(criteria);
        assertThat(sm).hasSize(0);
    }

    @Test
    public void findStorageMedium_unknownId() {
        final BscsStorageMediumSearchCriteria criteria = bscsObjectFactory.createBscsStorageMediumSearchCriteria();
        criteria.setSubmarketId(1L);
        criteria.setNetworkId(69L);
        criteria.setSerialNumber("toto");
        criteria.setType(1L);
        List<BscsStorageMedium> sm = adapter.findStorageMedium(criteria);
        assertThat(sm).isEmpty();
    }


}
