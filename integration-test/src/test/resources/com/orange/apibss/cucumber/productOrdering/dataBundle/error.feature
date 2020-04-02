@OBW
Feature: Product ordering data bundle error

	Scenario: Add data bundle without product offering
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And Add bundleData product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error
