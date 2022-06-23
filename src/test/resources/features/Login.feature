Feature: Validation of login scenarios

  Background:
  #Given user is navigated to HRMS Application

  @smoke @sprint12 @regression @latest
  Scenario: Admin login
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

  @regression @logintest
  Scenario: ESS login
    When user enters valid ess username and password
    And user clicks on login button
    Then ess user is successfully logged in

  @regression
  Scenario: Invalid login
    When user enters invalid username and password
    And user clicks on login button
    Then user see invalid login error message on the screen

  @regression
  Scenario: Username cannot be empty
    When user enters empty username and a password
    And user clicks on login button
    Then user see username cannot be empty error message on the screen

  @regression
  Scenario: Password cannot be empty
    When user enters a username and empty password
    And user clicks on login button
    Then user see password cannot be empty error message on the screen
