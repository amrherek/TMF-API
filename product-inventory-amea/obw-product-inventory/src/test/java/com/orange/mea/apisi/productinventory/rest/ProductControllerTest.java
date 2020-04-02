package com.orange.mea.apisi.productinventory.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.orange.mea.apisi.obw.webservice.TArrayOfFellowISDNDto;
import com.orange.mea.apisi.obw.webservice.TFellowISDNDto;
import com.orange.mea.apisi.obw.webservice.ZteWebService;
import com.orange.mea.apisi.obw.webservice.exception.FafServiceNotConfiguredException;
import com.orange.mea.apisi.productinventory.obw.configuration.ProductInventoryConfigurationOBW;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
@ContextConfiguration(classes = { ProductInventoryConfigurationOBW.class })
@ComponentScan({ "com.orange.mea.apisi.productinventory.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
@ActiveProfiles("file")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZteWebService webService;

    @Test
    public void getFaf() throws UnsupportedEncodingException, Exception {
        final String msisdn = "26772118188";

        given(webService.listFriendsAndFamily(msisdn)).willReturn(buildFafAnswer());

        final String res = mockMvc
                .perform(get("/api/productInventory/v1/product")//
                        .param("publicKey", msisdn) //
                        .param("productSpecification.id", "faf") //
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())//
                .andExpect(status().isOk())//
                .andReturn().getResponse().getContentAsString();
        final String ref = new String(Files.readAllBytes(Paths.get("src/test/resources/faf.json")));
        JSONAssert.assertEquals(ref, res, JSONCompareMode.NON_EXTENSIBLE);
    }

    private TArrayOfFellowISDNDto buildFafAnswer() {
        TArrayOfFellowISDNDto res = new TArrayOfFellowISDNDto();
        TFellowISDNDto faf = new TFellowISDNDto();
        faf.setFellowISDN("26774843725");
        res.getFellowISDNDto().add(faf);
        faf = new TFellowISDNDto();
        faf.setFellowISDN("26774843726");
        res.getFellowISDNDto().add(faf);
        return res;
    }

    @Test
    public void getFaf_contractWithoutFaf() throws UnsupportedEncodingException, Exception {
        final String msisdn = "26772105013";

        given(webService.listFriendsAndFamily(msisdn))
                .willThrow(new FafServiceNotConfiguredException(msisdn, "no faf"));

        mockMvc
        .perform(get("/api/productInventory/v1/product")//
                .param("publicKey", msisdn) //
                        .param("productSpecification.id", "faf") //
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())//
                .andExpect(status().is5xxServerError());
    }

}
