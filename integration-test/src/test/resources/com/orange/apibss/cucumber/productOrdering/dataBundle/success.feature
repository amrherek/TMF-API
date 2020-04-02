@OBW
Feature: Product ordering data bundle

	Scenario: Add data bundle
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With data bundle valid product offering
    And Add bundleData product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress
