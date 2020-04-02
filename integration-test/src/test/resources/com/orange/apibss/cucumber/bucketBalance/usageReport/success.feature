@OTN
@OBW
Feature: Bucket balance Usage report

  Scenario: Get usage report for prepaid customer
    When Use a MSISDN prepaid for bucket balance
     And Use a prepaid rating type
     And Refer to usage report
    Then Get prepaid usage reports

  Scenario: Get usage report for postpaid customer
    When Use a MSISDN postpaid for bucket balance
     And Use a postpaid rating type
     And Refer to usage report
    Then Get postpaid usage reports

