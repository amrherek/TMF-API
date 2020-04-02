@Default
Feature: Product ordering defaults errors

  Scenario: Missing order item id
    Given Use a valid product ordering body
    When Use an order item with no id
    And Send product ordering request
    Then Empty parameter error

  Scenario: Missing hasPrerequisite item
    Given Use a valid product ordering body
    When Use an order item
    And With is prerequisite of next order item
    And Send product ordering request
    Then Missing parameter error

  Scenario: Missing isPrerequisite item
    Given Use a valid product ordering body
    When Use an order item
    And With has prerequisite of previous order item
    And Send product ordering request
    Then Missing parameter error