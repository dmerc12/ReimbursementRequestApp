Feature: An employee needs to be able to create a new category.

  Scenario Outline: As an employee, I incorrectly attempt to create a new category.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories button
    When  I click the create category modal
    When  I input <categoryName> in the create category name input
    When  I click the create category button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|categoryName|expectedToastText|
      |"test@email.com"|"test"|""|"Category name field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"this category name is too long and so it should fail"|"Category name field cannot exceed 60 characters, please try again!"|
      |"test@email.com"|"test"|"test"|"Category with this name already exists, please try again!"|

  Scenario Outline: As an employee, I correctly attempt to create a category.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories button
    When  I click the create category modal
    When  I input <categoryName> in the create category name input
    When  I click the create category button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|categoryName|expectedToastText|
    |"test@email.com"|"test"|"new category"|"Category Successfully Added!"|
