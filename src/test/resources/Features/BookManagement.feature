Feature: Book Management cases

  Background: login and store the access token
    Given I perform login with "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
    Then store the access token from the response

  @Create
  Scenario: Create a new book.
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

  @Update
  Scenario: Update a book by id.
    When I perform Post request for "/books" endpoint with below body
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
    When I perform Post request for "/books/id" endpoint with below body
    """
    {
    "name" : "QA API Update <random>",
    "author" : "Satish Prasad Update <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book Update <random>"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "CreateUpdateBook.json"

  @AllBooks
  Scenario: Get all books.
    When I perform GET request for "/books" endpoint
    Then I get a 200 http status code
    And validate the response with Json schema "AllBooks.json"

  @BookByID
  Scenario: Get book by id.
    When I perform Post request for "/books" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    When I perform GET request for "/books/id" endpoint
    Then I get a 200 http status code
    And validate the response with Json schema "AllBooks.json"

  @Delete
  Scenario: Delete book by id
    When I perform Post request for "/books" endpoint with below body
    """
    {
    "name" : "QA API <random>",
    "author" : "Satish Prasad <random>" ,
    "published_year" : 2025,
    "book_summary" : "QA Book <random>"
    }
    """
    Then I get a 200 http status code
    And I get the book id
    When I perform Delete request for "/books/id" endpoint
    Then I get a 200 http status code
    And I verify "Book deleted successfully" message is displayed



