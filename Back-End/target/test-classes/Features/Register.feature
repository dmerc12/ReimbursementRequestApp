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
    Then  I am on the register page

    Examples:
      |firstName|lastName|email| password|confirmationPassword|phoneNumber|streetAddress|city|state|zipCode|
      |thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|last|first@email.com|first|first|1112224444|123 First St|New|OK|73071|
      |first|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|first@email.com|first|first|1112224444|123 First St |New|OK|73071|
      |first|last|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|first|first|1112224444|123 First St|New|OK|73071 |
      |first|last|first@email.com|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|first|1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com|first|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com|first|first|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|123 First St|New|OK|73071|
      |first|last|first@email.com|first|first|1112224444|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|New|OK|73071|
      |first|last|first@email.com|first|first|1112224444|123 First St|thisistoolongandsoitshouldraisethedesirederrormessageandsothiserrorwillultimatelybethrownbutthetestwillpass|OK|73071|
      ||last|first@email.com|first|first|1112224444|123 First St|New|OK|73071|
      |first||first@email.com|first|first|1112224444|123 First St|New|OK|73071|
      |first|last||first|first|1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com||first|1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com|first||1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com|first|first||123 First St|New|OK|73071|
      |first|last|test@email.com|first|first|1112224444|123 First St|New|OK|73071|
      |first|last|first@email.com|first|first|1112224444|||||
      |first|last|first@email.com|first|wrong|55|123 First St|New|OK|73071|


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
    Then  I am back on the login page

    Examples:
    |firstName|lastName|email          | password|confirmationPassword|phoneNumber|streetAddress|city|state|zipCode|
    |first    |last    |first@email.com|first    |first               |1112224444 |123 First St |New |OK   |73071  |