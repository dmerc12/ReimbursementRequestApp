Feature: An employee needs to be able to delete an existing request.

  Scenario Outline: As an employee, I delete a request.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests button
    When  I click the delete request modal for request <requestId>
    When  I click the delete request button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|requestId|expectedToastText|
    |"test@email.com"|"test"|"1"|"Request Successfully Deleted!"|
