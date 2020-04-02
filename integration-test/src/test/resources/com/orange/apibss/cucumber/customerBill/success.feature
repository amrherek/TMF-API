Feature: Customer bill Success

  @OTN @OCM
  Scenario: UC30 - list customer bill info
    Given a valid msisdn
      And a distant start date     
      And an authorized user
    When listing the customer bills
    Then Get ordered bills

  @OBW
  Scenario: UC30 - list customer bill info
    Given a valid msisdn
    And an authorized user
    And a distant start date     
    And a maximum of 5 results
    When listing the customer bills
    Then Get ordered bills
    
  @OBW
  Scenario: UC30 - list customer bill info without prefix
    Given a msisdn without prefix
    And an authorized user
    And a distant start date     
    And a maximum of 5 results
    When listing the customer bills
    Then Get ordered bills

  @OTN @OBW @OCM
  Scenario: UC30 - list customer bill info with limit
    Given a valid msisdn
      And an authorized user
      And a distant start date
      And a maximum of 1 results
    When listing the customer bills
    Then the number of bills should be 1
    
  @OTN @OBW @OCM
  Scenario: UC31 - get information for a customer bill
  Given an authorized user
    And a valid customer bill id
  When getting bill information
  Then Get the bill
  
  @OTN @OBW @OCM
  Scenario: UC30 - list customer bill info from party id
    Given a valid party id
      And an authorized user
      And a distant start date
      And a maximum of 5 results
    When listing the customer bills
    Then Get ordered bills
          
  @OTN @OCM @OBW
  Scenario: UC30 - list customer bill info with end date
    Given a valid msisdn
      And an authorized user
      And a distant start date
      And a valid end date
    When listing the customer bills
    Then the number of bills should be 2
    
  @OTN @OBW @OCM
  Scenario: UC30 - list customer bill info with start and end date
    Given a valid msisdn
      And an authorized user
      And a valid start date
      And a valid end date
    When listing the customer bills
    Then the number of bills should be 1
    
  @OTN
  Scenario: UC32 - download customer bill image
    Given a valid customer bill id
      And an authorized user
    When download bill image
    Then Get the image
    
  @OBW @OCM @OTN
  Scenario: UC30 - list customer bill info from party id for large account
    Given a large account party id
    	And an authorized user
    	And a distant start date
    When listing the customer bills
    Then the number of bills should be 0
    
  @OBW
  Scenario: UC30 - list customer bill info from party id for large account with hierarchy
    Given a large account party id
      And a maximum of 5 results
      And a distant start date
      And search with hierarchy
    When listing the customer bills
    Then the number of bills should be 5