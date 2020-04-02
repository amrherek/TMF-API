package com.orange.mea.apisi.productcatalog.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.cxf.helpers.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.orange.apibss.common.configuration.ApiErrorsHandler;
import com.orange.mea.apisi.obw.webservice.TQueryAllPricePlanResponse;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.productcatalog.obw.ProductCatalogApplicationWebXmlOBW;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductCatalogApplicationWebXmlOBW.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProductOfferingControllerOBWTest {

    private MockMvc mockMvc;

    @MockBean
    private ZteWebService webService;

    @Autowired
    private ProductOfferingController productOfferingController;

    @Autowired
    private ApiErrorsHandler apiErrorsHandler;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productOfferingController)//
                .setControllerAdvice(apiErrorsHandler)// to format errors
                .setMessageConverters(jacksonMessageConverter)// to remove null parameters
                .build();
    }

    /**
     * Different types
     * 
     * @throws Exception
     */
    // @Test
    public void getProductOffering_configurationForDifferentType_ok() throws Exception {
        String productOfferingId = "BAOC";
        String res = mockMvc
                .perform(get("/productOffering/" + productOfferingId)//
                .accept(MediaType.APPLICATION_JSON) //
                .param("operationType", "parameterConfiguration"))//
                .andDo(print()).andExpect(status().isOk())//
                .andReturn().getResponse().getContentAsString();
        String ref = IOUtils
                .toString(Files.newBufferedReader(Paths.get("src/test/resources/BAOC.json"), StandardCharsets.UTF_8));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.STRICT);
    }

    /**
     * DB - free unit
     * 
     * @throws Exception
     */
    // @Test
    public void getProductOffering_configurationForFreeUnit_ok() throws Exception {
        String productOfferingId = "CAPSV";
        String res = mockMvc
                .perform(get("/productOffering/" + productOfferingId)//
                        .accept(MediaType.APPLICATION_JSON) //
                        .param("operationType", "parameterConfiguration"))//
                .andDo(print()).andExpect(status().isOk())//
                .andReturn().getResponse().getContentAsString();
        String ref = IOUtils
                .toString(Files.newBufferedReader(Paths.get("src/test/resources/CAPSV.json"), StandardCharsets.UTF_8));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.STRICT);
    }

    /**
     * UC 3
     * 
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    @Test
    public void successForOptionsCompatibility() throws UnsupportedEncodingException, Exception {
        when(webService.queryAllPricePlan("2")).thenReturn(createQueryAllPricePlanResponse());

        String res = mockMvc
                .perform(get("/productOffering") //
                        .param("offerProductOfferingId", "GeSPH") //
                        .param("operationType", "compatibilityWith") //
                        .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/uc3.json")), "UTF-8");
        JSONAssert.assertEquals(ref, res, JSONCompareMode.NON_EXTENSIBLE);
    }

    private TQueryAllPricePlanResponse createQueryAllPricePlanResponse() throws JAXBException {
        final File file = new File("src/test/resources/queryAllPricePlan.xml");
        final JAXBContext jaxbContext = JAXBContext.newInstance(TQueryAllPricePlanResponse.class);
        final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        final JAXBElement<TQueryAllPricePlanResponse> root = jaxbUnmarshaller.unmarshal(new StreamSource(file),
                TQueryAllPricePlanResponse.class);
        return root.getValue();
    }

}
