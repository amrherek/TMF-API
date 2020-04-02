@Default
Feature: Billing account errors

  Scenario: Get billing account by id - invalid id
    When Use a billing account id invalid
     And Refer to the billing account
    Then Not found error
    
  Scenario: Get billing account by msisdn - invalid msisdn
    When Use a MSISDN invalid for billing account
     And Refer to billing accounts
    Then Get empty BA result
    
  Scenario: Get billing account by party id - invalid party
    When Use a party id invalid for billing account
     And Refer to billing accounts
    Then Get empty BA result

  Scenario: Get billing account by msisdn - invalid msisdn format
    When Use a MSISDN bad format for billing account
     And Refer to billing accounts
    Then Get empty BA result
