@DeleteBookNegative
Feature: Possible Negative Scenarios for Delete method:

  Scenario: Invalid `book_id` (string)
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform Delete request for "/books/abc" endpoint
    Then I get a 422 http status code

  Scenario: Special characters in ID
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform Delete request for "/books/@#!#" endpoint
    Then I get a 422 http status code

  Scenario: Non-existent book
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform Delete request for "/books/94994949" endpoint
    Then I get a 404 http status code
    And I verify "Book not found" message is displayed

  Scenario: Empty/blank ID
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform Delete request for "/books/" endpoint
    Then I get a 405 http status code
