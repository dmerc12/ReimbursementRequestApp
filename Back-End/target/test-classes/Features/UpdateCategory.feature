Feature: An employee needs to be able to update an existing category.

  Scenario Outline: As an employee, I incorrectly attempt to update an existing category.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories button
    When  I click the update category modal for category <categoryId>
    When  I input <categoryName> in the update category name input
    When  I click the update category button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|categoryId|categoryName|expectedToastText|
      |"test@email.com"|"test"|"1"|""|"Category Successfully Updated!"|
      |"test@email.com"|"test"|"1"|"updated category name is too long and won't work"|"Category Successfully Updated!"|
      |"test@email.com"|"test"|"1"|"new category"|"Category Successfully Updated!"|
      |"test@email.com"|"test"|"1"|"test"|"Category Successfully Updated!"|

  Scenario Outline: As an employee, I correctly attempt to update an existing category.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories button
    When  I click the update category modal for category <categoryId>
    When  I input <categoryName> in the update category name input
    When  I click the update category button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|categoryId|categoryName|expectedToastText|
    |"test@email.com"|"test"|"1"|"updated category name"|"Category Successfully Updated!"|
