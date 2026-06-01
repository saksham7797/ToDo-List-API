# 🛡️ Secure ToDo List REST API

An enterprise-grade, highly secure ToDo List RESTful API built with **Spring Boot** and **Spring Security**. This architecture enforces strict data isolation, stateless authentication, and clean code principles using a Thin Controller / Fat Service design pattern.

## 🚀 Key Features

* **Stateless JWT Authentication:** Implements secure token-based access. No session state is stored on the server.
* **Robust Data Isolation (Anti-IDOR):** Custom security logic at the Service layer ensures users can only Read, Update, or Delete their own tasks. Complete protection against Insecure Direct Object Reference vulnerabilities.
* **Clean Architecture:** Strict adherence to "Thin Controllers" and "Fat Services". Controllers only handle HTTP routing and payloads, delegating all business logic to the service layer.
* **Data Transfer Objects (DTOs):** Complete separation of database entities from API responses to prevent data leakage and ensure stable JSON structures.
* **BCrypt Password Hashing:** User credentials are encrypted before hitting the database.

## 🛠️ Tech Stack

* **Framework:** Spring Boot (Java)
* **Security:** Spring Security, JSON Web Tokens (JWT)
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **API Testing:** Postman

---

## 📸 API Execution & Testing Evidence

*(Below are Postman executions demonstrating system capabilities and security protocols)*

### 1. Authentication & Onboarding
**Registration (Creating a new identity):**
> 🖼️ `![Register User](./screenshots/register.png)`

**Login (JWT Generation):**
> 🖼️ `![Login JWT Token](./screenshots/login.png)`

### 2. User Profile Management (Protected Routes)
**Read Profile Details:**
> 🖼️ `![Get Profile](./screenshots/get-profile.png)`

**Update Credentials:**
> 🖼️ `![Update Password](./screenshots/update-password.png)`

### 3. Factory Floor: Todo CRUD Operations
**Create Task:**
> 🖼️ `![Create Todo](./screenshots/create-todo.png)`

**Read Isolated User Tasks:**
> 🖼️ `![Get All Todos](./screenshots/get-todos.png)`

**Update Task (With Authorization check):**
> 🖼️ `![Update Todo](./screenshots/update-todo.png)`

---

## 🛣️ API Endpoints Reference

### Public Routes (No Token Required)
| Method | Endpoint | Action |
| :--- | :--- | :--- |
| `POST` | `/todo/auth/register` | Register a new user |
| `POST` | `/todo/auth/login` | Authenticate and receive JWT |

### User Routes (Requires Bearer Token)
| Method | Endpoint | Action |
| :--- | :--- | :--- |
| `GET` | `/todo/user/profile` | Retrieve logged-in user details |
| `PUT` | `/todo/user/update-password` | Update account password |
| `DELETE` | `/todo/user/delete` | Delete account and associated data |

### Todo Routes (Requires Bearer Token)
| Method | Endpoint | Action |
| :--- | :--- | :--- |
| `POST` | `/api/todos` | Create a new task |
| `GET` | `/api/todos` | Get all tasks belonging to the current user |
| `PUT` | `/api/todos` | Update an existing task |
| `DELETE` | `/api/todos/{id}` | Delete a specific task |

---

## ⚙️ Local Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/saksham7797/ToDoList-API

2. **Configure Database:**
    Update your application.properties or application.yml with your local database credentials.

3. **Run the Application:**
    Execute via your IDE or use Maven:

    ```Bash
    mvn spring-boot:run

4. **Testing:**
    Import the API endpoints into Postman. Register a user, grab the token, set it in the Authorization header as a Bearer Token, and test the protected routes.