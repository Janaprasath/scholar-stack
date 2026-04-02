# ScholarStack: Secure Academic Resource Portal

ScholarStack is a full-stack academic management platform built with **Java** and **Spring Boot**. It allows students to securely upload, share, and organize educational resources such as notes and links. The project focuses on high-level security using **JSON Web Tokens (JWT)** and **Stateless Architecture**.

---

## Core Features

* **Secure Authentication:** User registration and login with passwords encrypted via **BCrypt**.
* **JWT Authorization:** All resource-related endpoints are protected. The server issues a signed token upon login, which is required for all subsequent requests.
* **Resource Management:** Users can contribute academic links and metadata (Title, Subject Code, Description).
* **Personal Dashboard:** A dedicated "My Uploads" feature that isolates a user's own data from the global feed.
* **Tamper-Proof Security:** Implements a custom Security Filter that verifies the digital signature of every incoming request.

---

## Tech Stack

* **Backend:** Java 21, Spring Boot 3.x
* **Security:** Spring Security, JJWT (Java JWT Library)
* **Database:** MySQL
* **Build Tool:** Maven

---

## Project Structure

```text
com.janaprasath.scholarstack
├── config          # Security configuration (SecurityConfig)
├── controller      # REST API endpoints (UserController, ResourceController)
├── entity          # Database models (User, AcademicResource)
├── filter          # Security interceptor (JwtAuthenticationFilter)
├── repository      # JPA Data Access (UserRepository, ResourceRepository)
├── service         # Business logic (UserService, ResourceService)
└── util            # JWT Utilities (JwtUtils for Token Logic)
