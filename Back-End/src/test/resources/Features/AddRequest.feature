Feature: An employee needs to be able to create a new request.

  Scenario Outline: As an employee, I correctly attempt to create a new request.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests button
    When  I click the create request modal
    When  I select <category> from the create request category input
    When  I input <comment> in the create request comment input
    When  I input <amount> in the create request amount input
    When  I click the create request button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|category|comment|amount|expectedToastText|
      |"test@email.com"|"test"|"1"|""|50.76|"The comment field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"1"|"this comment is too long and so it will fail and it should raise the desired error message that will be grabbed below and then compared to what it should be"|50.76|"The comment field cannot exceed 150 characters, please try again!"|
      |"test@email.com"|"test"|"1"|"test comment"|-50.76|"The amount field cannot be $0.01, please try again!"|

  Scenario Outline: As an employee, I correctly attempt to create a new request.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests button
    When  I click the create request modal
    When  I select <category> from the create request category input
    When  I input <comment> in the create request comment input
    When  I input <amount> in the create request amount input
    When  I click the create request button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|category|comment|amount|expectedToastText|
    |"test@email.com"|"test"|"1"|"test comment"|50.76|"Request Successfully Added!"|
