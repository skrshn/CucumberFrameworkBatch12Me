Feature: US-12345 - Search an employee in HRMS Application

  Background:
    #Given user is navigated to HRMS Application
    When user enters valid admin credentials
    And user clicks on login button
    And user navigates to employee list page

  @regression @sakir
  Scenario: Search an employee by id
#    Given user is navigated to HRMS Application
#    When user enters valid admin credentials
#    And user clicks on login button
#    And user navigates to employee list page
    When user enter valid employee id
    And clicks on search button
    Then user is able to see employee information

  Scenario: Search an employee by name
#    Given user is navigated to HRMS Application
#    When user enters valid admin credentials
#    And user clicks on login button
#    And user navigates to employee list page
    When user enter valid employee name
    And clicks on search button
    Then user is able to see employee information