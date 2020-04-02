Feature: Product ordering update offer status errors

  @Default
  Scenario: Reactivate an active offer
    Given Use a valid product ordering body
    When  Add reactivate offer with valid product id
    And  Send product ordering request
    Then  Contract state change error
    
  @Default
  Scenario: Suspend a terminated offer
    Given Use a valid product ordering body
    When  Add suspend offer with terminated product id
    And  Add suspend status reason	
    And  Send product ordering request
    Then  Contract state change error
    
