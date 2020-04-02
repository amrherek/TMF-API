@Default
Feature: Party individual errors

  Scenario: Get individual with unknown id
    When Use a customer id unknown
     And Refer to individual
    Then Not found error

  Scenario: Update individual bad email
    Given Have an existing individual
    When Change the family name
     And Change the email with invalid
     And Update the customer
    Then Bad parameter value error
    
  Scenario: Update individual bad title
    Given Have an existing individual
    When Change the title with invalid
     And Update the customer
    Then Bad parameter value error
    
  Scenario: Update individual incompatible gender and title
    Given Have an existing individual
    When Change the gender with incompatible
     And Change the title with incompatible
     And Update the customer
    Then Bad parameter value error
    
  Scenario: Get individual all criteria
    When Use a customer given name valid
     And Use a customer family name valid
     And Use a customer email valid
     And Use a customer identification id valid
     And Use a customer identification type valid
     And Refer to individuals
    Then Bad request error    