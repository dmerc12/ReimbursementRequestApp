Feature: An employee needs to be able to delete their profile with the reimbursement request system.

  Scenario Outline: As an employee, I successfully delete my profile.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage information button
    When  I click the delete profile modal
    When  I click the delete profile button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|expectedToastText|
    |"test@email.com"|"test"|"Profile successfully deleted, goodbye!"|
