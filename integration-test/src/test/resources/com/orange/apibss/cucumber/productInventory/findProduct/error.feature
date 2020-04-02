@Default
Feature: Product inventory find product errors

    Scenario: Get product with invalid MSISDN
    When Use a MSISDN invalid for PI
     And Refer to the products
    Then Get empty result
  
  Scenario: Get product with no input
    When Refer to the products
    Then Get error no input
    
  Scenario: Get product with MSISDN and iccId
    When Use a MSISDN with contracts for PI
     And Use a IccId with contracts
     And Refer to the products
    Then Get too many parameters error
    
  Scenario: Get product with invalid category
    When Use a MSISDN with contracts for PI
     And Filter category with pony value
     And Refer to the products
    Then Get empty result
    
  Scenario: Get product with invalid productSpecId
    When Use a MSISDN with contracts for PI
     And Filter product specification id with bad value
     And Refer to the products
    Then Get empty result    
    
  Scenario: Get product with invalid product status
    When Use a MSISDN with contracts for PI
     And Use a product status invalid
     And Refer to the products
    Then Bad parameter format error

  Scenario: Get product with MSISDN without contracts
    When Use a MSISDN without contracts for PI
     And Refer to the products
    Then Get empty result
    
  Scenario: Get product with ICCID without contracts
    When Use a IccId without contracts
     And Refer to the products
    Then Get empty result
    
 Scenario: Get product with invalid iccId
    When Use a IccId invalid 
    And Refer to the products
    Then Get empty result

  Scenario: Get product by id - invalid
     When Use a product id invalid
     And Refer to the product
    Then Not found error