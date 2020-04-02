package com.orange.mea.apisi.eligibility.rest;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

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
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.emergency.TDebitAuthResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EligibilityApplicationWebXmlOBW.class)
public class EligibleOptionsControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private AuditContext auditContext;

    @Autowired
    private EligibleOptionsController eligibilityController;

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
    public void eligibleOptionsFilteredByProductOfferingId() throws UnsupportedEncodingException, Exception {
        String msisdn = "555706910221";
        when(emergencyWebService.debitAuth(msisdn)).thenReturn(createEmergencyResponse());
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        mockMvc.perform(
                get("/eligibleOptions?publicKey={msisdn}&productOfferingId={productOfferingId}",
                        "555706910221", "RESRE"))
        .andDo(print()) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$", hasSize(1))) //
        .andExpect(jsonPath("$.[0].canBeSubscribed").value(true))
        .andExpect(jsonPath("$.[0].productOfferingId").value("RESRE"))
        .andExpect(jsonPath("$.[0].productOfferingName").value("Resource Replacement"))
        .andExpect(jsonPath("$.[0].price.currencyCode").value("BWP"))
        .andExpect(jsonPath("$.[0].price.taxIncludedAmount").value("0.0"))
        .andExpect(jsonPath("$.[0].productSpecification.id").value("serviceBSCS"));
    }

    @Test
    public void eligibleOptionsFull() throws UnsupportedEncodingException, Exception {
        String msisdn = "555706910221";
        when(emergencyWebService.debitAuth(msisdn)).thenReturn(createEmergencyResponse());
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        mockMvc.perform(get("/eligibleOptions?publicKey={msisdn}", msisdn)).andDo(print()) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$.[*].canBeSubscribed").value(hasItem(true)))
        .andExpect(jsonPath("$.[*].productOfferingId").value(hasItem("RESRE")))
        .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("Resource Replacement")))
        .andExpect(jsonPath("$.[*].productOfferingId").value(hasItem("DIRNU")))
        .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("Directory Number Replacement")))
                .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("EmergencyCredit")))
        .andExpect(jsonPath("$.[*].price.currencyCode").value(everyItem(equalTo("BWP"))))
                .andExpect(jsonPath("$.[*].productSpecification.id")
                        .value(everyItem(
                                anyOf(equalTo("serviceBSCS"), equalTo("emergencyCredit"), equalTo("bundleData")))))
                .andExpect(jsonPath("$.[*].canBeSubscribed").value(everyItem(anyOf(equalTo(true), is(nullValue())))))
                .andExpect(jsonPath("$.[*].price.taxIncludedAmount").value(containsInAnyOrder(200.0, 0.0)));
    }

    @Test
    public void eligibleOptionsFilteredByProductSpecificationId() throws UnsupportedEncodingException, Exception {
        String msisdn = "555706910221";
        when(emergencyWebService.debitAuth(msisdn)).thenReturn(createEmergencyResponse());
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        mockMvc.perform(get("/eligibleOptions?publicKey={msisdn}&productSpecification.id={productSpecId}",
                "555706910221", "serviceBSCS")).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$.[*].canBeSubscribed").value(hasItem(true)))
                .andExpect(jsonPath("$.[*].productOfferingId").value(hasItem("RESRE")))
                .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("Resource Replacement")))
                .andExpect(jsonPath("$.[*].productOfferingId").value(hasItem("DIRNU")))
                .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("Directory Number Replacement")))
                .andExpect(jsonPath("$.[*].price.currencyCode").value(everyItem(equalTo("BWP"))))
                .andExpect(jsonPath("$.[*].productSpecification.id").value(everyItem(equalTo("serviceBSCS"))))
                .andExpect(jsonPath("$.[*].canBeSubscribed").value(everyItem(equalTo(true))))
                .andExpect(jsonPath("$.[*].price.taxIncludedAmount").value(containsInAnyOrder(200.0, 0.0)));
    }

    @Test
    public void eligibleOptionsFilteredByBadProductSpecificationId() throws UnsupportedEncodingException, Exception {
        mockMvc.perform(get("/eligibleOptions?publicKey={msisdn}&productSpecification.id={productSpecId}",
                "555706910221", "option")).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    public void eligibleOptions_emergencyCredit() throws UnsupportedEncodingException, Exception {
        String msisdn = "555706910221";
        when(emergencyWebService.debitAuth(msisdn)).thenReturn(createEmergencyResponse());
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        mockMvc.perform(get("/eligibleOptions?publicKey={msisdn}&productSpecification.id={productSpecId}",
                msisdn, "emergencyCredit")).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(1))) //
                .andExpect(jsonPath("$.[0].canBeSubscribed").value(true))
                .andExpect(jsonPath("$.[0].productOfferingName").value("EmergencyCredit"))
                .andExpect(jsonPath("$.[0].productSpecification.id").value("emergencyCredit"))
                .andExpect(jsonPath("$.[0].eligibleCharacteristic[0].name").value("maxAmount"))
                .andExpect(jsonPath("$.[0].eligibleCharacteristic[0].productSpecCharacteristicValues[0].value")
                        .value("100"));
    }

    @Test
    public void eligibleOptions_bundleData() throws UnsupportedEncodingException, Exception {
        String msisdn = "555706910221";
        when(emergencyWebService.debitAuth(msisdn)).thenReturn(createEmergencyResponse());
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        mockMvc.perform(get("/eligibleOptions?publicKey={msisdn}&productSpecification.id={productSpecId}", msisdn,
                "bundleData")).andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(8))) //
                .andExpect(jsonPath("$.[*].productOfferingId").value(hasItem("290")))
                .andExpect(jsonPath("$.[*].productOfferingName").value(hasItem("Prepaid Data Bundle-10MB")))
                .andExpect(jsonPath("$.[*].productSpecification.id").value(everyItem(equalTo("bundleData"))));
    }

    private TQueryAllPricePlanResponse createQueryAllPricePlanResponse() throws JAXBException {
        final File file = new File("src/test/resources/queryAllPricePlan.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TQueryAllPricePlanResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final JAXBElement<TQueryAllPricePlanResponse> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
                TQueryAllPricePlanResponse.class);
        return root.getValue();
    }

    private TDebitAuthResponse createEmergencyResponse() {
        TDebitAuthResponse res = new TDebitAuthResponse();
        res.setMaxAmount("100");
        return res;
    }

}
