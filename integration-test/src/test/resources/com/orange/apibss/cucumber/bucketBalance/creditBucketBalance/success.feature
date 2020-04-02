Feature: Bucket balance Credit Bucket Balance

	@OBW
  Scenario: Get Credit Bucket Balance transactions
    When Use a MSISDN prepaid for bucket balance
     And Use a distant start date for bucket balance    
     And Refer to Credit Bucket Balance Transactions
    Then Get Credit Bucket Balance Transactions
    
	@OTN
  Scenario: Get Credit Bucket Balance transactions
    When Use a MSISDN prepaid for bucket balance
     And Use a postpaid rating type
     And Use a distant start date for bucket balance    
     And Refer to Credit Bucket Balance Transactions
    Then Get Credit Bucket Balance Transactions    

	@OTN
  Scenario: Credit Bucket Balance By Value
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add integer value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Api return the Credit Bucket Balance By Value
    
  @OTN
  @OBW
  Scenario: Credit Bucket Balance By Value - with id
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid id to Credit Bucket Balance By Value
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add integer value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Api return the Credit Bucket Balance By Value
    
  @OBW
  Scenario: Credit Bucket Balance By Value - float value and id
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid id to Credit Bucket Balance By Value
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add float value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Api return the Credit Bucket Balance By Value
    
  @OTN
  Scenario: Credit Bucket Balance By Value - problematic float value
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add problematic value to Credit Bucket Balance By Value
     And Add TND unit to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Api return the Credit Bucket Balance By Value
    
  @OTN  
	Scenario: Credit Bucket Balance By Voucher
   Given Use a Credit Bucket Balance By Voucher 
     And Add a valid Public Key to Credit Bucket Balance By Voucher
     And Add number to Credit Bucket Balance By Voucher
     And Send Credit Bucket Balance By Voucher request
    Then Api return the Credit Bucket Balance By Voucher
   

