Feature: Search in Google

Scenario: Search Google
   Given I am home page
   When I search as "Testing with Selenium"
   Then I found results