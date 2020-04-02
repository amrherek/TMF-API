@OCM
Feature: Bucket balance Credit Bucket Balance - errors not implemented

  Scenario: Get Credit Bucket Balance transactions
    When Use a MSISDN prepaid for bucket balance
     And Use a distant start date for bucket balance     
     And Refer to Credit Bucket Balance Transactions
    Then Not implemented error

  Scenario: Credit Bucket Balance By Value
   Given Use a Credit Bucket Balance By Value 
   	 And Add a valid Public Key to Credit Bucket Balance By Value
     And Add integer value to Credit Bucket Balance By Value
     And Add valid characteristics to Credit Bucket Balance By Value
     And Send Credit Bucket Balance By Value request
    Then Not implemented error
   
  @OBW  
	Scenario: Credit Bucket Balance By Voucher
   Given Use a Credit Bucket Balance By Voucher 
   	 And Add a valid id to Credit Bucket Balance By Voucher
     And Add a valid Public Key to Credit Bucket Balance By Voucher
     And Add number to Credit Bucket Balance By Voucher
     And Send Credit Bucket Balance By Voucher request
    Then Not implemented error
    
  @OBW  
  @OTN
  Scenario: Credit Bucket Balance By Transfer
   Given Use a Credit Bucket Balance By Transfer 
   	 And Add a valid Public Key to Credit Bucket Balance By Transfer
     And Add integer value to Credit Bucket Balance By Transfer
     And Send Credit Bucket Balance By Transfer request
    Then Not implemented error

