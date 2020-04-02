Feature: Product ordering emergency credit errors

	@OTN
	@OBW
	Scenario: Add emergency credit without productOffering.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And Add emergencyData product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error
    
  @OTN
  Scenario: Add emergency credit without msisdn
    Given Use a valid product ordering body
    And Use an order item with add action
    And With emergency credit valid product offering
    And Add emergencyCredit product.productSpecification.id    
    And Send product ordering request
    Then Missing parameter error
    
	@OTN
	Scenario: Add emergency credit invalid msisdn 
    Given Use a valid product ordering body
    When Use a related public key with invalid msisdn
    And Use an order item with add action
    And With emergency credit valid product offering
    And Add emergencyCredit product.productSpecification.id    
    And Send product ordering request
    Then Internal WS error
    
	@OBW
	Scenario: Add emergency credit without volume
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With emergencyCreditFee productOffering.id
    And Add emergencyCredit product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error      
