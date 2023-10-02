Feature: An employee needs to be able to update their current information.

  Scenario Outline: As an employee, I incorrectly attempt to update my current information.
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
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |email|password|firstName|lastName|updatedEmail|phoneNumber|streetAddress|city|state|zipCode|expectedToastText|
      |"test@email.com"|"test"|""|"last"|"updated@email.com"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The first name field cannot be left empty, please try again!" |
      |"test@email.com"|"test"|"this is too long and so it should raise the desired error message"|"last"|"updated@email.com"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The first name field cannot exceed 36 characters, please try again!"|
      |"test@email.com"|"test"|"first"|""|"updated@email.com"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The last name field cannot be left empty, please try again!"                                                                |
      |"test@email.com"|"test"|"first"|"this is too long and so it should raise the desired error message"|"updated@email.com"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The last name field cannot exceed 36 characters, please try again!"|
      |"test@email.com"|"test"|"first"|"last"|""|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The email field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"first"|"last"|"this is too long and so it should raise the desired error message"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"The email field cannot exceed 60 characters, please try again!"|
      |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|""|"123 Updated"|"Updated"|"OK"|"73072"|"The phone number field cannot be left empty, please try again!"                                                            |
      |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|"this is too long and so it should raise the desired error message"|"123 Updated"|"Updated"|"OK"|"73072"|"The phone number field cannot exceed 13 characters, please try again!"|
      |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|"1234567890"|""|""|""|""|"The address field cannot be left empty, please try again!"|
      |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|"1234567890"|"this is too long and so it should raise the desired error message"|"Updated"|"OK"|"73072"|"The address field cannot exceed 60 characters, please try again!"|
      |"test@email.com"|"test"|"test"|"test"|"test@email.com"|"1112223333"|"123 Test St"|"Test"|"OK"|"73002"|"Nothing has changed, please try again!"|

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
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |email|password|firstName|lastName|updatedEmail|phoneNumber|streetAddress|city|state|zipCode|expectedToastText|
    |"test@email.com"|"test"|"first"|"last"|"updated@email.com"|"1234567890"|"123 Updated"|"Updated"|"OK"|"73072"|"Information successfully updated!"|
