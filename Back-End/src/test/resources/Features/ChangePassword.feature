Feature: An employee needs to be able to change their current password.

  Scenario Outline: As an employee, I incorrectly attempt to change my password.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage information button
    When  I click the change password modal
    When  I input <newPassword> in the change password input
    When  I input <confirmationPassword> in the change password confirmation input
    When  I click the change password button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|newPassword|confirmationPassword|expectedToastText|
      |"test@email.com"|"test"|""|"ok"|"The password field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"this is too long and so it should raise the desired error message"|"ok"|"The password field cannot exceed 60 characters, please try again!"|
      |"test@email.com"|"test"|"ok"|""|"The confirmation password field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"ok"|"this is too long and so it should raise the desired error message"|"The confirmation password field cannot exceed 60 characters, please try again!"|
      |"test@email.com"|"test"|"not"|"matching"|"The confirmation password field must match the password field, please try again!"|
      |"test@email.com"|"test"|"test"|"test"|"Nothing has changed, please try again!"|

  Scenario Outline: As an employee, I successfully change my password.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage information button
    When  I click the change password modal
    When  I input <newPassword> in the change password input
    When  I input <confirmationPassword> in the change password confirmation input
    When  I click the change password button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|newPassword|confirmationPassword|expectedToastText|
    |"test@email.com"|"test"|"work"|"work"|"Password successfully changed!"|
