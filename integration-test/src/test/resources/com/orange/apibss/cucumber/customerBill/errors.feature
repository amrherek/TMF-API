Feature: Customer bill Error

  @Default
  Scenario: UC30 - list customer bill info without MSISDN
    Given an authorized user
      And a valid start date    
    When listing the customer bills
    Then the response should be a bad request
      And the error is a bad combination error for publicKey

  @Default
  Scenario: UC30 - list customer bill info with an empty MSISDN
    Given an authorized user
      And an empty msisdn
      And a valid start date      
    When listing the customer bills
    Then the response should be a bad request
    And the error is a bad parameter error for publicKey

  @Default
  Scenario: UC30 - list customer bill info with an invalid MSISDN
    Given an authorized user
      And an invalid msisdn
      And a valid start date      
    When listing the customer bills
    Then the number of bills should be 0
    
 	@Default
  Scenario: UC30 - list customer bill info with a MSISDN with a bad format
    Given an authorized user
      And a bad format msisdn
      And a valid start date      
    When listing the customer bills
    Then the number of bills should be 0

  @Default
  Scenario: UC30 - list customer bill info without start date
    Given an authorized user
      And a valid msisdn    
    When listing the customer bills
    Then the response should be a bad request
    And the error is a missing parameter error for billDate.gte

  @Default
  Scenario: UC30 - list customer bill info with an invalid limit value
    Given an authorized user
      And a valid msisdn
      And a valid start date      
      And a maximum of two results
    When listing the customer bills
    Then the response should be a bad request
    And the error is a bad format error for limit


  @Default
  Scenario: UC31 - get information for a customer bill with invalid id
  Given an authorized user
    And an invalid customer bill id
  When getting bill information
  Then the response should be a not found response
     And the error should be a no bill found error
     
  @Default
  Scenario: UC31 - get information for a customer bill with a bad format id
  Given an authorized user
    And a bad format customer bill id
  When getting bill information
  Then Bad parameter format error

	@OCM
  Scenario: UC32 - download customer bill image
    Given a valid customer bill id
      And an authorized user
    When download bill image
    Then Not implemented error
    
  @OBW
  Scenario: UC32 - download customer bill image
    Given a valid customer bill id
      And an authorized user
    When download bill image
    Then Internal BSCS error
    
  @OTN @OBW @OCM
  Scenario: UC30 - list customer bill info from invalid party id
    Given a invalid party id
      And a valid start date    
     And an authorized user
    When listing the customer bills
    Then the number of bills should be 0
    
  @Default
  Scenario: UC30 - list customer bill info from party id without start date
    Given an authorized user
      And a valid party id    
    When listing the customer bills
    Then the response should be a bad request
    And the error is a missing parameter error for billDate.gte   
    
  @OCM
  Scenario: UC30 - list customer bill info from party id with bad format
    Given a bad format party id
      And a valid start date    
    When listing the customer bills
    Then Bad parameter format error
    
  @OTN @OBW
  Scenario: UC30 - list customer bill info from party id with bad format
    Given a bad format party id
      And an authorized user      
      And a valid start date    
    When listing the customer bills
    Then the number of bills should be 0
    
  @OCM @OTN
  Scenario: UC30 - list customer bill info from party id for large account with hierarchy
    Given a large account party id
      And an authorized user    
      And a valid start date    
      And a maximum of 5 results
      And search with hierarchy
    When listing the customer bills
    Then Not implemented error
    
  @OTN @OBW @OCM
  Scenario: UC30 - list customer bill info with invalid end date
    Given a valid msisdn
      And an authorized user      
      And a valid start date    
      And an authorized user
      And a invalid end date
    When listing the customer bills
    Then Bad parameter format error
