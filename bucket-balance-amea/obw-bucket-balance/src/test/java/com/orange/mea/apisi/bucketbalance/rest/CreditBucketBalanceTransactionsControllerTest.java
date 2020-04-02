package com.orange.mea.apisi.bucketbalance.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.joda.time.LocalDate;
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
import com.orange.mea.apisi.obw.webservice.TQueryOCSCDRResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CreditBucketBalanceTransactionsController.class, secure = false)
@ContextConfiguration(classes = { BucketBalanceConfigurationOBW.class })
@ComponentScan({ "com.orange.mea.apisi.bucketbalance.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
public class CreditBucketBalanceTransactionsControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private ZteWebService webService;

	@Test
	public void success() throws UnsupportedEncodingException, Exception {
        when(webService.getTopupHistory("555706910221", new LocalDate(2010, 1, 1), null, 10))
                .thenReturn(buildTQueryOCSCDRResponse());
        when(webService.getTopupHistory("555706910221", new LocalDate(2010, 1, 1), null, null))
                .thenReturn(buildTQueryOCSCDRResponse());

		String res = mockMvc
				.perform(get("/api/bucketBalance/v1/creditBucketBalanceTransactions") //
						.param("publicKey.id", "555706910221") //
						.param("limit", "10") //
                        .param("date.gte", "2010-01-01") //
						.accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk())//
                .andExpect(header().longValue("X-Total-Count", 2))//
                .andExpect(header().longValue("X-Result-Count", 2))//
                .andReturn().getResponse().getContentAsString();

        String ref = "[{\"date\":\"2017-05-16T12:55:38Z\",\"value\":40.0,\"unit\":\"BWP\"},{\"date\":\"2017-05-16T12:55:35Z\",\"value\":30.0,\"unit\":\"BWP\"}]";
		JSONAssert.assertEquals(ref, res, JSONCompareMode.NON_EXTENSIBLE);

	}

    private TQueryOCSCDRResponse buildTQueryOCSCDRResponse() throws JAXBException {
        final File file = new File("src/test/resources/queryOCSCDRResponse.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TQueryOCSCDRResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final JAXBElement<TQueryOCSCDRResponse> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
                TQueryOCSCDRResponse.class);
        return root.getValue();
    }

}
