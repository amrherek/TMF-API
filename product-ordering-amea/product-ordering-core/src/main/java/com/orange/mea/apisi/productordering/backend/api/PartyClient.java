package com.orange.mea.apisi.productordering.backend.api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.apibss.common.exceptions.ApiError;
import com.orange.apibss.common.exceptions.ApiException;
import com.orange.apibss.common.exceptions.ApiExceptionCode;
import com.orange.apibss.common.exceptions.badrequest.NotFoundException;
import com.orange.apibss.party.model.Individual;
import com.orange.apibss.productOrdering.model.ProductOrder;
import com.orange.apibss.productOrdering.model.RelatedIndividual;
import com.orange.apibss.productOrdering.model.Role;
import com.orange.mea.apisi.productordering.exception.PartyTechnicalException;
import com.orange.mea.apisi.productordering.exception.PartyUpdateException;
import com.orange.mea.apisi.productordering.transformer.RelatedPartyToIndividualTransformer;

/**
 * Client for calling remote Party API
 *
 * @author xbbs3851
 *
 */
@Service
public class PartyClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PARTY = "Party";

    @Value("${apitmf.party.url.base}")
    protected String partyUrlBase;

    @Value("${apitmf.party.url.individual.update:/individual/{id}}")
    private String updatePartyUrl;

    @Value("${http.proxy.host:}")
    private String proxyHost;

    @Value("${http.proxy.port:}")
    private String proxyPort;

    @Autowired
    protected ObjectMapper objectMapper;

    protected RestTemplate restTemplate;

    @Autowired
    protected RelatedPartyToIndividualTransformer relatedPartyToIndividualTransformer;

    // TODO : make a generic restClient initializer @see TutorServiceImplOCM
    @PostConstruct
    private void initRestClient() {

        // Managing an http proxy
        if (!StringUtils.isEmpty(proxyHost) && !StringUtils.isEmpty(proxyPort)) {
            logger.debug("Using proxy {}:{}", proxyHost, proxyPort);

            final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

            final Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
            requestFactory.setProxy(proxy);

            restTemplate = new RestTemplate(requestFactory);
        } else {
            restTemplate = new RestTemplate();
        }
        final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        final MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);
        messageConverters.add(jsonMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
    }

    /**
     * Sends a PUT http request to Party API for updating individual
     *
     * @param productOrder
     * @return
     * @throws ApiException
     */
    public Individual updateIndividual(final ProductOrder productOrder) throws ApiException {
        // retreive customer id
        final RelatedIndividual relatedIndividual = findCustomer(productOrder);

        // build Individual
        if (relatedIndividual != null) {
            final Individual customer = relatedPartyToIndividualTransformer.doTransform(relatedIndividual);
            final String customerId = customer.getId();
            try {

                final String url = partyUrlBase.concat(updatePartyUrl);

                final HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                final HttpEntity<Individual> entity = new HttpEntity<Individual>(customer, headers);

                final ResponseEntity<Individual> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                        entity,
                        Individual.class, customerId);

                if (responseEntity.getBody() == null) {
                    throw new NotFoundException("Individual not found for updating : " + customerId);
                }

                return responseEntity.getBody();

            } catch (HttpServerErrorException | HttpClientErrorException e) {
                logger.error(e.getResponseBodyAsString());
                try {
                    final ApiError error = objectMapper.readValue(e.getResponseBodyAsString(), ApiError.class);
                    if (error != null && e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        throw new PartyUpdateException(error.getDescription());
                    }
                    if (error != null && error.getCode().intValue() == ApiExceptionCode.FC_NOT_FOUND) {
                        throw new PartyUpdateException(error.getDescription());
                    }
                    if (error != null) {
                        throw new PartyTechnicalException(PARTY, error.getDescription(), e);
                    }
                    throw new PartyTechnicalException(PARTY, "unkwown error", e);
                } catch (final IOException e1) {
                    logger.debug("Unable to parse response", e);
                    throw new PartyTechnicalException(PARTY, "unreachable API", e);
                }
            } catch (final RestClientException e) {
                logger.error(e.getMessage());
                throw new PartyTechnicalException(PARTY, "unreachable API", e);
            }
        }

        return null;
    }

    protected RelatedIndividual findCustomer(final ProductOrder productOrder) {
        if (productOrder.getRelatedIndividual() == null) {
            return null;
        }
        for (final RelatedIndividual indiv : productOrder.getRelatedIndividual()) {
            if (indiv.getRole().equals(Role.CUSTOMER)) {
                return indiv;
            }
        }
        return null;
    }

}
