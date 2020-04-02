package com.orange.mea.apisi.payment.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.orange.apibss.common.exceptions.ApiExceptionCode;
import com.orange.mea.apisi.payment.obw.configuration.PaymentConfigurationOBW;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PaymentController.class, secure = false)
@ContextConfiguration(classes = { PaymentConfigurationOBW.class })
@ComponentScan({ "com.orange.mea.apisi.payment.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void success() throws UnsupportedEncodingException, Exception {
        String res = mockMvc
                .perform(get("/api/payment/v1/payment") //
                .param("payer.id", "CUST0000000148") //
                        .param("paymentDate.gte", "2000-01-01")//
                .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk())//
                .andExpect(header().longValue("X-Total-Count", 179))//
                .andExpect(header().longValue("X-Result-Count", 5))//
                .andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/latestPayments.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.STRICT);
    }

    @Test
    public void success_withLimit() throws UnsupportedEncodingException, Exception {
        String res = mockMvc
                .perform(get("/api/payment/v1/payment") //
                        .param("payer.id", "CUST0000000148") //
                        .param("limit", "3") //
                        .param("paymentDate.gte", "2000-01-01")//
                        .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk())//
                .andExpect(header().longValue("X-Total-Count", 179))//
                .andExpect(header().longValue("X-Result-Count", 3))//
                .andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/3Payments.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.STRICT);
    }

    @Test
    public void success_withDate() throws UnsupportedEncodingException, Exception {
        String res = mockMvc
                .perform(get("/api/payment/v1/payment") //
                        .param("payer.id", "CUST0000000148") //
                        .param("paymentDate.lte", "2015-07-22") //
                        .param("paymentDate.gte", "2015-07-22") //
                        .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk())//
                .andExpect(header().longValue("X-Total-Count", 1))//
                .andExpect(header().longValue("X-Result-Count", 1))//
                .andReturn().getResponse().getContentAsString();

        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/1Payment.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.STRICT);
    }

    @Test
    public void fail_missingDate() throws UnsupportedEncodingException, Exception {
        mockMvc.perform(get("/api/payment/v1/payment") //
                .param("payer.id", "CUST0000000148") //
                .accept(MediaType.APPLICATION_JSON)) //
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_EMPTY_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Missing parameter")))
                .andExpect(jsonPath("$.description", containsString("paymentDate.gte")));

    }

}
