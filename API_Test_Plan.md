# API Test Plan
**Project Name**: Module Lab - API Testing with REST Assured

## 1. Objective
The objective of this project is to automate API testing using REST Assured. This test plan aims to validate the functionality, performance, and security of the target APIs by executing automated test cases for CRUD operations (Create, Read, Update, Delete) and verifying status codes, response bodies, headers, and JSON schemas.

## 2. Scope
### In Scope
- API Functional Testing: Validating positive and negative test cases.
- Validating CRUD operations for the `Users` API (`https://reqres.in`).
- Endpoints tested:
  - `GET /api/users?page=2` (Read)
  - `POST /api/users` (Create)
  - `PUT /api/users/{id}` (Update)
  - `DELETE /api/users/{id}` (Delete)
- Validating JSON schemas, HTTP status codes, and HTTP response headers.
- Report generation using Allure Reports.
- Integrating tests via Maven inside a Docker container and a CI/CD pipeline (GitHub Actions).

### Out of Scope
- Load/Performance testing beyond functional scope.
- Security vulnerability scanning.

## 3. Test Environment Setup
- **Programming Language**: Java 17
- **Build Tool**: Maven
- **Test Framework**: JUnit 5
- **Automation Framework**: REST Assured
- **Reporting Tool**: Allure Reports
- **Containerization**: Docker
- **CI/CD**: GitHub Actions
- **Base URI**: `https://reqres.in` (Configurable via `pom.xml`)

## 4. Test Strategy
All tests are organized under the `ApiTests` class utilizing JUnit 5 annotations.
- The base URI and common configurations are initialized in the `@BeforeAll` setup method.
- **REST Assured**'s Given/When/Then structure is used for expressing the tests.
- **Data Validation**: Hamcrest matchers are utilized for asserting specific fields inside the response JSON.
- **Schema Validation**: REST Assured JSON schema validator is checking against `users-schema.json`.

## 5. Test Matrix

| Test Case Name | Method | Endpoint | Expected Status | Validations Performed |
| --- | --- | --- | --- | --- |
| Fetch Users List | GET | `/api/users?page=2` | 200 OK | Content-Type header, Body format (page=2, data array is not empty), JSON Schema validation. |
| Create User | POST | `/api/users` | 201 Created | Content-Type header, Name matches, Job matches, ID is generated. |
| Update User | PUT | `/api/users/2` | 200 OK | Updated job matches, `updatedAt` field exists. |
| Delete User | DELETE | `/api/users/2` | 204 No Content | Status Code validation indicating successful deletion. |

## 6. Execution Details
### Local Execution
1. Ensure Java and Maven are installed.
2. Run tests via Terminal:
   ```bash
   mvn clean test
   ```
3. Generate and open Allure Report (requires Allure command-line tool):
   ```bash
   allure serve target/allure-results
   ```

### Docker Execution
1. Ensure Docker is installed.
2. Run via Docker Compose:
   ```bash
   docker-compose up --build
   ```
   *The target folder will be synchronized, containing test results.*

### CI/CD Pipeline
The tests execute automatically on `push` and `pull_request` triggers using the **GitHub Actions** pipeline configured in `.github/workflows/maven.yml`. Test results (Allure targets) are saved as downloadable artifacts.
