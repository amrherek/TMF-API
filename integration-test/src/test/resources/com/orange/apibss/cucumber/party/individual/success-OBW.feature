@OBW
Feature: Party individual success for OBW

  Scenario: Update individual registration status
    Given Have an existing individual
    When Change the registration status
     And Update the customer
    Then The API return updated user
