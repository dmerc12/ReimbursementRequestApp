Feature: An employee needs to be able to delete an existing category.

  Scenario Outline: As an employee, I correctly attempt to delete an existing category.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories button
    When  I click the delete category modal for category <categoryId>
    When  I click the delete category button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|categoryId|expectedToastText|
    |"test@email.com"|"test"|"1"|"Category Successfully Deleted!"|
