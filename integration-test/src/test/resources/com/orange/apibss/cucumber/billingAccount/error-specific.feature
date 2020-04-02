Feature: Billing account specific errors

	@OCM
  Scenario: Get billing account by id - invalid id format
    When Use a billing account id bad format
     And Refer to the billing account
    Then Bad parameter format error
    
  @OTN @OBW
  Scenario: Get billing account by id - invalid id format
    When Use a billing account id bad format
     And Refer to the billing account
    Then Not found error
   
  @OCM  
  Scenario: Get billing account by party id - invalid party format
    When Use a party id bad format for billing account
     And Refer to billing accounts
    Then Bad parameter format error
    
	@OBW @OTN 
  Scenario: Get billing account by party id - invalid party format
    When Use a party id bad format for billing account
     And Refer to billing accounts
    Then Get empty BA result