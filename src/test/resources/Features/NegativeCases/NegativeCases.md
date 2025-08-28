### Negative Test Cases for POST /books and PUT /books/{book_id}

| **TC ID**          | **Scenario**                                  | **Input (Example)**                                                                                               | **Expected Code**        | **Expected Response**                                    |
| ------------------ | --------------------------------------------- | ----------------------------------------------------------------------------------------------------------------- | ------------------------ | -------------------------------------------------------- |
| NEG-01             | Missing required field (`author`)             | `json { "name": "QA API", "published_year": 2025, "book_summary": "QA Book" } `                                   | 400 Bad Request          | `{ "error": "Field 'author' is required" }`              |
| NEG-02             | Empty string in `name`                        | `json { "name": "", "author": "Satish", "published_year": 2025, "book_summary": "QA Book" } `                     | 400 Bad Request          | `{ "error": "Field 'name' cannot be empty" }`            |
| NEG-03             | Invalid datatype (`published_year` as string) | `json { "name": "QA API", "author": "Satish", "published_year": "twenty", "book_summary": "QA Book" } `           | 400 Bad Request          | `{ "error": "Field 'published_year' must be a number" }` |
| NEG-04             | Invalid year (future 3025)                    | `json { "name": "QA API", "author": "Satish", "published_year": 3025, "book_summary": "QA Book" } `               | 422 Unprocessable Entity | `{ "error": "Invalid published_year value" }`            |
| NEG-05             | Extra unexpected field                        | `json { "name": "QA API", "author": "Satish", "published_year": 2025, "book_summary": "QA Book", "price": 500 } ` | 400 Bad Request          | `{ "error": "Unexpected field 'price'" }`                |
| NEG-06             | Malformed JSON (missing brace)                | `json { "name": "QA API", "author": "Satish" `                                                                    | 400 Bad Request          | `{ "error": "Malformed JSON" }`                          |
| NEG-07             | Very large `book_summary` (>10k chars)        | `"book_summary": "AAAA....(10000 times)"`                                                                         | 413 Payload Too Large    | `{ "error": "Payload size exceeds limit" }`              |
| NEG-08             | Invalid characters in `name`                  | `json { "name": "@@@###", "author": "Satish", "published_year": 2025, "book_summary": "QA Book" } `               | 422 Unprocessable Entity | `{ "error": "Invalid characters in 'name'" }`            |
| NEG-09 (POST only) | Duplicate book                                | (Same payload as an existing book)                                                                                | 409 Conflict             | `{ "error": "Book already exists" }`                     |
| NEG-10 (PUT only)  | Update non-existent book                      | `PUT /books/99999` with valid body                                                                                | 404 Not Found            | `{ "error": "Book not found" }`                          |


### Negative Test Cases for DELETE /books/{book_id}
| **TC ID** | **Scenario**                             | **Input (Example)**                        | **Expected Code**     | **Expected Response**                               |
| --------- | ---------------------------------------- | ------------------------------------------ | --------------------- | --------------------------------------------------- |
| DEL-01    | Invalid `book_id` (string)               | `DELETE /books/abc`                        | 400 Bad Request       | `{ "error": "Invalid book_id format" }`             |
| DEL-02    | Special characters in ID                 | `DELETE /books/@@@`                        | 400 Bad Request       | `{ "error": "Invalid book_id format" }`             |
| DEL-03    | Non-existent book                        | `DELETE /books/99999`                      | 404 Not Found         | `{ "error": "Book not found" }`                     |
| DEL-04    | Empty/blank ID                           | `DELETE /books/ `                          | 400 Bad Request       | `{ "error": "Book ID is required" }`                |
| DEL-06    | Unauthorized access (if auth required)   | `DELETE /books/101` without token          | 401 Unauthorized      | `{ "error": "Authentication required" }`            |


### Negative Test Cases for GET /books/{book_id}
| **TC ID** | **Scenario**                           | **Input (Example)**                | **Expected Code** | **Expected Response**                          |
| --------- | -------------------------------------- | ---------------------------------- | ----------------- | ---------------------------------------------- |
| GET-01    | Invalid `book_id` (string)             | `GET /books/abc`                   | 400 Bad Request   | `{ "error": "Invalid book_id format" }`        |
| GET-02    | Special characters in ID               | `GET /books/###`                   | 400 Bad Request   | `{ "error": "Invalid book_id format" }`        |
| GET-03    | Non-existent book                      | `GET /books/99999`                 | 404 Not Found     | `{ "error": "Book not found" }`                |
| GET-05    | Deleted book access                    | `GET /books/105` (already deleted) | 404 Not Found     | `{ "error": "Book not found" }`                |
| GET-06    | Unauthorized access (if auth required) | `GET /books/101` without token     | 401 Unauthorized  | `{ "error": "Authentication required" }`       |

### Negative Test Cases for GET /books (Get all books)

| GETALL-06 | Unauthorized access (if auth required)     | `GET /books` without token          | 401 Unauthorized      | `{ "error": "Authentication required" }`          |
