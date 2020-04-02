@OTN
Feature: Product catalog find productOffering 

	@OBW
	@OCM
  Scenario: Get productOffering for migration (UC2)
    When Use a rate plan valid for productCatalog
     And Use a migration type
     And Refer to productOfferings
    Then Get productOfferings for migration

  @OBW
  @OCM
  Scenario: Get productOffering for compatibility (UC3)
    When Use a rate plan valid for productCatalog
     And Use a compatibility type
     And Refer to productOfferings
    Then Get productOfferings for compatibility
    
  @OBW
  @OCM
  Scenario: Get productOffering for configuration (UC1)
    When Use a productOffering id valid for productCatalog
     And Use a configuration type
     And Refer to the productOffering
    Then Get valid productOffering
    
#  Scenario: Get productOffering for faf configuration (UC1-FaF)
#    When Use a rate plan valid for productCatalog
#     And Use a configuration type
#     And Use a faf productSpecification.id
#     And Refer to productOfferings
#    Then Get productOfferings for faf configuration
    


