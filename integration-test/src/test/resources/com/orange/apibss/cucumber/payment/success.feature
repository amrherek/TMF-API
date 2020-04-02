@OBW
Feature: Payment success

  Scenario: Get Payment by id
    When Use a payment id valid
     And Refer to the payment
    Then Get valid payment
   
  @OTN  
	Scenario: Get payments by party id
    When Use a party id valid for payment
     And Use a distant payment start date
     And Refer to payments
    Then Get valid payments