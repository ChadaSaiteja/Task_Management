# Task Management API

A lightweight RESTful task management API built with **Spring Boot**, **Spring Data JPA**, and **MySQL**. The application provides endpoints for creating, viewing, searching, updating, and deleting tasks.

## Features

- Create and persist tasks in a MySQL database
- Retrieve all tasks
- Find a task by its holder name
- Delete a task by its task ID
- Change a task's status
- REST API built with Spring Web
- Database access through Spring Data JPA
- Maven Wrapper included for easy setup

## Technology Stack

- **Java 17**
- **Spring Boot 3.0.3**
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- JUnit 5 / Spring Boot Test

## Project Structure

```text
src/
├── main/
│   ├── java/com/example/Task/Management/
│   │   ├── controller/TaskController.java
│   │   ├── model/Task.java
│   │   ├── TaskRepository.java
│   │   └── TaskManagementApplication.java
│   └── resources/application.properties
└── test/
    └── java/com/example/Task/Management/
        └── TaskManagementApplicationTests.java
```

## Prerequisites

Before running the application, install:

- Java 17 or later
- MySQL 8 or later
- Git

Create the database used by the application:

```sql
CREATE DATABASE task_management;
```

Then update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_management
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

> **Security note:** Do not commit real database passwords or other secrets to source control. For production deployments, use environment variables or an external configuration mechanism.

## Getting Started

Clone the repository and move into the project directory:

```bash
git clone https://github.com/ChadaSaiteja/Task_Management.git
cd Task_Management
```

Run the test suite:

```bash
# Linux/macOS
./mvnw test

# Windows
mvnw.cmd test
```

Start the application:

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## API Reference

The current controller exposes the following endpoints.

### Create a task

```http
POST /saveTask
Content-Type: application/json
```

Example request body:

```json
{
  "taskId": "TASK-001",
  "taskHolderName": "Alex",
  "taskDate": "2026-09-21",
  "taskName": "Prepare project report",
  "taskStatus": "pending"
}
```

### Get all tasks

```http
GET /allTasks
```

### Get a task by holder name

```http
GET /getTask/{holderName}
```

Example:

```bash
curl http://localhost:8080/getTask/Alex
```

### Change a task's status

```http
GET /changeStatus/{taskId}
```

This sets the matching task's status to `changed`.

Example:

```bash
curl http://localhost:8080/changeStatus/TASK-001
```

### Delete a task

```http
GET /deleteTask/{taskId}
```

Example:

```bash
curl http://localhost:8080/deleteTask/TASK-001
```

## Task Fields

| Field | Description |
| --- | --- |
| `id` | Database-generated numeric identifier |
| `taskId` | Application-level task identifier |
| `taskHolderName` | Name of the person responsible for the task |
| `taskDate` | Date associated with the task |
| `taskName` | Task title or description |
| `taskStatus` | Current task status |

## Building the Application

Create a packaged JAR file with Maven:

```bash
# Linux/macOS
./mvnw clean package

# Windows
mvnw.cmd clean package
```

Run the generated JAR:

```bash
java -jar target/Task-Management-0.0.1-SNAPSHOT.jar
```

## Testing with cURL

Create a task:

```bash
curl -X POST http://localhost:8080/saveTask \
  -H "Content-Type: application/json" \
  -d '{
    "taskId": "TASK-002",
    "taskHolderName": "Sam",
    "taskDate": "2026-09-22",
    "taskName": "Review pull requests",
    "taskStatus": "pending"
  }'
```

Retrieve all tasks:

```bash
curl http://localhost:8080/allTasks
```

## Future Improvements

- Use conventional HTTP methods for update and delete operations
- Add request validation and consistent error responses
- Return appropriate HTTP status codes for success and failure cases
- Add pagination, filtering, and sorting
- Add authentication and authorization
- Move database credentials to environment variables
- Add API documentation with OpenAPI/Swagger
- Add broader unit and integration test coverage

## License

No license has been specified for this project yet.
