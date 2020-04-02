package com.orange.bscs.cms;

import java.util.Map;

import com.orange.bscs.api.model.AbstractSVLObjectFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.orange.bscs.api.connection.ConnectionHolder;
import com.orange.bscs.api.connection.ISOIConnectionProvider;
import com.orange.bscs.api.connection.backend.ConnectionBackendFactoryFile;
import com.orange.bscs.cms.servicelayeradapter.MarketSegmentServiceAdapterI;
import com.orange.bscs.model.factory.SVLObjectFactoryIXR4;

import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext-test.xml" })
public class MarketSegmentTest {
        
        @Autowired
        MarketSegmentServiceAdapterI adapter;
        
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
            fileFactory.changeRacinePath("/mock/marketsegment");

            ConnectionHolder.setConnection(connFactory.retreiveConnection());
        }
        
        
        @Test
        public void phoneUsageRead(){
            Map<Integer,String> res = adapter.phoneUsageRead();
            
            assertThat(res).as("phoneUsage").hasSize(3);
        }
     
}
