@Default
Feature: Party individual success

  Scenario: Get valid individual
    When Use a customer id valid
     And Refer to individual
    Then Get valid individual

  Scenario: Update individual
    Given Have an existing individual
    When Change the family name
     And Change the given name
     And Update the customer
    Then The API return updated user

  Scenario: Update individual email
    Given Have an existing individual
    When Change the family name
     And Change the email with valid
     And Update the customer
    Then The API return updated user
    
  Scenario: Get valid individual with name
    When Use a customer family name valid
     And Refer to individuals
    Then Contain valid individual

#  Scenario: Create individual
#    Given Have a new individual
#    When Change the family name
#    And Change the given name
#    And Create the customer
#    Then The API return new user