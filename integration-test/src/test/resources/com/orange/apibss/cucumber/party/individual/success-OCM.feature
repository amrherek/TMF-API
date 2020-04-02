@OCM
Feature: Party individual success for OCM

  Scenario: Get minor individual
    When Use a customer id minor
     And Refer to individual
    Then Get minor individual

  Scenario: Get minor tutor
    When Use a customer id minor
     And Use a tutor id valid
     And Refer to the tutor
    Then Get tutor individual
    
  Scenario: Get valid individual with identification
    When Use a customer identification id valid
     And Use a customer identification type valid
     And Refer to individuals
    Then Contain valid individual

	Scenario: Get with id individual
    When Use a customer id with id
     And Refer to individual
    Then Get with id individual

  Scenario: Update tutor
    Given Have an existing tutor
    When Change the given name
     And Change the family name
     And Change the birth date
     And Update the tutor
    Then The API return updated tutor
