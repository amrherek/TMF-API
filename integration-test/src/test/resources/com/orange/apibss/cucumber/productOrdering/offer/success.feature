Feature: Product ordering update offer status

  @Default
  Scenario: Suspend and Reactivate an active offer with old format
    Given Use a valid product ordering body
    When  Add suspend offer with old format category    
    And  Add reactivate offer with old format category
    And  Send product ordering request
    Then  Api return the product order in progress

  @Default
  Scenario: Suspend and Reactivate an active offer
    Given Use a valid product ordering body
    When  Add suspend offer with updatable product id
    And  Wait for 20 seconds    
    And  Add reactivate offer with updatable product id
    And  Send product ordering request
    Then  Api return the product order in progress
