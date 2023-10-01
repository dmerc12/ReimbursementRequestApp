Feature: An employee needs to be able to update their current information.

  Scenario Outline: As an employee, I successfully update my current information.
    Given I am on the login page
    When  I input <email> in the login email input
    When  I input <password> in the login password input
    When  I click the login button
    When  I click the manage information button
    When  I click the update information modal
    When  I input <firstName> in the update first name input
    When  I input <lastName> in the update last name input
    When  I input <updatedEmail> in the update email input
    When  I input <phoneNumber> in the update phone number input
    When  I input <streetAddress> in the update street address input
    When  I input <city> in the update city input
    When  I input <state> in the update state input
    When  I input <zipCode> in the update zip code input
    When  I click the update information button
    Then  I remain on the update information page

    Examples:
    |email|password|firstName|lastName|updatedEmail|phoneNumber|streetAddress|city|state|zipCode|
    |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|"123-456-7890"|"123 Updated"|"Updated"|"OK"|"73072"|