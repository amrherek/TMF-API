@OCM
Feature: Product inventory find product for OCM

  Scenario: Get product with Tutor
    When Use a MSISDN with tutor
     And Refer to the products
    Then Get product with tutor
    
  Scenario: Get product by PartyId
    When Use a party id with contracts for product inventory
    And Refer to the products
    Then Get product with contract
    
 	Scenario: Get product with a good and a bad partyId without contracts
    When Use a party id with contracts for product inventory
     And Use a party id without contracts for product inventory
     And Refer to the products
    Then Get product with contract

  Scenario: Get product offer by PartyId - with category
    When Use a party id with contracts for product inventory
    And Filter category with offer value
    And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by PartyId - with productSpecId
    When Use a party id with contracts for product inventory
    And Filter product specification id with offer value
    And Refer to the products
    Then Get product offer    
          
  Scenario: Get product by id - core service
     When Use a product id core service
     And Refer to the product
    Then Get core service product