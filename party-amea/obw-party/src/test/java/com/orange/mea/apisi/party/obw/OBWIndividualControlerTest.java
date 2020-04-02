package com.orange.mea.apisi.party.obw;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// Note: @WebMvcTest disable auto-configuration needed for BSCS
//@WebMvcTest(IndividualController.class)
@ContextConfiguration(classes = { PartyApplicationWebXmlOBW.class })
public class OBWIndividualControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void successForGetIndividual() throws Exception {
		String individualId = "CUST0000000001";
		mockMvc.perform(get("/api/party/v1/individual/" + individualId) //
				.accept(MediaType.APPLICATION_JSON)) //
                .andDo(print())
				.andExpect(status().isOk())//
                .andExpect(jsonPath("familyName", is("Test001")))
                .andExpect(jsonPath("givenName", is("Test")))
                .andExpect(jsonPath("birthDate", is("1979-08-26")));

	}

    @Test
    public void successForGetIndividualWithoutBirthDate() throws Exception {
        String individualId = "CUST0000000032";
        mockMvc.perform(get("/api/party/v1/individual/" + individualId) //
                .accept(MediaType.APPLICATION_JSON)) //
                .andDo(print()).andExpect(status().isOk())//
                .andExpect(jsonPath("familyName", is("Mueller")))
                .andExpect(jsonPath("givenName", is("Peter")))
                .andExpect(jsonPath("birthDate").doesNotExist());

    }

}
