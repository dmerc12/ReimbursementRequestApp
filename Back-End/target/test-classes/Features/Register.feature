Feature: A new employee needs to register with the reimbursement request system to put in and track their reimbursement requests.

  Scenario Outline: As a new employee, I should not be able to register if I do not input the correct information needed to register.
    Given I am on the login page
    When  I click the register tab in the nav bar
    When  I input <firstName> in the register first name input
    When  I input <lastName> in the register last name input
    When  I input <email> in the register email input
    When  I input <password> in the register password input
    When  I input <confirmationPassword> in the register confirmation password input
    When  I input <phoneNumber> in the register phone number input
    When  I input <streetAddress> in the register street address input
    When  I input <city> in the register city input
    When  I input <state> in the register state input
    When  I input <zipCode> in the register zip code input
    When  I click the register button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
      |firstName|lastName|email| password|confirmationPassword|phoneNumber|streetAddress|city|state|zipCode|expectedToastText|
      |"this is too long and so it should raise the desired error message"|"last"|"first@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The first name field cannot exceed 36 characters, please try again!"|
      |"first"|"this is too long and so it should raise the desired error message"|"first@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The last name field cannot exceed 36 characters, please try again!"|
      |"first"|"last"|"this is too long and so it should raise the desired error message"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The email field cannot exceed 60 characters, please try again!"|
      |"first"|"last"|"first@email.com"|"this is too long and so it should raise the desired error message"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The password field cannot exceed 60 characters, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"this is too long and so it should raise the desired error message"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The confirmation password field cannot exceed 60 characters, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"first"|"this is too long and so it should raise the desired error message"|"123 First St"|"New"|"OK"|"73071"|"The phone number field cannot exceed 13 characters, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"first"|"1112224444"|"this is too long and so it should raise the desired error message"|"New"|"OK"|"73071"|"The address field cannot exceed 60 characters, please try again!"|
      |""|"last"|"first@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The first name field cannot be left empty, please try again!"|
      |"first"|""|"first@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The last name field cannot be left empty, please try again!"|
      |"first"|"last"|""|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The email field cannot be left empty, please try again!"|
      |"first"|"last"|"first@email.com"|""|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The password field cannot be left empty, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|""|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"The confirmation password field cannot be left empty, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"first"|""|"123 First St"|"New"|"OK"|"73071"|"The phone number field cannot be left empty, please try again!"|
      |"first"|"last"|"test@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"An employee with this email already exists, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"first"|"1112224444"|""|""|""|""|"The address field cannot be left empty, please try again!"|
      |"first"|"last"|"first@email.com"|"first"|"wrong"|"55"|"123 First St"|"New"|"OK"|"73071"|"The passwords do not match, please try again!"|


  Scenario Outline: As a new employee, I successfully input the information needed to register.
    Given I am on the login page
    When  I click the register tab in the nav bar
    When  I input <firstName> in the register first name input
    When  I input <lastName> in the register last name input
    When  I input <email> in the register email input
    When  I input <password> in the register password input
    When  I input <confirmationPassword> in the register confirmation password input
    When  I input <phoneNumber> in the register phone number input
    When  I input <streetAddress> in the register street address input
    When  I input <city> in the register city input
    When  I input <state> in the register state input
    When  I input <zipCode> in the register zip code input
    When  I click the register button
    Then  I should see a toast notification saying <expectedToastText>

    Examples:
    |firstName|lastName|email| password|confirmationPassword|phoneNumber|streetAddress|city|state|zipCode|expectedToastText|
    |"first"|"last"|"first-time@email.com"|"first"|"first"|"1112224444"|"123 First St"|"New"|"OK"|"73071"|"Employee successfully created"|