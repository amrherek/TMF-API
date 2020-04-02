package com.orange.apibss.cucumber.productInventory;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.apibss.cucumber.Arguments;
import com.orange.apibss.cucumber.StepDefs;
import com.orange.apibss.cucumber.config.ApibssProperties;
import com.orange.apibss.cucumber.config.ProductInventoryProperties;
import com.orange.apibss.cucumber.config.productInventory.IccId;
import com.orange.apibss.cucumber.config.productInventory.Msisdn;
import com.orange.apibss.cucumber.config.productInventory.PartyId;
import com.orange.apibss.cucumber.config.productInventory.ProductStatus;
import com.orange.apibss.cucumber.tools.Tools;
import com.orange.apibss.productInventory.model.Product;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Step defs for find products API
 *
 * @author Thibault Duperron
 */
public class FindProductStepDefs extends StepDefs {

    @Autowired
    private ApibssProperties apibssProperties;
    @Autowired
    private ProductInventorySharedData productInventorySharedData;
    @Autowired
    private RestTemplate restTemplate;

    @When("^Use a IccId (with contracts|without contracts|invalid|partial)")
    public void use_iccid(final String type) {
        final Arguments arg = Arguments.iccId;
        IccId properties = apibssProperties.getProductInventory().getIccId();
        switch (type) {
            case "with contracts":
                sharedData.getArguments().put(arg, properties.getWithContracts());
                break;
            case "without contracts":
                sharedData.getArguments().put(arg, properties.getWithoutContracts());
                break;
            case "invalid":
                sharedData.getArguments().put(arg, properties.getInvalid());
                break;
            case "partial":
                sharedData.getArguments().put(arg, properties.getPartial());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a IccId (.*)\" step");
        }
    }

    @When("^Use a MSISDN (with contracts|without contracts|invalid) for PI")
    public void use_msisdn(final String type) {
        final Arguments arg = Arguments.MSISDN;
        Msisdn properties = apibssProperties.getProductInventory().getMsisdn();
        switch (type) {
            case "with contracts":
                sharedData.getArguments().put(arg, properties.getWithContracts());
                break;
            case "without contracts":
                sharedData.getArguments().put(arg, properties.getWithoutContracts());
                break;
            case "invalid":
                sharedData.getArguments().put(arg, properties.getInvalid());
                break;
            default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a MSISDN (.*) for PI\" step");
        }
    }


    @When("^Use a party id (.*) for product inventory")
    public void use_party_id(final String type) {
        final Arguments arg = Arguments.partyId;
        PartyId properties = apibssProperties.getProductInventory().getPartyId();
        String partyId = sharedData.getArguments().get(arg);
        if (partyId == null) {
            partyId = "";
        } else {
            partyId += ",";
        }
        switch (type) {
            case "with contracts":
            sharedData.getArguments().put(arg, partyId + properties.getWithContracts());
                break;
            case "without contracts":
            sharedData.getArguments().put(arg, partyId + properties.getWithoutContracts());
                break;
            case "invalid":
            sharedData.getArguments().put(arg, partyId + properties.getInvalid());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a party id (.*)\" step");
        }
    }

    @When("^Use a product status (.*)")
    public void use_product_status(final String type) {
        final Arguments arg = Arguments.productStatus;
        ProductStatus properties = apibssProperties.getProductInventory().getProductStatus();
        switch (type) {
            case "with contracts":
                sharedData.getArguments().put(arg, properties.getWithContracts());
                break;
            case "without contracts":
                sharedData.getArguments().put(arg, properties.getWithoutContracts());
                break;
            case "invalid":
                sharedData.getArguments().put(arg, properties.getInvalid());
                break;
            default:
                throw new IllegalArgumentException("Can't use key " + type + " on \"Use a product status (.*)\" step");
        }
    }

    @When("^Use a product id (offer|faf|service|package|core service|invalid|parameter service|transfer credit)")
    public void use_id(String type) {
        final Arguments arg = Arguments.productId;
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        Map<Arguments, String> arguments = sharedData.getArguments();
        switch (type) {
        case "offer":
            arguments.put(arg, properties.getProductWithContracts().get(0).getId());
            break;
        case "faf":
            arguments.put(arg, properties.getProductFaf().get(0).getId());
            break;
        case "service":
            arguments.put(arg, findFirstService(properties.getProductWithContracts()).getId());
            break;
        case "package":
            arguments.put(arg, findFirstServicePackage(properties.getProductWithContracts()).getId());
            break;
        case "core service":
            arguments.put(arg, findFirstCoreService(properties.getProductWithContracts()).getId());
            break;
        case "invalid":
            arguments.put(arg, apibssProperties.getProductInventory().getIccId().getInvalid());
            break;
        case "parameter service":
            arguments.put(arg, apibssProperties.getProductInventory().getServiceWithParameter().getId());
            break;
        case "transfer credit":
            arguments.put(arg, apibssProperties.getProductInventory().getProductTransferCredit().get(0).getId());
            break;
        default:
            throw new IllegalArgumentException("Can't use key " + type + " on \"Use a product id (.*)\" step");
        }
    }

    private Product findFirstCoreService(List<Product> productWithContracts) {
        for (Product product : productWithContracts) {
            if (product.getProductSpecification() != null
                    && "serviceBSCS".equals(product.getProductSpecification().getId())) {
                // it can be a service or a core service
                if (product.getProductOffering() != null
                        && "includedService".equals(product.getProductOffering().getCategory())) {
                    return product;
                }
            }
        }
        return null;
    }

    private Product findFirstService(List<Product> productWithContracts) {
        for (Product product : productWithContracts) {
            if (product.getProductSpecification() != null
                    && "serviceBSCS".equals(product.getProductSpecification().getId())) {
                // it can be a service or a core service
                if (product.getProductOffering() != null
                        && "option".equals(product.getProductOffering().getCategory())) {
                    return product;
                }
            }
        }
        return null;
    }

    private Product findFirstServicePackage(List<Product> productWithContracts) {
        for (Product product : productWithContracts) {
            if (product.getId() != null && product.getId().contains("|C|")) {
                return product;
            }
        }
        return null;
    }

    @When("^Filter category with (.*) value")
    public void filter_with_offer(final String type) {
        final Arguments arg = Arguments.productOfferingCategory;
        sharedData.getArguments().put(arg, type);
    }

    @When("^Filter product specification id with (.*) value")
    public void filter_productspecId(final String type) {
        final Arguments arg = Arguments.productSpecificationId;
        sharedData.getArguments().put(arg, type);
    }

    @When("Add parameters filter")
    public void add_parameters() {
        sharedData.getArguments().put(Arguments.parameters, "true");
    }

    @When("^Refer to the products$")
    public void refer_to_the_products() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apibssProperties.getProductInventoryApiUrl() + "/product");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case iccId:
                    builder.queryParam(key.name(), value);
                    break;
                case partyId:
                    builder.queryParam("relatedParty.id", value);
                    break;
                case productStatus:
                    builder.queryParam("status", value);
                    break;
                case MSISDN:
                    builder.queryParam("publicKey", value);
                    break;
                case productOfferingCategory:
                    builder.queryParam("productOffering.category", value);
                    break;
                case productSpecificationId:
                    builder.queryParam("productSpecification.id", value);
                    break;
                default:
                    throw new IllegalArgumentException("Can't use key " + key + " on \"Refer to the main offer\" step");
                }
            }
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Product[]> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Product[].class);
            productInventorySharedData.setProducts(Arrays.asList(response.getBody()));
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @When("^Refer to the product$")
    public void refer_to_the_product() throws Throwable {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(apibssProperties.getProductInventoryApiUrl() + "/product");
            for (Arguments key : sharedData.getArguments().keySet()) {
                String value = sharedData.getArguments().get(key);
                switch (key) {
                case productId:
                    builder.pathSegment(value);
                    break;
                case parameters:
                    builder.queryParam("withParameters", value);
                    break;
                default:
                    throw new IllegalArgumentException("Can't use key " + key + " on \"Refer to the main offer\" step");
                }
            }
            System.out.println(builder.build().toUri());
            HttpHeaders headers = new HttpHeaders();
            Tools.addHeaders(apibssProperties.getHeaders(), headers);
            ResponseEntity<Product> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET,
                    new HttpEntity<>(headers), Product.class);
            productInventorySharedData.setProduct(response.getBody());
        } catch (HttpStatusCodeException e) {
            sharedData.setException(e);
        }
    }

    @Then("^Get error no input$")
    public void error_no_input() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4005, "Bad parameter combination",
				"\\QOne or many parameters required from combination :\\E");
    }

    @Then("^Get too many parameters error$")
    public void error_too_many_parameters() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4005, "Bad parameter combination", ".*");
    }

    @Then("^Get bad MSISDN error$")
    public void error_bad_msisdn() throws Throwable {
        error_bad_format();
    }

    @Then("^Get bad party id error$")
    public void error_bad_party_id() throws Throwable {
        error_bad_format();
    }

    @Then("^Get bad IccId error$")
    public void error_bad_iccid() throws Throwable {
        error_bad_format();
    }

    @Then("^Get bad category error$")
    public void error_bad_category() throws Throwable {
        error_bad_format();
    }

    @Then("^Get product with contract$")
    public void get_product_with_contract() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        List<Product> result = properties.getProductWithContracts();
        assertThat(productInventorySharedData.getProducts()).containsOnly(result.toArray(new Product[result.size()]));
    }

    @Then("^Get product offer$")
    public void get_product_offer() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        // main offer is the first product
        Product result = properties.getProductWithContracts().get(0);
        assertThat(productInventorySharedData.getProducts()).containsOnly(result);
    }

    @Then("^Get (offer|faf|service|package|core service|parameter service|transfer credit) product$")
    public void get_product(String type) throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        checkNoException();
        assertThat(productInventorySharedData.getProduct()).isNotNull();
        final Product result;
        switch (type) {
        case "offer":
            // main offer is the first product
            result = properties.getProductWithContracts().get(0);
            break;
        case "faf":
            result = properties.getProductFaf().get(0);
            break;
        case "service":
            result = findFirstService(properties.getProductWithContracts());
            break;
        case "package":
            result = findFirstServicePackage(properties.getProductWithContracts());
            break;
        case "core service":
            result = findFirstCoreService(properties.getProductWithContracts());
            break;
        case "parameter service":
            result = properties.getServiceWithParameter();
            break;
        case "transfer credit":
            result = properties.getProductTransferCredit().get(0);
            break;
        default:
            throw new IllegalArgumentException("Can't use " + type + "for get (.*) product");
        }
        assertThat(productInventorySharedData.getProduct()).isEqualTo(result);
    }

    @Then("^Get empty result$")
    public void get_empty_result() throws Throwable {
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        assertThat(productInventorySharedData.getProducts()).isEmpty();
    }

    /**
     * Compare only ID for test without equals
     *
     * @throws Throwable
     */
    @Deprecated
    @Then("^Get MSISDN with contract by id$")
    public void get_msisdn_with_contract_by_id() throws Throwable {
        ProductInventoryProperties properties = apibssProperties.getProductInventory();
        List<Product> result = properties.getProductWithContracts();
        checkNoException();
        assertThat(productInventorySharedData.getProducts()).isNotNull();
        assertThat(productInventorySharedData.getProducts()).hasSameSizeAs(result);
        String[] resultId = new String[result.size()];
        List<String> productsId = new ArrayList<>();
        for (int pony = 0; pony < result.size(); pony++) {
            resultId[pony] = result.get(pony).getId();
            productsId.add(productInventorySharedData.getProducts().get(pony).getId());
        }
        assertThat(productsId).containsOnly(resultId);
    }

	/**
     * Generic exception mapper for bad enumerations
     * 
     * @throws Throwable
     */
	private void error_bad_format() throws Throwable {
        checkError(HttpStatus.BAD_REQUEST, 4002, "Bad parameter format", "\\QBad format for parameter\\E");
	}
}