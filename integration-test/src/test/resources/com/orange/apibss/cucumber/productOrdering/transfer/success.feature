Feature: Product ordering transfer data/credit

	@OTN
	Scenario: Add transfer data 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferDataService productOffering.id
    And Add transferData product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress
 
 	@OTN   
	Scenario: Delete transfer data
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete action
    And Add transferData product.productSpecification.id
    And With transferDataService product.productOffering.id
    And Send product ordering request
    Then Api return the product order in progress

	@OTN    
	Scenario: Perform a transfer data 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferDataFee productOffering.id
    And Add transferData product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress  
    
	@OBW
	Scenario: Perform a transfer credit 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFee productOffering.id
    And Add transferCredit product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress 
    
	@OTN
	Scenario: Perform a transfer credit for postpaid with pin code
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFeePostpaid productOffering.id
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Add 0000 value for pinCode productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress 
    
@OTN
	Scenario: Perform a transfer credit for postpaid without pin code
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFeePostpaid productOffering.id
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress     
    
	@OTN
	Scenario: Add num to white list for transfer credit for postpaid
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Add true value for automaticTransfer productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress 
    
	@OTN
	Scenario: Modify num from white list for transfer credit for postpaid
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with modify characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Add true value for automaticTransfer productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress   
    
	@OTN
	Scenario: Delete 1 num from white list for transfer credit for postpaid
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Api return the product order in progress
    
    
	@OTN
	Scenario: Delete all nums from white list for transfer credit for postpaid
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Send product ordering request
    Then Api return the product order in progress                              
