Feature: Sign up / sign in test cases

  @signup
  Scenario: sign up with random user
    When I perform Post request for "/signup" endpoint with below body
    """
    {
    "email": "randomQA+<random>@gmail.com",
    "password" : "QATech"
    }
    """
    Then I get a 200 http status code
    And validate the response with Json schema "signup.json"
    And I verify "User created successfully" message is displayed

    @signin
    Scenario: sign with the user
      When I perform Post request for "/login" endpoint with below body
    """
    {
    "email": "randomQA@gmail.com@gmail.com",
    "password" : "QATech"
    }
    """
      Then I get a 200 http status code
      And store the access token from the response
