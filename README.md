A RESTful backend service built with Java Spring Boot and PostgreSQL to fetch, store, and retrieve GitHub repositories.
No UI is included—API is fully testable via Postman.

Prerequisites
Java 17+

Maven 3.8+

PostgreSQL (local or remote)

Setup & Run
1. Clone the Repository
text
git clone <your-repo-url>
cd java1
2. Configure Database
Create a PostgreSQL database (e.g., githubdb).

Update src/main/resources/application.properties:

text
spring.datasource.url=jdbc:postgresql://localhost:5432/githubdb
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
3. Build and Run the App
text
./mvnw spring-boot:run
or run Java1Application.java from your IDE.

API Documentation
1. Search & Store GitHub Repositories
Endpoint: POST /api/github/search

Description: Fetches repositories from GitHub and saves them to the database.

Request Body Example
json
{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
}
query (string): Search keywords (required)

language (string): Filter by language (optional)

sort (string): stars, forks, or updated (optional, default: stars)

Response Example
json
{
  "repositories": [
    {
      "id": 123,
      "name": "example-repo",
      "description": "A sample repository.",
      "owner": "octocat",
      "language": "Java",
      "stars": 100,
      "forks": 50,
      "lastUpdated": "2025-07-12T13:58:05Z"
    }
  ],
  "message": "Repositories fetched and saved successfully"
}
2. Retrieve Stored Repositories
Endpoint: GET /api/github/repositories

Description: Returns stored repositories, with optional filtering and sorting.

Query Parameters
language (string, optional): Filter by programming language

minStars (int, optional): Minimum stars

sort (string, optional): stars, forks, or updated (default: stars)

Example Request
text
GET /api/github/repositories?language=Java&minStars=100&sort=stars
Response Example
json
{
  "repositories": [
    {
      "id": 123,
      "name": "example-repo",
      "description": "A sample repository.",
      "owner": "octocat",
      "language": "Java",
      "stars": 100,
      "forks": 50,
      "lastUpdated": "2025-07-12T13:58:05Z"
    }
  ]
}
Testing the API with Postman
POST /api/github/search

Set method to POST, URL to http://localhost:8080/api/github/search

Set body to raw JSON (see above)

Click "Send" and verify response

GET /api/github/repositories

Set method to GET, URL to http://localhost:8080/api/github/repositories?language=Java&minStars=100&sort=stars

Click "Send" and verify response

Error Handling
Returns 400 Bad Request for invalid input.

Returns 500 Internal Server Error for unexpected server/database errors.

Error responses are JSON with a clear message field.

Project Structure
controller/ – REST API endpoints

service/ – Business logic

repository/ – Database access

entity/ – JPA entities#   G i t h u b S e a r c h 
 
 
