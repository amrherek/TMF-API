@OCM
Feature: Party individual errors for OCM

  Scenario: Get individual with invalid id
    When Use a customer id invalid
     And Refer to individual
    Then Bad parameter format error
    
  Scenario: Get tutor with unknown Id
    When Use a customer id valid
     And Use a tutor id unknown
    And Refer to the tutor
    Then Not found error

  Scenario: Get tutor with invalid id
    When Use a customer id valid
     And Use a tutor id invalid
     And Refer to the tutor
    Then Bad parameter format error   