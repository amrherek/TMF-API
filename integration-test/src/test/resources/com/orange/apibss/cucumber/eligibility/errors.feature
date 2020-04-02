Feature: Eligibility errors

#	@OBW
	@OCM
  Scenario: Get all eligible options
    When Use a MSISDN invalid for EL
    And Request for eligible options
    Then Response contain no eligible option
    
	@OTN
  Scenario: Get all eligible options
    When Use a MSISDN invalid for EL
    And Request for eligible options
    Then Internal WS error    

