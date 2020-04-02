@OTN
Feature: Product ordering modify all friend and family

  Scenario: Modify all Friend and family numbers - old format
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with modify characteristic action
    And With friend and family product with valid contract, valid product characteristic, valid product relationship and no product specification
		And Add faf product.productOffering.category
    And Send product ordering request
    Then Api return the product order in progress
    
  Scenario: Modify all Friend and family numbers with productSpecification.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with modify characteristic action
    And With friend and family product with valid contract, valid product characteristic, valid product relationship and valid product specification
    And Send product ordering request
    Then Api return the product order in progress    
