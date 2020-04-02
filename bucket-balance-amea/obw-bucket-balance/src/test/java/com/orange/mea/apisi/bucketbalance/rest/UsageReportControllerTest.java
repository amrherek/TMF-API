package com.orange.mea.apisi.bucketbalance.rest;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.orange.mea.apisi.bucketbalance.obw.configuration.BucketBalanceConfigurationOBW;
import com.orange.mea.apisi.obw.webservice.TQueryProfileAndBalForCRMResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UsageReportController.class, secure = false)
@ContextConfiguration(classes = { BucketBalanceConfigurationOBW.class })
@ComponentScan({ "com.orange.mea.apisi.bucketbalance.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
public class UsageReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZteWebService webService;

    @Test
    public void prepaidOk() throws Exception {
        given(webService.getBalances("555706910221")).willReturn(buildTQueryProfileAndBalForCRMResponseForPrepaid());

        final String res = mockMvc
                .perform(get("/api/bucketBalance/v1/usageReport?publicKey.id=555706910221&ratingType=prepaid")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(jsonPath("$[0].date", not(nullValue())))
                .andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/uc19_prepaid.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.LENIENT);
    }

    @Test
    public void prepaidOkWithFilter() throws Exception {
        given(webService.getBalances("555706910221")).willReturn(buildTQueryProfileAndBalForCRMResponseForPrepaid());

        final String res = mockMvc
                .perform(
                        get("/api/bucketBalance/v1/usageReport?publicKey.id=555706910221&ratingType=prepaid&buckets.bucketBalances.unit=BWP")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(jsonPath("$[0].date", not(nullValue()))).andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/uc19_prepaid.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.LENIENT);
    }

    @Test
    public void prepaidOkWithFilter_noResult() throws Exception {
        given(webService.getBalances("555706910221")).willReturn(buildTQueryProfileAndBalForCRMResponseForPrepaid());

        mockMvc.perform(
                get("/api/bucketBalance/v1/usageReport?publicKey.id=555706910221&ratingType=prepaid&buckets.bucketBalances.unit=")
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(jsonPath("$[0].date", not(nullValue())))//
                .andExpect(jsonPath("$[0].buckets").doesNotExist());
    }

    @Test
    public void postpaidOk() throws Exception {
        given(webService.getBalances("555706910221")).willReturn(buildTQueryProfileAndBalForCRMResponse());

        final String res = mockMvc
                .perform(get("/api/bucketBalance/v1/usageReport?publicKey.id=555706910221&ratingType=postpaid")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())//
                .andExpect(status().isOk())//
                .andExpect(jsonPath("$[0].date", not(nullValue()))).andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/uc19.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.LENIENT);
    }

    private TQueryProfileAndBalForCRMResponse buildTQueryProfileAndBalForCRMResponse() throws JAXBException {
        final File file = new File("src/test/resources/profileAndBalForCRMResponse.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TQueryProfileAndBalForCRMResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final JAXBElement<TQueryProfileAndBalForCRMResponse> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
                TQueryProfileAndBalForCRMResponse.class);
        return root.getValue();
    }

    private TQueryProfileAndBalForCRMResponse buildTQueryProfileAndBalForCRMResponseForPrepaid() throws JAXBException {
        final File file = new File("src/test/resources/profileAndBalForCRMResponse_prepaid.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TQueryProfileAndBalForCRMResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final JAXBElement<TQueryProfileAndBalForCRMResponse> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
                TQueryProfileAndBalForCRMResponse.class);
        return root.getValue();
    }

}
