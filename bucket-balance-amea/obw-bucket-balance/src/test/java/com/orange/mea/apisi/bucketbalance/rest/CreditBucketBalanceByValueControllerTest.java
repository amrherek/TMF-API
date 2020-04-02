package com.orange.mea.apisi.bucketbalance.rest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

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
import com.orange.mea.apisi.obw.webservice.ZteWebService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CreditBucketBalanceByValueController.class, secure = false)
@ContextConfiguration(classes = { BucketBalanceConfigurationOBW.class })
@ComponentScan({ "com.orange.mea.apisi.bucketbalance.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
public class CreditBucketBalanceByValueControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private ZteWebService webService;

    @Test
    public void missingId() throws UnsupportedEncodingException, Exception {
        String req = "{\"publicKey\":{\"id\":\"0666666666\",\"name\":\"MSISDN\"},\"value\":20,\"unit\":\"BWP\",\"characteristic\":[{\"name\":\"externalData1\",\"value\":\"comment\"},{\"name\":\"externalData2\",\"value\":\"desc\"},{\"name\":\"profileId\",\"value\":\"0\"},{\"name\":\"taxCode\",\"value\":\"0\"}]}";

        mockMvc.perform(post("/api/bucketBalance/v1/creditBucketBalanceByValue").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(req)).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(4001)))
                .andExpect(jsonPath("$.description", containsString("id")));
    }

	@Test
	public void success() throws UnsupportedEncodingException, Exception {
        String req = "{\"id\":\"12345\",\"publicKey\":{\"id\":\"0666666666\",\"name\":\"MSISDN\"},\"value\":20,\"unit\":\"BWP\",\"characteristic\":[{\"name\":\"externalData1\",\"value\":\"comment\"},{\"name\":\"externalData2\",\"value\":\"desc\"},{\"name\":\"profileId\",\"value\":\"0\"},{\"name\":\"taxCode\",\"value\":\"0\"}]}";

		String res = mockMvc
				.perform(post("/api/bucketBalance/v1/creditBucketBalanceByValue").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(req))
                .andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		JSONAssert.assertEquals(req, res, JSONCompareMode.NON_EXTENSIBLE);

	}

}
