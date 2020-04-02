@OCM
Feature: Product ordering simswap errors for OCM

  
  Scenario: Sim swap - bad format contract id
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and bad format contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and bad format contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Bad parameter format error
    