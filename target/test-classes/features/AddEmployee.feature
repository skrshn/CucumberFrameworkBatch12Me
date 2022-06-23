Feature: Adding employees in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user navigates to add employee option

  @regression
  Scenario: Adding one Employee from feature file
    When user enters firstname middlename and lastname
    And user clicks on save button
    Then user is able to add employee successfully

    @ETETest1
  Scenario: Adding one Employee from cucumber feature file
    When user enters "sakir" "sako" and "sahin"
    And user grabs the employee ID
    And user clicks on save button
    And user queries the database for same employee ID
    Then user verifies the results

  @test @datatable
  Scenario Outline: Adding multiple Employees
    When user provides "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then user is able to add employee successfully
    Examples:
      | firstName | middleName | lastName |
      | john      | j          | doe      |
      | mary      | m          | jane     |
      | jim       | m          | poe      |
      | tim       | t          | toe      |


  Scenario: Adding employee using data table
    When user enters multiple employee data and verify they are added
      | firstName | middleName | lastName |
      | john      | j          | doe      |
      | mary      | m          | jane     |
      | jim       | m          | poe      |
      | tim       | t          | toe      |
    Then user is able to add all employees successfully


  @excel
  Scenario: Adding employee using excel
    When user adds multiple employees from excel file using "EmployeeData" sheet and verify the user added