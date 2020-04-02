@OTN
Feature: Product ordering manage option

  Scenario: add option old format with category - UM7
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With um7 valid product offering
    And Add option productOffering.category
    And Send product ordering request
    Then Api return the product order in progress

  Scenario: add option with productspecId - UM7
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With um7 valid product offering
    And Add option product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress

  Scenario: delete option old format with category - UM7
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete action
    And With um7 product with valid product offering
		And Add option product.productOffering.category
    And Send product ordering request
    Then Api return the product order in progress

	Scenario: delete option with productspecId - UM7
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete action
    And With um7 product with valid product offering
		And Add option product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress