@OCM
Feature: Product inventory find product errors for OCM
    
  Scenario: Get product with invalid partyId
    When Use a party id invalid for product inventory
     And Refer to the products
    Then Get bad party id error
    
        
  Scenario: Get product with PARTYID without contracts
    When Use a party id without contracts for product inventory
     And Refer to the products
    Then Get empty result
  