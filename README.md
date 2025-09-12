# URL Shortener
 
## Description
A simple API to shorten and retrieve URLs, built with Spring Boot and MongoDB.
 
## Features
- Create a short URL from a long URL
- Retrieve the original URL from a short one
 
## Tech Stack
- Java 19
- Spring Boot 3.5.5
- MongoDB
- Docker (for containerized setup)
 
## Prerequisites
- Java 19 installed
- Maven installed (or use the wrapper `./mvnw`)
- Docker installed (optional, for containerized setup)
 
## Installation and Running
 
### Running Locally (without Docker)
1. Install dependencies and build the project:
```bash
./mvnw clean install
```
 
2. Run the application:
```bash
./mvnw spring-boot:run
```
 
The application will be available at http://localhost:8080
 
### Configure MongoDB
- **Locally:** edit `src/main/resources/application.yml` or `application.properties`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/urlshortener
server:
  port: 8080
```
- You can also use environment variables:
  - `MONGO_HOST`, `MONGO_PORT`, `MONGO_DB`
 
- **With Docker:** the application connects automatically to the MongoDB container.
 
### Running with Docker
1. Build and start the containers:
```bash
docker-compose up --build
```
 
2. Access the application at: http://localhost:8080
 
## Testing the API
 
### Create a short URL (POST)
```bash
curl -X POST http://localhost:8080/api/shorten \
     -H "Content-Type: application/json" \
     -d '{"originalUrl":"https://google.com"}'
```
Example response:
```json
{
  "data": {
    "domain": "http://localhost:8080",
    "short_url": "http://localhost:8080/abc123",
    "url": "https://google.com"
  },
  "code": 200,
  "errors": []
}
```
 
### Retrieve the original URL (GET)
```bash
curl -v http://localhost:8080/api/{shortUrl}
```
Example response:
```json
{
  "data": {
    "domain": "http://localhost:8080",
    "short_url": "http://localhost:8080/abc123",
    "url": "https://google.com"
  },
  "code": 200,
  "errors": []
}
```
 
## Notes / Out of scope
In a production environment, I would consider adding:
- Caching (e.g., Redis) to improve performance for frequently accessed URLs.
- CRUD interface and additional API endpoints for easy URL management.
- OpenAPI documentation to standardize the API contract and simplify integration.
- Extra features commonly found in URL shorteners, such as link expiration, custom aliases, analytics, and security controls.
