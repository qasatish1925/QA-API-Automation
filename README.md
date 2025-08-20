# QA-API-Automation

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://opensource.org/)

> A robust and scalable framework for API testing and automation, designed to ensure the reliability and performance of your APIs. This project leverages Behavior-Driven Development (BDD) using Cucumber, RestAssured for API interactions, and Maven for dependency management, generating comprehensive HTML reports for test results.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoint Testing](#api-endpoint-testing)
    - [Test Case Design (BDD with Cucumber)](#test-case-design-bdd-with-cucumber)
    - [Example Feature Files](#example-feature-files)
- [Running Tests](#running-tests)
- [Interpreting Results](#interpreting-results)
- [Example Automation Scripts (Java & RestAssured)](#example-automation-scripts-java--restassured)
- [Generating HTML Reports](#generating-html-reports)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

This project provides a comprehensive solution for API testing and automation. It is designed to help QA engineers, developers, and testers ensure the quality, reliability, and performance of APIs through automated testing. The framework supports Behavior-Driven Development (BDD) using Cucumber, making tests more readable and collaborative. It utilizes RestAssured for simplified API interactions in Java and Maven for efficient dependency management. The framework generates detailed HTML reports for easy analysis of test results.

## Features

- **BDD with Cucumber:** Write tests in plain language using Gherkin syntax.
- **RestAssured:** Simplify REST API testing in Java.
- **Maven:** Manage project dependencies and build process.
- **HTML Reporting:** Generate detailed and visually appealing test reports.
- **Comprehensive Testing:** Supports functional, regression, and performance testing.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [x] **Java Development Kit (JDK):** Version 8 or higher.  Download from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/)
- [x] **Maven:** Version 3.6.0 or higher. Download from [Apache Maven](https://maven.apache.org/download.cgi)
- [x] **Integrated Development Environment (IDE):** IntelliJ IDEA, Eclipse, or any preferred IDE.
- [x] **Git:** For version control. Download from [Git](https://git-scm.com/downloads)

> Note: Ensure that JAVA_HOME and MAVEN_HOME environment variables are properly configured.

## Installation

1.  Clone the repository:

    bash
    mvn clean install
        > Adjust the configuration file format and content according to your project's needs.  This file should contain configurations like base URLs, API keys, etc.

2.  **Environment Variables:** Alternatively, you can set environment variables for sensitive information. Access these variables in your Java code using `System.getenv("VARIABLE_NAME")`.

## API Endpoint Testing

### Test Case Design (BDD with Cucumber)

When designing test cases for API endpoints using BDD with Cucumber, follow these guidelines:

-   **Feature Files:** Create `.feature` files to define test scenarios in Gherkin syntax.
-   **Step Definitions:** Implement step definitions in Java classes to execute the test steps.
-   **Data-Driven Testing:** Use Scenario Outlines to run the same scenario with different data sets.

### Example Feature Files

1.  **Get User Feature:**

gherkin
    Feature: Create User
      Scenario: Create a new user with valid data
        Given the API endpoint is "/users"
        And I set the request body with the following data:
          | name  | email                |
          | John  | john.doe@example.com |
        When I send a POST request
        Then the response status code should be 201
        And the response should contain the new user ID
    -   **Console Output:** Maven displays the test results in the console, indicating the number of passed, failed, and skipped scenarios.
-   **HTML Report:** After the tests are executed, an HTML report is generated in the `target/cucumber-reports` directory.

## Example Automation Scripts (Java & RestAssured)

java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTests {

    private final String BASE_URL = "https://api.example.com"; // Replace with your API base URL

    @Test
    public void testGetUser() {
        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.get("/users/1");

        assertEquals(200, response.getStatusCode());
        assertEquals("John Doe", response.jsonPath().getString("name"));
    }

    @Test
    public void testCreateUser() {
        RestAssured.baseURI = BASE_URL;
        String requestBody = "{\"name\":\"Jane Doe\",\"email\":\"jane.doe@example.com\"}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/users");

        assertEquals(201, response.getStatusCode());
        // Add assertions to validate the created user
    }
}
> Adapt the `BASE_URL` and assertions to match your API and test requirements. These scripts should be placed in the `src/test/java` directory.

## Generating HTML Reports

The Cucumber Maven plugin automatically generates HTML reports after test execution.  The reports are located in the `target/cucumber-reports` directory. You can customize the report generation by configuring the `cucumber-maven-plugin` in your `pom.xml`.

xml
> <plugin>
>    <groupId>tech.grasshopper</groupId>
>    <artifactId>cucumber-reporting</artifactId>
>    <version>5.0.0</version>
>    <executions>
>       <phase>post-cucumber</phase>
>       <goals>
>          <goal>generate</goal>
>       </goals>
>       <configuration>
>          <projectName>QA-API-Automation</projectName>
>          <outputDirectory>${project.build.directory}/cucumber-reports</outputDirectory>
>          <inputDirectory>${project.build.directory}/cucumber-reports/cucumber.json</inputDirectory>
>       </configuration>
>    </executions>
> </plugin>
> -   **Dependency Issues:** Ensure all dependencies are correctly defined in your `pom.xml` file and that Maven can resolve them.
-   **Test Failures:** Analyze the console output and HTML reports to identify the cause of test failures.  Check API availability, request parameters, and expected results.
-   **Configuration Errors:** Verify that all configuration settings in `cucumber.properties` are correct.
-   **Incorrect Step Definitions:** Ensure that step definitions in Java classes match the steps defined in your feature files.

## Contributing

We welcome contributions from the community! To contribute:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Write your code and tests.
4.  Ensure all tests pass before submitting a pull request.
5.  Submit a pull request with a clear description of your changes.

> Add more detailed guidelines on coding style, commit message format (e.g., Conventional Commits), and the review process. Also, specify which branch to submit PRs to (e.g., `develop`).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

> Add contact information or links to relevant communication channels (e.g., email, Slack, forum).
