Feature: Product ordering service

  @Default
  Scenario: deactivate and reactivate a service with old format without productSpecId
    Given Use a valid product ordering body
    When  Add deactivate service with valid product id and valid service id	
    And 	Add activate service with valid product id and valid service id	
    And  	Send product ordering request
    Then  Api return the product order in progress
    
  @Default
  Scenario: deactivate and reactivate a service with productSpecId
    Given Use a valid product ordering body
    When  Add deactivate service with valid product id and valid service id	
    And   Wait for 20 seconds  
    And   Add serviceBSCS product.productSpecification.id
    And 	Add activate service with valid product id and valid service id	
    And   Add serviceBSCS product.productSpecification.id
    And  	Send product ordering request
    Then  Api return the product order in progress