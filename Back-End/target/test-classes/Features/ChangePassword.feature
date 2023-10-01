Feature: An employee needs to be able to change their current password.

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
    Then  I remain on the update information page

    Examples:
    |email|password|newPassword|confirmationPassword|
    |"test@email.com"|"test"|"work"|"work"          |