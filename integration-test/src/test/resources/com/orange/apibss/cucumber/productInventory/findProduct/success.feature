@Default
Feature: Product inventory find product
  Scenario: Get product by MSISDN
    When Use a MSISDN with contracts for PI
     And Refer to the products
    Then Get product with contract

  Scenario: Get product offer by MSISDN - with category
    When Use a MSISDN with contracts for PI
     And Filter category with offer value
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by MSISDN - with productSpecId
    When Use a MSISDN with contracts for PI
     And Filter product specification id with offer value
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by MSISDN - with category and productSpecId
    When Use a MSISDN with contracts for PI
     And Filter category with offer value    
     And Filter product specification id with offer value
     And Refer to the products
    Then Get product offer            

  Scenario: Get product by IccId
    When Use a IccId with contracts
     And Refer to the products
    Then Get product with contract

  Scenario: Get product offer by IccId - with category
    When Use a IccId with contracts
     And Filter category with offer value
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by IccId - with productSpecId
    When Use a IccId with contracts
     And Filter product specification id with offer value
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by IccId - with category and productSpecId
    When Use a IccId with contracts
     And Filter category with offer value
     And Filter product specification id with offer value     
     And Refer to the products
    Then Get product offer        
    
  Scenario: Get product offer with partial IccId
    When Use a IccId partial
     And Filter product specification id with offer value 
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product by id - offer
     When Use a product id offer
     And Refer to the product
    Then Get offer product
    
  Scenario: Get product by id - service
     When Use a product id service
     And Refer to the product
    Then Get service product

  Scenario: Get product by id - service package
     When Use a product id package
     And Refer to the product
    Then Get package product
    
  Scenario: Get product by id - service with parameter
     When Use a product id parameter service
     And Add parameters filter
     And Refer to the product
    Then Get parameter service product 

# must be added for each valid country, because OTN has no core service    
#  Scenario: Get product by id - core service
#     When Use a product id core service
#     And Refer to the product
#    Then Get core service product