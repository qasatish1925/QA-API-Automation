@UpdateBookNegative
Feature: Possible Negative Scenarios for Update request

  Background: login and store the access token
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response

    # Expected Response: { "error": "Field 'name' cannot be empty" } : Expected Code: 400 Bad Request
  Scenario: Title/author provided as empty string "".
    When I perform Post request for "/books/" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"
    And I get the book id
    When I perform PUT request for "/books/id" endpoint with below body
    """
    {
    "name" : " ",
    "author" : " " ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"

    # Expected Response: { "error": "Field 'published_year' must be a number" }
  Scenario: Invalid data types
    When I perform Post request for "/books/" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"
    And I get the book id
    When I perform PUT request for "/books/id" endpoint with below body
    """
    {
    "name" : 9748494,
    "author" : "Satish Prasad <random>" ,
    "published_year" : "twenty twenty-five",
    "book_summary" : 121
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"

  # Expected Response: { "error": "Invalid published_year value" } : Expected Code: 422 Unprocessable Entity
  Scenario: Invalid data types
    When I perform Post request for "/books/" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"
    And I get the book id
    When I perform PUT request for "/books/id" endpoint with below body
    """
    {
    "name" : 9748494,
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2050,
    "book_summary" : 121
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"

  Scenario: Malformed JSON
    When I perform Post request for "/books/" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"
    And I get the book id
    When I perform PUT request for "/books/id" endpoint with below body
    """
    {
    "name" : 9748494,
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2050,
    "book_summary" : 121

    """
    Then I get a 422 http status code


