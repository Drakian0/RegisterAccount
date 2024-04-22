Feature: Register

  Scenario Outline: Try to register
    Given I have chosen the browser "<browser>"
    And I enter my date of birth
    And I enter first name "<fName>" and last name "<lName>"
    And I enter a email
    And I choose the password "<password>" and confirmed with "<cPassword>"
    And I "<choice>" the terms and conditions
    And I'm over 18 and agree to the code of ethics
    When I click confirm and join
    Then I get the result "<result>"

    Examples:
    | browser | fName |   lName   | password | cPassword | choice |  result  |
    | chrome  | Test  | Testerson | Test123! | Test123!  | accept | accepted |
    | fireFox | Test  |           | Test123! | Test123!  | accept | rejected |
    | chrome  | Test  | Testerson | Test123! | Test123-  | accept | rejected |
    | fireFox | Test  | Testerson | Test123! | Test123!  | reject | rejected |
