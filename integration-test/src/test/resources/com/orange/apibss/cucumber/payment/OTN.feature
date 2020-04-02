@OTN
Feature: Payment for OTN

  Scenario: Get Payment by id
    When Use a payment id valid
     And Refer to the payment
    Then Not implemented error
    
  Scenario: Get Payment by correlatorId
    When Use a payment correlatorId valid
     And Refer to payments
    Then Get correlatorId payments   