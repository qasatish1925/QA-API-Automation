@GetBookNegative
Feature: Possible Negative Scenarios for Get method:

  Scenario: Get with Invalid `book_id`
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform GET request for "/books/abc" endpoint
    Then I get a 422 http status code

  Scenario: Get with Special characters in ID
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform GET request for "/books/abc" endpoint
    Then I get a 422 http status code

  Scenario: Get with Non-existent book
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response
    When I perform GET request for "/books/44443" endpoint
    Then I get a 404 http status code
    And I verify "Book not found" message is displayed
