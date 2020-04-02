Feature: Bucket balance Credit Bucket Balance errors 
   
  @OTN  
  @OBW
  Scenario: Credit Bucket Balance By Value without msisdn
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid id to Credit Bucket Balance By Value
   	 And Add integer value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Missing parameter error
    
  @OTN  
	Scenario: Credit Bucket Balance By Voucher without msisdn
   Given Use a Credit Bucket Balance By Voucher 
     And Add number to Credit Bucket Balance By Voucher
     And Send Credit Bucket Balance By Voucher request
    Then Missing parameter error
  
  @OTN  
  @OBW
  Scenario: Credit Bucket Balance By Value without value
   Given Use a Credit Bucket Balance By Value 
     And Add a valid id to Credit Bucket Balance By Value
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Missing parameter error
    
  @OTN
  Scenario: Credit Bucket Balance By Value - float value
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add float value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Invalid credit error
   
  @OTN 
	Scenario: Credit Bucket Balance By Voucher without number
   Given Use a Credit Bucket Balance By Voucher 
     And Add a valid Public Key to Credit Bucket Balance By Voucher
     And Send Credit Bucket Balance By Voucher request
    Then Missing parameter error
   
  @OBW
  @OTN
  Scenario: Get Credit Bucket Balance transactions - invalid msisdn
    When Use a MSISDN invalid for bucket balance
     And Use a distant start date for bucket balance
     And Use a postpaid rating type
     And Refer to Credit Bucket Balance Transactions
    Then Get empty credit bucket balance transactions result   
    
  @OTN  
  @OBW
  Scenario: Get Credit Bucket Balance transactions - missing start date
    When Use a MSISDN prepaid for bucket balance
     And Refer to Credit Bucket Balance Transactions
    Then Missing parameter error  
    
	@OTN
  Scenario: Get Credit Bucket Balance transactions - missing ratingType
    When Use a MSISDN prepaid for bucket balance
     And Use a distant start date for bucket balance    
     And Refer to Credit Bucket Balance Transactions
    Then Missing parameter error      
    
 	@OBW
 	Scenario: Credit Bucket Balance By Value without id
  Given Use a Credit Bucket Balance By Value 
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add integer value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Missing parameter error
