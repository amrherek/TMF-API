package com.orange.mea.apisi.eligibility.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.mea.apisi.eligibility.obw.EligibilityApplicationWebXmlOBW;
import com.orange.mea.apisi.obw.webservice.EmergencyWebService;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EligibilityApplicationWebXmlOBW.class)
public class EligibleContractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private EligibleContractController eligibilityController;
    @MockBean
    private AuditContext auditContext;

    @MockBean
    private EmergencyWebService emergencyWebService;

    @MockBean
    private ZteWebService webService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(eligibilityController).build();
    }

    @Test
    public void eligibleContract() throws UnsupportedEncodingException, Exception {
        mockMvc.perform(get("/eligibleContract?publicKey={msisdn}", "26774843725"))//
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.productOffering[0].id", is("PROZE")))
                .andExpect(jsonPath("$.productOffering[0].name", is("Orange Zebra")))
                .andExpect(jsonPath("$.productOffering[1].id", is("PROMM")))
                .andExpect(jsonPath("$.productOffering[1].name", is("Orange Masa Mova")));
    }

}
