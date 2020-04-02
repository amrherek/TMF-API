package com.orange.mea.apisi.customerbill.obw.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.orange.apibss.common.exceptions.ApiExceptionCode;
import com.orange.mea.apisi.customerbill.obw.CustomerBillApplicationWebXmlOBW;
import com.orange.mea.apisi.customerbill.rest.CustomerBillController;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerBillController.class)
@ContextConfiguration(classes = { CustomerBillApplicationWebXmlOBW.class })
@ComponentScan({ "com.orange.mea.apisi.customerbill.obw", "com.orange.bscs.autoconfigure",
        "com.orange.apibss.common.autoconfigure" })
public class OBWCustomerBillResourceTest {
    @Value("${api.basePath}")
    private String basePath;

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test of the service find customerBill
     *
     * @throws Exception if something went wrong
     */
    @Test
    public void shouldReturnCustomerBill() throws Exception {
        mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "26712345645").param("billDate.gte", "2000-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is("4790336")))
                .andExpect(jsonPath("$[1].state", is("settled")))
                .andExpect(jsonPath("$[1].amountDue.value", is(1092.75)))
                .andExpect(jsonPath("$[1].remainingAmount.value", is(0.0)))
                .andExpect(jsonPath("$[1].billDate", is("2014-12-16T00:00:00Z")))
                .andExpect(jsonPath("$[1].paymentDueDate", is("2014-12-30T00:00:00Z")))
                .andExpect(jsonPath("$[1].amountDue.unit", is("BWP")))
                .andExpect(jsonPath("$[1].appliedPayment[0].appliedAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[1].appliedPayment[0].appliedAmount.value", is(1092.75)))
                .andExpect(jsonPath("$[1].appliedPayment[0].payment.id", is("4852821")))
                .andExpect(jsonPath("$[1].appliedPayment[0].payment.paymentDate", is("2015-02-11T00:00:00Z")));
    }

