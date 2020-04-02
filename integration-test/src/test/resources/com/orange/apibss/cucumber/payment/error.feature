@OBW
Feature: Payment errors

  Scenario: Get payment by id - invalid id
    When Use a payment id invalid
     And Refer to the payment
    Then Not found error
    
  Scenario: Get payment by id - bad format id
    When Use a payment id bad format
     And Refer to the payment
    Then Bad parameter format error

	@OTN
  Scenario: Get payments by party id - invalid party
    When Use a party id invalid for payment
     And Use a distant payment start date    
     And Refer to payments
    Then Get empty payment result
   
  @OTN  
  Scenario: Get payments by party id - missing start date
    When Use a party id invalid for payment
     And Refer to payments
    Then Missing parameter error    
