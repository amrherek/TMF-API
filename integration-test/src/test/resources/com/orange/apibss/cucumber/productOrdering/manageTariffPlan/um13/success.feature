Feature: Product ordering manage tariff plan

	@OTN
  Scenario: Update tariff plan with old format - UM 13
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Wait for 20 seconds
    And Use an order item with migrate action
    And With um13 valid product offering
		And Add offer product.productOffering.category
		And Send product ordering request
    Then Api return the product order in progress

	@OTN
  Scenario: Update tariff plan with productSpecId - UM 13
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Wait for 20 seconds
    And Use an order item with migrate action
    And With um13 valid product offering
		And Add offer product.productSpecification.id
		And Send product ordering request
    Then Api return the product order in progress
    
	@OBW
  Scenario: Update tariff plan with old format - UM 13
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And Wait for 20 seconds
    And With um13 valid product offering
    And With um13 product with valid contract and valid product offering
		And Add offer product.productOffering.category
    And Use an order item with migrate action
    And With um13 initial product offering
    And With um13 product with valid contract and valid product offering
		And Add offer product.productOffering.category    
		And Send product ordering request
    Then Api return the product order in progress

	@OBW
  Scenario: Update tariff plan with productSpecId - UM 13
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And Wait for 20 seconds    
    And With um13 valid product offering
    And With um13 product with valid contract and valid product offering
		And Add offer product.productSpecification.id
    And Use an order item with migrate action
    And With um13 initial product offering
    And With um13 product with valid contract and valid product offering
		And Add offer product.productSpecification.id    
		And Send product ordering request
    Then Api return the product order in progress    