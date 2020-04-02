Feature: Product ordering transfer data/credit errors

	@OTN
	Scenario: Add transfer data missing productOffering.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And Add transferData product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error

	@OTN
	Scenario: Add transfer data bad productOffering.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With bad productOffering.id
    And Add transferData product.productSpecification.id
    And Send product ordering request
    Then Bad parameter value error

	@OTN    
	Scenario: Delete transfer data missing product.productOffering.id
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete action
    And Add transferData product.productSpecification.id
    And Send product ordering request
    Then Missing parameter error

	@OTN    
 	Scenario: Perform a transfer data missing volume
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferDataFee productOffering.id
    And Add transferData product.productSpecification.id
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Missing parameter error
    
	@OBW    
 	Scenario: Perform a transfer credit missing volume
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFee productOffering.id
    And Add transferCredit product.productSpecification.id
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Missing parameter error 
    
	@OBW
	Scenario: Perform a transfer credit bad productOffering.id 
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With bad productOffering.id
    And Add transferCredit product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Bad parameter value error 
    
	@OTN
	@OCM
	Scenario: Perform a transfer credit not implemented
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFee productOffering.id
    And Add transferCredit product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Send product ordering request
    Then Not implemented error   
    
	@OTN
	Scenario: Perform a transfer credit for postpaid missing targetPublicKey
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add action
    And With transferCreditFeePostpaid productOffering.id
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Send product ordering request
    Then Missing parameter error  
    
	@OTN
	Scenario: Add num to white list for transfer credit for postpaid with 2 volume
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with add characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 50 value for volume productCharacteristic
    And Add 60 value for volume productCharacteristic
    And Add 123456 value for targetPublicKey productCharacteristic
    And Add true value for automaticTransfer productCharacteristic
    And Send product ordering request
    Then Too many parameter error                 

  @OTN
	Scenario: Delete 1 num from white list for transfer credit for postpaid bad param name
    Given Use a valid product ordering body
    When Use a related public key with valid msisdn
    And Use an order item with delete characteristic action
    And Add transferCreditPostpaid product.productSpecification.id
    And Add 123456 value for targetPublicKey_bad productCharacteristic
    And Send product ordering request
    Then Missing parameter error