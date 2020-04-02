@OTN
Feature: Product ordering modify all friend and family errors

  Scenario: Add Friend and family no misdn 
    Given Use a valid product ordering body
    And Use an order item with modify characteristic action
    And With friend and family product with valid contract, valid product characteristic, valid product relationship and valid product specification
    And Send product ordering request
    Then Missing parameter error

  Scenario: Add Friend and family invalid product characteristic 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with modify characteristic action
    And With friend and family product with valid contract, invalid product characteristic, valid product relationship and valid product specification
    And Send product ordering request
    Then Bad parameter value error
