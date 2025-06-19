# Title

Prueba Técnica – Desarrollador(a) Java Spring Boot

# Description

REST API in Spring Boot that authenticates users against DummyJSON, logs successful logins in PostgreSQL, and allows querying the user's profile.

**Key Flow:**

1. Authentication with DummyJSON (/auth/login)
2. Logging into local database (login_log)
3. Profile query (/auth/me) using cookies
4. User listing (/users)

# Table of contents

- [Description](#description)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation and Execution](#installation-and-execution)
- [Backend Tests](#backend-tests)
- [API Endpoints](#api-endpoints)
- [Test Endpoint](#test-endpoint)
- [Login and Registration Explanation](#login-and-registration-explanation)

# Technologies Used

- **Java**: 21
- **Spring Boot**:3.5.0
- **PostgreSQL**: 17.5
- **Docker**: 28.1.1

# Prerequisites

Before running the project, make sure the following prerequisites are met:

- Docker Engine
- Docker Compose


# Installation and Execution

1. Clone the repository:

   ```bash
   https://github.com/Deivid30Medina/technical-test.git
   ```

2.  Navigate to the project directory.

    ```bash
    cd technical-test
    ```

3.  Run with Docker (builds images and starts containers).

    ```bash
    docker compose up --build
    ```

4.  Verify running containers.

    ```bash
    docker ps -a
    ```

5. Containers created:

- technical-test-app: API Spring Boot (puerto 8080)
- postgres: Base de datos (puerto 5432)

# Backend Tests

To run the tests, ensure you are in the **technical-test** project folder.

1.  Run test:

    ```bash
    docker-compose run --rm test-runner
    ```

2. Successful tests

    ```bash
    [INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
    ```

# API Endpoints

Here are the available endpoints to interact with the application:

- **POST /api/v1/auth/login:** Autenticación + registro en DB.

  ```bash
  http://localhost:8080/api/v1/auth/login
  ```

- **GET /api/v1/auth/me:** Perfil de usuario (requiere cookie).

  ```bash
  http://localhost:8080/api/v1/auth/me
  ```

- **GET /api/v1/users:** Todos los usuarios.

  ```bash
  http://localhost:8080/api/v1/users
  ```

# Test Endpoint

Test users (DummyJSON):

| UserName  | Password     |
|-----------|--------------|
| michaelw  | michaelwpass |


### Endpoint: /api/v1/auth/login
  ```bash
  curl -X POST http://localhost:8080/api/v1/auth/login -H "Content-Type: application/json" -d "{\"username\": \"michaelw\", \"password\": \"michaelwpass\"}"
  ```

### Endpoint: /api/v1/auth/me
  ```bash
  curl -v --cookie "accessToken=TOKEN" http://localhost:8080/api/v1/auth/me
  ```

### Endpoint: /api/v1/users
  ```bash
  curl http://localhost:8080/api/v1/users
  ```

# Login and Registration Explanation

### Technical level:

Each successful login is saved in the login_log table with the following structure:

```bash
  CREATE TABLE login_log (
    id UUID PRIMARY KEY,
    username VARCHAR NOT NULL,
    login_time TIMESTAMPTZ NOT NULL,
    access_token TEXT NOT NULL,
    refresh_token TEXT NOT NULL
);
```

### How to view it?

1. **Connect to PostgreSQL:**

   ```bash
    docker exec -it postgres psql -U postgres -d dummyDB
    ```

2. **Query records:**

   ```bash
    SELECT * FROM login_log;
    ```

### Flow Diagram

