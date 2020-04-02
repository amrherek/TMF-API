@OTN
@OBW
Feature: Product inventory find product with FaF

  Scenario: Get FaF
    When Use a MSISDN with faf
     And Filter product with faf
     And Refer to the products
    Then Get product with faf
    
  Scenario: Get FaF - no faf configured 
    When Use a MSISDN without faf
     And Filter product with faf
     And Refer to the products
    Then Get empty result
    
  Scenario: Get product by id - faf
     When Use a product id faf
     And Refer to the product
    Then Get faf product
