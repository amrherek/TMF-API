@Default
Feature: Eligibility success

  Scenario: Get all eligible option
    When Use a MSISDN valid for EL
    And Request for eligible options
    Then Response contain all eligible option

  Scenario: Get filtered eligible option
    When Use a MSISDN valid for EL
    And Use a product offering id for EL
    And Request for eligible options
    Then Response contain filtered eligible option

  Scenario: Get no eligible option
    When Use a MSISDN unknown for EL
    And Use a product offering id for EL
    And Request for eligible options
    Then Response contain no eligible option
    
  Scenario: Get eligible contract
    When Use a MSISDN valid for EL
    And Request for eligible contract
    Then Response contain eligible contract    
