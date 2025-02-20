@smoke
Feature: Search

  Background: Visit The Movie dB
    Given I open the The Movie dB homepage

  Scenario: Cast list includes selected actors/actresses
    Given I am on the film page for "Shaun of the Dead"
    Then I accept all cookies if prompted
    When I open the full cast and crew page
     # Read from yml (data/film_urls)
    And I make note of all the cast
    Then I check that the cast list includes:
      | Actor 1    | Actor 2       |
      | Simon Pegg | Kate Ashfield |

  Scenario: Cast list shows first billed only
    Given I am on the film page for "Hot Fuzz"
    Then I accept all cookies if prompted
    When I open the full cast and crew page
    And I make note of all the cast
    Then I expect the Hot Fuzz characters to be listed

  Scenario: Display film details for "Companion" using search
    Then I search for an film "Companion"
    When I click the first movie poster result
    Then I should see the film title "Companion"




