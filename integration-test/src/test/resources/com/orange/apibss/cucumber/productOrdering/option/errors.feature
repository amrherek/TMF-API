@OTN
Feature: Product ordering manage option errors

	Scenario: UM7 add option without productOffering.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And Add option product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error
    
  Scenario: UM7 - delete option without product.productOffering
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete action
    And With um7 product with valid product relationship
		And Add option product.productSpecification.id    
    And Send product ordering request
    Then Missing parameter error
