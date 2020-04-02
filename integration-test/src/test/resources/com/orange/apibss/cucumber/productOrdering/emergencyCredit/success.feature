Feature: Product ordering emergency credit

	@OTN
	Scenario: Add emergency credit 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With emergency credit valid product offering
    And Add emergencyCredit product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress
    
	@OBW
	Scenario: Add emergency credit 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With emergencyCreditFee productOffering.id
    And Add emergencyCredit product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress    
   
  @OTN  
	Scenario: Add emergency data 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With emergency credit valid product offering
    And Add emergencyData product.productSpecification.id    
    And Send product ordering request
    Then Api return the product order in progress
  
  @OTN  
	Scenario: Add emergency voice 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With emergency credit valid product offering
    And Add emergencyVoice product.productSpecification.id    
    And Send product ordering request
    Then Api return the product order in progress        
