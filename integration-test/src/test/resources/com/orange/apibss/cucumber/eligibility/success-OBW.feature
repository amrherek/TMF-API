@OBW
Feature: Eligibility success for OBW

  Scenario: Get all eligible option with local msisdn
    When Use a MSISDN valid without prefix for EL
    And Request for eligible options
    Then Response contain all eligible option

  Scenario: Get bundleData eligible option
    When Use a MSISDN valid for EL
    And Use bundleData product specification id for EL
    And Request for eligible options
    Then Response contain data bundle eligible option   
    
  Scenario: Get emergencyCredit eligible option
    When Use a MSISDN valid for EL
    And Use emergencyCredit product specification id for EL
    And Request for eligible options
    Then Response contain emergency credit eligible option        