    @Test
    public void shouldReturnCustomerBillWithMostRecentFirst() throws Exception {
        mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "26712345645").param("billDate.gte", "2000-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].billDate", is("2015-01-16T00:00:00Z")))
                .andExpect(jsonPath("$[1].billDate", is("2014-12-16T00:00:00Z")));
    }


    /**
     * <b>API : findCustomerBill<br/>
     * </b> <b>publicKey is mandatory parameter</B> Should return the
     * appropriate error if publicKey is omitted<br/>
     * 
     * @throws Exception
     */
    @Test
    public void shouldCheckMsisdnOrPartyIdIsInRequest() throws Exception {

		mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_BAD_PARAMETERS)))
                .andExpect(jsonPath("$.message", is("Bad parameter combination")))
                .andExpect(jsonPath("$.description", containsString("publicKey")))
                .andExpect(jsonPath("$.description", containsString("relatedParty.id")));
    }

    @Test
    public void shouldCheckEmptyMsisdnIsInRequest() throws Exception {

		mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "").param("billDate.gte", "2000-01-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_BAD_VALUE_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Bad parameter value")))
                .andExpect(jsonPath("$.description", containsString("publicKey")));
    }

    @Test
    public void shouldCheckBillDateGteIsInRequest() throws Exception {

        mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON).param("publicKey",
                "26712345645")).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_EMPTY_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Missing parameter")))
                .andExpect(jsonPath("$.description", containsString("billDate.gte")));
    }

    @Test
    public void shouldCheckDateValues() throws Exception {

        mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "26712345645")
                .param("billDate.gte", "2012-05-01")
                .param("billDate.lte", "2012-04-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_BAD_VALUE_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Bad parameter value")))
                .andExpect(jsonPath("$.description", containsString("endDate")));
    }


    @Test
    public void shouldCheckDateFormat() throws Exception {
		mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "26712345645")
                .param("billDate.gte","2012-44-01"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_BAD_FORMAT_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Bad parameter format")))
                .andExpect(jsonPath("$.description", containsString("startDate")));

    }

    @Test
    public void shouldCheckLimitFormat() throws Exception {
		mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "26712345645")
                .param("limit", "un"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(ApiExceptionCode.FC_BAD_FORMAT_PARAMETER)))
                .andExpect(jsonPath("$.message", is("Bad parameter format")))
                .andExpect(jsonPath("$.description", containsString("limit")));
    }


    /**
     * Test of the service find invoice by Rest
     *
     * @throws Exception if something went wrong
     */
    @Test
    public void shouldReturnFilteredByDate() throws Exception {
        mockMvc.perform(get("/" + basePath + "customerBill")
                .accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "5550001154")
                .param("billDate.gte", "2015-01-02")
                .param("billDate.lte", "2015-01-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("4832268")))
                .andExpect(jsonPath("$[0].state", is("validated")))
                .andExpect(jsonPath("$[0].amountDue.value", is(799.0)))
                .andExpect(jsonPath("$[0].amountDue.unit", is("BWP")))
                .andExpect(jsonPath("$[0].remainingAmount.value", is(799.0)))
                .andExpect(jsonPath("$[0].remainingAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[0].appliedAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[0].appliedAmount.value", is(7.25)))
                .andExpect(jsonPath("$[0].appliedPayment[0].payment.id", is("4852821")))
                .andExpect(jsonPath("$[0].appliedPayment[0].payment.paymentDate", is("2015-02-11T00:00:00Z")))
                .andExpect(jsonPath("$[0].appliedPayment[1].appliedAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[1].appliedAmount.value", is(791.75)))
                .andExpect(jsonPath("$[0].appliedPayment[1].payment.id", is("4895475")))
                .andExpect(jsonPath("$[0].appliedPayment[1].payment.paymentDate", is("2015-03-04T00:00:00Z")));
    }

    /**
     * Test of the service find invoice by Rest
     *
     * @throws Exception if something went wrong
     */
    @Test
    public void shouldReturnFilteredByNbOfResult() throws Exception {
		mockMvc.perform(get("/" + basePath + "customerBill").accept(MediaType.APPLICATION_JSON)
                .param("publicKey", "5550001154")
                .param("billDate.gte", "2000-01-01")
                .param("limit", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("4832268")))
                .andExpect(jsonPath("$[0].state", is("validated")))
                .andExpect(jsonPath("$[0].amountDue.value", is(799.0)))
                .andExpect(jsonPath("$[0].amountDue.unit", is("BWP")))
                .andExpect(jsonPath("$[0].remainingAmount.value", is(799.0)))
                .andExpect(jsonPath("$[0].remainingAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[0].appliedAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[0].appliedAmount.value", is(7.25)))
                .andExpect(jsonPath("$[0].appliedPayment[0].payment.id", is("4852821")))
                .andExpect(jsonPath("$[0].appliedPayment[0].payment.paymentDate", is("2015-02-11T00:00:00Z")))
                .andExpect(jsonPath("$[0].appliedPayment[1].appliedAmount.unit", is("BWP")))
                .andExpect(jsonPath("$[0].appliedPayment[1].appliedAmount.value", is(791.75)))
                .andExpect(jsonPath("$[0].appliedPayment[1].payment.id", is("4895475")))
                .andExpect(jsonPath("$[0].appliedPayment[1].payment.paymentDate", is("2015-03-04T00:00:00Z")));
    }


//    @Test
//    public void shouldThrowNoCustomerFound() throws Exception {
//
//        mockMvc.perform(get("/api/customerBill").accept(MediaType.APPLICATION_JSON)
//                .param("publicKey", "toto"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.code", is(CustomerBillErrorDictionary.API_ERR_FC001)))
//                .andExpect(jsonPath("$.label", is("Missing parameter")))
//                .andExpect(jsonPath("$.message", containsString("publicKey")));
//    }


}
