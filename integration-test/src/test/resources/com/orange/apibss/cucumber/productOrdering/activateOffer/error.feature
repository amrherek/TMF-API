Feature: Product ordering activate errors

  @Default
  Scenario: Activate - UM46 - already activated offer
    Given Use a valid product ordering body
    When  Add activate offer with valid product id
    And  Send product ordering request
    Then  Contract state change error

  @OTN @OBW
  Scenario: Activate - UM46 - two individual error
    Given Use a valid product ordering body
    When  Add activate offer with valid product id
     And  Add a valid related individual
     And  Add a valid related individual
     And  Send product ordering request
    Then  Too many parameter error

  @OCM
  Scenario: Activate - UM46 - two individual error
    Given Use a valid product ordering body
    When  Add activate offer with valid product id
    And  Add a valid related individual
    And  Add a valid related individual
    And  Add a valid related individual
    And  Send product ordering request
    Then  Too many parameter error
    
  @OTN @OCM
  Scenario: Activate with invalid offer - UM46
    Given Use a valid product ordering body
    When  Add activate offer with invalid product id
    And  Send product ordering request
    Then   Contract state change error
    
  @OBW
  Scenario: Activate with invalid offer - UM46
    Given Use a valid product ordering body
    When  Add activate offer with invalid product id
    And  Send product ordering request
    Then  Contract state change error
    
  @Default
  Scenario: Activate with terminated offer - UM46
    Given Use a valid product ordering body
    When  Add activate offer with terminated product id
    And  Send product ordering request
    Then  Contract state change error