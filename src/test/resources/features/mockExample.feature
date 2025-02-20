@smoke @mock
Feature: Search Mock

# The WireMock hook automatically starts the mock server and stubs the API endpoint.
  Scenario: Display film details for "Companion" using mock data
    Given I open the mock film page
    And I should see the film director mock "Drew Hancock"
    And I should see the following cast members mock:
      | Sophie Thatcher |
      | Kate Ashfield   |