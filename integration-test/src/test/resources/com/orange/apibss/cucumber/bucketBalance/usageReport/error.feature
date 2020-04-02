
Feature: Bucket balance Usage report - errors

  @OTN
  Scenario: Get usage report for prepaid customer - invalid msisdn
    When Use a MSISDN invalid for bucket balance
     And Use a prepaid rating type
     And Refer to usage report
    Then Internal WS error
    
  @OBW
  Scenario: Get usage report for prepaid customer - invalid msisdn
    When Use a MSISDN invalid for bucket balance
     And Use a prepaid rating type
     And Refer to usage report
    Then Get empty usage reports result

	@OTN
	@OBW
  Scenario: Get usage report for postpaid customer - invalid msisdn
    When Use a MSISDN invalid for bucket balance
     And Use a postpaid rating type
     And Refer to usage report
    Then Get empty usage reports result

