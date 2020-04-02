@OBW
Feature: Product ordering, konnecta booster errors

  Scenario: konnecta booster with invalid offer - UM7 
    Given Use a valid product ordering body
    When  Add add service with invalid product id and valid service id and valid service package id	
		And Add boostData product.productSpecification.id
    And Send product ordering request
    Then Option add error
    
  Scenario: konnecta booster with invalid service - UM7 
    Given Use a valid product ordering body
    When  Add add service with valid product id and invalid service id and valid service package id	
		And Add boostData product.productSpecification.id
    And Send product ordering request
    Then Option add error
    
  Scenario: konnecta booster with invalid service package - UM7 
    Given Use a valid product ordering body
    When  Add add service with valid product id and valid service id and invalid service package id	
		And Add boostData product.productSpecification.id
    And Send product ordering request
    Then Option add error
    
  Scenario: konnecta booster with bad service - UM7 
    Given Use a valid product ordering body
    When  Add add service with valid product id and bad service id and valid service package id	
		And Add boostData product.productSpecification.id
    And Send product ordering request
    Then Option add error    