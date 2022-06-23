Feature: This feature covers all the API related scenarios

  Background:
    Given a JWT is generated

  @api
  Scenario: Adding an employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls

  @api
  Scenario: Get the newly created employee
    Given a request is prepared to get the employee
    When a GET call is made to retrieve the created employee
    Then the status code for the get the created employee is 200
    And the employee we are getting having ID "employee.employee_id" must match with the globally stored employee id
    And the retrieved data at "employee" object matches the data used to create the employee having employee id "employee.employee_id"
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title
      | sak           | shn          | zak             | Male       | 1990-06-12   | OFF        | IT MANAGER

  @jsonpayload
  Scenario: Adding an employee using json object
    Given a request is prepared to create an employee via json payload
    When a POST call is made to create an employee
    Then the status code for the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls


  @dynamic
  Scenario: Adding an employee using dynamic scenario
    Given a request is prepared to create an employee via dynamic payload "sak", "shn", "zak", "M", "1990-06-12", "OFF", "IT MANAGER"
    When a POST call is made to create an employee
    Then the status code for the created employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable to be used for other calls