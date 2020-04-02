package com.orange.bscs.cms.servicelayeradapter;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.connection.ISOIConnectionProvider;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryFile;
import com.orange.bscs.api.model.AbstractSVLObjectFactory;
import com.orange.bscs.model.contract.BSCSContract;
import com.orange.bscs.model.contract.BSCSContractSearch;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by deyb6792 on 11/08/2016.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
public class ContractServiceAdapterTest  {

    @Autowired
    ContractServiceAdapterI adapter;

    @Autowired
    ConnectionBackendFactoryFile fileFactory;

    @Autowired
    ISOIConnectionProvider connFactory;

    @BeforeClass
    public static void init(){
        AbstractSVLObjectFactory.init(new SVLObjectFactoryIXR4());
    }


    @Before
    public void setConnection() throws Exception{
        fileFactory.changeRacinePath("/mock/contract");
        ConnectionHolder.setConnection(connFactory.retreiveConnection());
    }

    @Test
    public void testContractSearch() {
        BSCSContractSearch contractSearchModel = new BSCSContractSearch();
        contractSearchModel.setStringValue("DN_NUM", "26772174850");
        List<BSCSContract> contracts = adapter.contractSearch(contractSearchModel);
        assertThat(contracts).isNotNull().isNotEmpty();
    }

}
