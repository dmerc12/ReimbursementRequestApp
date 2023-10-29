Feature: An employee needs to be able to update an existing request.

  Scenario Outline: As an employee, I correctly attempt to update an existing request.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests button
    When  I click the update request modal for request <requestId>
    When  I select <category> from the update request category input
    When  I input <comment> in the update request comment input
    When  I input <amount> in the update request amount input
    When  I click the update request button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|requestId|category|comment|amount|expectedToastText|
      |"test@email.com"|"test"|"1"|"1"|""|59.34|"The comment field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"1"|"1"|"this comment is too long and so it will fail and it should raise the desired error message that will be grabbed below and then compared to what it should be"|59.34|"The comment field cannot exceed 150 characters, please try again!"|
      |"test@email.com"|"test"|"1"|"1"|"test comment"|-59.34|"The amount field cannot be below $0.01, please try again!"|
      |"test@email.com"|"test"|"1"|"1"|"test comment"|50.76|"Nothing changed, please try again!"|

  Scenario Outline: As an employee, I correctly attempt to update an existing request.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests button
    When  I click the update request modal for request <requestId>
    When  I select <category> from the update request category input
    When  I input <comment> in the update request comment input
    When  I input <amount> in the update request amount input
    When  I click the update request button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|requestId|category|comment|amount|expectedToastText|
      |"test@email.com"|"test"|"1"|"1"|"test comment"|59.34|"Request Successfully Updated!"|
