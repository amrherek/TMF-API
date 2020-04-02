@Default
Feature: Product ordering service with parameters
    
  Scenario: modify parameter service - old format with no productOffering Category
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and valid service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Send product ordering request
    Then Api return the product order in progress
    
  Scenario: modify parameter service with productSpecification.id
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and valid service id	
    And  Wait for 20 seconds   
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Add serviceBSCS product.productSpecification.id
    And  Send product ordering request
    Then Api return the product order in progress
