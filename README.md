# QA-API-Automation
# 🚀 API Automation Framework

This repository contains a **REST API Automation Framework** built with **Java**, **RestAssured**, **Cucumber (BDD)**, and **TestNG**.  
It automates testing of APIs for **Book Management**, **User Authentication**, and **Health Check** services.

---

## 📂 Project Structure

APIAutomation/
│── .idea/                         # IDE specific files
│── src/                           # (reserved for application code if any)
│── test/
│   ├── java/
│   │   ├── pages/                 # Common API functions & utilities
│   │   │   └── commonAPIFunctions.java
│   │   ├── runners/               # Test runners
│   │   │   └── TestRunner.java
│   │   ├── steps/                 # Step Definitions
│   │   │   ├── APIStepFunctions.java
│   │   │   └── Hooks.java
│   │   └── utils/                 # Helper classes
│   │       └── RestAssuredRequestFilter.java
│   │
│   └── resources/
│       ├── Features/              # Cucumber feature files
│       │   ├── BookManagement.feature
│       │   ├── HealthCheck.feature
│       │   └── Login.feature
│       │
│       ├── Schemas/               # JSON schemas for validation
│       │   ├── AllBooks.json
│       │   ├── CreateUpdateBook.json
│       │   ├── login.json
│       │   ├── signup.json
│       │   └── sampleTest.json
│       │
│       └── config.properties      # Configurations (baseURL, tokens, etc.)
│
│── target/
│   ├── cucumber-html/             # HTML reports
│   │   └── index.html
│   ├── cucumber.json              # JSON report
│   ├── failedstep.txt             # Stores failed scenarios
│   └── test-classes/              # Compiled test classes


---

## 📌 API Endpoints Covered

### 📚 Book Management
- **POST /books/** → Create a new book
- **PUT /books/{book_id}** → Update a book by ID
- **DELETE /books/{book_id}** → Delete a book by ID
- **GET /books/{book_id}** → Get a book by ID
- **GET /books/** → Get all books

### 👤 User Authentication
- **POST /signup** → Sign up a new user
- **POST /login** → Log in and receive an access token

### 💓 Health Check
- **GET /health** → Check the health of the API

---

## 🛠️ Tech Stack
- **Java 11+**
- **Maven** (dependency management)
- **RestAssured** (API testing)
- **Cucumber (BDD)** + **Gherkin**
- **TestNG** (test execution)
- **JSON Schema Validator** (response validation)

---

## ▶️ How to Run Tests

### 1️⃣ Clone the repository
git clone https://github.com/<your-username>/APIAutomation.git
cd APIAutomation

### Run all tests
mvn clean test

### Run specific tagged tests
mvn test -Dcucumber.filter.tags="@Create"

### Rerun only failed scenarios
mvn test -Dcucumber.options="@target/failedstep.txt"


📊 Test Reports

After execution, reports are available in the target/ folder:

HTML Report → target/cucumber-html/index.html

JSON Report → target/cucumber.json

Failed Scenarios File → target/failedstep.txt

✅ Features

BDD-style test scenarios (.feature files)

Centralized reusable API request functions

Bearer Token authentication support

JSON Schema validations for responses

Configurable properties for endpoints and environment

Rich reporting: HTML, JSON, rerun support