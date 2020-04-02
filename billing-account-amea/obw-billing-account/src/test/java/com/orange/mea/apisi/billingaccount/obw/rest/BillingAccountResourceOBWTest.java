package com.orange.mea.apisi.billingaccount.obw.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.orange.apibss.common.audit.AuditContext;
import com.orange.apibss.common.audit.AuditEvent;
import com.orange.apibss.common.configuration.ApiErrorsHandler;
import com.orange.mea.apisi.billingaccount.obw.BillingAccountApplicationWebXmlOBW;
import com.orange.mea.apisi.billingaccount.rest.BillingAccountController;

/**
 * Test of {@link BillingAccountController} for OTN
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingAccountApplicationWebXmlOBW.class)
public class BillingAccountResourceOBWTest {

    @MockBean
    private AuditContext auditContext;

    @Autowired
    private ApiErrorsHandler apiErrorsHandler;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private BillingAccountController billingAccountController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(auditContext.getAuditEvent()).thenReturn(new AuditEvent());

        mockMvc = MockMvcBuilders.standaloneSetup(billingAccountController).setControllerAdvice(apiErrorsHandler)
                .setMessageConverters(jacksonMessageConverter).build();
    }
    /**
     * Test of the service find invoice by Rest
     *
     * @throws Exception if something went wrong
     */
    @Test
    public void shouldReturnBillingAccount() throws Exception {
        mockMvc.perform(get("/billingAccount").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "0123456789"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("BA0000000656"))).andExpect(jsonPath("$[0].state", is("active")))
                .andExpect(jsonPath("$[0].billStructure.cycleSpecification.frequency", is("1 month")))
                .andExpect(jsonPath("$[0].billStructure.cycleSpecification.dateShift", is(14)))
                .andExpect(jsonPath("$[0].defaultPaymentMethod.name", is("Credit Card")));

    }

    @Test
    public void shouldReturnBillingAccountWithoutPaymentMeanName() throws Exception {
        mockMvc.perform(get("/billingAccount")
                .accept(MediaType.APPLICATION_JSON).param("publicKey", "9876543210"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("BA0000000101")))
                .andExpect(jsonPath("$[0].state", is("active")))
                .andExpect(jsonPath("$[0].billStructure.cycleSpecification.frequency", is("1 month")))
                .andExpect(jsonPath("$[0].billStructure.cycleSpecification.dateShift").doesNotExist())
                .andExpect(jsonPath("$[0].defaultPaymentMethod.name").doesNotExist());

    }

    /**
     * <b>API : findBillingAccount<br/>
     * </b> <b>publicKey is mandatory parameter</B> Should return the
     * appropriate error if publicKey is omitted<br/>
     */
    @Test
    public void shouldCheckMsisdnIsInRequest() throws Exception {
        mockMvc.perform(
                get("/billingAccount").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message", is("Bad parameter combination")))
        .andExpect(jsonPath("$.description", containsString("publicKey")));
    }

}
