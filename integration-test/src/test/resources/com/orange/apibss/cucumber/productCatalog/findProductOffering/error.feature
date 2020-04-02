Feature: Product catalog find productOffering errors

	@OTN
	@OBW
	@OCM
  Scenario: Get productOffering no operation
    When Use a rate plan valid for productCatalog
     And Refer to productOfferings
    Then Empty parameter error
  
  @OTN
  @OBW
  @OCM
  Scenario: Get productOffering invalid msisdn for migration
    When Use a rate plan invalid for productCatalog
     And Use a migration type
     And Refer to productOfferings
    Then Get empty productOffering result

  @OTN
  @OBW
  @OCM
  Scenario: Get productOffering invalid msisdn for compatibility
    When Use a rate plan invalid for productCatalog
     And Use a compatibility type
     And Refer to productOfferings
    Then Get empty productOffering result
   
  @OTN
  @OBW
  @OCM  
  Scenario: Get productOffering for configuration - invalid productOffering
    When Use a productOffering id invalid for productCatalog
     And Use a configuration type
     And Refer to the productOffering
    Then Not found error
    
  @OCM  
  Scenario: Get productOffering for configuration - badFormat productOffering
    When Use a productOffering id with bad format for productCatalog
     And Use a configuration type
     And Refer to the productOffering
    Then Bad parameter format error

#	@OTN
  Scenario: Get productOffering no productSpecId for faf configuration
     When Use a rate plan valid for productCatalog
     And Use a configuration type
     And Refer to productOfferings
    Then Empty parameter error

#	@OTN
  Scenario: Get productOffering bad productSpecId for faf configuration
     When Use a rate plan valid for productCatalog
     And Use a configuration type
     And Use a bad productSpecification.id     
     And Refer to productOfferings
    Then Bad parameter value error
    
#	@OTN
  Scenario: Get productOffering for faf configuration - invalid rate plan
    When Use a rate plan invalid for productCatalog
     And Use a configuration type
     And Use a faf productSpecification.id
     And Refer to productOfferings
    Then Get empty productOffering result
    