Feature: An employee needs to be able to log in with their current credentials and also log out when they're done.

  Scenario Outline: As an employee, I should not be able to log in with incorrect credentials.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    Then  I am back on the login page

    Examples:
    |email|password|
    |"wrong@email.com"|"info"    |
    |""   |"info"      |
    |"wrong@email.com"|""        |

  Scenario Outline: As an employee, I should be able to login with the correct credentials.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    Then  I am on the home page

    Examples:
    |email|password|
    |"test@email.com"|"test"|

  Scenario: As an employee already logged in, I should be able to logout.
    Given I am on the home landing page
    When  I click the logout button
    Then  I am sent to the login page
