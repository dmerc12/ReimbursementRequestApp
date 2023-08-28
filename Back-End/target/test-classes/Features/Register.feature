Feature: A new employee needs to register with the reimbursement request system to put in and track their reimbursement requests.

  Scenario Outline: An employee successfully inputs the information needed to register.
    Given The employee is on the login page
    When  The employee hits the register tab in the nav bar
    When  The employee inputs <firstName>
    When  The employee inputs <lastName>
    Then  The employee is on the login page

    Examples:
    |firstName|lastName|
    |first    |last    |