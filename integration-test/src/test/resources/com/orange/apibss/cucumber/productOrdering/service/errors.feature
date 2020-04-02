Feature: Product ordering service errors

  @Default
  Scenario: activate already activated service
    Given Use a valid product ordering body
    When  Add activate service with valid product id and valid service id	
    And  Wait for 20 seconds      
    And  Send product ordering request
    Then  Service state change error
  
  @OCM
  Scenario: activate service with invalid offer
    Given Use a valid product ordering body
    When  Add activate service with invalid product id and valid service id	
    And  Send product ordering request
    Then  Service state change error
    
  @OTN @OBW
  Scenario: activate service with invalid offer
    Given Use a valid product ordering body
    When  Add activate service with invalid product id and valid service id	
    And  Send product ordering request
    Then  Service state change error
    
  @Default
  Scenario: activate service with invalid service
    Given Use a valid product ordering body
    When  Add activate service with valid product id and invalid service id	
    And  Send product ordering request
    Then  Service state change error
    
  @Default
  Scenario: activate service with bad service
    Given Use a valid product ordering body
    When  Add activate service with valid product id and bad service id	
    And  Send product ordering request
    Then  Service state change error
    
  @Default
  Scenario: activate service with terminated offer
    Given Use a valid product ordering body
    When  Add activate service with terminated product id and valid service id	
    And  Send product ordering request
    Then  Service state change error
    
  @Default
  Scenario: activate service with bad productSpecification.id
    Given Use a valid product ordering body
    When  Add activate service with valid product id and valid service id	
    And  Add bad product.productSpecification.id
    And  Send product ordering request
    Then  Bad parameter value error
    
  @Default
  Scenario: add already activated service
    Given Use a valid product ordering body
    When  Add add service with valid product id and activated service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @OCM
  Scenario: add service with invalid offer
    Given Use a valid product ordering body
    When  Add add service with invalid product id and valid service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @OTN @OBW
  Scenario: add service with invalid offer
    Given Use a valid product ordering body
    When  Add add service with invalid product id and valid service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @Default
  Scenario: add service with terminated offer
    Given Use a valid product ordering body
    When  Add add service with terminated product id and valid service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @Default
  Scenario: add service with invalid service package
    Given Use a valid product ordering body
    When  Add add service with valid product id and valid service id and invalid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @Default
  Scenario: add service with bad service package
    Given Use a valid product ordering body
    When  Add add service with valid product id and valid service id and bad service package id	
    And  Send product ordering request
    Then  Service add error
    
  @Default
  Scenario: add service with invalid service
    Given Use a valid product ordering body
    When  Add add service with valid product id and invalid service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
  @Default
  Scenario: add service with bad service
    Given Use a valid product ordering body
    When  Add add service with valid product id and bad service id and valid service package id	
    And  Send product ordering request
    Then  Service add error
    
	@Default
  Scenario: add service with bad productSpecification.id
    Given Use a valid product ordering body
    When  Add add service with valid product id and valid service id and valid service package id	
    And  Add bad product.productSpecification.id
    And  Send product ordering request
    Then  Bad parameter value error