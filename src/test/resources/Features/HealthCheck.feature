Feature: Health check

  Scenario: API - Sign in
    When I perform GET request for "/get" endpoint
    Then I get a 200 http status code
    And validate the response with Json schema "sampleTest.json"