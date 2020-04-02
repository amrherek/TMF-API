@Default
Feature: Product ordering service with parameters errors
    
  Scenario: add service with parameter - service without parameter
    Given Use a valid product ordering body
    When  Add add parameter service with valid product id and withoutParameter service id and withoutParameter service package id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Send product ordering request
    Then  Service add error
    
  Scenario: add service with parameter - bad service parameter id
    Given Use a valid product ordering body
    When  Add add parameter service with valid product id and valid service id and valid service package id	
    And  Add productCharacteristic for invalid parameter id and valid parameter value 
    And  Send product ordering request
    Then  Service add error
    
  Scenario: add service with parameter - parameters missing
    Given Use a valid product ordering body
    When  Add add parameter service with valid product id and valid service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  Scenario: add service with parameter - bad productSpecification.id
    Given Use a valid product ordering body
    When  Add add parameter service with valid product id and valid service id and valid service package id	
    And  Add productCharacteristic for invalid parameter id and valid parameter value 
   	And  Add bad product.productSpecification.id
    And  Send product ordering request
    Then  Bad parameter value error 
    
  Scenario: modify parameter service - bad service parameter id
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and valid service id	
    And  Add productCharacteristic for invalid parameter id and valid parameter value 
    And  Send product ordering request
    Then  Parameter modify error
       
  Scenario: modify parameter service - bad service parameter value
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and valid service id	
    And  Add productCharacteristic for valid parameter id and invalid parameter value 
    And  Send product ordering request
    Then  Parameter modify error
   
  Scenario: modify parameter service with invalid offer
    Given Use a valid product ordering body
    When  Add modify parameter service with invalid product id and valid service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Send product ordering request
    Then Parameter modify error
    
  Scenario: modify parameter service with terminated offer
    Given Use a valid product ordering body
    When  Add modify parameter service with terminated product id and valid service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Send product ordering request
    Then Parameter modify error
    
  Scenario: modify parameter service with invalid service
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and invalid service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Send product ordering request
    Then Parameter modify error
    
  Scenario: modify parameter service with bad service
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and bad service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value  
    And  Send product ordering request
    Then Parameter modify error

  Scenario: modify parameter service with bad productSpecification.id
    Given Use a valid product ordering body
    When  Add modify parameter service with valid product id and valid service id	
    And  Add productCharacteristic for valid parameter id and valid parameter value 
    And  Add bad product.productSpecification.id
    And  Send product ordering request
    Then Bad parameter value error    
    
