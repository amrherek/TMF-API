@OTN
@OBW
Feature: Billing account success

  @OCM
  Scenario: Get billing account by id
    When Use a billing account id valid
     And Refer to the billing account
    Then Get valid billing account
    
  @OCM
  Scenario: Get billing account by msisdn
    When Use a MSISDN valid for billing account
     And Refer to billing accounts
    Then Get valid billing accounts
    
  @OCM
  Scenario: Get billing account by party id
    When Use a party id valid for billing account
     And Refer to billing accounts
    Then Get valid billing accounts
    
  Scenario: Get billing account by party id - several BAs
    When Use a party id with several BAs for billing account
     And Refer to billing accounts
    Then Get several billing accounts