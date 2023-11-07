Feature: An employee needs to be able to use the navigation bar.

  Scenario Outline: As an employee, I click the register button in the navigation bar
    Given I am on the login page
    When  I click the register tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | title       |
      | "Registering" |

  Scenario Outline: As an employee, I click the login button in the navigation bar
    Given I am on the login page
    When  I click the register tab in the nav bar
    When  I click the login tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | title      |
      | "Logging In" |

  Scenario Outline: As an employee, I click the manage information button in the navigation bar
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage information tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | email          | password | title            |
      | "test@email.com"| "work"| "Managing Current Information"|

  Scenario Outline: As an employee, I click the manage categories button in the navigation bar
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | email          | password | title             |
      | "test@email.com" | "work"| "Managing Categories" |

  Scenario Outline: As an employee, I click the manage requests button in the navigation bar
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage requests tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | email          | password | title             |
      | "test@email.com" | "work"     | "Managing Requests" |

  Scenario Outline: As an employee, I click the home button in the navigation bar
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage categories tab in the nav bar
    When  I click the home tab in the nav bar
    Then  I am on a page with the title <title>

    Examples:
      | email          | password | title |
      | "test@email.com" |"work"     | "Home"  |