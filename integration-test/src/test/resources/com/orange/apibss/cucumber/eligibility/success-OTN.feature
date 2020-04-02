@OTN
Feature: Eligibility success for OTN

  Scenario: Get transferDateFee eligible option
    When Use a MSISDN valid for EL
    And Use transferData product specification id for EL
    And Request for eligible options
    Then Response contain transfer data eligible option
    
  Scenario: Get emergencyVoice eligible option
    When Use a MSISDN valid for EL
    And Use emergencyVoice product specification id for EL
    And Request for eligible options
    Then Response contain emergency voice eligible option    


