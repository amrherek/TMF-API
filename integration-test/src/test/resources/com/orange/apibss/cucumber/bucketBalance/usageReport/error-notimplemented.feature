@OCM
Feature: Bucket balance Usage report - errors not implemented

  Scenario: Get usage report for prepaid customer
    When Use a MSISDN prepaid for bucket balance
     And Use a prepaid rating type
     And Refer to usage report
    Then Not implemented error

  Scenario: Get usage report for postpaid customer
    When Use a MSISDN postpaid for bucket balance
     And Use a postpaid rating type
     And Refer to usage report
    Then Not implemented error

