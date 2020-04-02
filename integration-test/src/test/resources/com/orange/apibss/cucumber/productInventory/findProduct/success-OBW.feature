@OBW
Feature: Product inventory find product for OBW

  Scenario: Get product offer by MSISDN with local msisdn - old format with category
    When Use a MSISDN with contracts without prefix for PI
     And Filter category with offer value
     And Refer to the products
    Then Get product offer
    
  Scenario: Get product offer by MSISDN with local msisdn - with productSpecId
    When Use a MSISDN with contracts without prefix for PI
     And Filter product specification id with offer value
     And Refer to the products
    Then Get product offer    
           
  Scenario: Get product by id - core service
     When Use a product id core service
     And Refer to the product
    Then Get core service product
    
  Scenario: Get data bundle products by MSISDN
    When Use a MSISDN with data bundle
     And Filter product specification id with bundleData value
     And Refer to the products
    Then Get product with data bundle
    
  Scenario: Get transferData product by MSISDN - no service
    When Use a MSISDN without data bundle
     And Filter product specification id with bundleData value
     And Refer to the products
    Then Get empty result        