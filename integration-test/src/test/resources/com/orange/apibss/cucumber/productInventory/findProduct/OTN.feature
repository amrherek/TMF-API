@OTN
Feature: Product inventory find product for OTN

  Scenario: Get transferData product by MSISDN
    When Use a MSISDN with transfer data
     And Filter product specification id with transferData value
     And Refer to the products
    Then Get product with transfer data
    
  Scenario: Get transferData product by MSISDN - no service
    When Use a MSISDN without transfer data
     And Filter product specification id with transferData value
     And Refer to the products
    Then Get empty result
    
  Scenario: Get transferCredit product by MSISDN
    When Use a MSISDN with transfer credit
     And Filter product specification id with transferCreditPostpaid value
     And Refer to the products
    Then Get product with transfer credit   
    
  Scenario: Get transferCredit product by MSISDN - no service
    When Use a MSISDN without transfer credit
     And Filter product specification id with transferCreditPostpaid value
     And Refer to the products
    Then Get empty result   
    
  Scenario: Get product by id - transferCredit
     When Use a product id transfer credit
     And Refer to the product
    Then Get transfer credit product          