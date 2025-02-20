@login @smoke
Feature: Login
# I understand sensitive information such as login details/passwords should be in a separate file or something like aws secrets manager and should not be on the repo but its here just to speed things up for now
  Scenario: Successful Login with Valid Credentials
    Given I am on the login page
    When I enter username "Andre1717"
    And I enter password "StrongPassword"
    And I click the login button
    Then I should be redirected to the user page

  Scenario: Login with Invalid Credentials
    Given I am on the login page
    When I enter username "invaliduser"
    And I enter password "wrongpassword"
    And I click the login button
    Then I should see an error message "There was a problem"

  Scenario: Login with Empty Fields
    Given I am on the login page
    When I click the login button
    Then I should see an error message "There was a problem"
