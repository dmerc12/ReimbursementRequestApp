Feature: An employee needs to be able to log in with their current credentials and also log out when they're done.

  Scenario Outline: As an employee, I attempt to log in with incorrect credentials.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|expectedToastText|
    |"wrong@email.com"|"info"|"Either the email or the password is incorrect, please try again!"|
    |""   |"info"|"The email field cannot be left empty, please try again!"|
    |"wrong@email.com"|""|"The password field cannot be left empty, please try again!"|

  Scenario Outline: As an employee, I attempt to login with the correct credentials.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|expectedToastText|
    |"test@email.com"|"test"|"Welcome!"|

  Scenario Outline: As an employee already logged in, I attempt to logout.
    Given I am on the home landing page
    When  I click the logout button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |expectedToastText|
    |"Goodbye!"       |