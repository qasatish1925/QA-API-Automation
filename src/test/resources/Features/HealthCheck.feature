Feature: Health check

  Scenario: API - Health Check
    When I perform GET request for "/health" endpoint
    Then I get a 200 http status code
    And validate the response with Json schema "sampleTest.json"