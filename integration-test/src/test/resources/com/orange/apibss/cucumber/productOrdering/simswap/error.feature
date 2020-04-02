@Default
Feature: Product ordering simswap errors

  Scenario: Manage SIM Swap missing hasPrerequisite item
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and valid contract id
     And With is prerequisite of next order item

     And Send product ordering request
    Then Missing parameter error


  Scenario: Manage SIM Swap missing isPrerequisite item
    Given Use a valid product ordering body

    When Use an order item with add characteristic action
    And With simcard product after simswap with valid icc id and valid contract id
    And With has prerequisite of previous order item

    And Send product ordering request
    Then Missing parameter error
    
  Scenario: Sim swap - invalid new iccId
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and valid contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with invalid icc id and valid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Sim swap error
    
  Scenario: Sim swap - invalid old iccId
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with invalid icc id and valid contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and valid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Sim swap error
    
  Scenario: Sim swap - invalid contract id
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and invalid contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and invalid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Sim swap error
    
  Scenario: Sim swap - terminated contract id
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with terminated icc id and terminated contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and terminated contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Sim swap error
    
  Scenario: Sim swap - contract id different in orderItems
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and valid contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and invalid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Bad parameter value error
    
  Scenario: Sim swap - same iccId
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and valid contract id
     And With is prerequisite of next order item

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and valid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Sim swap error
    
Scenario: Sim swap - bad productSpecification.id
    Given Use a valid product ordering body
    When Use an order item with delete characteristic action
     And With simcard product before simswap with valid icc id and valid contract id
     And With is prerequisite of next order item
     And Add bad product.productSpecification.id

     And Use an order item with add characteristic action
     And With simcard product after simswap with valid icc id and valid contract id
     And With has prerequisite of previous order item

     And Send product ordering request
    Then Bad parameter value error