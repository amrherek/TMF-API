Feature: Product ordering manage tariff plan errors

	@OTN
  Scenario:  UM13 - Update tariff plan bad productSpecId
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with migrate action
    And With um13 valid product offering
		And Add bad product.productSpecification.id
		And Send product ordering request
    Then Bad parameter value error

	@OTN
  Scenario: UM13 - Update tariff plan no public key
    Given Use a valid product ordering body
    And Use an order item with migrate action
    And With um13 valid product offering
		And Add offer product.productSpecification.id		
		And Send product ordering request
    Then Missing parameter error

	@OBW
	@OCM
  Scenario: UM13 - Update tariff plan no product id
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And With um13 valid product offering
    And With um13 product with valid product offering
		And Add offer product.productSpecification.id    
    And Send product ordering request
    Then Missing parameter error
    
 	@OBW
	@OCM
  Scenario: UM13 - Update tariff plan invalid product id
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And With um13 valid product offering
    And With um13 product with invalid contract and valid product offering
		And Add offer product.productSpecification.id    
    And Send product ordering request
    Then Migration error
    
  @OBW
  @OCM
  Scenario: UM13 - Update tariff plan with current rate plan
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And Wait for 20 seconds
    And With um13 initial product offering
    And With um13 product with valid contract and valid product offering
		And Add offer product.productSpecification.id    
    And Send product ordering request
    Then Migration error
    
 	@OBW
	@OCM
  Scenario: UM13 - Update tariff plan bad productSpecId
    Given Use a valid product ordering body
    When Use an order item with migrate action
    And With um13 valid product offering
    And With um13 product with invalid contract and valid product offering
    And Add bad product.productSpecification.id
    And Send product ordering request
    Then Bad parameter value error