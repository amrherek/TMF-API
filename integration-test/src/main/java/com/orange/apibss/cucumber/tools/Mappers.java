package com.orange.apibss.cucumber.tools;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productInventory.model.Product;

/**
 * @author Thibault Duperron
 */
@Service
public class Mappers {

    @Autowired
	private ObjectMapper objectMapper;

    /**
     * Parse a HttpClientErrorException and return the {@link Error}
     *
     * @param exception the HTTP exception
     * @return the body error
     * @throws IOException IOException
     */
	public ApiError parseError(HttpStatusCodeException exception) throws IOException {
		return objectMapper.readValue(exception.getResponseBodyAsString(), ApiError.class);
    }

    /**
     * Parse a json and return the list of {@link Product}
     *
     * @param json Products as json
     * @return array of products
     * @throws IOException IOException
     */
	public Product[] parseProducts(String json) throws IOException {
        return objectMapper.readValue(json, Product[].class);
    }

    /**
     * Parse a json and return the {@link Individual}
     *
     * @param json Individual as json
     * @return the Individual
     * @throws IOException IOException
     */
	public Individual parseIndividual(String json) throws IOException {
        return objectMapper.readValue(json, Individual.class);
    }
}
