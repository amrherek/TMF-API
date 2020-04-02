@OTN
@OBW
Feature: Product ordering friend and family

  Scenario: Add Friend and family with old format - UM 15
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add characteristic action
    And With friend and family product with valid contract, valid product characteristic, valid product relationship and no product specification
		And Add faf product.productOffering.category
    And Send product ordering request
    Then Api return the product order in progress

  Scenario: Add Friend and family with productSpecification.id - UM 15
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add characteristic action
    And With friend and family product with valid contract, valid product characteristic, valid product relationship and valid product specification
    And Send product ordering request
    Then Api return the product order in progress    